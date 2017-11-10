package refactoring.videorental;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    @Test
    public void rentalRegularMovieForAWeek() {
        Movie movie = new Movie("Attack of the Killer Tomatoes!", Movie.REGULAR);
        Rental rental = new Rental(movie,7);
        Customer customer = new Customer("Mike");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Mike\n" +
                "\tAttack of the Killer Tomatoes!\t9.5\n" +
                "Amount owed is 9.5\n" +
                "You earned 1 frequent renter points",result);
    }
    @Test
    public void rentalChildrensMovieForTwoWeeks() {
        Movie movie = new Movie("PUELLA MAGI MADOKA MAGICA", Movie.CHILDRENS);
        Rental rental = new Rental(movie,14);
        Customer customer = new Customer("John");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for John\n" +
                "\tPUELLA MAGI MADOKA MAGICA\t16.5\n" +
                "Amount owed is 16.5\n" +
                "You earned 1 frequent renter points",result);
    }
    @Test
    public void rentalNewReleaseMovieForADay() {
        Movie movie = new Movie("The Return of the Living Dead", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie,1);
        Customer customer = new Customer("Nancy");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Nancy\n" +
                "\tThe Return of the Living Dead\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points",result);
    }
    @Test
    public void rentalNewReleaseMovieTwoDays() {
        Movie movie = new Movie("The Return of the Living Dead Part II", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie,2);
        Customer customer = new Customer("Nancy");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Nancy\n" +
                "\tThe Return of the Living Dead Part II\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points",result);
    }
}
