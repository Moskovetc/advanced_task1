import hotelbook.HotelBook;

public class RequestHandlerService {
    private Integer quantityRequests = 0;

    public void start(){
        HotelBook book = new HotelBook();
        for (int i = 0; i < 1; i++){
            Thread producerThread = new Thread(new MyProducer(book, quantityRequests));
            producerThread.start();
        }
        for (int i = 0; i < 1; i++){
            Thread consumerThread = new Thread(new MyConsumer(book));
            consumerThread.start();
        }
    }
}
