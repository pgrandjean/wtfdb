package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataInteger extends Data<Integer>
{
    protected DataInteger()
    {
        super();
    }

    protected DataInteger(int value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataInteger)) return false;
        
        DataInteger that = (DataInteger) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(INTEGER);
        output.writeInt(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readInt();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value);
    }
}
