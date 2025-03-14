// package skalajava;

import java.util.stream.IntStream;

public class skalatest16 {
    public static void main(String[] args) {
        int size = 5;
        int floor = 1;
        for (int i = floor; i <= size; i++) {
            for (int j=0; j < size - i; j++) {
                System.out.print(" ");
            }
            for(int j=0; j < 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        IntStream.rangeClosed(1, size)
                 .mapToObj(i -> " ".repeat(size - i) + "*".repeat(2 * i - 1)) // 공백 + 별 조합
                 .forEach(System.out::println); // 출력
    }
}
