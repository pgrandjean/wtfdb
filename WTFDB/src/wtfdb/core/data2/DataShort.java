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
}
