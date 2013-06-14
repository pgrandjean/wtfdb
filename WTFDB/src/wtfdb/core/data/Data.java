package wtfdb.core.data;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Data implements Iterable<Entry<String, Object>>
{
    private Map<String, Object> fields = new LinkedHashMap<String, Object>();
    
    public Object get(String key)
    {
        return fields.get(key);
    }

    public boolean isEmpty()
    {
        return fields.isEmpty();
    }
    
    @Override
    public boolean equals(Object that)
    {
        if (that == null) return false;
        
        if (!getClass().equals(that.getClass())) return false;
        
        Data thatData = (Data) that;
        Set<String> thisKeys = fields.keySet();
        Set<String> thatKeys = thatData.fields.keySet();
        
        if (!thisKeys.containsAll(thatKeys) || !thatKeys.containsAll(thisKeys)) return false;
        
        boolean equals = true;
        for (Entry<String, Object> entry : this)
        {
            equals = equals && fields.get(entry.getKey()).equals(entry.getValue());
            if (!equals) break;
        }
        
        return equals;
    }
    
    @Override
    public Iterator<Entry<String, Object>> iterator()
    {
        return fields.entrySet().iterator();
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
