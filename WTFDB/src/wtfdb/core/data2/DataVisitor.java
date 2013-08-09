package wtfdb.core.data2;

public abstract class DataVisitor
{
    public abstract void visit(DataBoolean data);
    
    public abstract void visit(DataByte data);
    
    public abstract void visit(DataShort data);
    
    public abstract void visit(DataInteger data);
    
    public abstract void visit(DataLong data);
    
    public abstract void visit(DataFloat data);
    
    public abstract void visit(DataDouble data);
    
    public abstract void visit(DataChar data);
    
    public abstract void visit(DataString data);
    
    public abstract void visit(DataByteArray data);
    
    public abstract void visit(DataDate data);
    
    public abstract void visit(DataArray data);
    
    public abstract void visit(DataMap data);
}
