package biz.eurosib.lkdkp.service;

import biz.eurosib.lkdkp.config.KafkaProducerConfig;
import biz.eurosib.lkdkp.kafka.RequestDto;
import biz.eurosib.lkdkp.kafka.ResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerClient {
    private final KafkaTemplate<Long, RequestDto> kafkaTemplate;
    private final KafkaProducerConfig config;
    private final ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(PlayerClient.class);

    @Autowired
    public PlayerClient(KafkaTemplate<Long, RequestDto> kafkaTemplate,
                        KafkaProducerConfig config,
                        ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.config = config;
        this.objectMapper = objectMapper;
    }

    public void produce(String data, UUID taskGuid) {
        RequestDto dto = new RequestDto(data, taskGuid);
        log.info("<=" + config.getRequestQueue() + "sending by POST {}", writeValueAsString(dto));
        kafkaTemplate.send(config.getRequestQueue(), dto);
    }



    private String writeValueAsString(RequestDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
