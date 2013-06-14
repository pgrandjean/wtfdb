package wtfdb.core.data;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

public abstract class DataVisitor
{   
    public abstract boolean visit(DataPath path, Boolean value);

    public abstract boolean visit(DataPath path, Byte value);

    public abstract boolean visit(DataPath path, Short value);

    public abstract boolean visit(DataPath path, Integer value);

    public abstract boolean visit(DataPath path, Long value);

    public abstract boolean visit(DataPath path, Float value);

    public abstract boolean visit(DataPath path, Double value);

    public abstract boolean visit(DataPath path, Character value);

    public abstract boolean visit(DataPath path, String value);

    public abstract boolean visit(DataPath path, byte[] value);

    public abstract boolean visit(DataPath path, Date value);
    
    private boolean visit0(DataPath path, DataInputStream input) throws IOException
    {
        int type = input.readByte();
        boolean cont = true;
        
        switch (type)
        {
            case DataTypes.BOOLEAN:
                if (cont) cont = visit(path, input.readBoolean());
                break;
                
            case DataTypes.BYTE:
                if (cont) cont = visit(path, input.readByte());
                break;
                
            case DataTypes.SHORT:
                if (cont) cont = visit(path, input.readShort());
                break;
                
            case DataTypes.INTEGER:
                if (cont) cont = visit(path, input.readInt());
                break;
                
            case DataTypes.LONG:
                if (cont) cont = visit(path, input.readLong());
                break;
                
            case DataTypes.FLOAT:
                if (cont) cont = visit(path, input.readFloat());
                break;
                
            case DataTypes.DOUBLE:
                if (cont) cont = visit(path, input.readDouble());
                break;
                
            case DataTypes.CHAR:
                if (cont) cont = visit(path, input.readChar());
                break;
                
            case DataTypes.STRING:
                if (cont) cont = visit(path, input.readUTF());
                break;
                
            case DataTypes.ARRAY:
                int vectorSize = input.readInt();
                
                for (int j = 0; j < vectorSize; j++)
                {
                    path.add(j);
                    if (cont) cont = visit0(path, input);
                    path.poll();

                    if (!cont) break;
                }
                
                break;
                
            case DataTypes.DATA:
                int dataSize = input.readInt();
                
                for (int k = 0; k < dataSize; k++)
                {
                    String key = input.readUTF();
                    
                    path.add(key);
                    if (cont) cont = visit0(path, input);
                    path.poll();

                    if (!cont) break;
                }
                
                break;

            case DataTypes.BYTE_ARRAY:
                int arraySize = input.readInt();
                byte[] bytes = new byte[arraySize];
                
                for (int k = 0; k < arraySize; k++)
                {
                    bytes[k] = input.readByte();
                }
                
                if (cont) cont = visit(path, bytes);
                break;
                
            case DataTypes.DATE:
                long date = input.readLong();
                if (cont) cont = visit(path, new Date(date));
                break;
                
            default:
                throw new IOException("unknown type: " + type);
        }
        
        return cont;
    }
    
    public final void visit(byte[] raw) throws IOException
    {
        ByteArrayInputStream input = new ByteArrayInputStream(raw);
        DataInputStream dataInput = new DataInputStream(input);
       
        visit0(new DataPath(), dataInput);
    }
}
