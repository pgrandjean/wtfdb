package wtfdb.core.data2;

public abstract class Data<T>
{
    protected Data<?> parent = null;
    
    protected T value = null;

    protected Data()
    {
        this.value = null;
        this.parent = null;
    }

    protected Data(T value)
    {
        this.value = value;
        this.parent = null;
    }

    protected Data(T value, DataMap parent)
    {
        this.value = value;
        this.parent = parent;
    }

    protected Data(T value, DataArray parent)
    {
        this.value = value;
        this.parent = parent;
    }
    
    public abstract void accept(DataVisitor visitor);

    @Override
    public abstract boolean equals(Object o);

    public T get()
    {
        return value;
    }
    
    public void set(T value)
    {
        this.value = value;
    }
}
