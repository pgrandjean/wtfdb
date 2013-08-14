package wtfdb.core.data;

public abstract class DataPrimitive<T> extends Data<T>
{
    protected DataPrimitive()
    {
        super();
    }

    protected DataPrimitive(T value)
    {
        super(value);
    }

    protected DataPrimitive(T value, DataMap parent)
    {
        super(value, parent);
    }

    protected DataPrimitive(T value, DataArray parent)
    {
        super(value, parent);
    }
    
    public T get()
    {
        return value;
    }
    
    public void set(T value)
    {
        this.value = value;
    }

    @Override
    public int hashCode()
    {
        return value == null? 0 : value.hashCode();
    }
    
    @Override
    public String toString()
    {
        return value == null? "<null>" : value.toString();
    }
}
