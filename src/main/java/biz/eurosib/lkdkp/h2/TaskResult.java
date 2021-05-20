package biz.eurosib.lkdkp.h2;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "task_result")
public class TaskResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "guid")
    private UUID guid;

    @Column(name = "result")
    private int result;

    @Column(name = "data", length = 32767)
    private String data;

    public TaskResult() {}
    public TaskResult(UUID guid, int result, String data) {
        this.guid = guid;
        this.result = result;
        this.data = data;
    }


    public long getId() {
        return id;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
