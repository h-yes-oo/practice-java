import java.util.List;

public class Scheduler<T> {
    private static final int waitms = 400;

    T schedule(List<T> workers) {
        int size = workers.size();
        int idx = (int)(Math.random()*size);
        return workers.size() == 0 ? null : workers.get(idx);
    }

    static void delay() {
        try {
            Thread.sleep(waitms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
