import hotelbook.HotelBook;
import hotelbook.RandomBookingRequest;

import java.util.concurrent.atomic.AtomicInteger;

public class MyProducer implements Runnable {
    private HotelBook book;
    private static AtomicInteger quantityProducedRequests = new AtomicInteger(0);
    private final int MAX_REQUESTS;

    public MyProducer(HotelBook book, Integer maxRequests) {
        this.book = book;
        this.MAX_REQUESTS = maxRequests;
    }

    @Override
    public void run() {
        while (MAX_REQUESTS > quantityProducedRequests.get()) {
            book.putRequest(new RandomBookingRequest().get());
            quantityProducedRequests.getAndIncrement();
            System.out.println(String.format("%s puted size: %s increment: %s",
                    Thread.currentThread().getName(), book.size(), quantityProducedRequests.get()));
        }
    }
}
