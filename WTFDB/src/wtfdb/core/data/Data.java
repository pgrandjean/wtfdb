package wtfdb.core.data;

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

    @Override
    public abstract int hashCode();
    
    @Override
    public abstract String toString();
}
