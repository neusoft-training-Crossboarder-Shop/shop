package neu.train.project.order.vo;

import java.util.Date;

public class GetAStoOrderQueryByTime {
    private Integer bvoId;
    private Integer stoId;
    private Date startTime;
    private Date endTime;

    public GetAStoOrderQueryByTime(Integer bvoId, Integer stoId, Date startTime, Date endTime) {
        this.bvoId = bvoId;
        this.stoId = stoId;
        this.startTime = startTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
