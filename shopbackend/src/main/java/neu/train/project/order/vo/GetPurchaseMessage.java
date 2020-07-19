package neu.train.project.order.vo;

public class GetPurchaseMessage {
    private Integer amount;
    private Integer proId;
    private Integer[] storeIds;

    public GetPurchaseMessage(Integer amount, Integer proId, Integer[] storeIds) {
        this.amount = amount;
        this.proId = proId;
        this.storeIds = storeIds;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer[] getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(Integer[] storeIds) {
        this.storeIds = storeIds;
    }
}
