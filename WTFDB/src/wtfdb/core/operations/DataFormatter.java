package wtfdb.core.operations;

import java.util.Map.Entry;

import wtfdb.core.data2.Data;
import wtfdb.core.data2.DataArray;
import wtfdb.core.data2.DataBoolean;
import wtfdb.core.data2.DataByte;
import wtfdb.core.data2.DataByteArray;
import wtfdb.core.data2.DataChar;
import wtfdb.core.data2.DataDate;
import wtfdb.core.data2.DataDouble;
import wtfdb.core.data2.DataFloat;
import wtfdb.core.data2.DataInteger;
import wtfdb.core.data2.DataLong;
import wtfdb.core.data2.DataMap;
import wtfdb.core.data2.DataShort;
import wtfdb.core.data2.DataString;
import wtfdb.core.data2.DataVisitor;

public class DataFormatter extends DataVisitor
{
    private StringBuffer buffer = null;

    private boolean indent = false;

    private boolean types = false;
    
    private int depth = 0;
    
    public DataFormatter(StringBuffer buffer)
    {
        this(buffer, false);
    }
    
    public DataFormatter(StringBuffer buffer, boolean indent)
    {
        this(buffer, indent, false);
    }

    public DataFormatter(StringBuffer buffer, boolean indent, boolean types)
    {
        this.buffer = buffer;
        this.indent = indent;
        this.types = types;
    }
    
    private void indent()
    {
        buffer.append("\n");
        
        for (int i = 0; i < depth; i++)
        {
            buffer.append('\t');
        }
    }
    
    @Override
    public void visit(DataBoolean data)
    {
        buffer.append(data.get());
    }

    @Override
    public void visit(DataByte data)
    {
        buffer.append(String.format("0x%02X ", data.get()));
    }

    @Override
    public void visit(DataShort data)
    {
        buffer.append(data.get());
        if (types) buffer.append('s');
    }

    @Override
    public void visit(DataInteger data)
    {
        buffer.append(data.get());
    }

    @Override
    public void visit(DataLong data)
    {
        buffer.append(data.get());
        if (types) buffer.append('L');
    }

    @Override
    public void visit(DataFloat data)
    {
        buffer.append(data.get());
        if (types) buffer.append('f');
    }

    @Override
    public void visit(DataDouble data)
    {
        buffer.append(data.get());
        if (types) buffer.append('d');
    }

    @Override
    public void visit(DataChar data)
    {
        buffer.append('\'').append(data.get()).append('\'');
    }

    @Override
    public void visit(DataString data)
    {
        buffer.append('"').append(data.get()).append('"');
    }

    @Override
    public void visit(DataByteArray data)
    {
        buffer.append("<binary>");
    }

    @Override
    public void visit(DataDate data)
    {
        buffer.append(data.get());
    }

    @Override
    public void visit(DataArray data)
    {
        buffer.append("[");
        
        if (indent)
        {
            depth++;
            indent();
        }
        else
        {
            buffer.append(' ');
        }
        
        int n = data.size();
        for (int i = 0; i < n; i++)
        {
            data.get().get(i).accept(this);
            
            if (i != n - 1)
            {
                buffer.append(",");
                
                if (indent)
                {
                    indent();
                }
                else
                {
                    buffer.append(' ');
                }
            }
        }

        if (indent)
        {
            depth--;
            indent();
        }
        else
        {
            buffer.append(' ');
        }
        
        buffer.append("]");
    }

    @Override
    public void visit(DataMap data)
    {
        buffer.append("{");

        if (indent)
        {
            depth++;
            indent();
        }
        else
        {
            buffer.append(' ');
        }
        
        int n = data.get().size();
        int i = 0;
        
        for (Entry<String, Data<?>> entry : data.get().entrySet())
        {
            buffer.append('"').append(entry.getKey()).append("\": ");
            entry.getValue().accept(this);
            
            if (i != n - 1)
            {
                buffer.append(",");

                if (indent)
                {
                    indent();
                }
                else
                {
                    buffer.append(' ');
                }
            }

            i++;
        }

        if (indent)
        {
            depth--;
            indent();
        }
        else
        {
            buffer.append(' ');
        }
        
        buffer.append("}");
    }
}
