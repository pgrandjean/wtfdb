package wtfdb.core.data2;

public class DataShort extends Data<Short>
{
    public DataShort()
    {
        super();
    }

    public DataShort(short value)
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
        if (!(o instanceof DataShort)) return false;
        
        DataShort that = (DataShort) o;
        
        return this.value.equals(that.value);
    }
}
