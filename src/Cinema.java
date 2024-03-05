import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Cinema {
    public  String title;
    public List<LocalDateTime> seanceTime;
    public  int price;
    public String genre;
    public double rating;

    public Cinema(String Title, List<LocalDateTime> SeanceTime, int Price, String Genre, double Rating)
    {
        title = Title;
        seanceTime = SeanceTime;
        price = Price;
        genre = Genre;
        rating = Rating;
    }

    public void caseAvaliable()
    {
        if (seanceTime.size() >= 5)
            System.out.println(title);
        else
            System.out.println("No");
    }

    public String getFilmName() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }
    public List<LocalDateTime> getParsedDateTimes() {
        return  seanceTime;
    }
    @Override
    public String toString() {
        return title + "(" + genre + "-" + price + ") - " + rating;
    }
}
