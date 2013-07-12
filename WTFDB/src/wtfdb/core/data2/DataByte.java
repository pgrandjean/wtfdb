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
}
