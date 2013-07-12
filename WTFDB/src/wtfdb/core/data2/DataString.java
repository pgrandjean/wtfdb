package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(STRING);
        output.writeUTF(value);
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        value = input.readUTF();
    }

    @Override
    public void toString(StringBuffer buffer)
    {
        buffer.append('"').append(value).append('"');
    }
}
