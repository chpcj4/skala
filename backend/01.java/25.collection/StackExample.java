import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        System.out.println("Stack 예제");
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");

        System.out.println("Stack pop 순서:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop()); // 입력 순서대로 출력됨
        }
    }
}
