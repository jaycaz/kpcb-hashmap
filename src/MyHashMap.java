// Jordan Cazamias
// MyHashMap.java : concrete implementation for hash map

/**
 * MyHashMap - concrete implementation of hash map
 * Uses linear probing (step = 1) for collision resolution
 */
public class MyHashMap extends AbstractHashMap {

    private int size;
    private int numEntries;
    private KeyValuePair[] table;

    // Null string is reserved for the special "deleted" entry
    public static final KeyValuePair DELETED = new KeyValuePair(null, null);

    public MyHashMap(int sz) {
        super(sz);

        size = sz;
        // TODO: experiment with different table sizes; if so, change instances of size below
        table = new KeyValuePair[sz];
    }

    @Override
    public boolean set(String key, Object value) {
        if(key == null) {
            return false;
        }

        // Perform linear probing
        int index = getHash(key);
        int numProbes = 0;
        KeyValuePair entry = table[index];

        while(entry != null && !entry.getKey().equals(key)) {
            index = (index + 1) % size;
            numProbes++;

            if(numProbes == size) {
                // Hash table is full, return failure
                return false;
            }

            entry = table[index];
        }

        if(entry == null) {
            // No previous entry with same key; create new entry
            table[index] = new KeyValuePair(key, value);
            numEntries++;
        } else {
            // Previous entry found with same key; overwrite value
            table[index].setValue(value);
        }
        return true;
    }

    @Override
    public Object get(String key) {

        KeyValuePair entry = getEntry(key);
        if(entry == null || entry == MyHashMap.DELETED) {
            return null;
        }

        return entry.getValue();
    }

    @Override
    public Object delete(String key) {
        int index = getIndex(key);
        if(index < 0) {
            return null;
        }

        // Entry found, mark table slot as DELETED
        KeyValuePair entry = table[index];
        table[index] = MyHashMap.DELETED;
        numEntries--;

        return entry.getValue();
    }

    @Override
    public float load() {
        return (float) numEntries / size;
    }

    /**
     * Helper function: searches table for key and returns entire KeyValuePair
     * @return KeyValuePair entry if found, null otherwise
     */
    private KeyValuePair getEntry(String key) {
        int index = getIndex(key);
        if(index < 0) {
            return null;
        }

        KeyValuePair entry = table[index];
        return entry;
    }

    /**
     * Helper function: finds value with associated key and gives index
     * @return index of KeyValuePair if present in the table, -1 otherwise
     */
    private int getIndex(String key) {
        int index = getHash(key);
        int numProbes = 0;

        // Perform Linear probing
        KeyValuePair entry = table[index];
        Object value;
        while(entry != null) {
            entry = table[index];
            if(entry != null && entry.getKey().equals(key)) {
                // Entry found, return value
                return index;
            }

            index = (index + 1) % size;
            numProbes++;

            // Entry not found and table is full
            if(numProbes == size) {
                return -1;
            }
        }

        // Entry not found
        return -1;
    }

    /**
     * Hash function for table
     * @param str value of string to be hashed
     * @return hash value in range [0, size)
     */
    public int getHash(String str) {
        if(str == null) {
            return 0;
        }
        int hash = str.hashCode() % size;
        if(hash < 0) {
            hash += size;
        }

        return hash;
    }

}