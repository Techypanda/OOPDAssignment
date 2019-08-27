import java.text.DecimalFormat;

/******************************************************************************
 * PURPOSE To store ships
 * DATE 23/05/2019
 * AUTHOR Jonathan Wright
 *
 *****************************************************************************/


class ShipStorage
{
    // class constants
    public static final int MAXSIZE = 30;
    
    // class fields
    private Ship[] ships;
    private int shipCount;

    // class methods
    /**************************************************************************
    *  PURPOSE To be the default constructor                                  *
    *  IMPORT None                                                            *
    *  EXPORT None                                                            *
    *  DATE 5/05/2019                                                        *
    **************************************************************************/
    public ShipStorage()
    {
        ships = new Ship[MAXSIZE];
        shipCount = 0;
    }
    /**************************************************************************
    *  PURPOSE To add a ship to ships array                              *
    *  IMPORT FighterJet inFighterJet                                         *
    *  EXPORT None                                                            *
    *  DATE 5/05/2019                                                        *
    **************************************************************************/
    public void addShip(Ship inShip)
    { 
        try
        {
            if (verifyShipConditions(inShip, shipCount))
            {
                ships[shipCount] = inShip;
                shipCount += 1;
            }
        }
        catch (IllegalArgumentException e)
        {
            Error.showError("Unable to add Ship due to: " + 
                               e.getMessage());
        }
    }
    /**************************************************************************
    *   PURPOSE throw exception if shipStorage is full. (For userInterface.)
    *   IMPORT None
    *   EXPORT None
    *   Date: 22/05/2019
    **************************************************************************/
    public void full()
    {
        if (shipCount >= MAXSIZE)
        {
            throw new IllegalArgumentException("ShipStorage is full! no more"
            + " ships can be added!");
        }
    }
    /**************************************************************************
    *  PURPOSE verifys ship conditions                                        *
    *  IMPORT object inObject, int count                                      *
    *  EXPORT boolean verification                                            *
    *  DATE 05/05/2019                                                        *
    **************************************************************************/
    private boolean verifyShipConditions(Ship inShip, int count)
    {
        boolean verification = false;
        if (inShip != null)
        {
            if (count < MAXSIZE)
            {
                verification = true;
            }
            else
            {
                throw new IllegalArgumentException("MaxSize has been reached");
            }
        }
        else
        {
            throw new IllegalArgumentException("Inputted Object is null!");
        }
        return verification;
    }
    /**************************************************************************
    *  PURPOSE To calc destination                                            *
    *  IMPORT int distance                                                    *
    *  EXPORT string fastestShip                                              *
    *  DATE 5/05/2019                                                         *
    **************************************************************************/
    public String destinationCheck(int distance)
    {
        DecimalFormat twodp = new DecimalFormat("##.00");
        String fastestShip = new String();
        try
        {
            if (shipCount < 1)
            {      
                throw new IllegalArgumentException("There are no ships!");
            }
            double currentLowest = Double.MAX_VALUE; // highest value initially
            for(int i = 0; i < shipCount; i++)
            { // CYCLES ALL SHIPS
                if (ships[i].calcDistance(distance) < currentLowest)
                {
                    currentLowest = ships[i].calcDistance(distance);
                    String[] shipFileString = ships[i].toFileString().split(",");
                    // ^^^^ returns file string format
                    String type = new String(); // ship type.
                    if (shipFileString[0].equals("F"))
                    { // if fighter
                        type = "FighterJet";
                    }
                    else if (shipFileString[0].equals("S"))
                    {          // if Submarine
                        type = "Submarine";
                    }
                    else
                    {
                        Error.showError("An unknown ship is the fastest?");
                    }
                    fastestShip = "Fastest Ship " + shipFileString[0] +
                                  ships[i].getSerialNum()
                                  + ", " + twodp.format(currentLowest) + 
                                  " hours";
                }
            }
        }
        /* if (fastestShip.equals(""))
        { // if string is empty it has skipped other two methods
            Error.showError("There is no fastest ship!");   
        } NOT NEEDED REDUNDANT */
        catch (IllegalArgumentException e)
        {
            Error.showError(e.getMessage());
            fastestShip = "";
        }
        return fastestShip;
    }
    /**************************************************************************
    *  PURPOSE To find duplicates                                             *
    *  IMPORT None                                                            *
    *  EXPORT string duplicates                                               *
    *  DATE 05/05/2019                                                        *
    **************************************************************************/
    public String findDuplicates()
    {
        String duplicates = new String();
        try
        {
            if (shipCount < 1)
            {
                throw new IllegalArgumentException("There are no ships!");
            }
            duplicates = duplicatesFinder(duplicates, shipCount,
                                       ships);
        }
        catch(IllegalArgumentException e)
        {
            Error.showError("There are no duplicates!");
            duplicates = "";
        }
        return duplicates;

    }
    /**************************************************************************
    *  PURPOSE To find duplicates (calc side) Submarine Input             *
    *  IMPORT String duplicates, int submarineCount, submarine[] submarineArray         
    *  EXPORT string duplicates                                               *
    *  DATE 05/05/2019                                                        *
    **************************************************************************/

