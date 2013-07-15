package wtfdb.core.data2;

import java.io.IOException;

import wtfdb.core.io.DataBuffer;

public class DataBoolean extends Data<Boolean>
{
    protected DataBoolean()
    {
        super();
    }

    protected DataBoolean(boolean value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataBoolean)) return false;
        
        DataBoolean that = (DataBoolean) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(BOOLEAN);
        buffer.writeBoolean(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        value = buffer.readBoolean();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append(value);
    }
}
