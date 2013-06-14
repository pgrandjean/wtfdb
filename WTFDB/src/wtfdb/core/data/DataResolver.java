package wtfdb.core.data;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class DataResolver extends DataVisitor
{
    private Vector<DataPath> paths = new Vector<>(1);
    
    private Map<DataPath, Object> values = new TreeMap<>();
    
    private boolean visit0(DataPath path, Object value)
    {
        int size = paths.size();
        int i = 0;
        
        while (i < size)
        {
            DataPath dataPath = paths.get(i);
            if (dataPath.matches(path))
            {
                values.put(dataPath, value);
                size--;
            }
            else
            {
                i++;
            }
        }
        
        return size > 0;
    }
    
    @Override
    public boolean visit(DataPath path, Boolean value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, Byte value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, Short value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, Integer value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, Long value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, Float value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, Double value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, Character value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, String value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, byte[] value)
    {
        return visit0(path, (Object) value);
    }

    @Override
    public boolean visit(DataPath path, Date value)
    {
        return visit0(path, (Object) value);
    }
    
    public Object get(byte[] raw, String path) throws IOException
    {   
        if (path == null) return null;

        DataPath dataPath = DataPath.valueOf(path);
        return get(raw, dataPath);
    }

    public Object get(byte[] raw, DataPath path) throws IOException
    {   
        if (path == null) return null;
        
        paths.clear();
        values.clear();

        paths.add(path);
        
        visit(raw);

        return values.size() == 0? null : values.values().iterator().next();
    }
    
    public Map<DataPath, Object> get(byte[] raw, Set<String> paths) throws IOException
    {   
        this.paths.clear();
        this.paths.ensureCapacity(paths.size());
        this.values.clear();

        for (String path : paths)
        {
            DataPath dataPath = DataPath.valueOf(path);   
            this.paths.add(dataPath);   
        }
        
        visit(raw);

        return values;
    }

    public Map<DataPath, Object> get(byte[] raw, String... paths) throws IOException
    {   
        Set<String> pathsSet = new HashSet<>();
        for (String path : paths)
        {
            pathsSet.add(path);
        }
        
        return get(raw, pathsSet);
    }
}
