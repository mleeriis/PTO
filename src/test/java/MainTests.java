import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void connectionSuccessful() {
        assertTrue(Main.connection());
    }


}
