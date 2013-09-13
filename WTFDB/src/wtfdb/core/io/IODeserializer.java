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
import wtfdb.core.data.DataId;
import wtfdb.core.data.DataInteger;
import wtfdb.core.data.DataLong;
import wtfdb.core.data.DataMap;
import wtfdb.core.data.DataShort;
import wtfdb.core.data.DataString;
import wtfdb.core.data.DataVisitor;

public class IODeserializer extends DataVisitor
{
    private IOBuffer buffer = null;
    
    private IODictionary dictionary = null;
    
    public IODeserializer(IOBuffer buffer, IODictionary dictionary)
    {
        this.buffer = buffer;
        this.dictionary = dictionary;
    }

    public Data<?> visit()
    {
        Data<?> data = null;
        byte type = buffer.readByte();
        
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
            {
                data = new DataArray();
                data.accept(this);
                break;
            }
            
            case IOTypes.DATA:
            {
                data = new DataMap();
                data.accept(this);
                break;
            }
            
            case IOTypes.ID:
            {
                long date = buffer.readLong();
                byte id = buffer.readByte();
                
                data = new DataId(date, id);
                break;
            }
            
            default:
                throw new IOException("unknown data type: " + type);
        }
        
        return data;
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
            short id = buffer.readShort();
            Data<?> value = visit();
            
            String key = dictionary.getString(id);
            
            data.set(key, value);
        }
    }

    @Override
    public void visit(DataId data)
    {
        
    }
}
