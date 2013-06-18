package wtfdb.core.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.Vector;

public class DataSerializer
{
    private DataSerializer()
    {
        
    }
    
//    protected static void serialize(Boolean value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.BOOLEAN.ordinal());
//        output.writeBoolean(value);
//    }
//
//    protected static void serialize(Byte value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.BYTE.ordinal());
//        output.writeByte(value);
//    }
//    
//    protected static void serialize(Short value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.SHORT.ordinal());
//        output.writeShort(value);
//    }
//
//    protected static void serialize(Integer value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.INTEGER.ordinal());
//        output.writeInt(value);
//    }
//
//    protected static void serialize(Long value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.LONG.ordinal());
//        output.writeLong(value);
//    }
//
//    protected static void serialize(Float value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.FLOAT.ordinal());
//        output.writeFloat(value);
//    }
//
//    protected static void serialize(Double value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.DOUBLE.ordinal());
//        output.writeDouble(value);
//    }
//
//    protected static void serialize(Character value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.CHAR.ordinal());
//        output.writeChar(value);
//    }
//
//    protected static void serialize(String value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.STRING.ordinal());
//        output.writeUTF(value);
//    }
//
//    @SuppressWarnings("rawtypes")
//    protected static void serialize(Vector value, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.VECTOR.ordinal());
//        output.writeInt(value.size());
//        
//        for (Object o : value)
//        {
//            serialize(o, output);
//        }
//    }
//    
//    protected static void serialize(Object data, DataOutputStream output) throws IOException
//    {
//        try
//        {
//            Class<?> klass = data.getClass();
//            DataSerializer.class.getDeclaredMethod("serialize", klass, DataOutputStream.class).invoke(null, data, output);
//        }
//        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
//        {
//            throw new NoSuchMethodError("format of " + data.getClass().getCanonicalName() + " not supported");
//        }
//    }
//    
//    protected static void serialize(Data data, DataOutputStream output) throws IOException
//    {
//        output.writeByte(TYPE.DATA.ordinal());
//        output.writeInt(data.size());
//        
//        for (String key : data)
//        {
//            output.writeUTF(key);
//            
//            Object value = data.get(key);
//            serialize(value, output);
//        }
//    }
//
//    protected static void serialize0(Object value, DataOutputStream output) throws IOException
//    {
//        Class<?> klass = value.getClass();
//        
//        if (klass == Boolean.class)
//        {
//            output.writeByte(DataTypes.BOOLEAN);
//            output.writeBoolean((boolean) value);
//        }
//        else if (klass == Byte.class)
//        {
//            output.writeByte(DataTypes.BYTE);
//            output.writeByte((byte) value);
//        }
//        else if (klass == Short.class)
//        {
//            output.writeByte(DataTypes.SHORT);
//            output.writeShort((short) value);
//        }
//        else if (klass == Integer.class)
//        {
//            output.writeByte(DataTypes.INTEGER);
//            output.writeInt((int) value);
//        }
//        else if (klass == Long.class)
//        {
//            output.writeByte(DataTypes.LONG);
//            output.writeLong((long) value);
//        }
//        else if (klass == Float.class)
//        {
//            output.writeByte(DataTypes.FLOAT);
//            output.writeFloat((float) value);
//        }
//        else if (klass == Double.class)
//        {
//            output.writeByte(DataTypes.DOUBLE);
//            output.writeDouble((double) value);
//        }
//        else if (klass == Character.class)
//        {
//            output.writeByte(DataTypes.CHAR);
//            output.writeChar((char) value);
//        }
//        else if (klass == String.class)
//        {
//            output.writeByte(DataTypes.STRING);
//            output.writeUTF((String) value);
//        }
//        else if (klass == Vector.class)
//        {
//            List<?> list = (List<?>) value;
//            output.writeByte(DataTypes.VECTOR);
//            output.writeInt(list.size());
//            
//            for (Object o : list)
//            {
//                serialize0(o, output);
//            }
//        }
//        else if (klass == Data.class)
//        {
//            Data data = (Data) value;
//            output.writeByte(DataTypes.DATA);
//            output.writeInt(data.size());
//            
//            for (String key : data)
//            {
//                output.writeUTF(key);
//                serialize0(data.get(key), output);
//            }
//        }
//        else
//        {
//            throw new IOException("unknown type: " + klass.getCanonicalName());
//        }
//    }
    @SuppressWarnings("unchecked")
    protected static void serialize00(Data data, DataOutputStream output) throws IOException
    {
        if (data == null || data.size() == 0) return;
        
        Stack<Object> stack = new Stack<>();
        stack.add(data);
        
        Object value = null;
        Class<?> klass = null;
        Byte type = null;
        
        while (!stack.empty())
        {
            value = stack.pop();

            if (value instanceof Entry)
            {
                Entry<String, Object> entry = (Entry<String, Object>) value;
                output.writeUTF(entry.getKey());
                
                value = entry.getValue();
            }
            
            klass = value.getClass();
            type = DataTypes.getType(klass);
            if (type == null)
            {
                throw new IOException("unknown type: " + klass.getCanonicalName());
            }
            
            switch (type)
            {
                case DataTypes.BOOLEAN:
                    output.writeByte(DataTypes.BOOLEAN);
                    output.writeBoolean((boolean) value);
                    break;

                case DataTypes.BYTE:
                    output.writeByte(DataTypes.BYTE);
                    output.writeByte((byte) value);
                    break;

                case DataTypes.SHORT:
                    output.writeByte(DataTypes.SHORT);
                    output.writeShort((short) value);
                    break;

                case DataTypes.INTEGER:
                    output.writeByte(DataTypes.INTEGER);
                    output.writeInt((int) value);
                    break;

                case DataTypes.LONG:
                    output.writeByte(DataTypes.LONG);
                    output.writeLong((long) value);
                    break;

                case DataTypes.FLOAT:
                    output.writeByte(DataTypes.FLOAT);
                    output.writeFloat((float) value);
                    break;

                case DataTypes.DOUBLE:
                    output.writeByte(DataTypes.DOUBLE);
                    output.writeDouble((double) value);
                    break;

                case DataTypes.CHAR:
                    output.writeByte(DataTypes.CHAR);
                    output.writeChar((char) value);
                    break;

                case DataTypes.STRING:
                    output.writeByte(DataTypes.STRING);
                    output.writeUTF((String) value);
                    break;

                case DataTypes.BYTE_ARRAY:
                    byte[] bytes = (byte[]) value;
                    output.writeByte(DataTypes.BYTE_ARRAY);
                    output.writeInt(bytes.length);
                    output.write(bytes);
                    break;
                    
                case DataTypes.DATE:
                    Date date = (Date) value;
                    output.writeByte(DataTypes.DATE);
                    output.writeLong(date.getTime());
                    break;
                    
                case DataTypes.ARRAY:
                    List<?> list = (List<?>) value;
                    output.writeByte(DataTypes.ARRAY);
                    output.writeInt(list.size());
                    stack.addAll(list);
                    break;

                case DataTypes.DATA:
                    data = (Data) value;
                    output.writeByte(DataTypes.DATA);
                    output.writeInt(data.size());
                    stack.addAll(data.entrySet());
                    break;

                default:
                    throw new IOException("unknown type: " + klass.getCanonicalName());
            }
        }
    }

