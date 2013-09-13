package wtfdb.core.io;

public class IOTypes
{
    public static final byte BOOLEAN = 0;
    
    public static final byte BYTE = 1;
    
    public static final byte SHORT = 2;
    
    public static final byte INTEGER = 3;
    
    public static final byte LONG = 4;
    
    public static final byte FLOAT = 5;
    
    public static final byte DOUBLE = 6;
    
    public static final byte CHAR = 7;
    
    public static final byte STRING = 8;

    public static final byte BYTE_ARRAY = 9;
    
    public static final byte DATE = 10;
    
    public static final byte ARRAY = 11;
    
    public static final byte DATA = 12;
    
    public static final byte ID = 13;
    
    public static final byte EOF = 15;
    
    public static final int[] SIZE = {
        1 + 1, // BOOLEAN
        1 + 1, // BYTE
        1 + 2, // SHORT
        1 + 4, // INTEGER
        1 + 8, // LONG
        1 + 4, // FLOAT
        1 + 8, // DOUBLE
        1 + 2, // CHAR
        1 + 4, // STRING (header)
        1 + 4, // BYTE_ARRAY (header)
        1 + 8, // DATE
        1 + 4, // ARRAY (header)
        1 + 4, // DATA (header)
        1 + 9, // ID
        1 + 1  // EOF
    };
    
    private IOTypes()
    {
        
    }
}
