package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DataMap extends Data<Map<String, Data<?>>>
{
    public DataMap()
    {
        super(new LinkedHashMap<String, Data<?>>());
    }
    
    @SuppressWarnings("unchecked")
    private <T> T get(String k)
    {
        Data<T> v = (Data<T>) value.get(k);
        
        if (v == null) return null;
        else return v.value;
    }

    protected void set(String k, Data<?> v)
    {
        v.parent = v;
        value.put(k, v);
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataMap)) return false;
        
        DataMap that = (DataMap) o;
        
        return this.value.equals(that.value);
    }
    
    public Boolean getBoolean(String k)
    {
        return get(k);
    }

    public Byte getByte(String k)
    {
        return get(k);
    }

    public Short getShort(String k)
    {
        return get(k);
    }
    
    public Integer getInteger(String k)
    {
        return get(k);
    }
    
    public Long getLong(String k)
    {
        return get(k);
    }
    
    public Float getFloat(String k)
    {
        return get(k);
    }
    
    public Double getDouble(String k)
    {
        return get(k);
    }
    
    public Character getChar(String k)
    {
        return get(k);
    }
    
    public String getString(String k)
    {
        return get(k);
    }
    
    public byte[] getByteArray(String k)
    {
        return get(k);
    }
    
    public Date getDate(String k)
    {
        return get(k);
    }
    
    public DataArray getDataArray(String k)
    {
        return (DataArray) value.get(k);
    }
    
    public DataMap getDataMap(String k)
    {
        return (DataMap) value.get(k);
    }
    
    public void set(String k, boolean v)
    {
        set(k, new DataBoolean(v));
    }

    public void set(String k, byte v)
    {
        set(k, new DataByte(v));
    }

    public void set(String k, short v)
    {
        set(k, new DataShort(v));
    }

    public void set(String k, int v)
    {
        set(k, new DataInteger(v));
    }

    public void set(String k, long v)
    {
        set(k, new DataLong(v));
    }

    public void set(String k, float v)
    {
        set(k, new DataFloat(v));
    }

    public void set(String k, double v)
    {
        set(k, new DataDouble(v));
    }

    public void set(String k, char v)
    {
        set(k, new DataChar(v));
    }

    public void set(String k, String v)
    {
        set(k, new DataString(v));
    }

    public void set(String k, byte[] v)
    {
        set(k, new DataByteArray(v));
    }

    public void set(String k, Date v)
    {
        set(k, new DataDate(v));
    }

    public void set(String k, DataArray v)
    {
        v.parent = this;
        this.value.put(k, v);
    }

    public void set(String k, DataMap v)
    {
        v.parent = this;
        this.value.put(k, v);
    }
    
    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(DATA);
        output.writeInt(value.size());
        
        for (Entry<String, Data<?>> entry : value.entrySet())        
        {
            output.writeUTF(entry.getKey());
            entry.getValue().serialize(output);
        }
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        int size = input.readInt();
        for (int i = 0; i < size; i++)
        {
            String key = input.readUTF();
            
            byte type = input.readByte();
            Data<?> value = deserialize(type, input);
            set(key, value);
        }
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        toString(buffer);
        
        return buffer.toString();
    }
    
    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append("{ ");

        int i = 0; int size = value.size();
        for (Entry<String, Data<?>> entry : value.entrySet())        
        {
            buffer.append('"').append(entry.getKey()).append("\": ");
            entry.getValue().toString(buffer);
            
            if (i++ != size - 1) buffer.append(", ");
        }
        
        buffer.append(" }");
    }
}
