package wtfdb.core.io;

import java.util.Date;

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
import wtfdb.core.data.DataShort;
import wtfdb.core.data.DataString;
import wtfdb.core.data.DataVisitor;

public class IODeserializer extends DataVisitor
{
    private IOBuffer buffer = null;
    
    public IODeserializer(IOBuffer buffer)
    {
        this.buffer = buffer;
    }

    public Data<?> visit()
    {
        Data<?> data = null;
        byte type = buffer.readByte();
        
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
        
        return data;
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
        int n = buffer.readInt();        
        for (int i = 0; i < n; i++)
        {
            Data<?> value = visit();
            data.add(value);
        }
    }

    @Override
    public void visit(DataMap data)
    {
        int n = buffer.readInt();        
        for (int i = 0; i < n; i++)
        {
            String key = buffer.readUTF();
            Data<?> value = visit();
            data.set(key, value);
        }
    }
}