    protected static void serialize0(Object value, DataOutputStream output) throws IOException
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
                output.writeByte(DataTypes.BOOLEAN);
                output.writeBoolean((boolean) value);
                break;

            case DataTypes.BYTE:
                output.writeByte(DataTypes.BYTE);
                output.writeByte((byte) value);
                break;

            case DataTypes.SHORT:
                output.writeByte(DataTypes.SHORT);
                output.writeShort((short) value);
                break;

            case DataTypes.INTEGER:
                output.writeByte(DataTypes.INTEGER);
                output.writeInt((int) value);
                break;

            case DataTypes.LONG:
                output.writeByte(DataTypes.LONG);
                output.writeLong((long) value);
                break;

            case DataTypes.FLOAT:
                output.writeByte(DataTypes.FLOAT);
                output.writeFloat((float) value);
                break;

            case DataTypes.DOUBLE:
                output.writeByte(DataTypes.DOUBLE);
                output.writeDouble((double) value);
                break;

            case DataTypes.CHAR:
                output.writeByte(DataTypes.CHAR);
                output.writeChar((char) value);
                break;

            case DataTypes.STRING:
                output.writeByte(DataTypes.STRING);
                output.writeUTF((String) value);
                break;

            case DataTypes.BYTE_ARRAY:
                byte[] bytes = (byte[]) value;
                output.writeByte(DataTypes.BYTE_ARRAY);
                output.writeInt(bytes.length);
                output.write(bytes);
                break;
                
