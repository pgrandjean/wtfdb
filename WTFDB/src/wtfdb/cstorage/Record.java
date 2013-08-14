package wtfdb.cstorage;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import wtfdb.core.data.DataBoolean;
import wtfdb.core.data.DataByte;
import wtfdb.core.data.DataByteArray;
import wtfdb.core.data.DataChar;
import wtfdb.core.data.DataDate;
import wtfdb.core.data.DataDouble;
import wtfdb.core.data.DataFloat;
import wtfdb.core.data.DataInteger;
import wtfdb.core.data.DataLong;
import wtfdb.core.data.DataPath;
import wtfdb.core.data.DataPrimitive;
import wtfdb.core.data.DataShort;
import wtfdb.core.data.DataString;

public class Record implements Iterable<Entry<DataPath, DataPrimitive<?>>>
{
    private Map<DataPath, DataPrimitive<?>> columns = new LinkedHashMap<>();
    
    protected Record()
    {
        
    }
    
    @SuppressWarnings("unchecked")
    private <T extends DataPrimitive<?>> T get(DataPath p)
    {
        T data = (T) columns.get(p);
        
        if (data == null)
        {
            for (Entry<DataPath, DataPrimitive<?>> c : columns.entrySet())
            {
                if (c.getKey().matches(p)) return (T) c.getValue();
            }
        }
        
        return data;
    }

    public void clear()
    {
        columns.clear();
    }
    
    public DataBoolean getBoolean(DataPath p)
    {
        return get(p);
    }

    public DataByte getByte(DataPath p)
    {
        return get(p);
    }

    public DataShort getShort(DataPath p)
    {
        return get(p);
    }
    
    public DataInteger getInteger(DataPath p)
    {
        return get(p);
    }
    
    public DataLong getLong(DataPath p)
    {
        return get(p);
    }
    
    public DataFloat getFloat(DataPath p)
    {
        return get(p);
    }
    
    public DataDouble getDouble(DataPath p)
    {
        return get(p);
    }
    
    public DataChar getChar(DataPath p)
    {
        return get(p);
    }
    
    public DataString getString(DataPath p)
    {
        return get(p);
    }
    
    public DataByteArray getByteArray(DataPath p)
    {
        return get(p);
    }
    
    public DataDate getDate(DataPath p)
    {
        return get(p);
    }

    public void set(DataPath p, DataPrimitive<?> v)
    {
        columns.put(p, v);
    }

    public int size()
    {
        return columns.size();
    }
    
    @Override
    public Iterator<Entry<DataPath, DataPrimitive<?>>> iterator()
    {
        return columns.entrySet().iterator();
    }
}
