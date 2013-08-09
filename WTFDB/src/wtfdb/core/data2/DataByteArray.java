package wtfdb.core.data2;

public class DataByteArray extends Data<byte[]>
{
    public DataByteArray()
    {
        super();
    }

    public DataByteArray(byte[] value)
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
        if (!(o instanceof DataByteArray)) return false;
        
        DataByteArray that = (DataByteArray) o;
        
        for (int i = 0; i < this.value.length; i++)
        {
            if (this.value[i] != that.value[i]) return false;
        }
        
        return true;
    }
}
