package wtfdb.core.data2;

public class DataInteger extends Data<Integer>
{
    public DataInteger()
    {
        super();
    }

    public DataInteger(int value)
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
        if (!(o instanceof DataInteger)) return false;
        
        DataInteger that = (DataInteger) o;
        
        return this.value.equals(that.value);
    }
}
