package wtfdb.core.storage;

import java.io.IOException;

import wtfdb.core.data2.DataMap;
import wtfdb.core.io.DataBuffer;

public class Record
{
    protected Boolean valid = null;

    protected Integer start = null;
    
    protected Integer end = null;
    
    public void serialize(DataBuffer buffer, DataMap data) throws IOException
    {
        start = buffer.position();
        
        buffer.writeBoolean(true);
        
        buffer.writeInt(start);
        data.serialize(buffer);
        
        end = buffer.position();
        
        buffer.writeInt(start, end);
    }

    public void deserialize(DataBuffer buffer) throws IOException
    {
        start = buffer.position();
        valid = buffer.readBoolean();
        end = buffer.readInt();
        
        buffer.position(end);
    }
    
    public void deserialize(DataBuffer buffer, DataMap data) throws IOException
    {
        deserialize(buffer);
        
        if (valid)
        {
            buffer.position(start + 5); // 1 (boolean) + 4 (integer)
            buffer.readByte(); // type data
            
            data.clear();
            data.deserialize(buffer);
        }
    }
}
