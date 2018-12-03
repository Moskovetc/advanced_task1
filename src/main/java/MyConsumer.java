import hotelbook.HotelBook;

public class MyConsumer implements Runnable {
    private volatile HotelBook book;
    private final int ACTIVITY = 5000;
    private Integer quantityRequests = 0;

    public MyConsumer(HotelBook book) {
        this.book = book;
    }

    @Override
    public void run() {
        while (!book.isEmpty()){
            try {
                Thread.sleep(ACTIVITY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s geted %s",Thread.currentThread().getName(), book.getRequest()));
//            System.out.println(String.format("%s geted, result: %s", Thread.currentThread().getName(), book.toString()));
        }
    }
}
