package studyGroup2.bending;


class NoItemState implements State{
    private static NoItemState INSTANCE = null;

    private NoItemState(){}

    public static NoItemState getINSTANCE(){
        if(INSTANCE == null) INSTANCE = new NoItemState();

        return INSTANCE;
    }

    @Override
    public void insertMoney(BendingMachine machine,int money) {
        System.out.println("재고가 부족합니다. 현재 잔액 : "+machine.getNowMoney()+"원");


    }

    @Override
    public void purchase(BendingMachine machine) {
        if(machine.getItemBox().getAmount()>0){
            if(machine.getNowMoney() >= machine.getCost()){
                machine.setState(EnoughMoneyState.getINSTANCE());
                EnoughMoneyState.getINSTANCE().purchase(machine);
            }else{
                machine.setState(NoMoneyState.getINSTANCE());
                System.out.println("돈이 부족합니다.");
            }

        }else
            System.out.println("재고가 부족합니다. 현재 잔액 : "+machine.getNowMoney()+"원");

    }

    @Override
    public void moneyChange(BendingMachine machine) {
        System.out.println("돈을 반환 합니다. 반환된 금액은 "+machine.getNowMoney()+"원 입니다.");
        machine.clearNowMoney();
    }
}

