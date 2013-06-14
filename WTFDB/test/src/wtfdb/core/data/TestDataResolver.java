package wtfdb.core.data;

import java.io.IOException;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataResolver
{
    private DataResolver resolver = null;
    
    private byte[] raw = null;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Before
    public void startUp() throws IOException
    {
        resolver = new DataResolver();
        
        // data0
        Data data0 = new Data();
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

        raw = DataSerializer.serialize(data0);
    }
    
    @Test
    public void test00() throws IOException
    {
        Object value = resolver.get(raw, "");
        Assert.assertTrue(value == null);
    }

    @Test
    public void test01() throws IOException
    {
        Object value = resolver.get(raw, "tata");
        Assert.assertTrue(value == null);
    }

    @Test
    public void test02() throws IOException
    {
        Object value = resolver.get(raw, "tata[1].tete");
        Assert.assertTrue(value == null);
    }

    @Test
    public void test03() throws IOException
    {
        Object value = resolver.get(raw, "boolean");
        Assert.assertTrue(value != null);
        Assert.assertEquals(Boolean.class, value.getClass());
        Assert.assertEquals(true, value);
    }

    @Test
    public void test04() throws IOException
    {
        Object value = resolver.get(raw, "list[0]");
        Assert.assertTrue(value != null);
        Assert.assertEquals(Integer.class, value.getClass());
        Assert.assertEquals(2, value);
    }

    @Test
    public void test05() throws IOException
    {
        Object value = resolver.get(raw, "list[1].byte");
        Assert.assertTrue(value != null);
        Assert.assertEquals(Byte.class, value.getClass());
        Assert.assertEquals((byte) 1, value);
    }

    @Test
    public void test06() throws IOException
    {
        Object value = resolver.get(raw, "list[1]");
        Assert.assertTrue(value != null);
        Assert.assertEquals(Byte.class, value.getClass());
        Assert.assertEquals((byte) 1, value);
    }

    @Test
    public void test07() throws IOException
    {
        Object value = resolver.get(raw, "list");
        Assert.assertTrue(value != null);
        Assert.assertEquals(Byte.class, value.getClass());
        Assert.assertEquals((byte) 1, value);
    }
}
