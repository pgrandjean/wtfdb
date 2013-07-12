package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataChar extends Data<Character>
{
    protected DataChar()
    {
        super();
    }

    protected DataChar(char value)
    {
        super(value);
    }

    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(CHAR);
        output.writeChar(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readChar();
    }
}
