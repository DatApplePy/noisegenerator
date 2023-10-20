package hu.szohrfe.noisegenerator.util;

import java.util.concurrent.CountDownLatch;

public class ResettableCountDownLatch {
    private final int initialCount;
    private volatile CountDownLatch latch;

    public ResettableCountDownLatch(int  count) {
        initialCount = count;
        latch = new CountDownLatch(count);
    }

    public void reset() {
        latch = new CountDownLatch(initialCount);
    }

    public void countDown() {
        latch.countDown();
    }

    public void await() throws InterruptedException {
        latch.await();
    }
}
