/*****************************************************************************
* PURPOSE To create a ship abstract class
* DATE 19/05/2019
* AUTHOR Jonathan Wright
*
******************************************************************************/
abstract class Ship
{
    // Classfields
    private String serialNum;
    private int year;
    private Engine engine;
    
    /**************************************************************************
    * Purpose: default constructor 
    * IMPORT: none
    * EXPORT: none
    * DATE: 19/05/2019
    **************************************************************************/
    // Class Methods
    public Ship()
    { // default
        serialNum = "100.101";
        year = 1951;
        engine = new Engine();
    }
    /**************************************************************************
    * Purpose: alternate constructor
    * IMPORT: String inSerail int inYear engine InEingien
    * EXPORT: none
    * DATE: 19/05/2019
    **************************************************************************/
    public Ship(String inSerial, int inYear, Engine inEngine)
    { // alternate constructor
        setSerialNum(inSerial);
        setYear(inYear);
        setEngine(new Engine(inEngine));
    }
    /**************************************************************************
    * Purpose: copy constructor
    * IMPORT: Ship inShip
    * EXPORT: none
    * DATE: 19/05/2019
    **************************************************************************/
    public Ship(Ship inShip)
    { // copy constructor
        setSerialNum(inShip.getSerialNum());
        setYear(inShip.getYear());
        setEngine(new Engine(inShip.getEngine()));
    }
    /**************************************************************************
    * Purpose: calculate the distance (ABSTRACT)
    * IMPORT: int distance
    * EXPORT: double time
    * DATE: 19/05/2019
    **************************************************************************/
    public abstract double calcDistance(int distance); // abstract
    /**************************************************************************
    * Purpose: clone the ship (ABSTRACT)
    * IMPORT: none
    * EXPORT: Ship shipClone
    * DATE: 19/05/2019
    **************************************************************************/
    public abstract Ship clone(); // abstract
    /**************************************************************************
    * Purpose: get the filestring of ship (ABSTRACT)
    * IMPORT: none
    * EXPORT: String fileString
    * DATE: 19/05/2019
    **************************************************************************/
    public abstract String toFileString();    
    /**************************************************************************
    * Purpose: To get the string details of ship
    * IMPORT: none
    * EXPORT: String outString
    * DATE: 19/05/2019
    **************************************************************************/
    public String toString()
    {
        String outString = "The ship " + serialNum + " was commissioned in "
        + year + ", " + engine.toString();
        return outString;
    }
    /**************************************************************************
    * Purpose: to et the serialnum iof ship
    * IMPORT: none
    * EXPORT: String serialNum
    * DATE: 19/05/2019
    **************************************************************************/
    public String getSerialNum()
    {
        return serialNum;
    }
    /**************************************************************************
    * Purpose: To get the year of ship 
    * IMPORT: inone
    * EXPORT: int year
    * DATE: 19/05/2019
    **************************************************************************/
    public int getYear()
    {
        return year;
    }
    /**************************************************************************
    * Purpose: to get the engine of ship 
    * IMPORT: none
    * EXPORT: engine engine
    * DATE: 19/05/2019
    **************************************************************************/
    public Engine getEngine()
    {
        return new Engine(engine);
    }
    /**************************************************************************
    * Purpose: to set the serialnum
    * IMPORT: String inSerialNum
    * EXPORT: non
    * DATE: 19/05/2019
    **************************************************************************/
    public void setSerialNum(String inSerialNum)
    {
        if (validSerialNum(inSerialNum) == false)
        {
            throw new IllegalArgumentException("Invalid serial");
        }
        serialNum = inSerialNum;
    }
    /**************************************************************************
    * Purpose: To validate the serialnum
    * IMPORT: String inSerialNum
    * EXPORT: boolean validSerialNum
    * DATE: 19/05/2019
    **************************************************************************/
    private boolean validSerialNum(String inSerialNum)
    {
        boolean validSerialNum = false;
        if (inSerialNum.length() == 7)
        {
            String[] serials = inSerialNum.split("\\.");
            if (Integer.parseInt(serials[0]) > 99 &&
                Integer.parseInt(serials[0]) < 301 &&
                Integer.parseInt(serials[1]) > 000 &&
                Integer.parseInt(serials[1]) < 1000)
            {
                validSerialNum = true;
            }
        }
        return validSerialNum;
    }
    /**************************************************************************
    * Purpose: To set the year
    * IMPORT: int iNyear
    * EXPORT: none
    * DATE: 19/05/2019
    **************************************************************************/
    public void setYear(int inYear)
    {
        if (inYear < 1950 || inYear > 2022)
        {
            throw new IllegalArgumentException("Invalid year.");
        }
        year = inYear;
    }
    /**************************************************************************
    * Purpose: To set the engine
    * IMPORT: Engine inEngine
    * EXPORT: none
    * DATE: 19/05/2019
    **************************************************************************/
    public void setEngine(Engine inEngine)
    {
        if (inEngine != null)
        {
            engine = new Engine(inEngine);
        }
        else 
        {
            throw new IllegalArgumentException("Invalid engine. yeeet");
        }
    }
    /**************************************************************************
    * Purpose: to check equality of ships
    * IMPORT: Object inObject
    * EXPORT: boolean isEqual
    * DATE: 19/05/2019
    **************************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if (inObject instanceof Ship)
        {
            Ship inShip = (Ship)inObject;
            if (inShip.getSerialNum().equals(serialNum))
            {
                if (inShip.getYear() == year)
                {
                    if (inShip.getEngine().equals(engine))
                    {
                        isEqual = true;
                    }
                }
            }
        }
        return isEqual;
    }
}
