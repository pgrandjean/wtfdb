package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataString extends Data<String>
{
    protected DataString()
    {
        super();
    }

    protected DataString(String value)
    {
        super(value);
    }

    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(STRING);
        output.writeUTF(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readUTF();
    }
}
