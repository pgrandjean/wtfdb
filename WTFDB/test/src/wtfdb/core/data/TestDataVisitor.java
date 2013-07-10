package wtfdb.core.data;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataVisitor
{
    private DataVisitor visitor = new DataVisitor();
    
    private Data data0 = null;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Before
    public void startUp() throws IOException
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
    }
    
    @Test
    public void test00() throws IOException
    {
        Data res = visitor.visit(data0, null);
        Assert.assertNull(res);
    }

    @Test
    public void test001() throws IOException
    {
        Data res = visitor.visit(data0, "");
        Assert.assertNotNull(res);
    }
    
    @Test
    public void test01() throws IOException
    {
        Data res = visitor.visit(data0, "tata");
        Assert.assertNotNull(res);
        Assert.assertEquals(0, res.size());
    }

    @Test
    public void test02() throws IOException
    {
        Data res = visitor.visit(data0, "tata[1].tete");
        Assert.assertNotNull(res);
        Assert.assertEquals(0, res.size());
    }

    @Test
    public void test03() throws IOException
    {
        Data res = visitor.visit(data0, "boolean");
        
        Assert.assertNotNull(res);
        
        Object value = res.get("boolean");
        
        Assert.assertEquals(Boolean.class, value.getClass());
        Assert.assertEquals(true, value);
    }

    @Test
    public void test04() throws IOException
    {
        Data res = visitor.visit(data0, "list[0]");

        Assert.assertNotNull(res);

        Object value = res.get("list");
        
        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof List);
        
        List<?> list = (List<?>) value;

        Assert.assertEquals(1, list.size());
        Assert.assertEquals(Integer.class, list.get(0).getClass());
        Assert.assertEquals(2, list.get(0));
    }

    @Test
    public void test05() throws IOException
    {
        Data res = visitor.visit(data0, "list[1].byte");

        Assert.assertNotNull(res);

        Object value = res.get("list");

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof List);

        List<?> list = (List<?>) value;

        Assert.assertEquals(1, list.size());
        Assert.assertEquals(Data.class, list.get(0).getClass());
        
        Data data = (Data) list.get(0);

        Assert.assertEquals(1, data.size());
        
        value = data.get("byte");

        Assert.assertNotNull(value);
        Assert.assertEquals(Byte.class, value.getClass());
        Assert.assertEquals((byte) 1, value);
    }

    @Test
    public void test06() throws IOException
    {
        Data res = visitor.visit(data0, "list[1]");

        Assert.assertNotNull(res);

        Object value = res.get("list");

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof List);

        List<?> list = (List<?>) value;
        
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(Data.class, list.get(0).getClass());
        
        Data data = (Data) list.get(0);

        Assert.assertEquals(1, data.size());
        
        value = data.get("byte");

        Assert.assertEquals(Byte.class, value.getClass());
        Assert.assertEquals((byte) 1, value);
    }

    @Test
    public void test07() throws IOException
    {
        Data res = visitor.visit(data0, "list");

        Assert.assertNotNull(res);

        Object value = res.get("list");

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof List);

        List<?> list = (List<?>) value;
        
        Assert.assertEquals(2, list.size());
        
        value = list.get(0);
        
        Assert.assertEquals(Integer.class, value.getClass());
        Assert.assertEquals(2, value);
        
        value = list.get(1);

        Assert.assertEquals(Data.class, value.getClass());
        
        Data data = (Data) value;

        Assert.assertEquals(1, data.size());
        
        value = data.get("byte");

        Assert.assertEquals(Byte.class, value.getClass());
        Assert.assertEquals((byte) 1, value);
    }
}
