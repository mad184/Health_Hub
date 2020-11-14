package staff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing class for UserID objects.
 */
public class UserIDTest {
    UserID user = new UserID(1, "John Wick");

    /**
     * Tests that the constructor works properly.
     */
    @Test
    public void testConstructor() {
        assertNotNull(user);
    }

}
