package biz.eurosib.lkdkp.service;

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

@Service
public class PlayerClient {
    private final KafkaTemplate<Long, RequestDto> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(PlayerClient.class);

    @Autowired
    public PlayerClient(KafkaTemplate<Long, RequestDto> kafkaTemplate,
                        ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    //@Scheduled(initialDelay = 10000, fixedDelay = 20000)
    public void low() {
        RequestDto dto = new RequestDto("low");
        log.info("<= low sending {}", writeValueAsString(dto));
        kafkaTemplate.send("lkdkp.request.low", dto);
    }

    public void low(String data) {
        RequestDto dto = new RequestDto(data);
        log.info("<= low sending by POST {}", writeValueAsString(dto));
        kafkaTemplate.send("lkdkp.request.low", dto);
    }

    //@Scheduled(initialDelay = 12000, fixedDelay = 4000)
    public void middle() {
        RequestDto dto = new RequestDto("middle");
        log.info("<= middle sending {}", writeValueAsString(dto));
        kafkaTemplate.send("slkdkp.request.middle", dto);
    }

    public void own(String data) {
        RequestDto dto = new RequestDto(data);
        log.info("<= own sending by POST {}", writeValueAsString(dto));
        kafkaTemplate.send("lkdkp.request.own1", dto);
    }

    //@Scheduled(initialDelay = 14000, fixedDelay = 4000)
    public void high() {
        RequestDto dto = new RequestDto("high");
        log.info("<= high sending {}", writeValueAsString(dto));
        kafkaTemplate.send("lkdkp.request.high", dto);
    }

    //@Scheduled(initialDelay = 16000, fixedDelay = 4000)
    public void critical() {
        RequestDto dto = new RequestDto("critical");
        log.info("<= critical sending {}", writeValueAsString(dto));
        kafkaTemplate.send("lkdkp.request.critical", dto);
    }


    ///////// readers

    //@KafkaListener(id = "Result", topics = {"lkdkp.result"}, containerFactory = "singleFactory")
    public ResultDto answer(ResultDto dto) {
        log.info("get from queue Result");
        //log.info("=> result {}", writeValueAsString(dto));
        return dto;
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
