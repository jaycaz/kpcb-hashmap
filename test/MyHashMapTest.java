import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Test Cases for MyHashMap
 */
public class MyHashMapTest {

    private final static double EPSILON = 1E-6;
    private final static int SIZE = 10;
    Random rand;

    MyHashMap map;

    @Before
    public void setUp() throws Exception {
        rand = new Random();
        rand.setSeed(0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructor() throws Exception {

        // Size zero : constructor should throw IllegalArgumentException
        try {
            MyHashMap zeroMap = new MyHashMap(0);
            fail("IllegalArgumentException not thrown for constructing zero-size MyHashMap");
        }
        catch (IllegalArgumentException e) {}

        // Negative size: constructor should throw IllegalArgumentException
        try {
            MyHashMap zeroMap = new MyHashMap(-1);
            fail("IllegalArgumentException not thrown for constructing negative-size MyHashMap");
        }
        catch (IllegalArgumentException e) {}

        // Positive size: constructor should not throw exceptions
        try {
            MyHashMap validMap = new MyHashMap(SIZE);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Constructor for MyHashMap threw exception when parameters were valid");
        }

    }


    @Test
    public void testSet() throws Exception {
        map = new MyHashMap(SIZE);

        for(int i = 0; i < SIZE; i++) {
            try {
                map.set(Integer.toString(i), valueString(i));
            } catch (AssertionError a) {
                fail("Set failed for index " + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGet() throws Exception {
        map = new MyHashMap(SIZE);

        for (int i = 0; i < SIZE; i++) {
            try {
                map.set(Integer.toString(i), valueString(i));
            } catch (AssertionError a) {
                fail("Set failed for index " + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Try to get all values in array
        for (int i = 0; i < SIZE; i++) {
            try {
                Object val = map.get(Integer.toString(i));
                assertEquals(val, valueString(i));
            } catch (Exception e) {
                e.printStackTrace();
                fail("Get failed for index " + i);
            }
        }

        // Delete half the values and try getting the rest
        for (int i = 0; i < SIZE / 2; i++) {
            map.delete(Integer.toString(i));
        }
        for (int i = SIZE / 2 + 1; i < SIZE; i++) {
            try {
                Object val = map.get(Integer.toString(i));
                assertEquals(val, valueString(i));
            } catch (Exception e) {
                e.printStackTrace();
                fail("Get failed for index " + i);
            }
        }
    }

    @Test
    public void testDelete() throws Exception {
        map = new MyHashMap(SIZE);

        for(int i = 0; i < SIZE; i++) {
            map.set(Integer.toString(i), valueString(i));
        }

        for(int i = 0; i < SIZE; i++) {
            try {
                Object val = map.get(Integer.toString(i));
                Object delVal = map.delete(Integer.toString(i));
                assertSame("Object retrieved using 'get' =/= object received using 'delete'", val, delVal);
            }
            catch (Exception e) {
                e.printStackTrace();
                fail("Deletion failed at index " + i);
            }
        }
    }

    @Test
    public void testLoad() throws Exception {
        // Fill up map and then empty it, testing load values along the way
        int numEntries = 0;
        map = new MyHashMap(SIZE);

        assertEquals("Load factor incorrect after map has been instantiated", map.load(), 0.0f, EPSILON);

        for(int i = 0; i < SIZE; i++) {
            map.set(Integer.toString(i), valueString(i));
            numEntries++;
            assertEquals("Load factor incorrect after adding entry " + i, map.load(), (float) numEntries / SIZE, EPSILON);
        }

        for(int i = 0; i < SIZE; i++) {
            map.delete(Integer.toString(i));
            numEntries--;
            assertEquals("Load factor incorrect after deleting entry " + i, map.load(), (float) numEntries / SIZE, EPSILON);
        }

        assertEquals("Load factor incorrect after map has been cleared", map.load(), 0.0f, EPSILON);
    }

    @Test
    public void testGetHash() throws Exception {
        map = new MyHashMap(SIZE);
        assertEquals("Null value does not hash to 0", map.getHash(null), 0);
        assertEquals("Empty string value does not hash to 0", map.getHash(""), 0);

        // Make sure that hash code is within bounds
        for(int i = 0; i < SIZE; i++) {
            int hash = map.getHash(Integer.toString(i));
            assertTrue("Index " + i + " hashes to the value " + hash + ", which is out of bounds (" + SIZE + ")",
                    hash < SIZE);
        }
    }

    public String valueString(int i) {
        return new String("This is entry " + i);
    }
}