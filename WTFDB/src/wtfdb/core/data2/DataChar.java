package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import wtfdb.core.io.DataBuffer;

public class DataChar extends Data<Character>
{
    protected DataChar()
    {
        super();
    }

    protected DataChar(char value)
    {
        super(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataChar)) return false;
        
        DataChar that = (DataChar) o;
        
        return this.value.equals(that.value);
    }
    
    @Override
    public void serialize(DataBuffer buffer) throws IOException
    {
        buffer.writeByte(CHAR);
        buffer.writeChar(value);
    }

    @Override
    public void deserialize(DataBuffer buffer) throws IOException
    {
        value = buffer.readChar();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append('\'').append(value).append('\'');
    }
}
