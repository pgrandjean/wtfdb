package wtfdb.core.data;

import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataPath implements Comparable<DataPath>
{
    private static final Pattern PATTERN_SIMPLE_KEY = Pattern.compile("\\w+");
    
    private static final Pattern PATTERN_ARRAY_KEY = Pattern.compile("(\\w+)\\[(\\d+)\\]");
    
    private Vector<Object> path = new Vector<>();

    private void toString(StringBuffer buffer, Object o, boolean first)
    {
        if (o.getClass() == Integer.class)
        {
            buffer.append('[').append((Integer) o).append(']');
        }
        else
        {
            if (!first)
            {
                buffer.append('.');
            }
            
            buffer.append((String) o);
        }
    }
    
    protected int size()
    {
        return path.size();
    }
    
    protected Object get(int i)
    {
        return path.get(i);
    }
    
    protected void poll()
    {
        int n = path.size();
        if (n > 0)
        {
            path.remove(n - 1);
        }
    }
    
    protected void add(String key)
    {
        path.add(key);
    }
    
    protected void add(int index)
    {
        path.add(index);
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == null || o.getClass() != DataPath.class) return false;
        
        DataPath that = (DataPath) o;
        
        int m = this.path.size(); 
        if (that.path.size() != m) return false;
        
        for (int i = 0; i < m; i++)
        {
            Object thisKey = this.path.get(i);
            Object thatKey = that.path.get(i);
            
            if (!thisKey.equals(thatKey)) return false;
        }
        
        return true;
    }
    
    public boolean matches(DataPath that)
    {
        if (that == null) return false;
        
        int k = this.path.size();
        int l = that.path.size();
        
        if (k > 0 && l == 0) return false; 
        else if (k == 0 && l > 0 || k == 0 && l == 0) return true;
        
        int i = 0;
        int j = 0;
        
        while (i < k && j < l)
        {
            Object thisKey = this.path.get(i);
            Object thatKey = that.path.get(i);
            
            if (thisKey.equals(thatKey))
            {
                // both equal
                i++;
                j++;
            }
            else if (thisKey.getClass() == thatKey.getClass())
            {
                // not equal but same class
                return false;
            }
            else if (thisKey.getClass() == Integer.class)
            {
                // int and string
                i += 2;
                j++;
            }
            else
            {
                // string and int
                i++;
                j += 2;
            }
        }
        
        return i == k;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        int n = path.size();
        
        if (n > 0)
        {
            for (int i = 0; i < n; i++)
            {
                Object o = path.get(i);
                toString(buffer, o, i == 0);
            }
        }
        
        return buffer.toString();
    }
    
    public static DataPath valueOf(String path)
    {
        String[] split = path.split("\\.");
        DataPath output = new DataPath();
        
        for (String key : split)
        {
            Matcher matcher = PATTERN_SIMPLE_KEY.matcher(key);
            if (matcher.matches())
            {
                output.add(key);
            }
            else
            {
                matcher = PATTERN_ARRAY_KEY.matcher(key);
                if (matcher.matches())
                {
                    String arrayKey = matcher.group(1);
                    String arrayIndex = matcher.group(2);
                    
                    output.add(arrayKey);
                    output.add(Integer.valueOf(arrayIndex));
                }
                else
                {
                    return null;
                }
            }
        }
        
        return output;
    }

    @Override
    public int compareTo(DataPath that)
    {
        if (that == null) return 1;
        
        int k = this.path.size();
        int l = this.path.size();
            
        int i = 0;
        int j = 0;
            
        while (i < k && j < l)
        {
            Object thisKey = this.path.get(i);
            Object thatKey = that.path.get(i);
            
            if (thisKey.equals(thatKey))
            {
                // both equal
                i++;
                j++;
            }
            else if (thisKey.getClass() == thatKey.getClass())
            {
                // same class and not equal
                if (thisKey.getClass() == String.class)
                {
                    return ((String) thisKey).compareTo((String) thatKey);
                }
                else
                {
                    return ((Integer) thisKey).compareTo((Integer) thatKey);
                }
            }
            else if (thisKey.getClass() == Integer.class)
            {
                // int and string
                return -1;
            }
            else
            {
                // string and int
                return 1;
            }
        }
        
        return 0;
    }
}
