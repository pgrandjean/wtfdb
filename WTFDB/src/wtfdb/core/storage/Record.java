package wtfdb.core.storage;

import wtfdb.core.data.DataMap;

public class Record
{
    protected Collection collection = null;
    
    protected Record prev = null;
    
    protected Record next = null;
    
    protected int pos = -1;
    
    protected Record(Record prev, Collection collection, DataMap data)
    {
        this.collection = collection;
        this.prev = prev;
        this.pos = collection.buffer.position();
        
        if (prev != null) prev.next = this;
        
        collection.serializer.visit(data);
    }
    
    protected DataMap deserialize()
    {
        collection.buffer.position(pos);
        return (DataMap) collection.deserializer.visit();
    }
}
