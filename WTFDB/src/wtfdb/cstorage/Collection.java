package wtfdb.cstorage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import wtfdb.core.data.DataMap;
import wtfdb.core.data.DataPath;
import wtfdb.core.data.DataPrimitive;
import wtfdb.core.io.IOBuffer;

public class Collection
{
    private class MappedFileInfo
    {
        private RandomAccessFile file = null;
        
        private MappedByteBuffer buffer = null;
        
        private IOBuffer iobuffer = null;
    }
    
    private DB db = null;
    
    private String name = null;
    
    private Map<DataPath, MappedFileInfo> storage = new LinkedHashMap<>();
    
    protected Collection(DB db, String name)
    {
        this.db = db;
        this.name = name;
    }
    
    private String getFilename(DataPath path)
    {
        return db.getName() + "." + name + "." + path + ".wftdb";
    }
    
    protected IOBuffer getIOBuffer(DataPath path)
    {
        MappedFileInfo fileinfo = storage.get(path);
        if (fileinfo == null)
        {   
            String filename = getFilename(path);
            
            try
            {
                fileinfo = new MappedFileInfo();
                fileinfo.file = new RandomAccessFile(filename, "rw");
                fileinfo.buffer = fileinfo.file.getChannel().map(MapMode.READ_WRITE, 0, 50 * 1_024 * 1_024);
                fileinfo.iobuffer = new IOBuffer(fileinfo.buffer);
            
                storage.put(path, fileinfo);
            
                return fileinfo.iobuffer;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
        
        return fileinfo.iobuffer;
    }
    
    public void close() throws IOException
    {
        for (Entry<DataPath, MappedFileInfo> entry : storage.entrySet())
        {
            entry.getValue().buffer.force();
            entry.getValue().file.close();
        }
    }
    
    public void create(DataMap data)
    {
        long id = IdGenerator.next();
        data.set("_id", id);
        
        IOSerializer serializer = new IOSerializer(this, true);
        data.accept(serializer);
    }
    
    public DataMap read(DataMap projections, DataMap conditions)
    {
        return null;
    }
    
    public void update(DataMap data, DataMap conditions)
    {
        
    }
    
    public void delete(DataMap conditions)
    {
        
    }
}
