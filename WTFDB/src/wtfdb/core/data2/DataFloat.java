package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import wtfdb.core.io.DataBuffer;

public class DataFloat extends Data<Float>
{
    protected DataFloat()
    {
        super();
    }

    protected DataFloat(float value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataFloat)) return false;
        
        DataFloat that = (DataFloat) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(FLOAT);
        buffer.writeFloat(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        value = buffer.readFloat();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('f');
    }
}
