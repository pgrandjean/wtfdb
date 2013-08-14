package wtfdb.cstorage;

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
import wtfdb.core.data.DataPath;
import wtfdb.core.data.DataShort;
import wtfdb.core.data.DataString;
import wtfdb.core.data.DataVisitor;
import wtfdb.core.io.IOBuffer;
import wtfdb.core.io.IOTypes;

public class IOSerializer extends DataVisitor
{
    private Collection collection = null;
    
    private Long id = null;
    
    private boolean write = false;
    
    private DataPath curr = null;
    
    public IOSerializer(Collection collection, boolean write)
    {
        this.collection = collection;
        this.write = write;
    }
    
    @Override
    public void visit(DataBoolean data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);
        
        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.BOOLEAN);
        buffer.writeBoolean(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataByte data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.BYTE);
        buffer.writeByte(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataShort data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.SHORT);
        buffer.writeShort(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataInteger data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.INTEGER);
        buffer.writeInt(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataLong data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.LONG);
        buffer.writeLong(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataFloat data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.FLOAT);
        buffer.writeFloat(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataDouble data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.DOUBLE);
        buffer.writeDouble(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataChar data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.CHAR);
        buffer.writeChar(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataString data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.STRING);
        buffer.writeUTF(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataByteArray data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.BYTE_ARRAY);
        buffer.writeInt(data.get().length);
        buffer.write(data.get());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataDate data)
    {
        IOBuffer buffer = collection.getIOBuffer(curr);

        int position = buffer.position();
        if (position - 8 >= 0) buffer.position(position - 8); // overwrite last EOF

        buffer.writeByte(IOTypes.DATE);
        buffer.writeLong(data.get().getTime());
        buffer.writeLong(this.id);
        buffer.writeBoolean(write);
        buffer.writeByte(IOTypes.EOF);
    }

    @Override
    public void visit(DataArray data)
    {
        DataPath curr = this.curr == null? new DataPath() : this.curr;
        
        int n = data.size();
        for (int i = 0; i < n; i++)
        {
            this.curr = new DataPath(curr, i);
            data.at(i).accept(this);
        }
        
        this.curr = curr;
    }

    @Override
    public void visit(DataMap data)
    {
        if (this.id == null) this.id = data.getLong("_id");
        
        DataPath curr = this.curr == null? new DataPath() : this.curr;
        
        for (Entry<String, Data<?>> entry : data)
        {
            this.curr = new DataPath(curr, entry.getKey());
            entry.getValue().accept(this);
        }
        
        this.curr = curr;
    }
}
