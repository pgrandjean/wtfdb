package wtfdb.core.data;

public class DataBoolean extends Data<Boolean>
{
    public DataBoolean()
    {
        super();
    }

    public DataBoolean(boolean value)
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
        if (!(o instanceof DataBoolean)) return false;
        
        DataBoolean that = (DataBoolean) o;
        
        return this.value.equals(that.value);
    }
}
