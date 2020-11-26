package healthhubTests.unit;

import healthhub.HealthHub;

import staff.Manager;
import healthhub.HealthHubAccessSingleton;

import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HealthHubAccessSingletonTest {

        //attributes for testing
        String owner;
        HealthHub organization;

        @Rule
        public final ExpectedException expectedException = ExpectedException.none();

        // try to get an instance of an organization that hasn't been created yet, expect a RuntimeException

        @Test
        @Order(1)
        void getObjectNotCreated() {
            assertThrows(RuntimeException.class, () -> {
                organization = HealthHubAccessSingleton.getHealthHub();
            });
        }
        @Test
        @Order(2)
        void testIfOrganizationIsCreated1(){
            assertFalse(HealthHubAccessSingleton.isOrganizationCreated());
        }

        @Test
        @Order(3)
        void testObjectCreation(){
            owner = "dustin";
            assertDoesNotThrow(()->{
                HealthHubAccessSingleton.newOrganziation("MyGym", owner);
            });
        }

        @Test
        @Order(4)
        void testCreatedObjectsName(){
            assertEquals("MyGym", HealthHubAccessSingleton.getOrganizationName());
        }

        @Test
        @Order(5)
        void testCreatedObjectsOwner(){
            assertEquals(HealthHubAccessSingleton.getOwner(),"dustin");
        }

        @Test
        @Order(6)
        void testIfOrganizationIsCreated2(){
            assertTrue(HealthHubAccessSingleton.isOrganizationCreated());
        }

        @Test
        @Order(7)
        void createMultipleOrganizations() {
            String owner2 = "Bob";
            assertThrows(RuntimeException.class, () -> {
                HealthHubAccessSingleton.newOrganziation("Golds", owner2);
            });
        }
}
