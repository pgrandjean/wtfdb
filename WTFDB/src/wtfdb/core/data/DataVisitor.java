package wtfdb.core.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

public class DataVisitor
{   
    private DataPath targPath = null;
    
    private DataPath currPath = null;
    
    public DataVisitor()
    {
        
    }

    @SuppressWarnings("unchecked")
    private void visit0(Object o) throws IOException
    {
        Class<?> klass = o.getClass();
        if (klass == Data.class)
        {
            Data data = (Data) o;
            Iterator<Entry<String, Object>> it = data.iterator();
            
            while (it.hasNext())
            {
                Entry<String, Object> entry = it.next();
                currPath.add(entry.getKey());

                boolean matches = targPath.matches(currPath);

                if (matches) visit0(entry.getValue());
                else it.remove();

                currPath.poll();
            }
        }
        else if (klass == Vector.class)
        {
            List<Object> list = (Vector<Object>) o;
            Iterator<Object> it = list.iterator();
            
            int i = 0;
            while (it.hasNext())
            {
                currPath.add(i);

                boolean matches = targPath.matches(currPath);
                Object p = it.next();
                
                if (matches) visit0(p);
                else it.remove();
                
                currPath.poll();
                i++;
            }
        }
        
//        int type = DataTypes.getType(o.getClass());
//        
//        switch (type)
//        {
//            case DataTypes.ARRAY:
//            {
//                List<Object> list = (List<Object>) o;
//                Iterator<Object> it = list.iterator();
//                
//                int i = 0;
//                while (it.hasNext())
//                {
//                    currPath.add(i);
//
//                    boolean matches = targPath.matches(currPath);
//                    Object p = it.next();
//                    
//                    if (matches) visit0(p);
//                    else it.remove();
//                    
//                    currPath.poll();
//                    i++;
//                }
//                
//                return;
//            }
//                
//            case DataTypes.DATA:
//            {
//                Data data = (Data) o;
//                Iterator<Entry<String, Object>> it = data.iterator();
//                
//                while (it.hasNext())
//                {
//                    Entry<String, Object> entry = it.next();
//                    currPath.add(entry.getKey());
//
//                    boolean matches = targPath.matches(currPath);
//
//                    if (matches) visit0(entry.getValue());
//                    else it.remove();
//
//                    currPath.poll();
//                }
//                
//                return;
//            }
//
//            default:
//                return;
//        }
    }
    
    public Data visit(Data data, String path) throws IOException
    {
        if (data == null || data.size() == 0) return null;
        if (path == null) return null;

        targPath = new DataPath(path);
        currPath = new DataPath();
        
        visit0(data);
        
        return data;
    }
}
