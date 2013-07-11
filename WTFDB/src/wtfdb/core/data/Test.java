package wtfdb.core.data;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

import wtfdb.core.data.Data;
import wtfdb.core.data.DataSerializer;
import wtfdb.core.data.DataTypes;
import wtfdb.core.data.DataVisitor;

public class Test
{
    private Data data0 = null;
    
    private Data data1 = null;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Test()
    {
        // data0
        data0 = new Data();

        Data subData0 = new Data();
        subData0.set("byte", (byte) 1);
        
        Vector list0 = new Vector<>();
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
        
        // data 1
        data1 = new Data();
        
        Data subData1 = new Data();
        subData1.set("byte", (byte) 1);
        
        Vector list1 = new Vector<>();
        list1.add(Integer.valueOf(2));
        list1.add(subData1);
        data1.set("list", list1);
        
        data1.set("boolean", true);
        data1.set("byte", (byte) 1);
        data1.set("short", (short) 1);
        data1.set("integer", (int) 1);
        data1.set("long", (long) 1);
        data1.set("float", (float) 1);
        data1.set("double", (double) 1);
        data1.set("character", (char) 1);
        data1.set("string", "1");
        
        data1.set("data", subData1);
    }

    private Data newData()
    {
        // data0
        Data data0 = new Data();

        Data subData0 = new Data();
        subData0.set("byte", (byte) 1);
        
        Vector list0 = new Vector<>();
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
        DataTypes.getType(null);
        
        long startTime = 0L;
        long endTime = 0L;
        long elapsedTime = 0L;
        long totalTime = 0L;
        double time = 0.0;
        byte[] bytes = null;
        
        DataSerializer serializer = new DataSerializer();
        
        for (int i = 0; i < 1000000; i++)
        {
            startTime = System.nanoTime();
            bytes = serializer.serialize(data0);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
        }
        
        time = (double) (totalTime);
        System.out.println("serialization time: " + time / 1000000 / 1000000);
        System.out.println(bytes.length);

        totalTime = 0L;
        for (int i = 0; i < 1000000; i++)
        {
            startTime = System.nanoTime();
            data1 = serializer.deserialize(bytes);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
        }
        
        time = (double) (totalTime);
        System.out.println("deserialization time: " + time / 1000000 / 1000000);
        
        Assert.assertTrue(data0.equals(data1));
    }
    
    private String path = "data";
    private String path2 = "list";
    
    // parse raw return raw
    private void testResolver() throws IOException
    {
        long startTime = 0L;
        long endTime = 0L;
        long elapsedTime = 0L;
        long totalTime = 0L;
        double time = 0.0;
        
        DataVisitor visitor = new DataVisitor();
        Data data = null;
        
        for (int i = 0; i < 1000000; i++)
        {
            data = newData();
            startTime = System.nanoTime();
            Data res = visitor.visit(data, path2);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            totalTime += elapsedTime;
        }

        time = (double) (totalTime);
        System.out.println("solve time: " + time / 1_000_000 / 1_000_000 +
                           " tps: " + 1 / time * 1_000_000 * 1_000_000 * 1_000);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println(null instanceof Object);
        Test test = new Test();
        test.testSerialization();
        test.testResolver();
        
        Pattern keyPattern = Pattern.compile("(\\w+)\\[(\\d+)\\]");
        Matcher keyMatcher = keyPattern.matcher("toto[00]");
        
        System.out.println(keyMatcher.matches());
        System.out.println(keyMatcher.group(1));
        System.out.println(Integer.valueOf(keyMatcher.group(2)));
        
        byte[] a = new byte[3];
        System.out.println(a.getClass());
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(output);
        dataOutput.writeInt(1);
        dataOutput.flush();
        dataOutput.close();
        
        System.out.println(output.toByteArray().length);
        
        String[] split = "toto".split("\\.");
        System.out.println(split.length + " " + split[0]);
    }
}
