// Jordan Cazamias
// KeyValuePair.java: Helper class to store keys and values together

import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class KeyValuePair {
    private String _key;
    private Object _value;

    // Constructors
    public KeyValuePair(String key, Object value) {
        _key = key;
        _value = value;
    }


//    public KeyValuePair(KeyValuePair kvp) {
//        _key = kvp.getKey();
//        value = kvp.getValue();
//    }

    // Getters
    public String getKey() {
        return _key;
    }

    public Object getValue() {
        return _value;
    }

    // Setters
    public void setValue(Object newVal) { _value = newVal; }

    // Object overrides
    @Override
    public boolean equals(Object otherObj) {
        if(otherObj == this) {
            return true;
        }
        if(otherObj == null || otherObj.getClass() != this.getClass()) {
            return false;
        }
        KeyValuePair other = (KeyValuePair) otherObj;

        if(_key == null && _value == null) {
            return other.getKey() == null && other.getValue() == null;
        }
        else if (_key == null) {
            return other.getKey() == null && _value.equals(other.getValue());
        }
        else if (_value == null) {
            return _key.equals(other.getKey()) && other.getValue() == null;
        }
        else {
            return _key.equals(other.getKey()) && _value.equals(other.getValue());
        }
    }

    @Override
    public String toString() {
        if(_value == null) {
            return "(" + _key + ": null)";
        }
        return "(" + _key + ": " + _value.toString() + ")";
    }

}