            case DataTypes.DATE:
                Date date = (Date) value;
                output.writeByte(DataTypes.DATE);
                output.writeLong(date.getTime());
                break;
                
            case DataTypes.ARRAY:
                List<?> list = (List<?>) value;
                output.writeByte(DataTypes.ARRAY);
                output.writeInt(list.size());
                
                for (Object o : list)
                {
                    serialize0(o, output);
                }
                break;

            case DataTypes.DATA:
                Data data = (Data) value;
                output.writeByte(DataTypes.DATA);
                output.writeInt(data.size());
                
                for (String key : data)
                {
                    output.writeUTF(key);
                    
                    Object o = data.get(key);
                    serialize0(o, output);
                }
                break;

            default:
                throw new IOException("unknown type: " + klass.getCanonicalName());
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected static Object deserialize0(DataInputStream input) throws IOException
    {
        Object o = null;
        
        int type = input.readByte();
        switch (type)
        {
            case DataTypes.BOOLEAN:
                o = input.readBoolean();
                break;
                
            case DataTypes.BYTE:
                o = input.readByte();
                break;
                
            case DataTypes.SHORT:
                o = input.readShort();
                break;
                
            case DataTypes.INTEGER:
                o = input.readInt();
                break;
                
            case DataTypes.LONG:
                o = input.readLong();
                break;
                
            case DataTypes.FLOAT:
                o = input.readFloat();
                break;
                
            case DataTypes.DOUBLE:
                o = input.readDouble();
                break;
                
            case DataTypes.CHAR:
                o = input.readChar();
                break;
                
            case DataTypes.STRING:
                o = input.readUTF();
                break;

            case DataTypes.BYTE_ARRAY:
                int arraySize = input.readInt();
                byte[] bytes = new byte[arraySize];
                input.read(bytes, 0, arraySize);
                o = bytes;
                break;
                
            case DataTypes.DATE:
                long date = input.readLong();
                o = new Date(date);
                break;
                
            case DataTypes.ARRAY:
                int vectorSize = input.readInt();
                Vector vector = new Vector<>(vectorSize);
                
                for (int j = 0; j < vectorSize; j++)
                {
                    vector.add(deserialize0(input));
                }
                
                o = vector;
                break;
                
            case DataTypes.DATA:
                int dataSize = input.readInt();
                Data data = new Data();
                
                for (int k = 0; k < dataSize; k++)
                {
                    String key = input.readUTF();
                    Object value = deserialize0(input);
                    
                    data.set(key, value);
                }
                
                o = data;
                break;

            default:
                throw new IOException("unknown type: " + type);
        }
        
        return o;
    }

    public static byte[] serialize(Data data) throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(output);
        
        serialize0(data, dataOutput);
        
        // no need to flush nor close
        return output.toByteArray();
    }

    public static byte[] serialize2(Data data) throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(output);
        
        serialize00(data, dataOutput);
        
        // no need to flush nor close
        return output.toByteArray();
    }
    
    public static Data deserialize(byte[] bytes) throws IOException
    {
        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        DataInputStream dataInput = new DataInputStream(input);

        // no need to close
        return (Data) deserialize0(dataInput);
    }
}
