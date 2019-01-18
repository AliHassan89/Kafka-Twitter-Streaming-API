package org.interview.service.kafka;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.HttpRequestInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import org.interview.dto.TweetInfoDto;
import org.interview.properties.CrawlerProperties;
import org.interview.properties.OauthProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.StatusListener;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StallWarning;
import twitter4j.FilterQuery;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Timer;

@Component
public class TweetProducer {

    private final KafkaListenerEndpointRegistry registry;

    private int tweetCount;

    private static final Logger logger = LoggerFactory.getLogger(TweetProducer.class);

    private final CrawlerProperties crawlerProperties;
    private final KafkaTemplate<String, TweetInfoDto> kafkaTemplate;

    @Autowired public TweetProducer(CrawlerProperties crawlerProperties,
            KafkaTemplate<String, TweetInfoDto> kafkaTemplate,
            KafkaListenerEndpointRegistry registry) {
        tweetCount = 0;
        this.crawlerProperties = crawlerProperties;
        this.kafkaTemplate = kafkaTemplate;
        this.registry = registry;
    }

    public void produce(HttpRequestInitializer initializer, OauthProperties oauthProperties) {
        if (initializer == null || oauthProperties == null) {
            logger.error("HttpRequestInitializer can't be null");
            throw new IllegalArgumentException();
        }

        ConfigurationBuilder confBuilder = new ConfigurationBuilder();
        confBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(oauthProperties.getConsumerKey())
                .setOAuthConsumerSecret(oauthProperties.getConsumerSecret())
                .setOAuthAccessToken(((OAuthParameters) initializer).token)
                .setOAuthAccessTokenSecret(((OAuthHmacSigner)((OAuthParameters) initializer).signer).tokenSharedSecret);
        TwitterStream twitterStream = new TwitterStreamFactory(confBuilder.build()).getInstance();

        new Timer().schedule(new ExitProducer(kafkaTemplate, twitterStream, registry), crawlerProperties.getMaxTimeLimit());
        twitterStream.addListener(initializeAndGetListener(twitterStream));

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(crawlerProperties.getKeywords().split(","));
        twitterStream.filter(filterQuery);
    }

    private StatusListener initializeAndGetListener(TwitterStream twitterStream) {
        return new StatusListener() {
            @Override public void onStatus(Status status) {
                ++tweetCount;

                User user = status.getUser();

                TweetInfoDto tweetInfoDto = TweetInfoDto.builder(status.getId())
                        .tweetCreatedAt(status.getCreatedAt())
                        .tweetMessage(status.getText())
                        .userId(user.getId())
                        .userCreatedAt(user.getCreatedAt())
                        .userName(user.getName())
                        .userScreenName(user.getScreenName())
                        .build();

                kafkaTemplate.send(CrawlerConstants.TOPIC, tweetInfoDto);
                if (tweetCount > crawlerProperties.getMaxRecordsLimit()) {
                    kafkaTemplate.flush();
                    twitterStream.clearListeners();
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("ENDED tweet count reached " + crawlerProperties.getMaxRecordsLimit());
                    System.out.println("------------------------------------------------------------------------");
                    registry.stop();
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                logger.info("staus deletion notice "+ statusDeletionNotice.toString());
            }

            @Override
            public void onTrackLimitationNotice(int i) {
                logger.info("on track limitation notice "+ i);
            }

            @Override
            public void onScrubGeo(long l, long l1) {
                logger.info("onScrubGeo "+ l);
            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {
                logger.warn("stall warning "+ stallWarning.getMessage());
            }

            @Override
            public void onException(Exception e) {
                logger.warn("exception " + e.getMessage());
            }
        };
    }
}
