package org.interview.dto;

import com.jparams.tester.ToStringTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TweetInfoDtoTest {

    @Test
    public void shouldCheckEqualsAndHashCodeContract() {
        EqualsVerifier.forClass(TweetInfoDto.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void shouldCheckToString() {
        ToStringTester.forClass(TweetInfoDto.class)
                .verify();
    }

    @Test
    public void shouldCreateTweetIngoDtoObject() {
        //given
        long tweetId = 123L;
        Date tweetCreatedAt = mock(Date.class);
        String tweetMessage = "biber";
        Long userId = 1234L;
        Date userCreatedAt = mock(Date.class);
        String userName = "user";
        String userScreenName = "patrick";

        //when
        TweetInfoDto tweetInfoDto = TweetInfoDto.builder(tweetId)
                .tweetCreatedAt(tweetCreatedAt)
                .tweetMessage(tweetMessage)
                .userId(userId)
                .userCreatedAt(userCreatedAt)
                .userName(userName)
                .userScreenName(userScreenName)
                .build();

        //then
        assertThat(tweetInfoDto, notNullValue());
        assertThat(tweetInfoDto.getTweetId(), equalTo(tweetId));
        assertThat(tweetInfoDto.getTweetCreatedAt(), equalTo(tweetCreatedAt));
        assertThat(tweetInfoDto.getTweetMessage(), equalTo(tweetMessage));
        assertThat(tweetInfoDto.getUserId(), equalTo(userId));
        assertThat(tweetInfoDto.getUserCreatedAt(), equalTo(userCreatedAt));
        assertThat(tweetInfoDto.getUserName(), equalTo(userName));
        assertThat(tweetInfoDto.getUserScreenName(), equalTo(userScreenName));
    }
}
