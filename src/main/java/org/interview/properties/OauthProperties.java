package org.interview.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties("oauth-properties")
public final class OauthProperties {

    private String consumerKey;
    private String consumerSecret;

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OauthProperties))
            return false;
        OauthProperties that = (OauthProperties) o;
        return Objects.equals(getConsumerKey(), that.getConsumerKey()) &&
                Objects.equals(getConsumerSecret(), that.getConsumerSecret());
    }

    @Override public int hashCode() {
        return Objects.hash(getConsumerKey(), getConsumerSecret());
    }

    @Override public String toString() {
        return "OauthProperties{" +
                "consumerKey='" + consumerKey + '\'' +
                ", consumerSecret='" + consumerSecret + '\'' +
                '}';
    }
}
