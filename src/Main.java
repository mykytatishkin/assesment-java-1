import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static ArrayList<Cinema> listOfCinema = new ArrayList<Cinema>();

    public static void main(String[] args)
    {

        try {
            File myObj = new File("data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // System.out.println(data);
                StringParser(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // 1 and 2 tasks
        for(Cinema item : listOfCinema)
        {
            item.caseAvaliable();
        }

        sortAndPrintByTitle(listOfCinema);
        System.out.println("-------------------");
        sortAndPrintByRating(listOfCinema);
        System.out.println("-------------------");
        sortAndPrintByPrice(listOfCinema);
        System.out.println("-------------------");
    }

    public static void StringParser(String inputString)
    {
        // Define the pattern for extracting information
        Pattern pattern = Pattern.compile("([^\\(]+)\\(([^*]+)\\*([0-9]+(?:\\.[0-9]+)?)eur\\*([^)]+)\\): (.+)");
        Matcher matcher = pattern.matcher(inputString);

        // Process each film separately
        while (matcher.find()) {
            String filmName = matcher.group(1).trim();
            String genre = matcher.group(2).trim();
            String price = matcher.group(3).trim();
            String rating = matcher.group(4).trim();
            String dateTimesString = matcher.group(5).trim();

            // Parse date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<DateTimeFormatter> dateTimeFormatters = new ArrayList<>();
            dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Split dateTimesString and store parsed DateTime values in a list
            List<LocalDateTime> parsedDateTimes = new ArrayList<>();
            String[] dateTimes = dateTimesString.split("\\\\\\\\");
            for (String dateTime : dateTimes) {
                try {
                    // Adjust the date format to accommodate single-digit minutes
                    Date date = dateFormat.parse(dateTime.replaceAll(":(\\d):", ":0$1:"));
                    LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatters.get(0));
                    parsedDateTimes.add(localDateTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            // Print film information
            System.out.println("Film Name: " + filmName);
            System.out.println("Genre: " + genre);
            System.out.println("Price: " + price);
            System.out.println("Rating: " + rating);

            // Print the list of parsed DateTime values
            System.out.println("Parsed Date and Time Values:");
            for (LocalDateTime dateTime : parsedDateTimes) {
                System.out.println(dateTime);
            }
            System.out.println("--------------");
            Cinema cinema = new Cinema(filmName, parsedDateTimes, Integer.valueOf(price), genre, Double.valueOf(rating));
            listOfCinema.add(cinema);
        }
    }

    public static void sortAndPrintByTitle(ArrayList<Cinema> listOfCinema) {
        Collections.sort(listOfCinema, Comparator
                .<Cinema, String>comparing(cinema -> cinema.getFilmName(), Comparator.reverseOrder()));

        // Print on each iteration
        for (Cinema cinema : listOfCinema) {
            System.out.println(cinema.toString() + " - " + cinema.rating);
        }
    }

    public static void sortAndPrintByRating(ArrayList<Cinema> listOfCinema) {
        Collections.sort(listOfCinema, Comparator
                .<Cinema, Double>comparing(cinema -> cinema.getRating(), Comparator.reverseOrder()));

        // Print on each iteration
        for (Cinema cinema : listOfCinema) {
            System.out.println(cinema.toString() + " - " + cinema.rating);
        }
    }

    public static void sortAndPrintByPrice(ArrayList<Cinema> listOfCinema) {
        Collections.sort(listOfCinema, Comparator
                .<Cinema, Integer>comparing(cinema -> cinema.getPrice(), Comparator.reverseOrder()));

        // Print on each iteration
        for (Cinema cinema : listOfCinema) {
            System.out.println(cinema.toString() + " - " + cinema.rating);
        }
    }
}