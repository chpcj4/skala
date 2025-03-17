// 공통 인터페이스 정의
interface Shape {
    int getArea();
}

// 직사각형 클래스 (가로, 세로를 독립적으로 설정 가능)
class Rectangle implements Shape {
    protected int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    @Override
    public int getArea() {
        return width * height;
    }
}

// 정사각형 클래스 (가로, 세로가 항상 같음)
class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public void setSide(int side) { this.side = side; }

    @Override
    public int getArea() {
        return side * side;
    }
}

// 사용 예시
public class LSPMain {
    public static void main(String[] args) {
        Shape rect = new Rectangle(10, 20);
        System.out.println("Rectangle Area: " + rect.getArea()); // 10 * 20 = 200

        Shape square = new Square(10);
        System.out.println("Square Area: " + square.getArea()); // 10 * 10 = 100
    }
}
