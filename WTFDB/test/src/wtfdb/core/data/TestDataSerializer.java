package wtfdb.core.data;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataSerializer
{
    private Data data0 = null;
    
    private Data data1 = null;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Before
    public void startUp()
    {
        // data0
        data0 = new Data();
        data0.set("boolean", true);
        data0.set("byte", (byte) 1);
        data0.set("short", (short) 1);
        data0.set("integer", (int) 1);
        data0.set("long", (long) 1);
        data0.set("float", (float) 1);
        data0.set("double", (double) 1);
        data0.set("character", 'c');
        data0.set("string", "s");
        
        Data subData0 = new Data();
        subData0.set("byte", (byte) 1);
        
        Vector list0 = new Vector<>();
        list0.add(Integer.valueOf(2));
        list0.add(subData0);
        data0.set("list", list0);
        data0.set("data", subData0);
        
        // data 1
        data1 = new Data();
        data1.set("boolean", true);
        data1.set("byte", (byte) 1);
        data1.set("short", (short) 1);
        data1.set("integer", (int) 1);
        data1.set("long", (long) 1);
        data1.set("float", (float) 1);
        data1.set("double", (double) 1);
        data1.set("character", 'c');
        data1.set("string", "s");
        
        Data subData1 = new Data();
        subData1.set("byte", (byte) 1);
        
        Vector list1 = new Vector<>();
        list1.add(Integer.valueOf(2));
        list1.add(subData1);
        data1.set("list", list1);
        data1.set("data", subData1);
    }
    
    @Test
    public void testEquals()
    {
        Assert.assertTrue(data0.equals(data1));
    }

    @Test
    public void testNotEquals()
    {
        data1.set("integer", null);
        Assert.assertTrue(!data0.equals(data1));
    }
    
    @Test
    public void testSerialization() throws Exception
    {
        byte[] raw = DataSerializer.serialize(data0);
        data1 = DataSerializer.deserialize(raw);
        
        DataVisitorTest dataResolver = new DataVisitorTest();
        dataResolver.visit(raw);
        
        Assert.assertTrue(data0.equals(data1));
    }
}
