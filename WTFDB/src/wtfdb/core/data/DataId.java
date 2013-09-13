package wtfdb.core.data;

public class DataId extends Data<Long>
{
    private static DataId prev = null;
    
    private long date = 0L;
    
    private byte id = 0;
    
    private DataId()
    {
        
    }
    
    public static DataId newInstance()
    {
        DataId dataId = new DataId();
        dataId.date = System.currentTimeMillis();
        dataId.id = 0;
        
        if (prev == null) prev = dataId;
        else 
        {
            while (dataId.equals(prev)) 
            {
                dataId.id++;
                
                if (dataId.id == Byte.MAX_VALUE) throw new RuntimeException("too fast");
            }
            
            prev = dataId;
        }
        
        return dataId;
    }

    @Override
    public void accept(DataVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != DataId.class) return false;
        
        DataId that = (DataId) o;
        
        return this.date == that.date && this.id == that.id;
    }

    public long getDate()
    {
        return date;
    }
    
    public int getId()
    {
        return id;
    }
    
    @Override
    public int hashCode()
    {
        return 31 * Long.valueOf(date).hashCode() + id;
    }

    @Override
    public String toString()
    {
        return "0x" + String.format("%016X", date) + String.format("%02X", id);
    }
}
