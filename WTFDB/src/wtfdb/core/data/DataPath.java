package wtfdb.core.data;

public class DataPath implements Comparable<DataPath>
{
    private DataPath root = null;
    
    private DataPath prev = null;
    
    private DataPath next = null;
    
    private Object key = null;
    
    private int size = 0;
    
    public DataPath(DataPath path, String key)
    {
        if (path.key == null)
        {
            this.root = this;
            this.prev = null;
            path.next = null;   
        }
        else
        {
            this.root = path.root;
            this.prev = path;
            path.next = this;
        }
        
        this.next = null;
        this.key = key;
        this.size = path.size + 1;
    }
    
    public DataPath(DataPath path, int key)
    {
        if (path.key == null)
        {
            this.root = this;
            this.prev = null;
            path.next = null;   
        }
        else
        {
            this.root = path.root;
            this.prev = path;
            path.next = this;
        }
        
        this.next = null;
        this.key = key;
        this.size = path.size + 1;
    }

    public DataPath(String key)
    {
        this.root = this;
        this.prev = null;
        this.next = null;
        this.key = key;
        this.size = 1;
    }
    
    public DataPath(int key)
    {
        this.root = this;
        this.prev = null;
        this.next = null;
        this.key = key;
        this.size = 1;
    }
    
    public DataPath()
    {
        this.root = null;
        this.prev = null;
        this.next = null;
        this.key = null;
        this.size = 0;
    }
    
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

        DataPath thisCurr = this.root;
        DataPath thatCurr = that.root;
        
        while (i < k && j < l)
        {
            Object thisKey = thisCurr.key;
            Object thatKey = thatCurr.key;
            
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
            
            thisCurr = thisCurr.next;
            thatCurr = thatCurr.next;
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

        DataPath thisCurr = this.root;
        DataPath thatCurr = that.root;
        
        for (int i = 0; i < m; i++)
        {
            Object thisKey = thisCurr.key;
            Object thatKey = thatCurr.key;
            
            if (!thisKey.equals(thatKey)) return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode()
    {
        int hashcode = 1;

        DataPath curr = this.root;
        int n = this.size;
        
        for (int i = 0; i < n; i++, curr = curr.next)
        {
            Object o = curr.key;
            hashcode += 31 * hashcode + o.hashCode(); 
        }
        
        return hashcode;
    }
    
    public boolean matches(DataPath that)
    {
        if (that == null) return false;
        
        int k = this.size;
        int l = that.size;
        
//        if (k > 0 && l == 0) return false; 
//        else if (k == 0 && l > 0 || k == 0 && l == 0) return true;
        
        int i = 0;
        int j = 0;

        DataPath thisCurr = this.root;
        DataPath thatCurr = that.root;
        
        while (i < k && j < l)
        {
            Object thisKey = thisCurr.key;
            Object thatKey = thatCurr.key;
            
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

            thisCurr = thisCurr.next;
            thatCurr = thatCurr.next;
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
            DataPath curr = this.root;
            for (int i = 0; i < n; i++, curr = curr.next)
            {
                Object o = curr.key;
                toString(buffer, o, i == 0);
            }
        }
        
        return buffer.toString();
    }
}
