package hotelbook;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class HotelBook {
    LinkedList<BookingRequest> requests = new LinkedList<>();
    private final int QUEUE_SIZE = 5;
    private final int MAX_REQUESTS = 15;
    private static AtomicInteger quantityRequests = new AtomicInteger(0);
    private final int ACTIVITY = 5000;

    @Override
    public String toString() {
        return "HotelBook{" +
                "requests=" + requests +
                '}';
    }

    public void consume() throws InterruptedException {
        while (MAX_REQUESTS > quantityRequests.get()) {

            Thread.sleep(ACTIVITY);
            synchronized (this) {
                if (requests.isEmpty()) {
                    wait();
                }
                BookingRequest request = requests.removeFirst();
                notify();
                System.out.println(String.format("%s geted size: %s increment: %s",
                        Thread.currentThread().getName(), requests.size(), quantityRequests.get()));
            }
        }
    }

    public void produce() throws InterruptedException {
        while (MAX_REQUESTS > quantityRequests.get()) {
            synchronized (this) {
                if (requests.size() != QUEUE_SIZE) {
                    this.requests.add(new RandomBookingRequest().get());
                    notify();
                    quantityRequests.getAndIncrement();
                    System.out.println(String.format("%s puted size: %s increment: %s",
                            Thread.currentThread().getName(), requests.size(), quantityRequests.get()));
                }
                wait();
            }
        }
    }
}
