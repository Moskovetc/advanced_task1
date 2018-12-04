import hotelbook.HotelBook;

public class RequestHandlerService {
    private final Integer MAX_REQUESTS = 14;

    public void start(){
        HotelBook book = new HotelBook();
        for (int i = 0; i < 3; i++){
            Thread producerThread = new Thread(new MyProducer(book, MAX_REQUESTS));
            producerThread.start();
        }
        for (int i = 0; i < 6; i++){
            Thread consumerThread = new Thread(new MyConsumer(book, MAX_REQUESTS));
            consumerThread.start();
        }
    }
}
