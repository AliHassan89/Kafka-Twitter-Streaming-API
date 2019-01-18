package org.interview.service;

import com.google.api.client.http.HttpRequestInitializer;

import org.interview.dto.TweetInfoDto;
import org.interview.properties.OauthProperties;
import org.interview.service.kafka.TweetConsumer;
import org.interview.service.kafka.CrawlerConstants;
import org.interview.service.kafka.TweetProducer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TweetCrawlerService {

    private final OauthProperties oauthProperties;
    private final TweetProducer tweetProducer;
    private final TweetConsumer tweetConsumer;

    public TweetCrawlerService(OauthProperties oauthProperties, TweetProducer tweetProducer, TweetConsumer tweetConsumer) {
        this.oauthProperties = oauthProperties;
        this.tweetProducer = tweetProducer;
        this.tweetConsumer = tweetConsumer;
    }

    public void publishTweetDataAndInfo(HttpRequestInitializer requestInitializer) {
        tweetProducer.produce(requestInitializer, oauthProperties);
    }

    @KafkaListener(topics = CrawlerConstants.TOPIC, groupId = CrawlerConstants.GROUP_ID,
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(TweetInfoDto tweetInfoDto) {
        tweetConsumer.consumeMessages(tweetInfoDto);
    }
}
