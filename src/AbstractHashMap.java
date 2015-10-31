// Jordan Cazamias
// AbstractHashMap.java - Abstract class for custom HashMap

import java.lang.IllegalArgumentException;

public abstract class AbstractHashMap {

    /**
     * Main constructor for map; ensures size is a valid value
     * @param size the number of slots that will be pre-allocated
     * @throws IllegalArgumentException if size is not positive
     */
    public AbstractHashMap(int size) throws IllegalArgumentException {
        if(size < 0) {
            throw new IllegalArgumentException("Invalid size for hash map: cannot be negative");
        }
        else if(size == 0) {
            throw new IllegalArgumentException("Invalid size for hash map: cannot be zero");
        }
    }

    /**
     * Sets the value in the map with the specified key to the specified value.
     * Note: if a key/value pair exists with the same key, this will overwrite the old value
     * @param key key of entry to search for
     * @param value value to set new entry to
     * @return true if key/value pair was successfully set, false otherwise
     */
    public abstract boolean set(String key, Object value);

    /**
     * Retrieves the value in the map with the specified key, if it exists
     * @param key key of entry to search for
     * @return value if found, null otherwise
     */
    public abstract Object get(String key);

    /**
     * Deletes the specified object from the map,
     * if it exists, and returns its reference
     * @param key key of entry to search for
     * @return object reference if found, null otherwise
     */
    public abstract Object delete(String key);

    /**
     * Returns the load factor of the map, i.e. (items in map) / (size of map)
     * @return load factor alpha (0 &lt;= alpha &lt;= 1)
     */
    public abstract float load();
}