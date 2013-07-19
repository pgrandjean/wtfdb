package wtfdb.core.data2;

import java.io.IOException;

import wtfdb.core.io.DataBuffer;

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
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(BYTE_ARRAY);
        buffer.writeInt(value.length);
        buffer.write(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        int size = buffer.readInt();
        byte[] bytes = new byte[size];
        buffer.readFully(bytes);
        value = bytes;
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append("<byte array>");
    }
}
