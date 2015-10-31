import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Test cases for KeyValuePair
 */
public class KeyValuePairTest {

    KeyValuePair nullEntry;
    KeyValuePair emptyKey;
    KeyValuePair nullKey;
    KeyValuePair entryA;
    KeyValuePair entryB;

    @Before
    public void setUp() throws Exception {
        nullEntry = null;
        emptyKey = new KeyValuePair("", "");
        nullKey = new KeyValuePair(null, null);
        entryA = new KeyValuePair("apple", "This is an apple");
        entryB = new KeyValuePair("banana", "This is a banana");

//        System.out.println("Entry A: " + entryA.toString());
//        System.out.println("Null Key: " + nullKey.toString());
//        System.out.println("Empty Key: " + emptyKey.toString());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetKey() throws Exception {
        assertEquals(emptyKey.getKey(), "");
        assertEquals(nullKey.getKey(), null);
        assertEquals(entryA.getKey(), "apple");

    }

    @Test
    public void testGetValue() throws Exception {
        assertSame(emptyKey.getValue(), "");
        assertEquals(nullKey.getValue(), null);
        assertEquals(entryA.getValue(), "This is an apple");
    }

    @Test
    public void testSetValue() throws Exception {
        String oldKey = "carrot";
        String oldValue = "This is a cnarrot";
        String newValue = "This is a carrot";
        KeyValuePair newEntry = new KeyValuePair(oldKey, oldValue);

        assertEquals(newEntry.getValue(), oldValue);
        // Set to new value
        newEntry.setValue("This is a carrot");
        assertEquals(newEntry.getValue(), newValue);
    }

    @Test
    public void testEquals() throws Exception {
        assert(!emptyKey.equals(nullEntry));
        assert(!nullKey.equals(nullEntry));
        assert(!entryA.equals(nullEntry));

        assert(!entryA.equals(nullKey));
        assert(!entryA.equals(emptyKey));
        assert(!entryA.equals(entryB));

        assert(!entryA.equals(entryB));
    }
}