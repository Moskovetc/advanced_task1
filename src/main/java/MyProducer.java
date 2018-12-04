import hotelbook.BookingRequest;
import hotelbook.HotelBook;
import hotelbook.RandomBookingRequest;

public class MyProducer implements Runnable {
    private HotelBook book;
    private static Integer quantityProducedRequests = 0;
    private final Integer MAX_REQUESTS;
    private final int QUEUE_SIZE = 5;

    public MyProducer(HotelBook book, Integer maxRequests) {
        this.book = book;
        this.MAX_REQUESTS = maxRequests;
    }

    @Override
    public void run() {
        while (quantityProducedRequests.compareTo(MAX_REQUESTS) < 1) {
            try {
                BookingRequest request = createRandomRequest();

                    if (book.size() < QUEUE_SIZE) {
                        synchronized (book) {
                            book.putRequest(request);
                            quantityProducedRequests++;
                            System.out.println(Thread.currentThread().getName() + " put " + book.size() + " " + quantityProducedRequests);
                        }
                    } else {
                        wait();
                    }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private BookingRequest createRandomRequest() {
        RandomBookingRequest request = new RandomBookingRequest();
        return request.get();
    }
}
