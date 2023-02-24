package studyGroup2.bending;

public class Client {
    public static void main(String[] args) {
        BendingMachine machine = new BendingMachine();
        machine.controlItemBox(0);
        machine.insertMoney(500);

        machine.purchase();
        machine.purchase();

        machine.controlItemBox(5);
        machine.purchase();
        machine.purchase();
        machine.insertMoney(500);
        machine.purchase();
        machine.purchase();
        machine.moneyChange();
        machine.moneyChange();
        machine.purchase();

    }
}
