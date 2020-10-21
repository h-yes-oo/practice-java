import java.util.Queue;

public abstract class Worker {
    private int id;
    static int num=0;
    Queue<Work> workQueue;

    public abstract void run();

    Worker(Queue<Work> workQueue) {
        this.id=this.num;
        num++;
        this.workQueue = workQueue;
    }

    void report(String msg){
        System.out.print("\b".repeat(300) +
                "[" + ">".repeat(workQueue.size()) + "] " + id + "-th Worker(" + getClass().getName() + ") " + msg);
    }
}

class Producer extends Worker {
    Producer(Queue<Work> workQueue) {
        super(workQueue);
    }

    public void run() {
        Work work = new Work();
        this.workQueue.add(work);
        this.report("produced work"+work.getId());
        return;
    }
}

class Consumer extends Worker {
    Consumer(Queue<Work> workQueue) {
        super(workQueue);
    }

    public void run() {
        int prob = (int)(Math.random()*3);
        if(prob == 1){
            Work work = this.workQueue.poll();
            if(work==null){
                this.report("failed to consume work");
                return;
            }
            this.report("consumed work"+work.getId());
            return;
        }
        this.report("failed to consume work");
        return;
    }
}
