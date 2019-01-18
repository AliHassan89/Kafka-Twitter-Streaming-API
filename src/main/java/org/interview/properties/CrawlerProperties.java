package org.interview.properties;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(value = "crawler-properties", ignoreUnknownFields = false)
@Component
public final class CrawlerProperties {

    private String keywords;

    private int maxRecordsLimit;

    private long maxTimeLimit;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getMaxRecordsLimit() {
        return maxRecordsLimit;
    }

    public void setMaxRecordsLimit(int maxRecordsLimit) {
        this.maxRecordsLimit = maxRecordsLimit;
    }

    public long getMaxTimeLimit() {
        return maxTimeLimit;
    }

    public void setMaxTimeLimit(long maxTimeLimit) {
        this.maxTimeLimit = maxTimeLimit;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CrawlerProperties))
            return false;
        CrawlerProperties that = (CrawlerProperties) o;
        return getMaxRecordsLimit() == that.getMaxRecordsLimit() &&
                getMaxTimeLimit() == that.getMaxTimeLimit() &&
                Objects.equals(getKeywords(), that.getKeywords());
    }

    @Override public int hashCode() {

        return Objects.hash(getKeywords(), getMaxRecordsLimit(), getMaxTimeLimit());
    }

    @Override public String toString() {
        return "CrawlerProperties{" +
                "keywords='" + keywords + '\'' +
                ", maxRecordsLimit=" + maxRecordsLimit +
                ", maxTimeLimit=" + maxTimeLimit +
                '}';
    }
}
