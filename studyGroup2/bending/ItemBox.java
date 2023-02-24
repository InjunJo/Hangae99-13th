package studyGroup2.bending;

public class ItemBox {
    private int amount;
    private int cost;

    ItemBox(){
        amount = 100;
    }

    public void putItem(){
        --amount;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

}
