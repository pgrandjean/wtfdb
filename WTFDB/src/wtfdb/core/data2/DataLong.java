package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(LONG);
        output.writeLong(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readLong();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('l');
    }
}
