package sa;

public class OilController implements ControlAble {

    private final static int MAX_OIL_AMOUNT = 100;

    private int currentOil;

    private boolean availAble;

    public OilController() {
        this.currentOil = 100;
    }

    @Override
    public boolean available() {

        if(currentOil <10){

            System.out.println("기름이 부족 합니다. 주유를 위해 차고지로 이동 합니다");
            return false;

        }else{
            availAble = true;
            return availAble;
        }
    }

    @Override
    public void control(Bus bus, int oil) {

        currentOil = currentOil + oil;

        if(available()){

            if(currentOil > MAX_OIL_AMOUNT){
                currentOil = MAX_OIL_AMOUNT;
            }
            System.out.println("현재 기름의 양 : ["+currentOil+"]");


        }else{
            bus.endOperate();

        }
    }


}
