package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataByteArray extends Data<byte[]>
{
    protected DataByteArray()
    {
        super();
    }

    protected DataByteArray(byte[] value)
    {
        super(value);
    }
    
    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(BYTE_ARRAY);
        output.writeInt(value.length);
        output.write(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        int size = input.readInt();
        byte[] bytes = new byte[size];
        input.read(bytes);
        value = bytes;
    }
}
