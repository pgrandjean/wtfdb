package wtfdb.core.storage;

import wtfdb.core.data.DataMap;
import wtfdb.core.io.IOBuffer;
import wtfdb.core.io.IODeserializer;
import wtfdb.core.io.IODictionary;
import wtfdb.core.io.IOSerializer;

public class Collection
{
    protected DB db = null;
    
    protected String name = null;
    
    protected IODictionary dictionary = null;
    
    protected IOBuffer buffer = null;
    
    protected IOSerializer serializer = null;
    
    protected IODeserializer deserializer = null;
    
    protected Record first = null;
    
    protected Record curr = null;
    
    protected Record last = null;
    
    protected Collection(DB db, String name)
    {
        this.db = db;
        this.name = name;
        this.dictionary = new IODictionary(db.name, name);
        this.buffer = new IOBuffer(db.name + "." + name + ".data.wtfdb");
        this.serializer = new IOSerializer(this.buffer, this.dictionary);
        this.deserializer = new IODeserializer(this.buffer, this.dictionary);
    }
    
    protected void close()
    {
        dictionary.close();
        buffer.close();
    }

    protected DataMap readNext()
    {
        if (curr == null) curr = first;
        
        if (curr != null)
        {
            DataMap data = curr.deserialize();
            curr = curr.next;
            
            return data;
        }
        
        return null;
    }
    
    public void create(DataMap data)
    {
        Record record = new Record(first, this, data);
        
        if (first == null) first = record;
        
        last = record;
    }
}
