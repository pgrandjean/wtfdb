package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Data<T>
{
    protected static final byte BOOLEAN = 0;
    
    protected static final byte BYTE = 1;
    
    protected static final byte SHORT = 2;
    
    protected static final byte INTEGER = 3;
    
    protected static final byte LONG = 4;
    
    protected static final byte FLOAT = 5;
    
    protected static final byte DOUBLE = 6;
    
    protected static final byte CHAR = 7;
    
    protected static final byte STRING = 8;

    protected static final byte BYTE_ARRAY = 9;
    
    protected static final byte DATE = 10;
    
    protected static final byte ARRAY = 11;
    
    protected static final byte DATA = 12;
    
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
    
    protected static Data<?> deserialize(byte type, DataInputStream input) throws IOException
    {
        Data<?> value = null;
        
        switch (type)
        {
            case BOOLEAN:
            {
                value = new DataBoolean();
                break;
            }

            case BYTE:
            {
                value = new DataByte();
                break;
            }

            case SHORT:
            {
                value = new DataShort();
                break;
            }

            case INTEGER:
            {
                value = new DataInteger();
                break;
            }

            case LONG:
            {
                value = new DataLong();
                break;
            }

            case FLOAT:
            {
                value = new DataFloat();
                break;
            }

            case DOUBLE:
            {
                value = new DataDouble();
                break;
            }

            case CHAR:
            {
                value = new DataChar();
                break;
            }

            case STRING:
            {
                value = new DataString();
                break;
            }

            case BYTE_ARRAY:
            {
                value = new DataByteArray();
                break;
            }
                
            case DATE:
            {
                value = new DataDate();
                break;
            }
                
            case ARRAY:
            {
                value = new DataArray();
                break;
            }

            case DATA:
            {
                value = new DataMap();
                break;
            }

            default:
                throw new IOException("unknown type: " + type);
        }

        value.deserialize(input);
        return value;
    }
    
    @Override
    public abstract boolean equals(Object o);
        
    public abstract void serialize(DataOutputStream output) throws IOException;
    
    public abstract void deserialize(DataInputStream input) throws IOException;

    public abstract void toString(StringBuffer buffer);
}
