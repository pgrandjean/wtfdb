package wtfdb.cstorage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DB
{
    private Map<String, Collection> collections = new HashMap<>();
    
    private String name = null;
    
    public DB(String name)
    {
        this.name = name;
    }
    
    public void close() throws IOException
    {
        for (Entry<String, Collection> entry : collections.entrySet())
        {
            entry.getValue().close();
        }
    }
    
    public Collection getCollection(String name)
    {
        Collection collection = collections.get(name);
        if (collection == null)
        {
            collection = new Collection(this, name);
            collections.put(name, collection);
        }
        
        return collection;
    }
    
    public String getName()
    {
        return name;
    }
}
