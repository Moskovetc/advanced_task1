import hotelbook.HotelBook;

public class MyConsumer implements Runnable {
    private HotelBook book;
    private final int ACTIVITY = 5000;
    private static Integer quantityRequests = 0;
    private final Integer MAX_REQUESTS;
    private final int QUEUE_SIZE = 5;

    public MyConsumer(HotelBook book, Integer maxRequests) {
        this.book = book;
        this.MAX_REQUESTS = maxRequests;
    }

    @Override
    public void run() {
        while (!(quantityRequests.compareTo(MAX_REQUESTS) == 0)) {
            synchronized (book) {
                if (!book.isEmpty()){
                    book.getRequest();
                    quantityRequests++;
                    System.out.println(Thread.currentThread().getName() + " get " + book.size());
                }
            }
            if (!book.isEmpty() && (book.size() < QUEUE_SIZE)) {
                notify();
            }
            try {
                Thread.sleep(ACTIVITY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
