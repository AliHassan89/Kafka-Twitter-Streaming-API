package org.interview.dto;

import java.util.Date;
import java.util.Objects;

public final class TweetInfoDto {

  private long tweetId;

  private Date tweetCreatedAt;

  private String tweetMessage;

  private long userId;

  private Date userCreatedAt;

  private String userName;

  private String userScreenName;

  public TweetInfoDto() {

  }

  public TweetInfoDto(long tweetId, Date tweetCreatedAt, String tweetMessage, long userId, Date userCreatedAt,
          String userName, String userScreenName) {
    this.tweetId = tweetId;
    this.tweetCreatedAt = tweetCreatedAt;
    this.tweetMessage = tweetMessage;
    this.userId = userId;
    this.userCreatedAt = userCreatedAt;
    this.userName = userName;
    this.userScreenName = userScreenName;
  }

  private TweetInfoDto(Builder builder) {
    this.tweetId = builder.tweetId;
    this.tweetCreatedAt = builder.tweetCreatedAt;
    this.tweetMessage = builder.tweetMessage;
    this.userId = builder.userId;
    this.userCreatedAt = builder.userCreatedAt;
    this.userName = builder.userName;
    this.userScreenName = builder.userScreenName;
  }

  public static Builder builder(long tweetId) {
    return new Builder(tweetId);
  }

  public long getTweetId() {
    return tweetId;
  }

  public void setTweetId(long tweetId) {
    this.tweetId = tweetId;
  }

  public Date getTweetCreatedAt() {
    return tweetCreatedAt;
  }

  public void setTweetCreatedAt(Date tweetCreatedAt) {
    this.tweetCreatedAt = tweetCreatedAt;
  }

  public String getTweetMessage() {
    return tweetMessage;
  }

  public void setTweetMessage(String tweetMessage) {
    this.tweetMessage = tweetMessage;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Date getUserCreatedAt() {
    return userCreatedAt;
  }

  public void setUserCreatedAt(Date userCreatedAt) {
    this.userCreatedAt = userCreatedAt;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserScreenName() {
    return userScreenName;
  }

  public void setUserScreenName(String userScreenName) {
    this.userScreenName = userScreenName;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof TweetInfoDto))
      return false;
    TweetInfoDto that = (TweetInfoDto) o;
    return getTweetId() == that.getTweetId() &&
            Objects.equals(getTweetCreatedAt(), that.getTweetCreatedAt()) &&
            Objects.equals(getTweetMessage(), that.getTweetMessage()) &&
            Objects.equals(getUserId(), that.getUserId()) &&
            Objects.equals(getUserCreatedAt(), that.getUserCreatedAt()) &&
            Objects.equals(getUserName(), that.getUserName()) &&
            Objects.equals(getUserScreenName(), that.getUserScreenName());
  }

  @Override public int hashCode() {

    return Objects
            .hash(getTweetId(), getTweetCreatedAt(), getTweetMessage(), getUserId(), getUserCreatedAt(),
                    getUserName(),
                    getUserScreenName());
  }

  @Override public String toString() {
    return "TweetInfoDto{" +
            "tweetId=" + tweetId +
            ", tweetCreatedAt=" + tweetCreatedAt +
            ", tweetMessage='" + tweetMessage + '\'' +
            ", userId=" + userId +
            ", userCreatedAt=" + userCreatedAt +
            ", userName='" + userName + '\'' +
            ", userScreenName='" + userScreenName + '\'' +
            '}';
  }

  public static final class Builder {
    private long tweetId;
    private Date tweetCreatedAt;
    private String tweetMessage;
    private long userId;
    private Date userCreatedAt;
    private String userName;
    private String userScreenName;

    Builder(long tweetId) {
      this.tweetId = tweetId;
    }

    public Builder tweetCreatedAt(Date tweetCreatedAt) {
      this.tweetCreatedAt = tweetCreatedAt;
      return this;
    }

    public Builder tweetMessage(String tweetMessage) {
      this.tweetMessage = tweetMessage;
      return this;
    }

    public Builder userId(long userId) {
      this.userId = userId;
      return this;
    }

    public Builder userCreatedAt(Date userCreatedAt) {
      this.userCreatedAt = userCreatedAt;
      return this;
    }

    public Builder userName(String userName) {
      this.userName = userName;
      return this;
    }

    public Builder userScreenName(String userScreenName) {
      this.userScreenName = userScreenName;
      return this;
    }

    public TweetInfoDto build() {
      return new TweetInfoDto(this);
    }
  }
}
