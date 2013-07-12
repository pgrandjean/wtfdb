package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataBoolean extends Data<Boolean>
{
    protected DataBoolean()
    {
        super();
    }

    protected DataBoolean(boolean value)
    {
        super(value);
    }

    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(BOOLEAN);
        output.writeBoolean(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readBoolean();
    }
}
