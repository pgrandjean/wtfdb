package wtfdb.core.data;

public class DataString extends Data<String>
{
    public DataString()
    {
        super();
    }

    public DataString(String value)
    {
        super(value);
        
        if (value == null) throw new NullPointerException();
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
        if (!(o instanceof DataString)) return false;
        
        DataString that = (DataString) o;
        
        return this.value.equals(that.value);
    }
}
