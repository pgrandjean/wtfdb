package wtfdb.core.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

public class DataSerializer
{
    private ByteArrayInputStream input = null;
    
    private DataInputStream deserializer = null;
    
    private ByteArrayOutputStream output = null;
    
    private DataOutputStream serializer = null;
    
    private void serialize0(Object value) throws IOException
    {
        Class<?> klass = value.getClass();
        Byte typeByte = DataTypes.getType(klass);
        if (typeByte == null)
        {
            throw new IOException("unknown type: " + klass.getCanonicalName());
        }
        
        switch (typeByte)
        {
            case DataTypes.BOOLEAN:
                serializer.writeByte(DataTypes.BOOLEAN);
                serializer.writeBoolean((boolean) value);
                break;

            case DataTypes.BYTE:
                serializer.writeByte(DataTypes.BYTE);
                serializer.writeByte((byte) value);
                break;

            case DataTypes.SHORT:
                serializer.writeByte(DataTypes.SHORT);
                serializer.writeShort((short) value);
                break;

            case DataTypes.INTEGER:
                serializer.writeByte(DataTypes.INTEGER);
                serializer.writeInt((int) value);
                break;

            case DataTypes.LONG:
                serializer.writeByte(DataTypes.LONG);
                serializer.writeLong((long) value);
                break;

            case DataTypes.FLOAT:
                serializer.writeByte(DataTypes.FLOAT);
                serializer.writeFloat((float) value);
                break;

            case DataTypes.DOUBLE:
                serializer.writeByte(DataTypes.DOUBLE);
                serializer.writeDouble((double) value);
                break;

            case DataTypes.CHAR:
                serializer.writeByte(DataTypes.CHAR);
                serializer.writeChar((char) value);
                break;

            case DataTypes.STRING:
                serializer.writeByte(DataTypes.STRING);
                serializer.writeUTF((String) value);
                break;

            case DataTypes.BYTE_ARRAY:
                byte[] bytes = (byte[]) value;
                serializer.writeByte(DataTypes.BYTE_ARRAY);
                serializer.writeInt(bytes.length);
                serializer.write(bytes);
                break;
                
            case DataTypes.DATE:
                Date date = (Date) value;
                serializer.writeByte(DataTypes.DATE);
                serializer.writeLong(date.getTime());
                break;
                
            case DataTypes.ARRAY:
                List<?> list = (List<?>) value;
                serializer.writeByte(DataTypes.ARRAY);
                serializer.writeInt(list.size());
                
                for (Object o : list)
                {
                    serialize0(o);
                }
                
                break;

            case DataTypes.DATA:
                Data data = (Data) value;
                serializer.writeByte(DataTypes.DATA);
                serializer.writeInt(data.size());
                
                for (Entry<String, Object> entry : data)
                {
                    serializer.writeUTF(entry.getKey());
                    
                    Object o = entry.getValue();
                    serialize0(o);
                }
                
                break;

            default:
                throw new IOException("unknown type: " + klass.getCanonicalName());
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object deserialize0() throws IOException
    {
        Object o = null;
        int type = deserializer.readByte();
        
        switch (type)
        {
            case DataTypes.BOOLEAN:
                o = deserializer.readBoolean();
                break;
                
            case DataTypes.BYTE:
                o = deserializer.readByte();
                break;
                
            case DataTypes.SHORT:
                o = deserializer.readShort();
                break;
                
            case DataTypes.INTEGER:
                o = deserializer.readInt();
                break;
                
            case DataTypes.LONG:
                o = deserializer.readLong();
                break;
                
            case DataTypes.FLOAT:
                o = deserializer.readFloat();
                break;
                
            case DataTypes.DOUBLE:
                o = deserializer.readDouble();
                break;
                
            case DataTypes.CHAR:
                o = deserializer.readChar();
                break;
                
            case DataTypes.STRING:
                o = deserializer.readUTF();
                break;

            case DataTypes.BYTE_ARRAY:
            {
                int size = deserializer.readInt();
                byte[] bytes = new byte[size];
                deserializer.read(bytes, 0, size);
                o = bytes;
                break;
            }
                
            case DataTypes.DATE:
                long date = deserializer.readLong();
                o = new Date(date);
                break;
                
            case DataTypes.ARRAY:
                int vectorSize = deserializer.readInt();
                Vector vector = new Vector<>(vectorSize);
                
                for (int j = 0; j < vectorSize; j++)
                {
                    vector.add(deserialize0());
                }
                
                o = vector;
                break;
                
            case DataTypes.DATA:
                int dataSize = deserializer.readInt();
                Data data = new Data();
                
                for (int k = 0; k < dataSize; k++)
                {
                    String key = deserializer.readUTF();
                    Object value = deserialize0();
                    
                    data.set(key, value);
                }
                
                o = data;
                break;

            default:
                throw new IOException("unknown type: " + type);
        }
        
        return o;
    }

    public byte[] serialize(Data data) throws IOException
    {
        output = new ByteArrayOutputStream();
        serializer = new DataOutputStream(output);
        
        serialize0(data);
        
        // no need to flush nor close
        return output.toByteArray();
    }

    public Data deserialize(byte[] raw) throws IOException
    {
        input = new ByteArrayInputStream(raw);
        deserializer = new DataInputStream(input);
        
        return (Data) deserialize0();
    }
}
