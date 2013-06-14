package wtfdb.core.data;

import java.util.Date;

public class DataVisitorTest extends DataVisitor
{
    @Override
    public boolean visit(DataPath iPath, Boolean iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue);
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, Byte iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue + 'b');
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, Short iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue + 's');
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, Integer iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue);
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, Long iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue + 'l');
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, Float iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue + 'f');
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, Double iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue);
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, Character iValue)
    {
        System.out.println(iPath.toString() + " = '" + iValue + '\'');
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, String iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue);
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, byte[] iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue);
        return true;
    }

    @Override
    public boolean visit(DataPath iPath, Date iValue)
    {
        System.out.println(iPath.toString() + " = " + iValue);
        return true;
    }
}
