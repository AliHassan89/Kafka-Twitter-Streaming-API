package org.interview.service.kafka;

import org.interview.dto.TweetInfoDto;
import org.springframework.stereotype.Component;

@Component
public class TweetConsumer {

    public void consumeMessages(TweetInfoDto tweetInfoDto) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Record:");
        System.out.println("Tweet ID: "+ tweetInfoDto.getTweetId());
        System.out.println("Tweet creation date: "+ tweetInfoDto.getTweetCreatedAt());
        System.out.println("Tweet : "+ tweetInfoDto.getTweetMessage());
        System.out.println("User ID : "+ tweetInfoDto.getUserId());
        System.out.println("User creation date : "+ tweetInfoDto.getUserCreatedAt());
        System.out.println("Username : "+ tweetInfoDto.getUserName());
        System.out.println("User screen name : "+ tweetInfoDto.getUserScreenName());
        System.out.println("------------------------------------------------------------------------");
        System.out.println();
    }
}
