package sa;


/**
 *
 * Bus 클래스는 승객을 태울 수 있으며, 연료량과 차량 속도를 조절 할 수 있다.
 *
 */

public class Bus {

    /**
     * Bus의 총 갯수를 세는 정적 멤버.
     */
    private static int totalBusCount;

    /**
     * 버스 제조사의 이름을 나타낸다.
     */
    private final static String BUS_COMPANY_NAME = "Jun_Transfer";

    /**
     * 최대 탑승 승객 인원
     */
    private final static int MAX_PASSENGER = 50;

    /**
     * 최대 가능 속력
     */
    private final static int MAX_SPEED = 160;

    /**
     * 버스마다 고유의 식별 넘버
     */
    private final String BUS_SERIAL_NUM;

    /**
     * 버스의 기본 속력 값
     */
    private final int DEFAULT_SPEED = 70;

    /**
     * 버스가 승객을 태우고 운행 할 때 기본 기름 소모 값
     */
    private final  int DEFAULT_OIL_USAGE = -20;

    /**
     * 현재 버스에 탑승한 승객의 인원
     */
    private int passenger;

    /**
     * 버스의 탑승 가격.
     */
    private final int ticketPrice;

    /**
     * 버스의 상태를 나타내기 위한 Enum 타입의 값.
     */
    private State state;

    /**
     * 버스의 핵심 부품들을 컨트롤하는 컨트롤러들을 따로 가지며 조절하는 manager
     */
    private ControllerManager manager;


    /**
     * 버스는 기본 생성자만 갖으며, 처음 초기화 때, 버스의 고유 번호, 탑승 가격, 컨트롤러 매니저가 초기화 된다.
     * 그리고 초기화된 컨트롤러 매니저에 기본으로 speed 조절, oil 관련 컨트롤러가 들어간다.
     * 버스는 처음 상태에선 운행 상태로 초기화 된다.
     */
    public Bus() {
        this.BUS_SERIAL_NUM = BUS_COMPANY_NAME+"-"+(++totalBusCount);
        this.state = State.OPERATING;
        this.ticketPrice = 1500;

        manager = new ControllerManager();

        manager.addController(SpeedController.class, new SpeedController(MAX_SPEED));
        manager.addController(OilController.class, new OilController());
    }


    /**
     * 버스의 운행을 시작 할 때, 호출하는 메소드이다. 이 메소드는 state를 변경함으로써 버스의 상태를 변경 시킨다.
     * 또한 운행 전, ControllerManager를 통해 버스에 속한 controller의 이상 유무를 체크 한다.
     *
     * @see ControllerManager
     */
    public void operate(){

        if(manager.checkController()){
            System.out.println("운행을 시작합니다.");
            System.out.println();
            state = State.OPERATING;
            speedControl(DEFAULT_SPEED);

        }else{
            state = State.GARAGE;
            System.out.println("버스에 문제가 있어 운행을 시작하지 못합니다.");

        }
    }

    /**
     * 버스가 운행을 마칠 때, 버스에 문제가 있을 때 사용 된다. 타고 있던 승객을 모두 하차 시킨 뒤
     * 버스는 차고지로 이동 한다.
     */
    public void endOperate(){

        this.passenger = 0;

        state = State.GARAGE;
        System.out.println(state.getMsg());
        speedControl(0);
        System.out.println();
    }


    /**
     * int형의 speed 값을 받아 버스의 속도를 조절한다. manager의 controllerBox에 있는 controller에게
     * 위임한다.
     * @param speed int형의 speed 값
     */
    public void speedControl(int speed){
        manager.execute(SpeedController.class,this,speed);
    }

    /**
     * 버스가 승객을 태울 수 있게 한다. 받은 매개변수 타려고 하는 승객의 수를
     * 현재 버스에 탑승한 승객과 합쳐, 전체 탈 수 있는 승객의 수와 빼므로써 탈 수 있는 승객의 수를 구한다.
     * 버스가 운행중인 상태에서만 탑승이 되며, 그렇지 않은 경우에는 탑승이 불가하다.
     *
     * @param passengerNum int형의 값을 받는다.
     */
    public void takePassenger(int passengerNum){

        int sum = passenger +passengerNum;
        int available = MAX_PASSENGER - passenger;


        if(state == State.OPERATING){

            if(available >= sum){

                passenger = sum;

                System.out.println(passengerNum+"명의 승객 탑승");
                System.out.println("버스 출발 합니다.============>");
                System.out.println();
                speedControl(DEFAULT_SPEED);

            }else{

                int nonTake = Math.abs(passengerNum - available);
                passenger += available;

                System.out.println("<======"+available+"명의 승객 탑승 완료. ");
                System.out.println("탑승 못한 승객은 "+nonTake+"분은 다음 버스를 기다려 주십시오");
                System.out.println();
                System.out.println("버스 출발 합니다.============>");
            }

            spendOil(DEFAULT_OIL_USAGE);

        }else{
            System.out.println("현재 운행 중이지 않습니다. 운행을 시작하려면 Operate()를 호출 하세요");
            System.out.println();

        }
    }

    /**
     * 매개 변수로 oil 소비량을 받아 버스의 기름양의 변화를 준다. 버스에서 직접 변화를 주지 않고,
     * controller에게 위임한다.
     *
     * @param oil int형의 음수의 값만 받는다.
     */
    private void spendOil (int oil){

        if(oil > 0){
            throw new IllegalArgumentException("oil은 양수가 될 수 없습니다.");
        }else{
            manager.execute(OilController.class, this ,oil);
        }
    }

    /**
     * 기름이 부족한 상태에서 기름을 채우기 위한 메소드
     *
     * @param oil int형의 0보다 큰 값만을 허용한다.
     */
    public void addOil(int oil){

        if(oil < 0){
            System.out.println("추가하려는 oil의 양은 음수가 될 수 없습니다.");
        }else{

            if(state == State.OPERATING){
                System.out.print("기름을 충전하기 위해 차고지로 이동 합니다");
                passenger = 0;
                state = State.GARAGE;
                System.out.println(state.getMsg());

            }else{
                System.out.print("기름을 충전 합니다  ");
            }

            manager.execute(OilController.class, this ,oil);
        }
    }


    @Override
    public String toString() {
        return "Bus{" +
            "BUS_SERIAL_NUM='" + BUS_SERIAL_NUM + '\'' +
            ", passenger=" + passenger +
            ", state=" + state +
            ", manager=" + manager.getClass().getSimpleName() +
            '}';
    }
}
