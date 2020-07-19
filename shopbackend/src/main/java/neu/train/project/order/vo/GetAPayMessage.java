package neu.train.project.order.vo;

public class GetAPayMessage {
    public Integer stoId;
    public String password;
    public String freightCost;

    public GetAPayMessage(Integer stoId, String password, String freightCost) {
        this.stoId = stoId;
        this.password = password;
        this.freightCost = freightCost;
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFreightCost() {
        return freightCost;
    }

    public void setFreightCost(String freightCost) {
        this.freightCost = freightCost;
    }
}
