package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataShort extends Data<Short>
{
    protected DataShort()
    {
        super();
    }

    protected DataShort(short value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataShort)) return false;
        
        DataShort that = (DataShort) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(SHORT);
        output.writeShort(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readShort();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('s');
    }
}
