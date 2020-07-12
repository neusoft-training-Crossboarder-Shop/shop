package neu.train.project.order.vo;

import java.util.Date;

public class GetAStoOrderQueryByTime {
    private int bvoId;
    private Date startTime;
    private Date endTime;

    public GetAStoOrderQueryByTime(int bvoId, Date startTime, Date endTime) {
        this.bvoId = bvoId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getBvoId() {
        return bvoId;
    }

    public void setBvoId(int bvoId) {
        this.bvoId = bvoId;
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
