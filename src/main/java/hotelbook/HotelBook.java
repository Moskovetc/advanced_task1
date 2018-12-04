package hotelbook;

import java.util.ArrayList;
import java.util.List;

public class HotelBook {
    private List<BookingRequest> requests = new ArrayList<>();
    private final int QUEUE_SIZE = 5;

    @Override
    public String toString() {
        return "HotelBook{" +
                "requests=" + requests +
                '}';
    }

    public synchronized int size() {
        return requests.size();
    }

    public synchronized boolean isEmpty() {
        return requests.isEmpty();
    }

    public synchronized BookingRequest getRequest() {
        while (requests.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        BookingRequest request = requests.get(0);
        requests.remove(0);
        notify();
        return request;
    }

    public synchronized void putRequest(BookingRequest request) {
        while (requests.size() == QUEUE_SIZE){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.requests.add(request);
        notify();
    }
}
