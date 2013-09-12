package wtfdb.core.io;

import java.util.HashMap;
import java.util.Map;

public class IODictionary
{
    private Map<String, Short> keys = new HashMap<>();
    
    private Map<Short, String> strings = new HashMap<>();
 
    private short key = 1;
    
    private IOBuffer buffer = null;
    
    public IODictionary(String db, String col)
    {
        buffer = new IOBuffer(db + "." + col + ".dictionary.wtfdb");
        
        short key = 0;
        while ((key = buffer.readShort()) != 0)
        {
            String value = buffer.readUTF();
            
            set(key, value, false);
        }
    }
    
    private void set(short key, String value, boolean write)
    {
        if (write)
        {
            buffer.writeShort(key);
            buffer.writeUTF(value);
        }
        
        keys.put(value, key);
        strings.put(key, value);
    }
    
    private short next()
    {
        return key++;
    }
    
    protected short add(String string)
    {
        short key = next();
        
        set(key, string, true);
        
        return key;
    }

    public void close()
    {
        buffer.close();
    }
    
    public short getKey(String string)
    {
        Short key = keys.get(string);
        if (key == null)
        {
            key = add(string);
        }
        
        return key;
    }
    
    public String getString(short key)
    {
        return strings.get(key);
    }
}
