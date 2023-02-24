package studyGroup2.bending;

class NotEnoughMoneyState implements State{
    private static NotEnoughMoneyState INSTANCE = null;

    private NotEnoughMoneyState(){}

    public static NotEnoughMoneyState getINSTANCE(){
        if(INSTANCE == null) INSTANCE = new NotEnoughMoneyState();

        return INSTANCE;
    }

    @Override
    public void insertMoney(BendingMachine machine,int money) {
        if(machine.getNowMoney() >= machine.getCost()){
            System.out.println("현재 투입된 돈은 "+machine.getNowMoney()+"원 입니다. 구매 가능합니다.");
            machine.setState(EnoughMoneyState.getINSTANCE());
        }

        else{
            System.out.println("현재 투입된 돈은 "+machine.getNowMoney()+"원 입니다.");
            machine.setState(NotEnoughMoneyState.getINSTANCE());
        }
    }

    @Override
    public void purchase(BendingMachine machine) {
        System.out.println("돈이 부족합니다. 구매하시려면 돈을 더 투입 하십시오.현재 잔액 : "+machine.getNowMoney());
    }

    @Override
    public void moneyChange(BendingMachine machine) {
        System.out.println("투입된 돈을 반환 합니다. 반환된 돈은 "+machine.getNowMoney()+"원 입니다");
        machine.clearNowMoney();
    }
}
