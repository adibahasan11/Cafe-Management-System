package sample.Order;

public class OrderList {

    int order_id;
    int bill_id;
    int amount;
    String bill_time;

    public OrderList(int order_id,int bill_id,int amount,String bill_time) {
        this.order_id=order_id;
        this.bill_id=bill_id;
        this.amount=amount;
        this.bill_time=bill_time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_time() {
        return bill_time;
    }

    public void setBill_time(String bill_time) {
        this.bill_time = bill_time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
