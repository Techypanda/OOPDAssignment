/*******************************************
*   AUTHOR: Jonathan Wright
*   DATE: 28/04/2019
*   PURPOSE: A engine class 
*   
*/
class Engine
{
    // constants
    public static final String[] FUELTYPES = {"BATTERY","DIESEL","BIO"}; 
    // size 3 constant string array

    // classfields
    private String fuel;
    private int cylinders;
    
    /**************************************************************************
    *  PURPOSE To be the default constructor                                  *
    *  IMPORT None                                                            *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public Engine()
    { // def constructor
        fuel = FUELTYPES[0];
        cylinders = 2;
    }
    /**************************************************************************
    *  PURPOSE To be the alternate constructor                                *
    *  IMPORT String inFuel, int inCylinders                                  *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public Engine(String inFuel, int inCylinders)
    { // alt constructor
        setFuel(inFuel);
        setCylinders(inCylinders);
    }
    /**************************************************************************
    *  PURPOSE To be the copy constructor                                     *
    *  IMPORT Engine inEngine                                                 *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public Engine(Engine inEngine)
    {
        setFuel(inEngine.getFuel());
        setCylinders(inEngine.getCylinders());
    }
    /**************************************************************************
    *  PURPOSE To clone a engine                                              *
    *  IMPORT None                                                            *
    *  EXPORT Engine engineClone                                              *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public Engine clone()
    {
        Engine engineClone = new Engine(this);
        return engineClone;
    }
    /**************************************************************************
    *  PURPOSE To check equality between object and engine                    *
    *  IMPORT Object inObject                                                 *
    *  EXPORT boolean isEquals                                                *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEquals = false;
        if (inObject instanceof Engine)
        {
            Engine inEngine = (Engine)inObject;
            if (fuel.equals(inEngine.fuel))
            {
                if (cylinders == inEngine.cylinders)
                {
                    isEquals = true;
                }
            }           
        }
        else
        {
            Error.showError("IM NOT A INSTANCE OF ENGINE");
        }
        return isEquals;
    }
    /**************************************************************************
    *  PURPOSE To get the fuel of a engine                                    *
    *  IMPORT None                                                            *
    *  EXPORT String fuel                                                     *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public String getFuel()
    {
        return fuel;
    }
    /**************************************************************************
    *  PURPOSE To get the cylinders of a engine                               *
    *  IMPORT None                                                            *
    *  EXPORT int cylinders                                                   *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public int getCylinders()
    {
        return cylinders;
    }
    /**************************************************************************
    *  PURPOSE To output engine deets as string                               *
    *  IMPORT None                                                            *
    *  EXPORT String engineString                                             *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public String toString()
    {
        String engineString = new String("it's engine has " + cylinders +
        " cylinders and runs " + " on " + fuel + " fuel.");
        return engineString;
    }
    /**************************************************************************
    *  PURPOSE To set fuel of a engine                                        *
    *  IMPORT String inFuel                                                   *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public void setFuel(String inFuel)
    {
        if (validFuel(inFuel) == false)
        {
            throw new IllegalArgumentException("Invalid Fuel");
        }
        fuel = inFuel.toLowerCase();
    }
    /**************************************************************************
    *  PURPOSE To set Cylinders of engine                                     *
    *  IMPORT int inCylinders                                                 *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public void setCylinders(int inCylinders)
    {
        if (inCylinders < 2 || inCylinders > 20)
        {
            throw new IllegalArgumentException("Invalid Cylinders");
        }
        cylinders = inCylinders;
    }
    /**************************************************************************
    *  PURPOSE To validate fuel                                               *
    *  IMPORT String inFuel                                                   *
    *  EXPORT boolean validFuel                                               *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    private boolean validFuel(String inFuel)
    {
        boolean validFuel = false;
        inFuel = inFuel.toUpperCase();
        for (int i = 0; i < 3; i++)
        {
            if (inFuel.equals(FUELTYPES[i]))
            {
                validFuel = true;
            }
        }
        return validFuel;
    }

}
