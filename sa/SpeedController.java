package sa;

public class SpeedController implements ControlAble {

    private int currentSpeed;

    private final int MAX_SPEED;

    private boolean available = true;

    public SpeedController(int MAX_SPEED) {

        this.MAX_SPEED = MAX_SPEED;
        currentSpeed = 0;
    }

    @Override
    public boolean available() {
        return available;
    }

    @Override
    public void control(Bus bus,int speed) {

        currentSpeed = speed;

        if(currentSpeed <= 0 ){

            currentSpeed = 0;
            System.out.println("[0km/h]으로 우리 버스 정지 합니다.");

        }else{

            currentSpeed = speed;

            if(currentSpeed >= MAX_SPEED){
                currentSpeed = MAX_SPEED;
                System.out.println("최대 속도 초과. 속도를 "+MAX_SPEED+"km/h 이상 올릴 수 없습니다.");
            }else{
                System.out.println("["+currentSpeed+"km/h]으로 버스 운행 중");
            }

        }

    }


}
