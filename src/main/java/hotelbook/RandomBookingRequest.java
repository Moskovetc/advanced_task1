package hotelbook;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomBookingRequest {
    private final int hoursForRandomDateGenerate = 500;
    private final List<String> adsForRandomGenerate = Arrays.asList("Just for rest", "Hotel for poor",
            "We have a BrauPlatz", "We haven't an Ad", "Expensive");
    private final List<String> nameForRandomGenerate = Arrays.asList(
            "Malibu",
            "Rostelecom",
            "ParkInn",
            "Mega",
            "Korston"
    );

    public BookingRequest get() {
        Random random = new Random();
        LocalDateTime randomDateTime = LocalDateTime.now();
        BookingRequest request = new BookingRequest();
        request.setAd(adsForRandomGenerate.get(random.nextInt(adsForRandomGenerate.size())));
        request.setDate(randomDateTime.minusHours(random.nextInt(hoursForRandomDateGenerate)));
        request.setName(nameForRandomGenerate.get(random.nextInt(nameForRandomGenerate.size())));
        return request;
    }

}
