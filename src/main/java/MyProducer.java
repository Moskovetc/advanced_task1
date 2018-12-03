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
        for (int i = 0; i < 15; i++){
            try {
                BookingRequest request = createRandomRequest();
                book.putRequest(request);
                System.out.println(String.format("%s puted %s",Thread.currentThread().getName(),request));
//                System.out.println(String.format("%s puted, result: %s", Thread.currentThread().getName(), book.toString()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private BookingRequest createRandomRequest(){
        RandomBookingRequest request = new RandomBookingRequest();
        return request.get();
    }
}
