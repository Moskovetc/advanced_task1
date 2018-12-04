package hotelbook;

import java.util.ArrayList;
import java.util.List;

public class HotelBook {
    private List<BookingRequest> requests = new ArrayList<>();

    @Override
    public String toString() {
        return "HotelBook{" +
                "requests=" + requests +
                '}';
    }

    public int size() {
        return requests.size();
    }

    public boolean isEmpty() {
        return requests.isEmpty();
    }

    public BookingRequest getRequest() {
        BookingRequest request = requests.get(0);
        requests.remove(0);
        return request;
    }

    public void putRequest(BookingRequest request) {
        this.requests.add(request);
    }
}
