package staff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ManagerTest {

    Manager manager = new Manager(
            "Jim",
            "jim456@usask.ca",
            "Rick",
            "Gold",
            "3061234556",
            "manager",
            36,
            1225,
            170,
            180,
            3000,
            2000,
            null
            );

    @Test
    void testObjectCreate(){
        assertEquals("Jim", manager.getName());
        assertEquals("jim456@usask.ca", manager.getEmail());
        assertEquals("Rick", manager.getInstructor());
        assertEquals("3061234556", manager.getPhoneNum());
        assertEquals("manager", manager.getPermission());
        assertEquals(36, manager.getAge());
        assertEquals(1225, manager.getId());
        assertEquals(170, manager.getWeight());
        assertEquals(180, manager.getHeight());
        assertEquals(3000, manager.getGoal(), 0.0001);
        assertEquals(2000, manager.getCalories(), 0.001);
        assertNull(manager.getComment());
    }
}