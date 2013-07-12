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
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataDouble)) return false;
        
        DataDouble that = (DataDouble) o;
        
        return this.value.equals(that.value);
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

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('d');
    }
}
