package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

import wtfdb.core.io.DataBuffer;

public class DataDate extends Data<Date>
{
    protected DataDate()
    {
        super();
    }

    protected DataDate(Date value)
    {
        super(value);
        
        if (value == null) throw new NullPointerException();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataDate)) return false;
        
        DataDate that = (DataDate) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(DATE);
        buffer.writeLong(value.getTime());
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        long time = buffer.readLong();
        value = new Date(time);
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value);
    }
}
