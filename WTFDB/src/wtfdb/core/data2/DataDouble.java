package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataDouble extends Data<Double>
{
    protected DataDouble()
    {
        super();
    }

    protected DataDouble(double value)
    {
        super(value);
    }
      
    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(DOUBLE);
        output.writeDouble(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readDouble();
    }
}
