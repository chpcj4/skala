// package skalajava;

import java.util.stream.IntStream;

public class skalatest15 {
    public static void main(String[] args) {
        int size = 5;
        int floor = 1;
        while (floor <= size) {
            for (int i=0; i < floor; i++) {
                System.out.print("*");
            }
            System.out.println();
            floor++;
        }
            
        // 1부터 size까지 반복하며 별 출력
        IntStream.rangeClosed(1, size)
                 .mapToObj(floor2 -> "*".repeat(floor2)) // floor 개수만큼 별 생성
                 .forEach(System.out::println); // 출력
    }
}
