package wtfdb.core.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataVisitor
{   
    private DataPath targPath = null;
    
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    
    private DataOutputStream serializer = new DataOutputStream(buffer);
    
    public DataVisitor()
    {
        
    }

    private byte[] visit0(
            DataPath currPath,
            DataInputStream input
            ) throws IOException
    {
        boolean matches = targPath.matches(currPath);
        
        int type = input.readByte();
        switch (type)
        {
            case DataTypes.BOOLEAN:
            {
                boolean value = input.readBoolean();
                
                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.BOOLEAN);
                    serializer.writeBoolean(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.BYTE:
            {
                byte value = input.readByte();

                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.BYTE);
                    serializer.writeByte(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.SHORT:
            {
                short value = input.readShort();

                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.SHORT);
                    serializer.writeShort(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.INTEGER:
            {
                int value = input.readInt();

                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.INTEGER);
                    serializer.writeInt(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.LONG:
            {
                long value = input.readLong();

                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.LONG);
                    serializer.writeLong(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
            
            case DataTypes.FLOAT:
            {
                float value = input.readFloat();

                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.FLOAT);
                    serializer.writeFloat(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.DOUBLE:
            {
                double value = input.readDouble();

                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.DOUBLE);
                    serializer.writeDouble(value);
                    return buffer.toByteArray();
                }

                break;
            }
                
            case DataTypes.CHAR:
            {
                char value = input.readChar();

                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.CHAR);
                    serializer.writeChar(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.STRING:
            {
                String value = input.readUTF();

                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.STRING);
                    serializer.writeUTF(value);
                    return buffer.toByteArray();
                }

                break;
            }

            case DataTypes.BYTE_ARRAY:
            {
                int size = input.readInt();
                byte[] value = new byte[size];
                input.read(value, 0, size);
                
                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.BYTE_ARRAY);
                    serializer.writeInt(size);
                    serializer.write(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.DATE:
            {
                long value = input.readLong();
                
                if (matches)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.DATE);
                    serializer.writeLong(value);
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.ARRAY:
            {
                int size = input.readInt();
                int count = 0;
                
                byte[][] list = new byte[size][];
                for (int i = 0; i < size; i++)
                {
                    currPath.add(i);
                    byte[] bytes = visit0(currPath, input);
                    currPath.poll();
                    
                    if (bytes != null)
                    {
                        list[count++] = bytes;
                    }
                }
                
                if (count > 0)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.ARRAY);
                    serializer.writeInt(count);
                    for (int i = 0; i < count; i++)
                    {
                        serializer.write(list[i]);
                    }
                    
                    return buffer.toByteArray();
                }
                
                break;
            }
                
            case DataTypes.DATA:
            {
                int size = input.readInt();
                int count = 0;
                
                String[] k = new String[size];
                byte[][] v = new byte[size][];
                for (int i = 0; i < size; i++)
                {
                    String key = input.readUTF();
                    
                    currPath.add(key);
                    byte[] bytes = visit0(currPath, input);
                    currPath.poll();
                    
                    if (bytes != null)
                    {
                        k[count] = key;
                        v[count] = bytes;
                        count++;
                    }
                }
                
                if (count > 0)
                {
                    buffer.reset();
                    serializer.writeByte(DataTypes.DATA);
                    serializer.writeInt(count);
                    for (int i = 0; i < count; i++)
                    {
                        serializer.writeUTF(k[i]);
                        serializer.write(v[i]);
                    }

                    return buffer.toByteArray();
                }
                
                break;
            }

            default:
                throw new IOException("unknown type: " + type);
        }
        
        return null;
    }
    
    public byte[] visit(byte[] raw, String path) throws IOException
    {
        if (raw == null) return null;
        if (path == null) return null;
        if (raw.length == 0) return null;

        targPath = new DataPath(path);
        if (targPath == null) return null;
        
        ByteArrayInputStream input = new ByteArrayInputStream(raw);
        DataInputStream inputData = new DataInputStream(input);
        
        DataPath currPath = new DataPath();
        byte[] res = visit0(
                currPath,
                inputData
                );
        
        return res;
    }
}
