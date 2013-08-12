package wtfdb.core.data;

public class DataByte extends DataPrimitive<Byte>
{
    public DataByte()
    {
        super();
    }

    public DataByte(byte value)
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
        if (!(o instanceof DataByte)) return false;
        
        DataByte that = (DataByte) o;
        
        return this.value.equals(that.value);
    }
}
