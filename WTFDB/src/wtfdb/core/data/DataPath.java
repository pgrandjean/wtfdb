package wtfdb.core.data;

import java.util.Stack;

public class DataPath implements Comparable<DataPath>
{
    private DataPath prev = null;
    
    private Object key = null;
    
    private int size = 0;
    
    private int hashcode = 0;
    
    public DataPath(DataPath prev, String key)
    {
        boolean prevnull = prev.key == null;
        int hashcode = key.hashCode();
        
        this.prev = prevnull? null : prev;
        this.key = key;
        this.size = prevnull? 1 : prev.size + 1;
        this.hashcode = prevnull? hashcode : 31 * prev.hashcode + hashcode;
    }
    
    public DataPath(DataPath prev, int key)
    {
        boolean prevnull = prev.key == null;
        int hashcode = Integer.valueOf(key).hashCode();
        
        this.prev = prevnull? null : prev;
        this.key = key;
        this.size = prevnull? 1 : prev.size + 1;
        this.hashcode = prevnull? hashcode : 31 * prev.hashcode + hashcode;
    }

    public DataPath(String key)
    {
        this.prev = null;
        this.key = key;
        this.size = 1;
        this.hashcode = key.hashCode();
    }
    
    public DataPath(int key)
    {
        this.prev = null;
        this.key = key;
        this.size = 1;
        this.hashcode = Integer.valueOf(key).hashCode();
    }
    
    public DataPath()
    {
        this.prev = null;
        this.key = null;
        this.size = 0;
        this.hashcode = 0;
    }
    
    private Stack<DataPath> toStack()
    {
        Stack<DataPath> stack = new Stack<>();
        DataPath curr = this;
        
        do
        {
            stack.add(curr);
        }
        while ((curr = curr.prev) != null);
        
        return stack;
    }
    
    protected int size()
    {
        return size;
    }

    @Override
    public int compareTo(DataPath that)
    {
        if (that == null) return 1;
        else if (that == this) return 0;
        
        int k = this.size;
        int l = that.size;
            
        int i = 0;
        int j = 0;

        Stack<DataPath> thisStack = this.toStack();
        Stack<DataPath> thatStack = that.toStack();
        
        while (i < k && j < l)
        {
            Object thisKey = thisStack.pop().key;
            Object thatKey = thatStack.pop().key;
            
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

    @Override
    public boolean equals(Object o)
    {
        if (o == null || o.getClass() != DataPath.class) return false;
        
        DataPath that = (DataPath) o;
        
        int m = this.size; 
        if (that.size != m) return false;

        Stack<DataPath> thisStack = this.toStack();
        Stack<DataPath> thatStack = that.toStack();
        
        for (int i = 0; i < m; i++)
        {
            Object thisKey = thisStack.pop().key;
            Object thatKey = thatStack.pop().key;
            
            if (!thisKey.equals(thatKey)) return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode()
    {
        return hashcode;
    }
    
    public boolean matches(DataPath that)
    {
        if (that == null) return false;
        
        int k = this.size;
        int l = that.size;
        
        int i = 0;
        int j = 0;

        Stack<DataPath> thisStack = this.toStack();
        Stack<DataPath> thatStack = that.toStack();
        
        while (i < k && j < l)
        {
            Object thisKey = thisStack.pop().key;
            Object thatKey = thatStack.pop().key;
            
            if (thisKey.equals(thatKey))
            {
                // both equal
                i++;
                j++;
            }
            else if (thisKey.getClass() == thatKey.getClass())
            {
                // same class and not equal
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
        
        return i == k || j == l;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        int n = size;
        
        if (n > 0)
        {
            Stack<DataPath> stack = this.toStack();
            
            for (int i = 0; i < n; i++)
            {
                Object o = stack.pop().key;
                
                if (i != 0) buffer.append('.');
                
                buffer.append(o);
            }
        }
        
        return buffer.toString();
    }
    
    public static DataPath valueOf(String s)
    {
        if (s == null) return null;
        else if (s.matches("\\w+[\\w\\.]*")) return null;
        
        DataPath path = null;
        String[] keys = s.split("\\,");
        
        for (int i = 0; i < keys.length; i++)
        {
            if (keys[i].matches("\\d+")) path = new DataPath(path, Integer.valueOf(keys[i]));
            else path = new DataPath(path, keys[i]);
        }
        
        return path;
    }
}
