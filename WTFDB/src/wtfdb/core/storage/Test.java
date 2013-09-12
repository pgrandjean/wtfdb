package wtfdb.core.storage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

import wtfdb.core.data.DataArray;
import wtfdb.core.data.DataMap;
import wtfdb.core.io.IOBuffer;
import wtfdb.core.visitors.DataEmpty;
import wtfdb.core.visitors.DataFormatter;
import wtfdb.core.visitors.DataPaths;

public class Test
{
    private DataMap data0 = null;
    
    private DataMap data1 = null;
    
    private Test()
    {
        // data0
        data0 = new DataMap();

        DataMap subData0 = new DataMap();
        subData0.set("byte", (byte) 1);
        
        DataArray list0 = new DataArray();
        list0.add(2);
        list0.add(subData0);
        data0.set("list", list0);
        
        data0.set("boolean", true);
        data0.set("byte", (byte) 1);
        data0.set("short", (short) 1);
        data0.set("integer", (int) 1);
        data0.set("long", (long) 1);
        data0.set("float", (float) 1);
        data0.set("double", (double) 1);
        data0.set("character", 'c');
        data0.set("string", "1");
        
        data0.set("data", subData0);
        
        // data 1
        data1 = new DataMap();
        
        DataMap subData1 = new DataMap();
        subData1.set("byte", (byte) 1);
        
        DataArray list1 = new DataArray();
        list1.add(2);
        list1.add(subData1);
        data1.set("list", list1);
        
        data1.set("boolean", true);
        data1.set("byte", (byte) 1);
        data1.set("short", (short) 1);
        data1.set("integer", (int) 1);
        data1.set("long", (long) 1);
        data1.set("float", (float) 1);
        data1.set("double", (double) 1);
        data1.set("character", 'c');
        data1.set("string", "1");
        
        data1.set("data", subData1);
    }

    private String formatTime(String prefix, double nano)
    {
        return prefix + nano / 1_000_000 + " ms, tps: " + 1_000_000_000 / nano;
    }
    
    private DataMap newData()
    {
        // data0
        DataMap data0 = new DataMap();

        DataMap subData0 = new DataMap();
        subData0.set("byte", (byte) 1);
        
        DataArray list0 = new DataArray();
        list0.add(Integer.valueOf(2));
        list0.add(subData0);
        data0.set("list", list0);
        
        data0.set("boolean", true);
        data0.set("byte", (byte) 1);
        data0.set("short", (short) 1);
        data0.set("integer", (int) 1);
        data0.set("long", (long) 1);
        data0.set("float", (float) 1);
        data0.set("double", (double) 1);
        data0.set("character", (char) 1);
        data0.set("string", "1");
        
        data0.set("data", subData0);
        
        return data0;
    }
    
    private void testSerialization() throws Exception
    {
        long startTime = 0L;
        long endTime = 0L;
        long elapsedTime = 0L;
        long totalTime = 0L;
        double time = 0.0;
        
        DB db = new DB("test");
        Collection col = db.getCollection("test");
        
        int n = 1_000_000;
        for (int i = 0; i < n; i++)
        {   
            startTime = System.nanoTime();
            col.create(data0);
            endTime = System.nanoTime();
            
            elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
        }
        
        time = (double) (totalTime);
        System.out.println(formatTime("serialization time: ", time / n));

        totalTime = 0L;

        for (int i = 0; i < 1000000; i++)
        {   
            startTime = System.nanoTime();
            data1 = col.readNext();
            endTime = System.nanoTime();
            
            elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
        }
        
        time = (double) (totalTime);
        System.out.println(formatTime("deserialization time: ", time / n));
        
        Assert.assertTrue(data0.equals(data1));
    }

    private void testSerialization2() throws Exception
    {
//        long startTime = 0L;
//        long endTime = 0L;
//        long elapsedTime = 0L;
//        long totalTime = 0L;
//        double time = 0.0;
//        
//        DB db = new DB("toto");
//        Collection collection = db.getCollection("tata");
//        
//        int n = 1_000_000; int i = 0;
//        for (i = 0; i < n; i++)
//        {   
//            startTime = System.nanoTime();
//            collection.create(data0);
//            endTime = System.nanoTime();
//            
//            elapsedTime = endTime - startTime;
//            totalTime += elapsedTime;
//        }
//        
//        collection.read(null, null);
//        
//        time = (double) (totalTime);
//        System.out.println(formatTime("serialization time: ", time / n));
//        
//        db.close();
    }
    
    private String path = "data";
    private String path2 = "list";

    // parse raw return raw
    private void testFormatter()
    {
        StringBuffer buffer = new StringBuffer();
        DataFormatter formatter = new DataFormatter(buffer, true, true);
        data0.accept(formatter);
        
        String string = buffer.toString();
        System.out.println(string);
        
        System.out.println();
    }

    // parse raw return raw
    private void testPaths()
    {
        long startTime = 0L;
        long endTime = 0L;
        long elapsedTime = 0L;
        long totalTime = 0L;
        double time = 0.0;

        DataPaths paths = new DataPaths();
//      DataEmpty paths = new DataEmpty();
        
        for (int i = 0; i < 1000000; i++)
        {   
            paths.clear();
            
            startTime = System.nanoTime();
            data0.accept(paths);
            endTime = System.nanoTime();
            
            elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
        }

        System.out.println(paths.getColumns());
        
        time = (double) (totalTime);
        System.out.println(formatTime("paths time: ", time / 1_000_000));
    }
    
    // parse raw return raw
    private void testResolver() throws IOException
    {
        long startTime = 0L;
        long endTime = 0L;
        long elapsedTime = 0L;
        long totalTime = 0L;
        double time = 0.0;
        
//        DataVisitor visitor = new DataVisitor();
//        Data data = null;
//        
//        for (int i = 0; i < 1000000; i++)
//        {
//            data = newData();
//            startTime = System.nanoTime();
//            Data res = visitor.visit(data, path2);
//            endTime = System.nanoTime();
//            elapsedTime = endTime - startTime;
//            totalTime += elapsedTime;
//        }
//
//        time = (double) (totalTime);
//        System.out.println("solve time: " + time / 1_000_000 / 1_000_000 +
//                           " tps: " + 1 / time * 1_000_000 * 1_000_000 * 1_000);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        while (System.in.read() != '\n');
        
        System.out.println(null instanceof Object);
        Test test = new Test();
        test.testFormatter();
        test.testPaths();
        test.testSerialization();
        test.testSerialization2();
        test.testResolver();
        
        Pattern keyPattern = Pattern.compile("(\\w+)\\[(\\d+)\\]");
        Matcher keyMatcher = keyPattern.matcher("toto[00]");
        
        System.out.println(keyMatcher.matches());
        System.out.println(keyMatcher.group(1));
        System.out.println(Integer.valueOf(keyMatcher.group(2)));
        
        byte[] a = new byte[3];
        System.out.println(a.getClass());
        
        String[] split = "toto".split("\\.");
        System.out.println(split.length + " " + split[0]);
    }
}
