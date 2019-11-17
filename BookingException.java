import java.lang.*;

class BookingException extends Exception
{
    private static final long serialVersionUID = 1;

    BookingException(String s)
    {
        System.out.println(s);
    }
}