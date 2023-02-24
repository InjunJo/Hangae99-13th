package studyGroup2.bending;

class EnoughMoneyState implements State{
    private static EnoughMoneyState INSTANCE = null;

    private EnoughMoneyState(){}

    public static EnoughMoneyState getINSTANCE(){
        if(INSTANCE == null) INSTANCE = new EnoughMoneyState();

        return INSTANCE;
    }

    @Override
    public void insertMoney(BendingMachine machine,int money) {
        System.out.println("현재 투입된 돈은 "+machine.getNowMoney()+"원 입니다. 구매 가능합니다.");

    }

    @Override
    public void purchase(BendingMachine machine) {
        if(machine.getItemBox().getAmount() > 0){
            machine.getItemBox().putItem();
            machine.changeOfMoney(machine.getCost());
            System.out.println("음료수가 나왔습니다. 현재 잔액 : "+machine.getNowMoney());
            System.out.println("더 구매하시려면 구매 버튼을 누르십시오");

            if(machine.getNowMoney() < machine.getCost()){
                machine.setState(NotEnoughMoneyState.getINSTANCE());
                System.out.println("돈이 부족합니다. 구매하시려면 돈을 더 투입 하십시오. 현재 잔액 : "+machine.getNowMoney());
            }


        } else{
            machine.setState(NoItemState.getINSTANCE());
            System.out.println("재고가 부족하여 음료를 구매 할 수 없습니다");
            System.out.println("돈을 반환 합니다");
            machine.clearNowMoney();
        }

    }

    @Override
    public void moneyChange(BendingMachine machine) {

        System.out.println("돈을 반환 합니다. 반환된 돈은 " + machine.getNowMoney()+"원 입니다.");
        machine.clearNowMoney();
    }
}
