package wtfdb.core.data;

public class DataFloat extends DataPrimitive<Float>
{
    public DataFloat()
    {
        super();
    }

    public DataFloat(float value)
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
        if (!(o instanceof DataFloat)) return false;
        
        DataFloat that = (DataFloat) o;
        
        return this.value.equals(that.value);
    }
}
