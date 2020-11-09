package Staff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest {
    List<String> clients = new List<String>();
    Object controller = new Object;
    Instructor instructor1 = new Instructor(
            "Remington",
            22,
            "rar129@usask.ca",
            3065555555,
            175,
            78,
            clients,
            "Average Joes",
            controller
        );

    @Test
    void testInstructorCreate() {
        assertNotNull(instructor1)
    }

    @Test
    void changeName() {
        instructor1.setName("Rohel");
        assertEquals("Rohel", instructor1.getName());
    }

}