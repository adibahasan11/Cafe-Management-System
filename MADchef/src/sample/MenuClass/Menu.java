package sample.MenuClass;

public class Menu {

    String ItemName;
    int ItemId;
    int ItemPrice;


    public Menu(int ItemID, String ItemName, int ItemPrice)
    {
        this.ItemName=ItemName;
        this.ItemId=ItemID;
        this.ItemPrice=ItemPrice;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        this.ItemName = itemName;
    }

    public int getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(int itemPrice) {
        ItemPrice = itemPrice;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int item_id) {
        this.ItemId = item_id;
    }
}
