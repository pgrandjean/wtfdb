package wtfdb.core.data;

import org.junit.Assert;
import org.junit.Test;

public class TestDataPath
{
    @Test
    public void testEquals00()
    {
        DataPath path0 = new DataPath();
        DataPath path1 = new DataPath();
        
        Assert.assertTrue(path0.equals(path1));
        Assert.assertTrue(path1.equals(path0));
    }
    
    @Test
    public void testEquals01()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        
        DataPath path1 = new DataPath();
        
        Assert.assertTrue(!path0.equals(path1));
        Assert.assertTrue(!path1.equals(path0));
    }
    
    @Test
    public void testEquals02()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        
        Assert.assertTrue(path0.equals(path1));
        Assert.assertTrue(path1.equals(path0));
    }

    @Test
    public void testEquals03()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add("tete");
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        
        Assert.assertTrue(!path0.equals(path1));
        Assert.assertTrue(!path1.equals(path0));
    }

    @Test
    public void testEquals04()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add("tete");
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path1.add("tete");
        
        Assert.assertTrue(path0.equals(path1));
        Assert.assertTrue(path1.equals(path0));
    }

    @Test
    public void testEquals05()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path1.add("tete");
        
        Assert.assertTrue(!path0.equals(path1));
        Assert.assertTrue(!path1.equals(path0));
    }

    @Test
    public void testEquals06()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path1.add(1);
        
        Assert.assertTrue(path0.equals(path1));
        Assert.assertTrue(path1.equals(path0));
    }

    @Test
    public void testEquals07()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        
        Assert.assertTrue(!path0.equals(path1));
        Assert.assertTrue(!path1.equals(path0));
    }

    @Test
    public void testEquals08()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        path0.add("tete");
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path0.add("tete");
        
        Assert.assertTrue(!path0.equals(path1));
        Assert.assertTrue(!path1.equals(path0));
    }

    @Test
    public void testMatches00()
    {
        DataPath path0 = new DataPath();
        DataPath path1 = new DataPath();
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches01()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        
        DataPath path1 = new DataPath();
        
        Assert.assertTrue(!path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches02()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches03()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        
        Assert.assertTrue(!path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches04()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path1.add(1);
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches05()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path1.add(2);
        
        Assert.assertTrue(!path0.matches(path1));
        Assert.assertTrue(!path1.matches(path0));
    }

    @Test
    public void testMatches06()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        path0.add("tete");
        path0.add(2);
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path1.add("tete");
        
        Assert.assertTrue(!path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches07()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        path0.add(2);
        path0.add("tete");
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path1.add("tete");
        
        Assert.assertTrue(!path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches08()
    {
        DataPath path0 = new DataPath();
        path0.add("tata");
        path0.add(1);
        path0.add(2);
        path0.add("tete");
        
        DataPath path1 = new DataPath();
        path1.add("tata");
        path0.add(1);
        path1.add("tete");
        
        Assert.assertTrue(!path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }
}
