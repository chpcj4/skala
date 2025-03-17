// 🔹 추상화 (인터페이스) 정의
interface InputDevice {
    void input();
}

interface DisplayDevice {
    void display();
}

// 🔹 저수준 모듈 (구체적인 구현)
class Keyboard implements InputDevice {
    @Override
    public void input() {
        System.out.println("키보드를 사용하여 입력합니다.");
    }
}

class Monitor implements DisplayDevice {
    @Override
    public void display() {
        System.out.println("모니터에 화면을 출력합니다.");
    }
}

// 🔹 새로운 입력/출력 장치 추가 가능
class Mouse implements InputDevice {
    @Override
    public void input() {
        System.out.println("마우스로 클릭합니다.");
    }
}

class Projector implements DisplayDevice {
    @Override
    public void display() {
        System.out.println("프로젝터에 화면을 출력합니다.");
    }
}

// 🔹 고수준 모듈 (비즈니스 로직) → 인터페이스에 의존 (DIP 준수)
class Computer {
    private InputDevice inputDevice;
    private DisplayDevice displayDevice;

    // 의존성 주입 (Dependency Injection) 사용
    public Computer(InputDevice inputDevice, DisplayDevice displayDevice) {
        this.inputDevice = inputDevice;
        this.displayDevice = displayDevice;
    }

    public void operate() {
        inputDevice.input();
        displayDevice.display();
    }
}

// 🔹 실행 예시
public class DIPMain {
    public static void main(String[] args) {
        InputDevice keyboard = new Keyboard();
        DisplayDevice monitor = new Monitor();

        // 💡 DIP 준수: 인터페이스를 통해 의존성 주입
        Computer computer1 = new Computer(keyboard, monitor);
        computer1.operate();

        // 💡 새로운 장치(마우스 + 프로젝터) 추가 → 기존 코드 수정 없이 사용 가능
        InputDevice mouse = new Mouse();
        DisplayDevice projector = new Projector();

        Computer computer2 = new Computer(mouse, projector);
        computer2.operate();
    }
}
