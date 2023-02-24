package studyGroup2.bending;

public class BendingMachine {
    private  State state;

    private int nowMoney = 0;
    private int cost;

    private ItemBox itemBox;

    public BendingMachine(){
        state = NoMoneyState.getINSTANCE();
        itemBox = new ItemBox();
        cost = 1000;
    }

    public void setState(State state){
        this.state = state;
    }

    public void insertMoney(int money){
        this.nowMoney += money;
        state.insertMoney(this, money);

    }

    public void purchase(){
        state.purchase(this);


    }
    public void moneyChange(){
        state.moneyChange(this);
        clearNowMoney();
    }

    protected int getCost(){
        return cost;
    }

    protected ItemBox getItemBox(){
        return itemBox;
    }


    protected void setCost(int cost){
        this.cost = cost;
    }

    protected int getNowMoney(){
        return nowMoney;
    }

    protected void clearNowMoney(){
        nowMoney = 0;
    }

    protected void changeOfMoney(int money){
        nowMoney = nowMoney - money;
    }

    public void controlItemBox(int amount){
        itemBox.setAmount(amount);
    }

}
