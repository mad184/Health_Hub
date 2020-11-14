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

    /**
     * Tests getId method.
     */
    @Test
    public void testGetID() {
        assertEquals(1, user.getId());
    }

    /**
     * Tests getName method.
     */
    @Test
    public void testGetName() {
        assertEquals("John Wick", user.getName());
    }

    /**
     * Tests setId method.
     */
    @Test
    public void testSetID() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    /**
     * Tests setName method.
     */
    @Test
    public void testSetName() {
        user.setName("Charles Xavier");
        assertEquals("Charles Xavier", user.getName());
    }
}
