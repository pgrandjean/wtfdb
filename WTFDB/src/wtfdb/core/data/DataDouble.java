package wtfdb.core.data;

public class DataDouble extends DataPrimitive<Double>
{
    protected DataDouble()
    {
        super();
    }

    public DataDouble(double value)
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
        if (!(o instanceof DataDouble)) return false;
        
        DataDouble that = (DataDouble) o;
        
        return this.value.equals(that.value);
    }
}
