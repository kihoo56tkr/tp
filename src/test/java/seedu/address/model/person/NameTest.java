package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Name(""));
        assertThrows(IllegalArgumentException.class, () -> new Name("   "));
        String tooLong = "a".repeat(101);
        assertThrows(IllegalArgumentException.class, () -> new Name(tooLong));
    }

    @Test
    public void isValidName() {
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("   ")); // multiple spaces only

        String tooLong = "a".repeat(101);
        assertFalse(Name.isValidName(tooLong));

        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names (under 100)

        assertTrue(Name.isValidName("John/Doe")); // with slash
        assertTrue(Name.isValidName("O'Connor")); // with apostrophe
        assertTrue(Name.isValidName("Jean-Luc")); // with hyphen
        assertTrue(Name.isValidName("Johnson & Sons")); // with ampersand
        assertTrue(Name.isValidName(" John Doe")); // starts with space
        assertTrue(Name.isValidName("peter*")); // with asterisk
        assertTrue(Name.isValidName("^")); // only special characters (if it's 1 char, under 100)
        assertTrue(Name.isValidName("!@#$%^&*()")); // all special characters

        String exactly100 = "a".repeat(100);
        assertTrue(Name.isValidName(exactly100));
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
