package wtfdb.core.io;

import java.util.Map.Entry;

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

public class IOSerializer extends DataVisitor
{
    private IOBuffer buffer = null;
    
    public IOSerializer(IOBuffer buffer)
    {
        this.buffer = buffer;
    }
    
    @Override
    public void visit(DataBoolean data)
    {
        buffer.writeByte(IOTypes.BOOLEAN);
        buffer.writeBoolean(data.get());
    }

    @Override
    public void visit(DataByte data)
    {
        buffer.writeByte(IOTypes.BYTE);
        buffer.writeByte(data.get());
    }

    @Override
    public void visit(DataShort data)
    {
        buffer.writeByte(IOTypes.SHORT);
        buffer.writeShort(data.get());
    }

    @Override
    public void visit(DataInteger data)
    {
        buffer.writeByte(IOTypes.INTEGER);
        buffer.writeInt(data.get());
    }

    @Override
    public void visit(DataLong data)
    {
        buffer.writeByte(IOTypes.LONG);
        buffer.writeLong(data.get());
    }

    @Override
    public void visit(DataFloat data)
    {
        buffer.writeByte(IOTypes.FLOAT);
        buffer.writeFloat(data.get());
    }

    @Override
    public void visit(DataDouble data)
    {
        buffer.writeByte(IOTypes.DOUBLE);
        buffer.writeDouble(data.get());
    }

    @Override
    public void visit(DataChar data)
    {
        buffer.writeByte(IOTypes.CHAR);
        buffer.writeChar(data.get());
    }

    @Override
    public void visit(DataString data)
    {
        buffer.writeByte(IOTypes.STRING);
        buffer.writeUTF(data.get());
    }

    @Override
    public void visit(DataByteArray data)
    {
        buffer.writeByte(IOTypes.BYTE_ARRAY);
        buffer.writeInt(data.get().length);
        buffer.write(data.get());
    }

    @Override
    public void visit(DataDate data)
    {
        buffer.writeByte(IOTypes.DATE);
        buffer.writeLong(data.get().getTime());
    }

    @Override
    public void visit(DataArray data)
    {
        buffer.writeByte(IOTypes.ARRAY);
        
        int n = data.get().size();
        buffer.writeInt(n);
        
        for (int i = 0; i < n; i++)
        {
            data.get().get(i).accept(this);
        }
    }

    @Override
    public void visit(DataMap data)
    {
        buffer.writeByte(IOTypes.DATA);
        
        int n = data.get().size();
        buffer.writeInt(n);
        
        for (Entry<String, Data<?>> entry : data.get().entrySet())
        {
            buffer.writeUTF(entry.getKey());
            entry.getValue().accept(this);
        }
    }
}
