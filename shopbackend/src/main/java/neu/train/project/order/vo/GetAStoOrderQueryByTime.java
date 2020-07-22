package neu.train.project.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class GetAStoOrderQueryByTime {
    private Integer bvoId;
    private Integer stoId;
    @JsonIgnore
    private String beginTime;
    @JsonIgnore
    private String endTime;

    public GetAStoOrderQueryByTime(Integer bvoId, Integer stoId, String beginTime, String endTime) {
        this.bvoId = bvoId;
        this.stoId = stoId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Integer getBvoId() {
        return bvoId;
    }

    public void setBvoId(Integer bvoId) {
        this.bvoId = bvoId;
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
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
