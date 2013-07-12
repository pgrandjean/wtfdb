package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class DataArray extends Data<List<Data<?>>>
{
    public DataArray()
    {
        super(new Vector<Data<?>>());
    }

    protected DataArray(DataArray parent)
    {
        super(new Vector<Data<?>>(), parent);
    }

    protected DataArray(DataMap parent)
    {
        super(new Vector<Data<?>>(), parent);
    }

    protected void add(Data<?> v)
    {
        v.parent = this;
        value.add(v);
    }
    
    public void add(boolean v)
    {
        value.add(new DataBoolean(v));
    }

    public void add(byte v)
    {
        value.add(new DataByte(v));
    }

    public void add(short v)
    {
        value.add(new DataShort(v));
    }

    public void add(int v)
    {
        value.add(new DataInteger(v));
    }

    public void add(long v)
    {
        value.add(new DataLong(v));
    }

    public void add(float v)
    {
        value.add(new DataFloat(v));
    }

    public void add(double v)
    {
        value.add(new DataDouble(v));
    }

    public void add(char v)
    {
        value.add(new DataChar(v));
    }

    public void add(String v)
    {
        value.add(new DataString(v));
    }

    public void add(byte[] v)
    {
        value.add(new DataByteArray(v));
    }

    public void add(Date v)
    {
        value.add(new DataDate(v));
    }

    public void add(DataArray v)
    {
        v.parent = this;
        value.add(v);
    }

    public void add(DataMap v)
    {
        v.parent = this;
        value.add(v);
    }
    
    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(ARRAY);
        output.writeInt(value.size());
        
        for (int i = 0; i < value.size(); i++)
        {
            value.get(i).serialize(output);
        }
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        int size = input.readInt();
        
        for (int i = 0; i < size; i++)
        {
            byte type = input.readByte();
            Data<?> value = deserialize(type, input);
            add(value);
        }
    }
}
