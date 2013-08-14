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
                    data = new DataBoolean();
                    break;
                
                case IOTypes.BYTE:
                    data = new DataByte();
                    break;
                
                case IOTypes.SHORT:
                    data = new DataShort();
                    break;
                
                case IOTypes.INTEGER:
                    data = new DataInteger();
                    break;
                
                case IOTypes.LONG:
                    data = new DataLong();
                    break;
                
                case IOTypes.FLOAT:
                    data = new DataFloat();
                    break;
                
                case IOTypes.DOUBLE:
                    data = new DataDouble();
                    break;
                
                case IOTypes.CHAR:
                    data = new DataChar();
                    break;
                
                case IOTypes.STRING:
                    data = new DataString();
                    break;

                case IOTypes.BYTE_ARRAY:
                    data = new DataByteArray();
                    break;
                
                case IOTypes.DATE:
                    data = new DataDate();
                    break;
                
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
        data.set(buffer.readBoolean());
    }

    @Override
    public void visit(DataByte data)
    {
        data.set(buffer.readByte());
    }

    @Override
    public void visit(DataShort data)
    {
        data.set(buffer.readShort());
    }

    @Override
    public void visit(DataInteger data)
    {
        data.set(buffer.readInt());
    }

    @Override
    public void visit(DataLong data)
    {
        data.set(buffer.readLong());
    }

    @Override
    public void visit(DataFloat data)
    {
        data.set(buffer.readFloat());
    }

    @Override
    public void visit(DataDouble data)
    {
        data.set(buffer.readDouble());
    }

    @Override
    public void visit(DataChar data)
    {
        data.set(buffer.readChar());
    }

    @Override
    public void visit(DataString data)
    {
        data.set(buffer.readUTF());
    }

    @Override
    public void visit(DataByteArray data)
    {
        int n = buffer.readInt();
        
        byte[] b = new byte[n];
        buffer.readFully(b);
        
        data.set(b);
    }

    @Override
    public void visit(DataDate data)
    {
        data.set(new Date(buffer.readLong()));
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
