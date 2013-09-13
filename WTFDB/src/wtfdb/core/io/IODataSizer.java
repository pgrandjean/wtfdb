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
import wtfdb.core.data.DataId;
import wtfdb.core.data.DataInteger;
import wtfdb.core.data.DataLong;
import wtfdb.core.data.DataMap;
import wtfdb.core.data.DataShort;
import wtfdb.core.data.DataString;
import wtfdb.core.data.DataVisitor;

public class IODataSizer extends DataVisitor
{
    private int size = 0;
    
    public int size(DataMap data)
    {
        size = 0;
        visit(data);
        
        return size;
    }
    
    @Override
    public void visit(DataBoolean data)
    {
        size += IOTypes.SIZE[IOTypes.BOOLEAN];
    }

    @Override
    public void visit(DataByte data)
    {   
        size += IOTypes.SIZE[IOTypes.BYTE];
    }

    @Override
    public void visit(DataShort data)
    {   
        size += IOTypes.SIZE[IOTypes.SHORT];
    }

    @Override
    public void visit(DataInteger data)
    {
        size += IOTypes.SIZE[IOTypes.INTEGER];
    }

    @Override
    public void visit(DataLong data)
    {
        size += IOTypes.SIZE[IOTypes.LONG];
    }

    @Override
    public void visit(DataFloat data)
    {   
        size += IOTypes.SIZE[IOTypes.FLOAT];
    }

    @Override
    public void visit(DataDouble data)
    {   
        size += IOTypes.SIZE[IOTypes.DOUBLE];
    }

    @Override
    public void visit(DataChar data)
    {   
        size += IOTypes.SIZE[IOTypes.CHAR];
    }

    @Override
    public void visit(DataString data)
    {   
        size += IOTypes.SIZE[IOTypes.STRING] + data.getUTF8().length;
    }

    @Override
    public void visit(DataByteArray data)
    {   
        size += IOTypes.SIZE[IOTypes.BYTE_ARRAY] + data.get().length;
    }

    @Override
    public void visit(DataDate data)
    {   
        size += IOTypes.SIZE[IOTypes.DATE];
    }

    @Override
    public void visit(DataArray data)
    {
        int n = data.size();

        size += IOTypes.SIZE[IOTypes.ARRAY];
        
        for (int i = 0; i < n; i++)
        {
            data.at(i).accept(this);
        }
    }

    @Override
    public void visit(DataMap data)
    {
        size += IOTypes.SIZE[IOTypes.DATA];
        
        for (Entry<String, Data<?>> entry : data)
        {
            Data<?> value = entry.getValue();
            
            size += IOTypes.SIZE[IOTypes.SHORT];
            
            value.accept(this);
        }
    }

    @Override
    public void visit(DataId iData)
    {
        size += IOTypes.SIZE[IOTypes.ID];
    }
}
