package wtfdb.core.data2;

import java.io.IOException;

import wtfdb.core.io.DataBuffer;

public class DataInteger extends Data<Integer>
{
    protected DataInteger()
    {
        super();
    }

    protected DataInteger(int value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataInteger)) return false;
        
        DataInteger that = (DataInteger) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(INTEGER);
        buffer.writeInt(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        value = buffer.readInt();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value);
    }
}
