package com.verifymycoin.TransactionManager.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Kafka message sender
     *
     * @param topic
     * @param obj
     * @throws JsonProcessingException
     */
    public void send(String topic, Object obj) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(obj);

        log.debug("Send kafka ====> {}", jsonString);
        kafkaTemplate.send(topic, jsonString);
        log.debug("End kafka =====>");
    }
}
