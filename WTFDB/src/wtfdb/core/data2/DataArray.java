package wtfdb.core.data2;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import wtfdb.core.io.DataBuffer;

public class DataArray extends Data<List<Data<?>>>
{
    public DataArray()
    {
        super(new Vector<Data<?>>());
    }

    protected DataArray(DataArray parent)
    {
        super(new Vector<Data<?>>(), parent);
    }

    protected DataArray(DataMap parent)
    {
        super(new Vector<Data<?>>(), parent);
    }

    protected void add(Data<?> v)
    {
        v.parent = this;
        value.add(v);
    }

    @SuppressWarnings("unchecked")
    private <T> T get(int i)
    {
        if (i >= value.size()) return null;
        
        Data<T> v = (Data<T>) value.get(i);
        
        return v.value;
    }

    public void add(boolean v)
    {
        add(new DataBoolean(v));
    }

    public void add(byte v)
    {
        add(new DataByte(v));
    }

    public void add(short v)
    {
        add(new DataShort(v));
    }

    public void add(int v)
    {
        add(new DataInteger(v));
    }

    public void add(long v)
    {
        add(new DataLong(v));
    }

    public void add(float v)
    {
        add(new DataFloat(v));
    }

    public void add(double v)
    {
        add(new DataDouble(v));
    }

    public void add(char v)
    {
        add(new DataChar(v));
    }

    public void add(String v)
    {
        add(new DataString(v));
    }

    public void add(byte[] v)
    {
        add(new DataByteArray(v));
    }

    public void add(Date v)
    {
        add(new DataDate(v));
    }

    public void add(DataArray v)
    {
        v.parent = this;
        this.value.add(v);
    }

    public void add(DataMap v)
    {
        v.parent = this;
        this.value.add(v);
    }

    public Boolean getBoolean(int i)
    {
        return get(i);
    }

    public Byte getByte(int i)
    {
        return get(i);
    }

    public Short getShort(int i)
    {
        return get(i);
    }
    
    public Integer getInteger(int i)
    {
        return get(i);
    }
    
    public Long getLong(int i)
    {
        return get(i);
    }
    
    public Float getFloat(int i)
    {
        return get(i);
    }
    
    public Double getDouble(int i)
    {
        return get(i);
    }
    
    public Character getChar(int i)
    {
        return get(i);
    }
    
    public String getString(int i)
    {
        return get(i);
    }
    
    public byte[] getByteArray(int i)
    {
        return get(i);
    }
    
    public Date getDate(int i)
    {
        return get(i);
    }
    
    public DataArray getDataArray(int i)
    {
        return (DataArray) value.get(i);
    }
    
    public DataMap getDataMap(int i)
    {
        return (DataMap) value.get(i);
    }
    
    public void remove(int i)
    {
        value.remove(i);
    }
    
    public int size()
    {
        return value.size();
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataArray)) return false;
        
        DataArray that = (DataArray) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(ARRAY);
        buffer.writeInt(value.size());
        
        for (int i = 0; i < value.size(); i++)
        {
            value.get(i).serialize(buffer);
        }
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        int size = buffer.readInt();
        for (int i = 0; i < size; i++)
        {
            byte type = buffer.readByte();
            Data<?> value = deserialize(type, buffer);
            add(value);
        }
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append("[ ");

        int size = value.size();
        for (int i = 0; i < size; i++)
        {
            value.get(i).toString(buffer);
            if (i != size) buffer.append(", ");
        }
        
        buffer.append(" ]");
    }
}
