package neu.train.project.order.vo;

import java.util.Date;

public class GetStiQuery {
    private Integer storeId;
    private Integer proId;
    private Date startTime;
    private Date endTime;

    public GetStiQuery(Integer storeId, Integer proId, Date startTime, Date endTime) {
        this.storeId = storeId;
        this.proId = proId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
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
