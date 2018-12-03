package hotelbook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingRequest {
    private String ad;
    private LocalDateTime date;
    private String name;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return "BookingRequest{" +
                "ad='" + ad + '\'' +
                ", date=" + dateFormat.format(date) +
                ", name='" + name + '\'' +
                '}';
    }
}
