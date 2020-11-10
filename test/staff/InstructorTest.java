package staff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.jmock.Mockery;
import org.jmock.Expectations;

public class InstructorTest {
    Mockery context = new Mockery();

    ArrayList<String> clients = new ArrayList<String>();
    final Controller controller = context.mock(Controller.class);
    //Object controller = new Object;
    Instructor instructor1 = new Instructor(
            "Remington",
            22,
            "rar129@usask.ca",
            42,
            175,
            78,
            clients,
            "Average Joes",
            controller,
            1
        );

    @Test
    void testInstructorCreate() {
        assertNotNull(instructor1);
    }

    @Test
    void changeName() {

        instructor1.setName("Rohel");
        assertEquals("Rohel", instructor1.getName());
    }

}