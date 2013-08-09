package wtfdb.core.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class IOBuffer implements DataInput, DataOutput
{
    private ByteBuffer buffer = null;
    
    public IOBuffer(ByteBuffer buffer)
    {
        this.buffer = buffer;
    }
    
    public int position()
    {
        return buffer.position();
    }
    
    public void position(int position)
    {
        buffer.position(position);
    }
    
    @Override
    public void write(int b)
    {
        buffer.putInt(b);
    }

    @Override
    public void write(byte[] b)
    {
        buffer.put(b);
    }

    @Override
    public void write(byte[] b, int off, int len)
    {
        buffer.put(b, off, len);
    }

    @Override
    public void writeBoolean(boolean v)
    {
        buffer.put((byte) (v ? 1 : 0));
    }

    @Override
    public void writeByte(int v)
    {
        buffer.put((byte) v);
    }

    @Override
    public void writeShort(int v)
    {
        buffer.putShort((short) v);
    }

    @Override
    public void writeChar(int v)
    {
        buffer.putChar((char) v);
    }

    @Override
    public void writeInt(int v)
    {
        buffer.putInt(v);
    }

    public void writeInt(int pos, int v)
    {
        buffer.putInt(pos, v);
    }
    
    @Override
    public void writeLong(long v)
    {
        buffer.putLong(v);
    }

    @Override
    public void writeFloat(float v)
    {
        buffer.putFloat(v);
    }

    @Override
    public void writeDouble(double v)
    {
        buffer.putDouble(v);
    }

    @Override
    public void writeBytes(String s)
    {
        int size = s.length();
        for (int i = 0 ; i < size ; i++) 
        {
            buffer.put((byte) s.charAt(i));
        }
    }

    @Override
    public void writeChars(String s)
    {
        int size = s.length();
        for (int i = 0 ; i < size ; i++) 
        {
            buffer.putChar(s.charAt(i));
        }
    }

    @Override
    public void writeUTF(String s)
    {
        try
        {
            byte[] b = s.getBytes("UTF-8");
            buffer.putInt(b.length);
            buffer.put(b);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IOException("could not write string: " + s, e);
        }
    }

    @Override
    public void readFully(byte[] b)
    {
        readFully(b, 0, b.length);
    }

    @Override
    public void readFully(byte[] b, int off, int len)
    {
        buffer.get(b, off, len);
    }

    @Override
    public int skipBytes(int n)
    {
        int p = buffer.position();
        buffer.position(p + n);
        
        return n;
    }

    @Override
    public boolean readBoolean()
    {
        return buffer.get() != 0;
    }

    @Override
    public byte readByte()
    {
        return buffer.get();
    }

    @Override
    public int readUnsignedByte()
    {
        return buffer.get();
    }

    @Override
    public short readShort()
    {
        return buffer.getShort();
    }

    @Override
    public int readUnsignedShort()
    {
        int b1 = buffer.get();
        int b2 = buffer.get();
        
        return (b1 << 8) + (b2 << 0);
    }

    @Override
    public char readChar()
    {
        return buffer.getChar();
    }

    @Override
    public int readInt()
    {
        return buffer.getInt();
    }

    @Override
    public long readLong()
    {
        return buffer.getLong();
    }

    @Override
    public float readFloat()
    {
        return buffer.getFloat();
    }

    @Override
    public double readDouble()
    {
        return buffer.getDouble();
    }

    @Deprecated
    @Override
    public String readLine()
    {
        throw new NoSuchMethodError("deprecated");
    }

    @Override
    public String readUTF()
    {
        int size = buffer.getInt();
        byte[] b = new byte[size];
        buffer.get(b, 0, size);
        
        try
        {
            return new String(b, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IOException("could not read string", e);
        }
    }
}
