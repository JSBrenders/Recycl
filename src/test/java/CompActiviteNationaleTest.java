import classes.CompActiviteSite;
import oracle.ucp.common.waitfreepool.Tuple;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CompActiviteNationaleTest {

    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2018");
    Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019");

    Tuple tuple = new Tuple(date1, date2);
    CompActiviteSite compActiviteSiteLille = new CompActiviteSite(1,1,tuple, "Lille");
    CompActiviteSite compActiviteSiteParis = new CompActiviteSite(1,1,tuple, "Paris");

    CompActiviteNationaleTest() throws ParseException {
    }

    @Test
    public void testNbDechetLille() {
        System.out.println("Inside testPrintMessage()");

        assertEquals(270, compActiviteSiteLille.getNbDechet());

    }

    @Test
    public void testNbDechetParis() {
        System.out.println("Inside testPrintMessage()");

        assertEquals(415, compActiviteSiteParis.getNbDechet());

    }
}