// Mock 객체 생성 (테스트용)
class MockInputDevice implements InputDevice {
    @Override
    public void input() {
        System.out.println("테스트용 입력 장치");
    }
}

class MockDisplayDevice implements DisplayDevice {
    @Override
    public void display() {
        System.out.println("테스트용 출력 장치");
    }
}

// 테스트 실행
public class DIPTest {
    public static void main(String[] args) {
        InputDevice mockInput = new MockInputDevice();
        DisplayDevice mockDisplay = new MockDisplayDevice();

        Computer testComputer = new Computer(mockInput, mockDisplay);
        testComputer.operate(); // 테스트 수행
    }
}
