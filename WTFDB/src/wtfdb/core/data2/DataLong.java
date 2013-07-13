package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import wtfdb.core.io.DataBuffer;

public class DataLong extends Data<Long>
{
    protected DataLong()
    {
        super();
    }

    protected DataLong(long value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataLong)) return false;
        
        DataLong that = (DataLong) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(LONG);
        buffer.writeLong(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        value = buffer.readLong();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('l');
    }
}
