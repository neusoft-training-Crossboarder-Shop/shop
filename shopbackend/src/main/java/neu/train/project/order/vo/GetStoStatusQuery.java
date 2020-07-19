package neu.train.project.order.vo;

public class GetStoStatusQuery {
    private Integer mvoId;
    private Integer stoId;
    private Integer status;

    public GetStoStatusQuery(Integer mvoId, Integer stoId, Integer status) {
        this.mvoId = mvoId;
        this.stoId = stoId;
        this.status = status;
    }

    public Integer getMvoId() {
        return mvoId;
    }

    public void setMvoId(Integer mvoId) {
        this.mvoId = mvoId;
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
