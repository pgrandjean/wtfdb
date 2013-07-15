package wtfdb.core.data2;

import java.io.IOException;

import wtfdb.core.io.DataBuffer;

public class DataString extends Data<String>
{
    protected DataString()
    {
        super();
    }

    protected DataString(String value)
    {
        super(value);
        
        if (value == null) throw new NullPointerException();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataString)) return false;
        
        DataString that = (DataString) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(STRING);
        buffer.writeUTF(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        value = buffer.readUTF();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append('"').append(value).append('"');
    }
}
