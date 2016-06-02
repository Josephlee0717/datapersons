package co.datapersons.utils;

import java.util.HashMap;
import java.util.Map;

public class CaseInsensitiveHashMap extends HashMap<String, Object>
{
    private final Map<String,String> lowerCaseMap = new HashMap<String,String>();

    /**
     * Required for serialization support.
     * 
     * @see java.io.Serializable
     */ 
    private static final long serialVersionUID = -2848100435296897392L;

    /** {@inheritDoc} */
    @Override
    public boolean containsKey(Object key) {
        Object realKey = lowerCaseMap.get(key.toString().toLowerCase());
        return super.containsKey(realKey);
    }

    /** {@inheritDoc} */
    @Override
    public Object get(Object key) {
        Object realKey = lowerCaseMap.get(key.toString().toLowerCase());
        return super.get(realKey);
    }

    /** {@inheritDoc} */
    @Override
    public Object put(String key, Object value) {
        Object oldKey = lowerCaseMap.put(key.toLowerCase(), key);
        Object oldValue = super.remove(oldKey);
        super.put(key, value);
        return oldValue;
    }

    /** {@inheritDoc} */
    @Override
    public void putAll(Map<? extends String,?> m) {
        for (Map.Entry<? extends String, ?> entry : m.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            this.put(key, value);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Object remove(Object key) {
        Object realKey = lowerCaseMap.remove(key.toString().toLowerCase());
        return super.remove(realKey);
    }
}
