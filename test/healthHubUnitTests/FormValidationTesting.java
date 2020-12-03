package healthHubUnitTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FormValidationTesting {
    /**
     * Comes from textField input
     * Used in:
     * - ClientSignUpController x 1
     * - InstructorSignUpController x 1
     * - ManagerSignUpController x 1
     * - OrganizationSignUpConrtoller x2
     *
     * @param name string
     * @return false if input is wrong
     */
    public boolean testName(String name) {
        //regex looks for a string space string, meaning users must enter a first and last name
        return (!(name.length() > 0) || name.matches("^\\s+$"));
    }

    /**
     * Comes from textField input
     * Used in:
     * - ClientSignUpController x 1
     * - InstructorSignUpController x 1
     * - ManagerSignUpController x 1
     * - OrganizationSignUpConrtoller x1
     *
     * @param ageString string
     * @return false if input is wrong or trouble parsing the int
     */
    public boolean testAge(String ageString) {
        int age;
        try {
            age = Integer.parseInt(ageString);
            return (!(age > 0) || !(age < 150));
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Comes from textField input
     * Used in:
     * - ClientSignUpController x 1
     * - InstructorSignUpController x 1
     * - ManagerSignUpController x 1
     * - OrganizationSignUpConrtoller x1
     *
     * @param email string
     * @return false if input is wrong
     */
    public boolean testEmail(String email) {
        //check to see if its anything@anything.anyting
        return (!(email.length() > 0) || !email.matches("^.+[@].+[.].+$"));

    }

    /**
     * Comes from textField input
     * Used in:
     * - ClientSignUpController x 1
     * - InstructorSignUpController x 1
     * - ManagerSignUpController x 1
     * - OrganizationSignUpConrtoller x1
     *
     * @param passWord string
     * @return false if input is wrong
     */
    public boolean testPassWord(String passWord) {
        return (!(passWord.length() > 5) || !passWord.matches("^(\\w|\\D|\\d|\\W)*$") || passWord.matches("^\\s*$"));
    }

    /**
     * All tests translates to when conditions are True throw an error (error dialog), false keep going
     */
    @Test
    void nameInput() {
        assertFalse(testName("Dustin Crosson"));
        assertFalse(testName("Dustin C"));
        assertFalse(testName("D C"));
        assertFalse(testName("D Crosson"));
        assertFalse(testName("larry Stevenson"));
        assertFalse(testName("Mark le de Vu"));
        assertFalse(testName("Larry"));
        assertFalse(testName("StevingtonBarry"));
        assertTrue(testName("  "));
        assertTrue(testName(""));


    }

    @Test
    void ageInput() {
        assertFalse(testAge("1"));
        assertFalse(testAge("25"));
        assertFalse(testAge("149"));
        assertTrue(testAge("  "));
        assertTrue(testAge("larry"));
        assertTrue(testAge("0"));
        assertTrue(testAge("-5"));
        assertTrue(testAge("150"));
        assertTrue(testAge("1000000"));
    }

    @Test
    void testEmail() {
        assertFalse(testEmail("dcrosson@gmail.com"));
        assertFalse(testEmail("burt@usask.ca"));
        assertFalse(testEmail("steve@larry.org"));
        assertFalse(testEmail("c@c.c"));
        assertTrue(testEmail("larry.ca"));
        assertTrue(testEmail("burt@ernie"));
        assertTrue(testEmail("       "));
        assertTrue(testEmail(""));
    }

    @Test
    void testPassWord() {
        assertFalse(testPassWord("123456"));
        assertFalse(testPassWord("test21"));
        assertFalse(testPassWord("11111111111111111111111111111111111"));
        assertFalse(testPassWord("test22adsfklj234klja323"));
        assertTrue(testPassWord("larry"));
        assertTrue(testPassWord("e"));
        assertTrue(testPassWord("      "));
        assertTrue(testPassWord("                               "));
        assertTrue(testPassWord("                       "));
        assertTrue(testPassWord(""));
    }


}
