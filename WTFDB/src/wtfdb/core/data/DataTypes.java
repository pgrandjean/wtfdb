package wtfdb.core.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public abstract class DataTypes
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
    
    private static final byte[] BYTE_ARRAY_INSTANCE = new byte[0];
    
    private static final Map<Class<?>, Byte> bytes;
    
    static
    {
        bytes = new HashMap<Class<?>, Byte>();
        bytes.put(Boolean.class, BOOLEAN);
        bytes.put(Byte.class, BYTE);
        bytes.put(Short.class, SHORT);
        bytes.put(Integer.class, INTEGER);
        bytes.put(Long.class, LONG);
        bytes.put(Float.class, FLOAT);
        bytes.put(Double.class, DOUBLE);
        bytes.put(Character.class, CHAR);
        bytes.put(String.class, STRING);
        bytes.put(BYTE_ARRAY_INSTANCE.getClass(), BYTE_ARRAY);
        bytes.put(Date.class, DATE);
        bytes.put(Vector.class, ARRAY);
        bytes.put(Data.class, DATA);
    }
    
    private DataTypes()
    {
        
    }

    public static Byte getType(Class<?> klass)
    {
        return bytes.get(klass);
    }
}
