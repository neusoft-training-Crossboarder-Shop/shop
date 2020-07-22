package neu.train.project.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class GetASaoQueryByTime {
    private Integer mvoId;
    private Integer salId;
    @JsonIgnore
    private String beginTime;
    @JsonIgnore
    private String endTime;

    public GetASaoQueryByTime(Integer mvoId, Integer salId, String beginTime, String endTime) {
        this.mvoId = mvoId;
        this.salId = salId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Integer getMvoId() {
        return mvoId;
    }

    public void setMvoId(Integer mvoId) {
        this.mvoId = mvoId;
    }

    public Integer getSalId() {
        return salId;
    }

    public void setSalId(Integer salId) {
        this.salId = salId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
