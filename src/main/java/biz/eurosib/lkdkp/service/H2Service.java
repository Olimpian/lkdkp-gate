package biz.eurosib.lkdkp.service;

import biz.eurosib.lkdkp.config.KafkaConsumerConfig;
import biz.eurosib.lkdkp.h2.TaskResult;
import biz.eurosib.lkdkp.h2.TaskResultRepository;
import biz.eurosib.lkdkp.kafka.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class H2Service {
    private KafkaConsumerConfig config;
    private TaskResultRepository repository;

    private final Logger log = LoggerFactory.getLogger(H2Service.class);

    @Autowired
    public H2Service(KafkaConsumerConfig config,
                     TaskResultRepository repository) {
        this.config = config;
        this.repository = repository;
    }

    @KafkaListener(id = "Result", topics = "#{@kafkaConsumerConfig.getResultQueue()}", containerFactory = "singleFactory")
    public void consume(ResultDto dto) {
        TaskResult result = new TaskResult();
        result.setGuid(dto.getTaskGuid());
        result.setResult(dto.getResult());
        result.setData(dto.getData().toString());

        repository.save(result);
    }

    public TaskResult getResult(UUID guid) {
        return repository.findDistinctTopByGuid(guid);
    }

    public void deleteResult(UUID guid) {
        repository.delete(repository.findDistinctTopByGuid(guid));
    }
}
