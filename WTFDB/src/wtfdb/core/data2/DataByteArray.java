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
        
        if (value == null) throw new NullPointerException();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataByteArray)) return false;
        
        DataByteArray that = (DataByteArray) o;
        
        for (int i = 0; i < this.value.length; i++)
        {
            if (this.value[i] != that.value[i]) return false;
        }
        
        return true;
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

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append("<byte array>");
    }
}
