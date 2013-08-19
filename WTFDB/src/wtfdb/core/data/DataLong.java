package wtfdb.core.data;

public class DataLong extends DataPrimitive<Long>
{
    protected DataLong()
    {
        super();
    }

    public DataLong(long value)
    {
        super(value);
    }

    @Override
    public void accept(DataVisitor visitor)
    {
        visitor.visit(this);
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof DataLong)) return false;
        
        DataLong that = (DataLong) o;
        
        return this.value.equals(that.value);
    }
}
