package neu.train.project.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class GetStiQuery {
    private Integer storeId;
    private Integer proId;
    @JsonIgnore
    private String beginTime;
    @JsonIgnore
    private String endTime;

    public GetStiQuery(Integer storeId, Integer proId, String beginTime, String endTime) {
        this.storeId = storeId;
        this.proId = proId;
        this.beginTime = beginTime;
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
