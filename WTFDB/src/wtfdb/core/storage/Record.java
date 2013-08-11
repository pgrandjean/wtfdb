package wtfdb.core.storage;

import java.io.IOException;

import wtfdb.core.data.DataMap;
import wtfdb.core.io.IOBuffer;
import wtfdb.core.io.IODeserializer;
import wtfdb.core.io.IOSerializer;

public class Record
{
    private boolean init = false;
    
    private boolean valid = false;
    
    private int start = -1;
    
    private int end = -1;
    
    public void serialize(IOBuffer buffer, DataMap data) throws IOException
    {
        valid = true;

        start = buffer.position();
        buffer.writeBoolean(valid);
        buffer.writeInt(0);
        
        IOSerializer serializer = new IOSerializer(buffer);
        serializer.visit(data);
        
        end = buffer.position();
        buffer.writeInt(start + 1, end);
    }
    
    public void deserialize(IOBuffer buffer) throws IOException
    {
        deserialize(buffer, false);
    }
    
    public DataMap deserialize(IOBuffer buffer, boolean full) throws IOException
    {
        if (!init)
        {
            start = buffer.position();
            valid = buffer.readBoolean();
            end = buffer.readInt();
            
            buffer.position(end);
            init = true;
        }
        
        if (full)
        {
            if (valid)
            {
                buffer.position(start + 5);
                    
                IODeserializer deserializer = new IODeserializer(buffer);
                DataMap data = (DataMap) deserializer.visit();
                    
                return data;
            }
        }
        
        return null;
    }
}
