// package skalajava;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapExample {
    public static void main(String[] args) {
        // 1. HashMap 예제
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("사과", 3);
        hashMap.put("바나나", 5);
        hashMap.put("체리", 2);

        System.out.println("HashMap:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 2. TreeMap 예제 (키의 자연 순서로 정렬)
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("사과", 3);
        treeMap.put("바나나", 5);
        treeMap.put("체리", 2);

        System.out.println("\nTreeMap (정렬된 순서):");
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 3. LinkedHashMap 예제 (입력 순서 유지)
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("사과", 3);
        linkedHashMap.put("바나나", 5);
        linkedHashMap.put("체리", 2);

        System.out.println("\nLinkedHashMap (입력된 순서 유지):");
        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 4. Map의 인수 처리
        System.out.println("\nMap에서 키 '바나나'의 값: " + hashMap.get("바나나")); // 출력: 5
        System.out.println("Map에 '포도'가 있나요? " + hashMap.containsKey("포도")); // 출력: false

        // 5. Map의 크기
        System.out.println("HashMap의 크기: " + hashMap.size()); // 출력: 3

        // 6. Map에서 요소 삭제
        hashMap.remove("체리");
        System.out.println("체리 삭제 후 HashMap:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 7. 모든 요소 삭제
        hashMap.clear();
        System.out.println("모든 요소 삭제 후 HashMap의 크기: " + hashMap.size()); // 출력: 0
    }
}
