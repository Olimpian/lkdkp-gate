package biz.eurosib.lkdkp.kafka;


public class ResultDto extends AbstractDto {
    private String result;

    public ResultDto() {}
    public ResultDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
