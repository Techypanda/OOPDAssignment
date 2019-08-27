/******************************************************************************
 *  Author: Jonathan Wright
 *  Date: 23/05/2019
 *  Purpose: To be the main loader of the program, the final exception handler
 *
 *****************************************************************************/

class ShipManager
{
    // class constants

    // class fields

    // class methods
    /**************************************************************************
 * Purpose: To be the main method
 * Date: 23/05/2019
 * IMPORT: none
 * EXPORT: none
 *****************************************************************************/
    public static void main(String[] args)
    {
        try
        {
            UserInterface UI = new UserInterface();
            UI.mainMenu();
        }
        catch (Exception e)
        {
            // DEBUG PURPOSES: e.printStackTrace();
            //System.out.println("Stacktrace: " + e.printStackTrace() + "\n");
            Error.showError("Error: " + e.getMessage() + 
                               "\n gracefully exiting!");
        }
    }
}
