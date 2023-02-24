package sa;

public class Test {

    public static void main(String[] args) {

        Bus bus1 = new Bus();

        System.out.println(bus1);

        bus1.operate();
        bus1.addOil(100);
        bus1.operate();
        bus1.takePassenger(20);
        bus1.takePassenger(20);

        System.out.println(bus1);

    }

}
