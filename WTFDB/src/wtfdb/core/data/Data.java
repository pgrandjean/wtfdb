package wtfdb.core.data;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Data implements Iterable<String>
{
    private Map<String, Object> fields = new LinkedHashMap<String, Object>();
    
    protected Set<Entry<String, Object>> entrySet()
    {
        return fields.entrySet();
    }
    
    public Object get(String key)
    {
        return fields.get(key);
    }

    public boolean isEmpty()
    {
        return fields.isEmpty();
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;
        
        if (!getClass().equals(o.getClass())) return false;
        
        Data that = (Data) o;
        Set<String> thisKeys = this.fields.keySet();
        Set<String> thatKeys = that.fields.keySet();
        
        if (!thisKeys.containsAll(thatKeys) || !thatKeys.containsAll(thisKeys)) return false;
        
        boolean equals = true;
        for (String key : this)
        {
            Object thisValue = this.get(key);
            Object thatValue = that.get(key);
            
            equals = equals && thisValue.equals(thatValue);
            if (!equals) break;
        }
        
        return equals;
    }
    
    @Override
    public Iterator<String> iterator()
    {
        return fields.keySet().iterator();
    }
    
    public void set(String key, Object value)
    {
        if (key == null) throw new NullPointerException("key == null");
        
        if (value == null)
        {
            fields.remove(key);
        }
        else
        {
            fields.put(key, value);
        }
    }
    
    public int size()
    {
        return fields.size();
    }
}
