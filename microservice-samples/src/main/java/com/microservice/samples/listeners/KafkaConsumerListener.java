package com.microservice.samples.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.samples.entities.Sample;
import com.microservice.samples.service.ISampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Configuration
public class KafkaConsumerListener {
    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ISampleService sampleService;

    @KafkaListener(topics = {"result_emited"}, groupId = "laboratoryMS-group")
    public void listener(String message) {
        objectMapper.registerModule(new Jdk8Module());
        LOGGER.info("Message received: "+message);

        try {
            Map<String, Long> mapMessage = objectMapper.readValue(message, new TypeReference<Map<String, Long>>() {});
            Optional<Sample> sample = sampleService.findById(mapMessage.get("sampleId"));

            if (sample.isPresent()) {
                Sample updatedSample = sample.get();
                Set<Long> resultIds = updatedSample.getResultListId();
                resultIds.add(mapMessage.get("resultId"));

                updatedSample.setResultListId(resultIds);
                sampleService.save(updatedSample);

            }
        } catch (JsonProcessingException e) {
            LOGGER.error("Error:",e);
        }

    }
}
