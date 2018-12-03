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
        BookingRequest request = requests.get(requests.size() - 1);
        requests.remove(requests.size() - 1);
        if (requests.size() < QUEUE_SIZE) {
            notify();
        }
        return request;
    }

    public synchronized void putRequest(BookingRequest request) throws InterruptedException {
        if (requests.size() < QUEUE_SIZE) {
            this.requests.add(request);
        } else {
            wait();
        }
    }
}
