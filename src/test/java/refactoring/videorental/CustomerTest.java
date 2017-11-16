package refactoring.videorental;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    @Test
    public void rentalRegularMovieForAWeek() throws IllegalAccessException {
        Movie movie = new Movie("Attack of the Killer Tomatoes!", Movie.REGULAR);
        Rental rental = new Rental(movie,7);
        Customer customer = new Customer("Mike");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Mike\n" +
                "\tAttack of the Killer Tomatoes!\t9.5\n" +
                "Amount owed is 9.5\n" +
                "You earned 1 frequent renter points",result);

        result = customer.htmlStatement();
        assertEquals("<H1>Rental Record for <EM>Mike</EM></H1><P>\n" +
                "Attack of the Killer Tomatoes!: 9.5<BR>\n" +
                "<P>Amount owed <EM> 9.5</EM><P>\n" +
                "On this rental you earned <EM>1</EM> frequent renter points<P>",result);
    }
    @Test
    public void rentalChildrensMovieForTwoWeeks() throws IllegalAccessException {
        Movie movie = new Movie("PUELLA MAGI MADOKA MAGICA", Movie.CHILDRENS);
        Rental rental = new Rental(movie,14);
        Customer customer = new Customer("John");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for John\n" +
                "\tPUELLA MAGI MADOKA MAGICA\t18.0\n" +
                "Amount owed is 18.0\n" +
                "You earned 1 frequent renter points",result);

        result = customer.htmlStatement();
        assertEquals("<H1>Rental Record for <EM>John</EM></H1><P>\n" +
                "PUELLA MAGI MADOKA MAGICA: 18.0<BR>\n" +
                "<P>Amount owed <EM> 18.0</EM><P>\n" +
                "On this rental you earned <EM>1</EM> frequent renter points<P>",result);
    }
    @Test
    public void rentalNewReleaseMovieForADay() throws IllegalAccessException {
        Movie movie = new Movie("The Return of the Living Dead", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie,1);
        Customer customer = new Customer("Nancy");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Nancy\n" +
                "\tThe Return of the Living Dead\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points",result);

        result = customer.htmlStatement();
        assertEquals("<H1>Rental Record for <EM>Nancy</EM></H1><P>\n" +
                "The Return of the Living Dead: 3.0<BR>\n" +
                "<P>Amount owed <EM> 3.0</EM><P>\n" +
                "On this rental you earned <EM>1</EM> frequent renter points<P>",result);
    }
    @Test
    public void rentalNewReleaseMovieTwoDays() throws IllegalAccessException {
        Movie movie = new Movie("The Return of the Living Dead Part II", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie,2);
        Customer customer = new Customer("Nancy");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Nancy\n" +
                "\tThe Return of the Living Dead Part II\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points",result);

        result = customer.htmlStatement();
        assertEquals("<H1>Rental Record for <EM>Nancy</EM></H1><P>\n" +
                "The Return of the Living Dead Part II: 6.0<BR>\n" +
                "<P>Amount owed <EM> 6.0</EM><P>\n" +
                "On this rental you earned <EM>2</EM> frequent renter points<P>",result);
    }
}
