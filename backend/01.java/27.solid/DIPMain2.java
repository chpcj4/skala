// 🔹 실행 예시
public class DIPMain2 {
    public static void main(String[] args) {
        InputDevice wirelessKeyboard = new WirelessKeyboard();
        DisplayDevice projector = new Projector();

        Computer computer = new Computer(wirelessKeyboard, projector);
        computer.operate();
    }
}
