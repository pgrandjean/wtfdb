package wtfdb.cstorage;

public class IdGenerator
{
    private static long curr = 0L;
    
    private IdGenerator() {}
    
    public static synchronized long next()
    {
        return curr++;
    }
}
