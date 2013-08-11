package wtfdb.core.data;

public class DataChar extends Data<Character>
{
    public DataChar()
    {
        super();
    }

    public DataChar(char value)
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
        if (!(o instanceof DataChar)) return false;
        
        DataChar that = (DataChar) o;
        
        return this.value.equals(that.value);
    }
}
