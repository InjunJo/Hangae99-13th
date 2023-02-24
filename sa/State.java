package sa;

public enum State {

    OPERATING("정상 운행 중"), GARAGE("========= 차고지로 이동 ========");

    private final String msg;

    State(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