    private String duplicatesFinder(String duplicates, int shipCount,
                                    Ship[] ships)
    {
        for (int i = 0; i < shipCount; i++)
        {
            for (int iK = 0; iK < shipCount; iK++)
            {
                if (ships[i].equals(ships[iK]) && i != iK)
                {
                 duplicates += "\n " + ships[i] + " is a duplicate of " + 
                            ships[iK];
                }
            }
        }
        if (duplicates.equals(""))
        {
            duplicates = "There are no duplicate ships"; // if no duplicates
        }
        return duplicates;
    }
    /**************************************************************************
    *  PURPOSE To view ships in array                                         *
    *  IMPORT None                                                            *
    *  EXPORT string shipView                                               *
    *  DATE 05/05/2019                                                        *
    **************************************************************************/

    public String viewShips()
    {
        String shipView = new String();
        try
        {
            if (shipCount < 1) {
                throw new IllegalArgumentException("There are no ships!");
            }
            shipView = viewShipsLoop();
        }
        catch (IllegalArgumentException e)
        {
            Error.showError(e.getMessage());
            shipView = "";
        }
        return shipView;
    }
    /*************************************************************************
 *  purpose view ships return it
 *  import none
 *  export string shipes
 *  DATE 19/05/2019
 */
    public String viewShipsLoop()
    {
        String shipes = new String("");
        for (int i = 0; i < shipCount; i++)
        {
            String[] shipFileString = ships[i].toFileString().split(",");
            String type = shipFileString[0];
            shipes += "\n " + type + i + ": " + 
            ships[i].toString();   
        }
        return shipes;
    }
    
    /**************************************************************************
    *  PURPOSE To get shipArray                        *
    *  IMPORT None                                                            *
    *  EXPORT Ship[] shipArray                                      *
    *  DATE 05/05/2019                                                        *
    **************************************************************************/

    public Ship[] getShipArray()
    {
        return ships;
    }
    /**************************************************************************
    *   PURPOSE: To get the classfield rather than length of array
    *   IMPORT none
    *   EXPORT int ShipCount
    *   DATE: 21/05/2019
    **************************************************************************/
    public int getShipCount()
    {
        return shipCount;
    }
    /**************************************************************************
    *  PURPOSE To check equality                                   *
    *  IMPORT object inobject                                              *
    *  EXPORT boolean isEqual                                               *
    *  DATE 05/05/2019                                                        *
    **************************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = true;        
        if (inObject instanceof ShipStorage)
        {
            ShipStorage inShipStorage = (ShipStorage)inObject;
           if(inShipStorage.getShipArray().length != ships.length)
            { // checking both arrays are same size.
                isEqual = false; // if the are not it cant be equal.
            }
            else
            {
                if (checkEqual(inShipStorage.getShipArray())) { // cheks sub and
                // chek fighter
                    isEqual = true;
                }       
            }
        }
        else 
        {
            isEqual = false;
        }
        return isEqual;
    }
    /**************************************************************************
    *  PURPOSE To check equality (calcs)                                      *
    *  IMPORT Submarine[] inSubmarineArray                                    *
    *  EXPORT boolean isEqual                                                 *
    *  DATE 05/05/2019                                                        *
    **************************************************************************/
    private boolean checkEqual(Ship[] inShipsArray)
    {
        boolean isEqual;
        int count = 0;
        do
        {
            isEqual = inShipsArray[count].equals(ships[count]);
            count += 1;
        }
        while(isEqual && count < ships.length);       
        return isEqual;
    }

}
