package wtfdb.core.data2;

import java.io.IOException;

import wtfdb.core.io.DataBuffer;

public class DataShort extends Data<Short>
{
    protected DataShort()
    {
        super();
    }

    protected DataShort(short value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataShort)) return false;
        
        DataShort that = (DataShort) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(SHORT);
        buffer.writeShort(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        value = buffer.readShort();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value).append('s');
    }
}
