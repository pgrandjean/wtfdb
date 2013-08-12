package wtfdb.core.visitors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import wtfdb.core.data.Data;
import wtfdb.core.data.DataArray;
import wtfdb.core.data.DataBoolean;
import wtfdb.core.data.DataByte;
import wtfdb.core.data.DataByteArray;
import wtfdb.core.data.DataChar;
import wtfdb.core.data.DataDate;
import wtfdb.core.data.DataDouble;
import wtfdb.core.data.DataFloat;
import wtfdb.core.data.DataInteger;
import wtfdb.core.data.DataLong;
import wtfdb.core.data.DataMap;
import wtfdb.core.data.DataPath;
import wtfdb.core.data.DataShort;
import wtfdb.core.data.DataString;
import wtfdb.core.data.DataVisitor;

public class DataPaths extends DataVisitor
{
    private Map<DataPath, Object> paths = new LinkedHashMap<>();
    
    private DataPath path = null;
    
    public void clear()
    {
        paths.clear();
    }
    
    public Map<DataPath, Object> getPaths()
    {
        return paths;
    }
    
    @Override
    public void visit(DataBoolean data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataByte data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataShort data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataInteger data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataLong data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataFloat data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataDouble data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataChar data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataString data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataByteArray data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataDate data)
    {
        paths.put(path, data);
    }

    @Override
    public void visit(DataArray data)
    {
        DataPath curr = this.path == null? new DataPath() : this.path;
        
        int n = data.size();
        for (int i = 0; i < n; i++)
        {
            this.path = new DataPath(curr, i);
            data.at(i).accept(this);
        }
        
        this.path = curr;
    }

    @Override
    public void visit(DataMap data)
    {
        DataPath curr = this.path == null? new DataPath() : this.path;
        
        for (Entry<String, Data<?>> entry : data)
        {
            this.path = new DataPath(curr, entry.getKey());
            entry.getValue().accept(this);
        }
        
        this.path = curr;
    }
}
