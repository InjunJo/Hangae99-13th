package sa;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 버스 부품을 컨트롤 하는 컨트롤러들을 관리하는 클래스이다. Map형태로 컨트롤러를 보관한다.
 *
 * @see ControlAble,OilController,SpeedController
 */
public class ControllerManager{

    /**
     * 컨트롤러를 담는 map형태의 박스. key는 interface ControlAble을 구현한 클래스로 갖는다. 그리고
     * value는 거기에 해당하는 인스턴스를 가진다.
     */
    private final Map<Class<? extends ControlAble>, ControlAble> controllerBox;

    /**
     * controller들의 상태가 모두 정상인지 아닌지를 표현한다.
     */
    public boolean available = true;

    public ControllerManager() {
        this.controllerBox = new HashMap<>();
    }

    public ControllerManager(Map<Class<? extends ControlAble>, ControlAble> controllerBox) {
        this.controllerBox = controllerBox;
    }


    /**
     * ControllerBox에 컨트롤러를 넣어서 보관한다.
     *
     * @param type interface {@link ControlAble}을 구현한 클래스 형
     * @param controller {@link ControlAble} 구현 인스턴스
     */
    public void addController(Class<? extends ControlAble> type ,ControlAble controller){

        controllerBox.put(type,controller);

    }

    /**
     * 매개변수에 해당하는 컨트롤러를 매니저 관리 목록에서 제거한다.
     *
     * @param type interface {@link ControlAble}을 구현한 클래스 형
     */
    public void removeController(Class<? extends ControlAble> type){

        controllerBox.remove(type);
    }

    /**
     * ControllerManager가 매개 변수 type에 맞는 컨트롤를 찾아서 그 컨트롤에게 작업을 위임한다.
     *
     * @param type
     * @param bus
     * @param amount
     */
    public void execute (Class<? extends ControlAble> type, Bus bus ,int amount){

        ControlAble cont = controllerBox.get(type);

        cont.control(bus, amount);

    }


    /**
     * 모든 controller들이 정상을 보이는지 확인한다.
     * @return controller중 하나의 비정상만 있어도, false를 반환한다.
     */
    public boolean checkController(){

        available = true;

        System.out.print("[Controller Manager]  ");

        controllerBox.values().forEach(new Consumer<ControlAble>() {
            @Override
            public void accept(ControlAble controlAble) {

                if(!controlAble.available()){
                    System.out.print(controlAble.getClass().getSimpleName()+" : 비정상 // ");
                    available = false;
                }else{
                    System.out.print(controlAble.getClass().getSimpleName()+" : 정상 // ");
                }
            }
        });

        return available;
    }

}
