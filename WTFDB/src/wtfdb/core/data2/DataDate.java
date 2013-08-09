package wtfdb.core.data2;

import java.util.Date;

public class DataDate extends Data<Date>
{
    public DataDate()
    {
        super();
    }

    public DataDate(Date value)
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
        if (!(o instanceof DataDate)) return false;
        
        DataDate that = (DataDate) o;
        
        return this.value.equals(that.value);
    }
}
