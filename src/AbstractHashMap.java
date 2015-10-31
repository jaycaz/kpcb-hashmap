// Jordan Cazamias
// AbstractHashMap.java - Abstract class for custom HashMap

public abstract class AbstractHashMap {

    /**
     * Main constructor for map
     * @param size the number of slots that will be pre-allocated
     */
    public abstract AbstractHashMap(int size) {}

    /**
     * Sets the value in the map with the specified key to the specified value
     * @param key
     * @param value
     * @return true if key/value pair was successfully set, false otherwise
     */
    public abstract boolean set(String key, Object value) {}

    /**
     * Retrieves the value in the map with the specified key, if it exists
     * @param key
     * @return value if found, null otherwise
     */
    public abstract Object get(String key) {}

    /**
     * Deletes the specified object from the map,
     * if it exists, and returns its reference
     * @param key
     * @return object reference if found, null otherwise
     */
    public abstract Object delete(String key) {}

    /**
     * Returns the load factor of the map, i.e. (items in map) / (size of map)
     * @return load factor alpha (0 <= alpha <= 1)
     */
    public abstract float load() {}

}