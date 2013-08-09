package wtfdb.core.io;

public class IOException extends RuntimeException
{
    public IOException(String what)
    {
        super(what);
    }
    
    public IOException(String what, Throwable cause)
    {
        super(what, cause);
    }
}
