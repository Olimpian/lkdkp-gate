package biz.eurosib.lkdkp.controller;

import biz.eurosib.lkdkp.h2.TaskResult;
import biz.eurosib.lkdkp.kafka.ResultDto;
import biz.eurosib.lkdkp.service.H2Service;
import biz.eurosib.lkdkp.service.PlayerClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PlayerClientController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlayerClient playerClient;
    @Autowired
    private H2Service h2Service;


    @PostMapping("/send")
    Map<String, Object> sendAction(@RequestParam(name = "type", required = true) String type,
                                   @RequestParam(name = "priority", required = false) Integer priority,
                                   @RequestParam(name = "data", required = true) String data) {
        logger.info("type = " + type);
        logger.info("priority = " + (priority == null ? "1" : priority));
        logger.info("data = " + data);

        UUID taskGuid = UUID.randomUUID();
        playerClient.produce(data, taskGuid);

        Map<String, Object> answer = new HashMap<>();
        answer.put("result", 0);
        answer.put("data", new HashMap<String, Object>(){{put("GUID", taskGuid);}});
        return answer;
    }

    @GetMapping("/result")
    TaskResult getResult(@RequestParam(name = "taskGuid", required = true) UUID taskGuid) {
        TaskResult result = h2Service.getResult(taskGuid);
        if (result == null) {
            result = new TaskResult();
            result.setResult(1);
        } else {
            h2Service.deleteResult(taskGuid);
        }
        logger.info("result = ", result);
        return result;
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello, i'm gate!";
    }

    @GetMapping("/version")
    public String version() {
        //todo release version
        return "2.0";
    }
}
