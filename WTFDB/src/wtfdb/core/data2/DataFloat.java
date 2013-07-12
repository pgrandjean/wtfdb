package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(FLOAT);
        output.writeFloat(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readFloat();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('f');
    }
}
