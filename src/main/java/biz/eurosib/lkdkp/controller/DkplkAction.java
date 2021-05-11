package biz.eurosib.lkdkp.controller;

import org.json.JSONObject;

public class DkplkAction {
    private String type;
    private Integer priority;
    private String data;


    public DkplkAction() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
