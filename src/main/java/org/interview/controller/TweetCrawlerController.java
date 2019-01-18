package org.interview.controller;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;

import org.interview.error.ErrorInfo;
import org.interview.oauth.twitter.TwitterAuthenticationException;
import org.interview.oauth.twitter.TwitterAuthenticator;
import org.interview.properties.OauthProperties;
import org.interview.service.TweetCrawlerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintStream;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/tweet/")
public class TweetCrawlerController {

    private final TweetCrawlerService tweetCrawlerService;
    private final OauthProperties oauthProperties;

    private static final Logger logger = LoggerFactory.getLogger(TweetCrawlerController.class);

    public TweetCrawlerController(TweetCrawlerService tweetCrawlerService, OauthProperties oauthProperties) {
        this.tweetCrawlerService = tweetCrawlerService;
        this.oauthProperties = oauthProperties;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void publishTwitterData() {
        authenticate().ifPresent(tweetCrawlerService::publishTweetDataAndInfo);
    }

    private Optional<HttpRequestInitializer> authenticate() {

        TwitterAuthenticator twitterAuthenticator =
                new TwitterAuthenticator(
                        new PrintStream(System.err),
                        oauthProperties.getConsumerKey(),
                        oauthProperties.getConsumerSecret());

        try {
            HttpRequestFactory httpRequestFactory = twitterAuthenticator.getAuthorizedHttpRequestFactory();
            return Optional.of(httpRequestFactory.getInitializer());
        } catch (TwitterAuthenticationException e) {
            logger.error(ErrorInfo.AUTHENTICATION_FAILED.getErrorMsg() +
                    ": code= "+ ErrorInfo.AUTHENTICATION_FAILED.getErrorCode());
        }

        return Optional.empty();
    }
}
