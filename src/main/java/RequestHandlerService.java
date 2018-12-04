import hotelbook.HotelBook;

public class RequestHandlerService {
    private final Integer MAX_REQUESTS = 14;

    public void start(){
        HotelBook book = new HotelBook();
        for (int i = 0; i < 3; i++){
            Thread producerThread = new Thread(() -> {
                try {
                    book.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            producerThread.start();
        }
        for (int i = 0; i < 6; i++){
            Thread consumerThread = new Thread(() -> {
                try {
                    book.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            consumerThread.start();
        }
    }
}
