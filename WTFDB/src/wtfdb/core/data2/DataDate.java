package wtfdb.core.data2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class DataDate extends Data<Date>
{
    protected DataDate()
    {
        super();
    }

    protected DataDate(Date value)
    {
        super(value);
    }

    @Override
    public void serialize(DataOutputStream output) throws IOException
    {
        output.writeByte(DATE);
        output.writeLong(value.getTime());
    }

    @Override
    public void deserialize(DataInputStream input) throws IOException
    {
        long time = input.readLong();
        value = new Date(time);
    }
}
