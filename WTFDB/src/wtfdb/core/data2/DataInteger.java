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
}
