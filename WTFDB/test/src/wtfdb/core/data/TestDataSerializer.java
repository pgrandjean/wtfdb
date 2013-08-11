package wtfdb.core.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataSerializer
{
    private DataMap data0 = null;
    
    private DataMap data1 = null;
    
    @Before
    public void startUp()
    {
        // data0
        data0 = new DataMap();
        data0.set("boolean", true);
        data0.set("byte", (byte) 1);
        data0.set("short", (short) 1);
        data0.set("integer", (int) 1);
        data0.set("long", (long) 1);
        data0.set("float", (float) 1);
        data0.set("double", (double) 1);
        data0.set("character", 'c');
        data0.set("string", "s");
        
        DataMap subData0 = new DataMap();
        subData0.set("byte", (byte) 1);
        
        DataArray list0 = new DataArray();
        list0.add(Integer.valueOf(2));
        list0.add(subData0);
        data0.set("list", list0);
        data0.set("data", subData0);
        
        // data 1
        data1 = new DataMap();
        data1.set("boolean", true);
        data1.set("byte", (byte) 1);
        data1.set("short", (short) 1);
        data1.set("integer", (int) 1);
        data1.set("long", (long) 1);
        data1.set("float", (float) 1);
        data1.set("double", (double) 1);
        data1.set("character", 'c');
        data1.set("string", "s");
        
        DataMap subData1 = new DataMap();
        subData1.set("byte", (byte) 1);
        
        DataArray list1 = new DataArray();
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
        data1.remove("integer");
        Assert.assertTrue(!data0.equals(data1));
    }
}
