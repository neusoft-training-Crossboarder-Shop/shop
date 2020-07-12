package neu.train.project.order.vo;

public class GetUpdateASto {
    private Integer stoId;
    private Integer qty;

    public GetUpdateASto(Integer stoId, Integer qty) {
        this.stoId = stoId;
        this.qty = qty;
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
