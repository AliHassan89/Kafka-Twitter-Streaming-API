package org.interview.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@ConfigurationProperties(value = "kafka-properties", ignoreUnknownFields = false)
@Component
public final class KafkaProperties {

    private String bootstrapServers;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof KafkaProperties))
            return false;
        KafkaProperties that = (KafkaProperties) o;
        return Objects.equals(getBootstrapServers(), that.getBootstrapServers());
    }

    @Override public int hashCode() {

        return Objects.hash(getBootstrapServers());
    }

    @Override public String toString() {
        return "KafkaProperties{" +
                "bootstrapServers='" + bootstrapServers + '\'' +
                '}';
    }
}
