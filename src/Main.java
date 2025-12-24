import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Variables
        int movieChoice;
        int tickets;
        Scanner scanner = new Scanner(System.in);
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        String[] movieList = {"The Nightmare Before Christmas",
                "Gremlins",
                "Violent Night",
                "Krampus",
                "Die Hard"};

        double[] priceList = {10.00, 12.00, 11.00, 9.00, 8.00};

        //Display greeting
        displayMessage("Welcome to Movie Night!");

        //Display movie selection
        displayMessage(showMovieList(movieList, priceList, currency));

        //Get input
        movieChoice = getMovieChoice(scanner);
        tickets = getTicketCount(scanner);

        //Display ticket prices;
        System.out.println("Total Price: " + currency.format(calcTotalPrice(movieChoice, priceList, tickets)));

        //Display confirmation message
        displayMessage("Thank you for your purchase. Enjoy the movie!");

        scanner.close();
    }

    //DISPLAY MESSAGE
    public static void displayMessage(String message) {
        System.out.println("===========================================================");
        System.out.println(message);
    }

    //SHOW MOVIE SELECTION
    public static String showMovieList(String[] movies, double[] prices, NumberFormat currency ) {
        StringBuilder movieString = new StringBuilder("Available Movies:\n");
        movieString.append("---------------------------------------------\n");
        for (int i = 0; i < movies.length; i++) {
            movieString.append(String.format("""
                            %d: %-35s %s
                            """,
                    i + 1,
                    movies[i],
                    currency.format(prices[i])
            ));
        }

        return movieString.toString();
    }

    //GET MOVIE CHOICE
    public static int getMovieChoice(Scanner scanner){

        while(true) {
            try {
                System.out.print("Select a movie by entering the corresponding number(1 - 5): ");
                int choice = Integer.parseInt(scanner.nextLine());
                if(choice < 1 || choice > 5){
                    throw new IllegalArgumentException("Enter a number between 1 and 5.");
                }
                return choice - 1;
            } catch (IllegalArgumentException e) {
                System.out.println("Enter a number between 1 and 5.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getTicketCount(Scanner scanner){
        while(true) {
            try {
                System.out.print("Enter number of required tickets: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //CALCULATE TOTAL TICKET PRICE
    public static double calcTotalPrice(int movieChoice, double[] prices, int tickets ){
        return prices[movieChoice] * tickets;
    }
}