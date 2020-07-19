package neu.train.project.order.vo;

import java.util.Date;

public class GetASaoQueryByTime {
    private Integer mvoId;
    private Integer salId;
    private Date startTime;
    private Date endTime;

    public GetASaoQueryByTime(Integer mvoId, Integer salId, Date startTime, Date endTime) {
        this.mvoId = mvoId;
        this.salId = salId;
        this.startTime = startTime;
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
