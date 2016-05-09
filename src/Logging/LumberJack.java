package Logging;

public final class LumberJack
{
    //Constructor
    private LumberJack() {}

    //Public Methods
    public static void LogException(String message, Exception e)
    {
        System.out.println("[EXCEPTION] - <"+message+"> - "+e.getMessage()
                + "\n" + e.getStackTrace());

    }

    //Private Methods
}
