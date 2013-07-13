package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import wtfdb.core.io.DataBuffer;

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
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(DOUBLE);
        buffer.writeDouble(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        value = buffer.readDouble();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('d');
    }
}
