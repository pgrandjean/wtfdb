package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataByte extends Data<Byte>
{
    protected DataByte()
    {
        super();
    }

    protected DataByte(byte value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataByte)) return false;
        
        DataByte that = (DataByte) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(BYTE);
        output.writeByte(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readByte();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('b');
    }
}
