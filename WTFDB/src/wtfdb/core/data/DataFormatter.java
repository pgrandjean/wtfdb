package wtfdb.core.data;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public class DataFormatter
{   
    protected void serialize(Boolean value, StringBuffer output)
    {
        output.append(value);
    }

    protected void serialize(Byte value, StringBuffer output)
    {
        output.append(value).append('b');
    }
    
    protected void serialize(Short value, StringBuffer output)
    {
        output.append(value).append('s');
    }

    protected void serialize(Integer value, StringBuffer output)
    {
        output.append(value);
    }

    protected void serialize(Long value, StringBuffer output)
    {
        output.append(value).append('l');
    }

    protected void serialize(Float value, StringBuffer output)
    {
        output.append(value).append('f');
    }

    protected void serialize(Double value, StringBuffer output)
    {
        output.append(value).append('d');
    }

    protected void serialize(Character value, StringBuffer output)
    {
        output.append(value);
    }

    protected void serialize(String value, StringBuffer output)
    {
        value = value.replaceAll("\t", "\\t")
                     .replaceAll("\b", "\\b")
                     .replaceAll("\n", "\\n")
                     .replaceAll("\r", "\\r")
                     .replaceAll("\f", "\\f")
                     .replaceAll("\"", "\\\"")
                     .replaceAll("\\", "\\\\");
        output.append("\"").append(value).append("\"");
    }

    @SuppressWarnings("rawtypes")
    protected void serialize(Vector value, StringBuffer output)
    {
        output.append("[ ");
        
        int size = value.size();
        int i = 0;
        
        for (Object o : value)
        {
            serialize(o, output);
            
            if (i != size - 1)
            {
                output.append(", ");
            }
            
            i++;
        }

        output.append(" ]");
    }

    protected void serialize(Object data, StringBuffer output)
    {
        try
        {
            Class<?> klass = data.getClass();
            getClass().getDeclaredMethod("serialize", klass, StringBuffer.class).invoke(this, data, output);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
        {
            throw new NoSuchMethodError("format of " + data.getClass().getCanonicalName() + " not supported");
        }
    }

    public String format(Data data)
    {
        StringBuffer output = new StringBuffer();
        output.append("{ ");
        
        int size = data.size();
        int i = 0;
        
        for (String key : data)
        {
            output.append(key).append(": ");
            
            Object value = data.get(key);
            serialize(value, output);
            
            if (i != size - 1)
            {
                output.append(", ");
            }
            
            i++;
        }
        
        output.append(" }");
        return output.toString();
    }
    
    private Data parseData(String input)
    {
        int size = input.length();
        
        int start = input.indexOf('{');
        if (start != 0) return null;
        
        int end = input.lastIndexOf('}');
        if (end != size - 1) return null;
                
        Data data = new Data();
        
        StringBuffer buffer = new StringBuffer();
        boolean isKey = true;
        boolean isString = true;
        boolean isEscape = false;
        
        String key = null;
        Object value = null;
        
        char curr = 0;
        char prev = 0;
        
        for (int i = 1; i < size - 1; i++)
        {
            prev = curr;
            curr = input.charAt(i);
            
            switch (curr)
            {
                case '\r':
                case '\n':
                    break;
                    
                case ' ':
                    if (isString) buffer.append(curr);
                    break;
                    
                case ':':
                    if (isString) buffer.append(curr);
                    else
                    {
                        key = buffer.toString();
                        buffer.setLength(0);
                    
                        if (!key.matches("\\w+")) return null; // TODO throw exception
                        
                        isKey = false;
                    }
                    break;
                    
                case '\\':
                    // escape character
                    if (isString && prev == '\\') buffer.append('\\');
                    else if (isString) isEscape = true;
                    else return null; // escape out of string, TODO throw exception
                    break;
                    
                case '"':
                    if (isString && isEscape)
                    {
                        buffer.append(curr);
                        isEscape = false;
                    }
                    else if (isString)
                    {
                        buffer.append(curr);
                        isString = false;
                    }
                    else isString = true;
                    break;
                    
                case ',':
                    if (isString) buffer.append(curr);
                    else
                    {
                        value = buffer.toString();
                        buffer.setLength(0);
                        
                        
                        isKey = true;
                    }
                    break;
                    
                default:
                    if (isEscape)
                    {
                        switch (curr)
                        {
                            case 't':
                                curr = '\t';
                                break;
                                
                            case 'b':
                                curr = '\b';
                                break;
                                
                            case 'n':
                                curr = '\n';
                                break;
                                
                            case 'r':
                                curr = '\r';
                                break;
                                
                            case 'f':
                                curr = '\f';
                                break;
                                
                            default:
                                return null; // TODO throw exception
                        }
                        
                        isEscape = false;
                    }
                    
                    if (isKey)
                    {
                        if (Character.isLetterOrDigit(curr))
                        {
                            buffer.append(curr);
                        }
                        else return null; // TODO throw exception
                    }
                    else if (isString) buffer.append(curr);
                    break;
            }
        }
        
        return data;
    }
    
    public Data parse(String input)
    {
        if (input == null) throw new NullPointerException("input is null");
        
        input = input.trim();
        
        if (input.length() < 2) return null;
        
        return parseData(input);
    }
}
