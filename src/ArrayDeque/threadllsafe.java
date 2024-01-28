import java.util.LinkedList;

public class SafeLinkedListExample {

    private static LinkedList<Integer> linkedList = new LinkedList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> addElements(1, 5));
        Thread thread2 = new Thread(() -> addElements(6, 10));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Linked List: " + linkedList);
    }

    private static void addElements(int start, int end) {
        synchronized (lock) {
            for (int i = start; i <= end; i++) {
                linkedList.add(i);
            }
        }
    }
}
