package wtfdb.core.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

public class DataBuffer implements DataInput, DataOutput
{
    private ByteBuffer buffer = null;
    
    public DataBuffer(ByteBuffer buffer)
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
    
<<<<<<< HEAD
    public void writeInt(int index, int b) throws IOException
    {
        buffer.putInt(index, b);
    }
    
=======
>>>>>>> branch 'master' of https://github.com/pgrandjean/wtfdb.git
    @Override
    public void write(int b) throws IOException
    {
        buffer.putInt(b);
    }

    @Override
    public void write(byte[] b) throws IOException
    {
        buffer.put(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException
    {
        buffer.put(b, off, len);
    }

    @Override
    public void writeBoolean(boolean v) throws IOException
    {
        buffer.put((byte) (v ? 1 : 0));
    }

    @Override
    public void writeByte(int v) throws IOException
    {
        buffer.put((byte) v);
    }

    @Override
    public void writeShort(int v) throws IOException
    {
        buffer.putShort((short) v);
    }

    @Override
    public void writeChar(int v) throws IOException
    {
        buffer.putChar((char) v);
    }

    @Override
    public void writeInt(int v) throws IOException
    {
        buffer.putInt(v);
    }

    public void writeInt(int pos, int v) throws IOException
    {
        buffer.putInt(pos, v);
    }
    
    @Override
    public void writeLong(long v) throws IOException
    {
        buffer.putLong(v);
    }

    @Override
    public void writeFloat(float v) throws IOException
    {
        buffer.putFloat(v);
    }

    @Override
    public void writeDouble(double v) throws IOException
    {
        buffer.putDouble(v);
    }

    @Override
    public void writeBytes(String s) throws IOException
    {
        int size = s.length();
        for (int i = 0 ; i < size ; i++) 
        {
            buffer.put((byte) s.charAt(i));
        }
    }

    @Override
    public void writeChars(String s) throws IOException
    {
        int size = s.length();
        for (int i = 0 ; i < size ; i++) 
        {
            buffer.putChar(s.charAt(i));
        }
    }

    @Override
    public void writeUTF(String s) throws IOException
    {
        byte[] b = s.getBytes("UTF-8");
        buffer.putInt(b.length);
        buffer.put(b);
    }

    @Override
    public void readFully(byte[] b) throws IOException
    {
        readFully(b, 0, b.length);
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException
    {
        buffer.get(b, off, len);
    }

    @Override
    public int skipBytes(int n) throws IOException
    {
        int p = buffer.position();
        buffer.position(p + n);
        
        return n;
    }

    @Override
    public boolean readBoolean() throws IOException
    {
        return buffer.get() != 0;
    }

    @Override
    public byte readByte() throws IOException
    {
        return buffer.get();
    }

    @Override
    public int readUnsignedByte() throws IOException
    {
        return buffer.get();
    }

    @Override
    public short readShort() throws IOException
    {
        return buffer.getShort();
    }

    @Override
    public int readUnsignedShort() throws IOException
    {
        int b1 = buffer.get();
        int b2 = buffer.get();
        
        return (b1 << 8) + (b2 << 0);
    }

    @Override
    public char readChar() throws IOException
    {
        return buffer.getChar();
    }

    @Override
    public int readInt() throws IOException
    {
        return buffer.getInt();
    }

    @Override
    public long readLong() throws IOException
    {
        return buffer.getLong();
    }

    @Override
    public float readFloat() throws IOException
    {
        return buffer.getFloat();
    }

    @Override
    public double readDouble() throws IOException
    {
        return buffer.getDouble();
    }

    @Deprecated
    @Override
    public String readLine() throws IOException
    {
        throw new NoSuchMethodError("deprecated");
    }

    @Override
    public String readUTF() throws IOException
    {
        int size = buffer.getInt();
        byte[] b = new byte[size];
        buffer.get(b, 0, size);
        
        return new String(b, "UTF-8");
    }
}
