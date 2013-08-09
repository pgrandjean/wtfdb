package wtfdb.core.data2;

public class DataLong extends Data<Long>
{
    public DataLong()
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
