import hotelbook.HotelBook;

import java.util.concurrent.atomic.AtomicInteger;

public class MyConsumer implements Runnable {
    private HotelBook book;
    private final int ACTIVITY = 5000;
    private static AtomicInteger quantityRequests = new AtomicInteger(0);
    private final int MAX_REQUESTS;

    public MyConsumer(HotelBook book, Integer maxRequests) {
        this.book = book;
        this.MAX_REQUESTS = maxRequests;
    }

    @Override
    public void run() {
        while (MAX_REQUESTS > quantityRequests.get()) {
            synchronized (book) {
                try {
                    Thread.sleep(ACTIVITY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (book.isEmpty() && MAX_REQUESTS != quantityRequests.get()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!book.isEmpty()) {
                    book.getRequest();
                    quantityRequests.getAndIncrement();
                    notifyAll();
                }
                System.out.println(String.format("%s geted size: %s increment: %s",
                        Thread.currentThread().getName(), book.size(), quantityRequests.get()));
            }
        }
    }
}