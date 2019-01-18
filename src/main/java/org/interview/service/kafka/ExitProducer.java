package org.interview.service.kafka;

import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import twitter4j.TwitterStream;

import java.util.TimerTask;

final class ExitProducer extends TimerTask {

    private final KafkaTemplate kafkaTemplate;
    private final TwitterStream twitterStream;
    private final KafkaListenerEndpointRegistry registry;

    ExitProducer(KafkaTemplate kafkaTemplate, TwitterStream twitterStream,
            KafkaListenerEndpointRegistry registry) {
        this.kafkaTemplate = kafkaTemplate;
        this.twitterStream = twitterStream;
        this.registry = registry;
    }

    @Override
    public void run() {
        if (kafkaTemplate != null) {
            kafkaTemplate.flush();
        }
        if (twitterStream != null) {
            twitterStream.clearListeners();
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("ENDED time limit exceeded");
        System.out.println("------------------------------------------------------------------------");
        registry.stop();
    }
}
