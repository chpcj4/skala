import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {

        System.out.println("Arraydeque 예제");
        Queue<String> aq = new ArrayDeque<>();
        aq.add("A");
        aq.add("C");
        aq.add("B");
        aq.add("D");

        System.out.println("Arraydeque poll 순서:");
        while (!aq.isEmpty()) {
            System.out.println(aq.poll()); // 입력 순서대로 출력됨
        }


        System.out.println("LinkedList 예제");
        Queue<String> ll = new LinkedList<>();
        ll.add("A");
        ll.add("C");
        ll.add("B");
        ll.add("D");

        System.out.println("LinkedList poll 순서:");
        while (!ll.isEmpty()) {
            System.out.println(ll.poll()); // 정렬된 순서대로 출력됨
        }

        System.out.println("PriorityQueue 예제");
        Queue<String> pq = new PriorityQueue<>(); // 기본 정렬 (오름차순)

        pq.add("A");
        pq.add("C");
        pq.add("B");
        pq.add("D");


        System.out.println("PriorityQueue poll 순서:");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // 정렬된 순서대로 출력됨
        }
    }
}

