import hotelbook.BookingRequest;
import hotelbook.HotelBook;
import hotelbook.RandomBookingRequest;

public class MyProducer implements Runnable{
    private volatile HotelBook book;
    private Integer quantityRequests;
    private final int MAX_REQUESTS = 15;

    public MyProducer(HotelBook book, Integer quantityRequests) {
        this.book = book;
        this.quantityRequests = quantityRequests;
    }

    @Override
    public void run() {
        while (quantityRequests < MAX_REQUESTS){
            try {
                book.putRequest(createRandomRequest());
                quantityRequests++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%s puted, result: %s", Thread.currentThread().getName(), book.toString()));
    }

    private BookingRequest createRandomRequest(){
        RandomBookingRequest request = new RandomBookingRequest();
        return request.get();
    }
}
