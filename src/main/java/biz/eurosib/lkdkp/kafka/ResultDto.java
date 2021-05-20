package biz.eurosib.lkdkp.kafka;


import java.util.UUID;

public class ResultDto extends AbstractDto {
    private int result;
    private Object data;
    private UUID taskGuid;

    public ResultDto() {}
    public ResultDto(int result, Object data, UUID taskGuid) {
        this.result = result;
        this.data = data;
        this.taskGuid = taskGuid;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public UUID getTaskGuid() {
        return taskGuid;
    }

    public void setTaskGuid(UUID taskGuid) {
        this.taskGuid = taskGuid;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
