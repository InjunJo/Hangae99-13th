package studyGroup2.bending;


class NoMoneyState implements State{
    private static NoMoneyState INSTANCE = null;

    private NoMoneyState(){}

    public static NoMoneyState getINSTANCE(){
        if(INSTANCE == null) INSTANCE = new NoMoneyState();

        return INSTANCE;
    }

    @Override
    public void insertMoney(BendingMachine machine,int money) {
        if(machine.getItemBox().getAmount() >0){
            if(machine.getCost() > machine.getNowMoney()) {
                machine.setState(NotEnoughMoneyState.getINSTANCE());

                System.out.println("돈이 부족합니다. 구매하시려면 금액을 더 넣어주십시오 현재 잔액 : "+machine.getNowMoney()+"원");
            }

            else {
                machine.setState(EnoughMoneyState.getINSTANCE());
                System.out.println("구매 가능합니다. 구매 하시려면 구매 버튼, 돈을 반환하시려면 반환 버튼을 누르십시오.");
            }
        }else{
            machine.setState(NoItemState.getINSTANCE());
            System.out.println("재고가 부족합니다.현재 잔액 : "+machine.getNowMoney()+"원");

        }


    }

    @Override
    public void purchase(BendingMachine machine) {
        System.out.println("구매하시려면 돈을 투입하십시오");
    }

    @Override
    public void moneyChange(BendingMachine machine) {
        System.out.println("투입된 돈이 없습니다.");
    }

}
