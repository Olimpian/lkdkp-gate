package biz.eurosib.lkdkp.controller;

import biz.eurosib.lkdkp.kafka.ResultDto;
import biz.eurosib.lkdkp.service.PlayerClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerClientController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlayerClient playerClient;

    @GetMapping("/hello")
    String hello() {
        return "Hello, i'm player!";
    }

    @PostMapping("/send")
    String sendAction(@RequestBody String json) throws JsonProcessingException {
//        logger.info("type = " + action.getType());
//        logger.info("priority = " + action.getPriority());
//        logger.info("data = " + action.getData());


//        DkplkAction action = new DkplkAction();
//        ObjectMapper mapper = new ObjectMapper();
//        action = mapper.readValue(json, DkplkAction.class);

        JSONObject action = new JSONObject(json);

//        playerClient.low(action.get("data").toString());
        playerClient.own(action.get("data").toString());
      //  return action.getData();
        return "ok\n";
    }

//    @GetMapping("/consume")
//    ResultDto consumeAnswer() {
//        playerClient.answer()
//    }


    @PostMapping("/send1")
    String sendAction1(@RequestParam String type, @RequestParam String priority, @RequestParam String data) {
        //logger.info("type = " + action.getType());
        //logger.info("priority = " + action.getPriority());
        //logger.info("data = " + action.getData());

        //  playerClient.low(action.getData());
        //  return action.getData();
        return "ok\n";
    }
}
