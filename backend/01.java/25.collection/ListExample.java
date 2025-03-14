package skalajava;

import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        // 1. 리스트 생성
        List<String> fruits = new ArrayList<>();

        // 2. 요소 추가: add()
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("체리");

        // 3. 리스트의 크기 출력: size()
        System.out.println("과일의 개수: " + fruits.size()); // 출력: 과일의 개수: 3

        // 4. 특정 요소에 접근: get()
        System.out.println("첫 번째 과일: " + fruits.get(0)); // 출력: 첫 번째 과일: 사과

        // 5. 요소 수정: set()
        fruits.set(1, "오렌지");
        System.out.println("수정된 리스트: " + fruits); // 출력: 수정된 리스트: [사과, 오렌지, 체리]

        // 6. 요소 삭제: remove()
        fruits.remove("체리");
        System.out.println("삭제된 리스트: " + fruits); // 출력: 삭제된 리스트: [사과, 오렌지]

        // 7. 리스트에 있는 모든 요소 반복: forEach()
        System.out.println("리스트의 모든 과일:");
        fruits.forEach(System.out::println);
        
        // 8. 리스트가 비어있는지 확인: isEmpty()
        System.out.println("리스트가 비었나요? " + fruits.isEmpty()); // 출력: false

        // 9. 모든 요소 삭제: clear()
        fruits.clear();
        System.out.println("모든 과일 삭제 후 리스트: " + fruits); // 출력: 모든 과일 삭제 후 리스트: []

        // 10. 리스트에 요소가 다시 추가됨
        fruits.add("망고");
        fruits.add("키위");
        System.out.println("다시 추가된 리스트: " + fruits); // 출력: 다시 추가된 리스트: [망고, 키위]
    }
}
