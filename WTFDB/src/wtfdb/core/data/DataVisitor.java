package wtfdb.core.data;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class DataVisitor
{   
    public DataVisitor()
    {
        
    }
    
    private Object read(Data root, DataPath path)
    {
        Object curr = root;
        
        for (int i = 0; i < path.size() && curr != null; i++)
        {
            Object key = path.get(i);

            if (key instanceof String && curr instanceof Data)
            {
                curr = ((Data) curr).get((String) key);
            }
            else if (key instanceof Integer && curr instanceof List)
            {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) curr;
                Integer index = (Integer) key;
                
                if (index < list.size()) curr = list.get(index);
                else curr = null;
            }
        }
        
        return curr;
    }

    private Object create(DataPath path, int index, Object value)
    {
        Object curr = value;
        
        int n = path.size();
        for (int i = n - 1; i > index; i--)
        {
            Object k = path.get(i);
            if (k instanceof String)
            {
                Data data = new Data();
                data.set((String) k, curr);
                
                curr = data;
            }
            else if (k instanceof Integer)
            {
                List<Object> list = new Vector<>();
                list.add(curr);
                
                curr = list;
            }
        }
        
        return curr;
    }
    
    private Data update(Data root, DataPath path, Object value)
    {
        Object curr = root;
        
        int n = path.size();
        for (int i = 0; i < n; i++)
        {
            Object key = path.get(i);
            if (key instanceof String && curr instanceof Data)
            {
                Data data = (Data) curr;
                Object node = data.get((String) key);
                if (node == null)
                {
                    value = create(path, i, value);
                    data.set((String) key, value);
                    break;
                }
                else if (i == n - 1)
                {
                    data.set((String) key, value);
                    break;
                }
                else
                {
                    curr = node;
                }
            }
            else if (key instanceof Integer && curr instanceof List)
            {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) curr;
                Integer index = (Integer) key;
                
                if (index < list.size())
                {
                    if (i == n - 1)
                    {
                        list.add(index, value);
                    }
                    else
                    {
                        curr = list.get(index);
                    }
                }
                else
                {
                    value = create(path, i, value);
                    list.add(value);
                }
            }
        }
        
        return root;
    }
    
    private Data visit0(Data data, DataPath path)
    {
        Data output = new Data();
        
        Object value = read(data, path);
        if (value != null)
        {
            output = update(output, path, value);
        }
        
        return output;
    }
    
    public Data visit(Data data, String path) throws IOException
    {
        if (data == null || data.size() == 0) return null;
        if (path == null) return null;

        return visit0(data, new DataPath(path));
    }
}
