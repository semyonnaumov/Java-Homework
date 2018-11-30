public class SemaphoreImpl implements Semaphore {
    private final int threadCount;
    private int threadsInside;

    public SemaphoreImpl(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public synchronized void acquire() {
        while (threadsInside >= threadCount) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        ++threadsInside;
    }

    @Override
    public synchronized void release() {
        --threadsInside;
        this.notify();
    }
}
