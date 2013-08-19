package wtfdb.cstorage;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import wtfdb.core.data.Data;
import wtfdb.core.data.DataArray;
import wtfdb.core.data.DataBoolean;
import wtfdb.core.data.DataByte;
import wtfdb.core.data.DataByteArray;
import wtfdb.core.data.DataChar;
import wtfdb.core.data.DataDate;
import wtfdb.core.data.DataDouble;
import wtfdb.core.data.DataFloat;
import wtfdb.core.data.DataInteger;
import wtfdb.core.data.DataLong;
import wtfdb.core.data.DataMap;
import wtfdb.core.data.DataPath;
import wtfdb.core.data.DataPrimitive;
import wtfdb.core.data.DataShort;
import wtfdb.core.data.DataString;
import wtfdb.core.data.DataVisitor;
import wtfdb.core.io.IOBuffer;
import wtfdb.core.io.IOException;
import wtfdb.core.io.IOTypes;

public class IODeserializer extends DataVisitor
{
    private Collection collection = null;
    
    private DataPath path = null;
    
    private DataPrimitive<?> value = null;
    
    private IOBuffer buffer = null;
    
    public IODeserializer(Collection collection, DataPath path, DataPrimitive<?> value)
    {
        this.collection = collection;
        this.path = path;
        this.value = value;
        this.buffer = collection.getIOBuffer(path);
    }

    public Set<Long> visit()
    {
        Set<Long> ids = new HashSet<>();
        
        byte type = 0;
        
        while ((type = buffer.readByte()) != IOTypes.EOF)
        {
            Data<?> data = null;
            
            switch (type)
            {
                case IOTypes.BOOLEAN:
                {
                    boolean value = buffer.readBoolean();
                    data = new DataBoolean(value);
                    break;
                }
                
                case IOTypes.BYTE:
                {
                    byte value = buffer.readByte();
                    data = new DataByte(value);
                    break;
                }
                
                case IOTypes.SHORT:
                {
                    short value = buffer.readShort();
                    data = new DataShort(value);
                    break;
                }
                
                case IOTypes.INTEGER:
                {
                    int value = buffer.readInt();
                    data = new DataInteger(value);
                    break;
                }
                
                case IOTypes.LONG:
                {
                    long value = buffer.readLong();
                    data = new DataLong(value);
                    break;
                }
                
                case IOTypes.FLOAT:
                {
                    float value = buffer.readFloat();
                    data = new DataFloat(value);
                    break;
                }
                
                case IOTypes.DOUBLE:
                {
                    double value = buffer.readDouble();
                    data = new DataDouble(value);
                    break;
                }
                
                case IOTypes.CHAR:
                {
                    char value = buffer.readChar();
                    data = new DataChar(value);
                    break;
                }
                
                case IOTypes.STRING:
                {
                    String value = buffer.readUTF();
                    data = new DataString(value);
                    break;
                }

                case IOTypes.BYTE_ARRAY:
                {
                    int n = buffer.readInt();
                    
                    byte[] value = new byte[n];
                    buffer.readFully(value);
                    
                    data = new DataByteArray(value);
                    break;
                }
                
                case IOTypes.DATE:
                {
                    long date = buffer.readLong();
                    Date value = new Date(date);
                    
                    data = new DataDate(value);
                    break;
                }
                
                case IOTypes.ARRAY:
                    data = new DataArray();
                    break;
                
                case IOTypes.DATA:
                    data = new DataMap();
                    break;
                
                default:
                    throw new IOException("unknown data type: " + type);
            }

            data.accept(this);
            
            long id = buffer.readLong();
            boolean write = buffer.readBoolean();
            
            if (write && data.equals(value)) ids.add(id);
            else ids.remove(id);
        }
        
        return ids;
    }
    
    @Override
    public void visit(DataBoolean data)
    {
        
    }

    @Override
    public void visit(DataByte data)
    {
        
    }

    @Override
    public void visit(DataShort data)
    {
        
    }

    @Override
    public void visit(DataInteger data)
    {
        
    }

    @Override
    public void visit(DataLong data)
    {
        
    }

    @Override
    public void visit(DataFloat data)
    {
        
    }

    @Override
    public void visit(DataDouble data)
    {
        
    }

    @Override
    public void visit(DataChar data)
    {
        
    }

    @Override
    public void visit(DataString data)
    {
        
    }

    @Override
    public void visit(DataByteArray data)
    {
        
    }

    @Override
    public void visit(DataDate data)
    {
        
    }

    @Override
    public void visit(DataArray data)
    {   
        throw new RuntimeException("should never be called");
    }

    @Override
    public void visit(DataMap data)
    {
        throw new RuntimeException("should never be called");
    }
}
