package wtfdb.core.visitors;

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

public class DataEmpty extends DataVisitor
{
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
        int n = data.size();
        for (int i = 0; i < n; i++)
        {
            data.get().get(i).accept(this);
        }
    }

    @Override
    public void visit(DataMap data)
    {
        for (Entry<String, Data<?>> entry : data.get().entrySet())
        {
            entry.getValue().accept(this);
        }
    }
}
