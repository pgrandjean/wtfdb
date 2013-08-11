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
        DataPath path0 = new DataPath("tata");
        DataPath path1 = new DataPath();
        
        Assert.assertTrue(!path0.equals(path1));
        Assert.assertTrue(!path1.equals(path0));
    }
    
    @Test
    public void testEquals02()
    {
        DataPath path0 = new DataPath("tata");
        DataPath path1 = new DataPath("tata");
        
        Assert.assertTrue(path0.equals(path1));
        Assert.assertTrue(path1.equals(path0));
    }

    @Test
    public void testEquals03()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, "tete");
        
        DataPath path1 = new DataPath("tata");
        
        Assert.assertTrue(!path0.equals(path1));
        Assert.assertTrue(!path1.equals(path0));
    }

    @Test
    public void testEquals04()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, "tete");

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, "tete");
        
        Assert.assertTrue(path0.equals(path1));
        Assert.assertTrue(path1.equals(path0));
    }

    @Test
    public void testEquals05()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, "tete");

        Assert.assertTrue(path0.equals(path1));
        Assert.assertTrue(path1.equals(path0));
    }

    @Test
    public void testEquals06()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, 1);

        Assert.assertTrue(path0.equals(path1));
        Assert.assertTrue(path1.equals(path0));
    }

    @Test
    public void testEquals07()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);

        DataPath path1 = new DataPath("tata");
        
        Assert.assertTrue(!path0.equals(path1));
        Assert.assertTrue(!path1.equals(path0));
    }

    @Test
    public void testEquals08()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);
        path0 = new DataPath(path0, 1);

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, "tete");

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
        DataPath path0 = new DataPath("tata");
        DataPath path1 = new DataPath();
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches02()
    {
        DataPath path0 = new DataPath("tata");
        DataPath path1 = new DataPath("tata");
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches03()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);

        DataPath path1 = new DataPath("tata");
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches04()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, 1);
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches05()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, 2);
        
        Assert.assertTrue(!path0.matches(path1));
        Assert.assertTrue(!path1.matches(path0));
    }

    @Test
    public void testMatches06()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);
        path0 = new DataPath(path0, "tete");
        path0 = new DataPath(path0, 2);
        
        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, "tete");
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches07()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);
        path0 = new DataPath(path0, 2);
        path0 = new DataPath(path0, "tete");

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, "tete");
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }

    @Test
    public void testMatches08()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);
        path0 = new DataPath(path0, 2);
        path0 = new DataPath(path0, "tete");

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, 1);
        path1 = new DataPath(path1, "tete");
        
        Assert.assertTrue(path0.matches(path1));
        Assert.assertTrue(path1.matches(path0));
    }
    
    @Test
    public void testHashCode()
    {
        DataPath path0 = new DataPath("tata");
        path0 = new DataPath(path0, 1);
        path0 = new DataPath(path0, 2);
        path0 = new DataPath(path0, "tete");

        DataPath path1 = new DataPath("tata");
        path1 = new DataPath(path1, 1);
        path1 = new DataPath(path1, 2);
        path1 = new DataPath(path1, "tete");
        
        Assert.assertEquals(path0.hashCode(), path1.hashCode());
    }
}
