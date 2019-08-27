/*  AUTHOR JONATHAN WRIGHT
 *  DATE 28/04/2019
 *  PURPOSE TO MAKE A SUBMARINE
 *
 */

import java.text.DecimalFormat; // decimalformat
// java.util not needed.
class Submarine extends Ship
{
    // CLASS CONSTANTS
    public static final String[] VALIDHULL = {"STEEL","ALLOY","TITANIUM"}; 
                                              // Size 3 Array Constant
    // CLASS FIELDS
    private String hull;
    private double maxDepth; 
    // CLASS METHODS
    /**************************************************************************
    *  PURPOSE To be the default constructor                                  *
    *  IMPORT None                                                            *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public Submarine()
    { // default constructoar
        super();
        hull = VALIDHULL[0];
        maxDepth = -500.0;
    }
    /**************************************************************************
    *  PURPOSE To be the alternate constructor                                *
    *  IMPORT int inYear String inHull double inMaxDepth                      *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public Submarine(int inYear, String inHull, double inMaxDepth, 
                     String inSerialNum, Engine inEngine)
    { // Alternate Constructor
        super(inSerialNum, inYear, inEngine);
        setHull(inHull);
        setMaxDepth(inMaxDepth);
    }
    /**************************************************************************
    *  PURPOSE To be the copy constructor                                     *
    *  IMPORT Submarine inSubmarine                                           *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public Submarine(Submarine inSubmarine)
    {
        // submarine will be copied
        super(inSubmarine);
        hull = inSubmarine.getHull();
        maxDepth = inSubmarine.getMaxDepth();
    }
    /**************************************************************************
    *  PURPOSE To clone a submarine                                           *
    *  IMPORT None                                                            *
    *  EXPORT Submarine submarineClone                                        *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    @Override
    public Submarine clone()
    {
        // Object will be cloned to a new object.
        Submarine submarineClone = new Submarine(this);
        return submarineClone;
    }
    /**************************************************************************
    *  PURPOSE To get hull of sub                                             *
    *  IMPORT None                                                            *
    *  EXPORT String hull                                                     *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/   
    public String getHull() 
    {
        return hull;
    }
    /**************************************************************************
    *  PURPOSE To get maxDepth                                                *
    *  IMPORT None                                                            *
    *  EXPORT double maxDepth                                                 *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public double getMaxDepth()
    {
        return maxDepth;
    }
    /**************************************************************************
    *  PURPOSE To check object equal to sub                                   *
    *  IMPORT Object inObject                                                 *
    *  EXPORT boolean isEqual                                                 *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if (inObject instanceof Submarine)
        {
            Submarine inSubmarine = (Submarine)inObject;
            if (super.equals(inSubmarine))
            {
                if (hull.equals(inSubmarine.getHull()))
                {
                    if (maxDepth == inSubmarine.getMaxDepth())
                    {
                        isEqual = true;
                    }
                }
            }
        }
        return isEqual;
    }
    /**************************************************************************
    *  PURPOSE To turn sub deets into string                                  *
    *  IMPORT None                                                            *
    *  EXPORT String submarineString                                          *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public String toString()
    {
        DecimalFormat twodp = new DecimalFormat("##.00");
        String submarineString = super.toString() + " It is a submarine "
        + "with a " + hull + " hull and a max depth of "
        + twodp.format(maxDepth) + " meters.";
        return submarineString;
    }
    /**************************************************************************
    *  PURPOSE To set hull of sub                                             *
    *  IMPORT String inHull                                                   *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public void setHull(String inHull)
    {
        if (validHull(inHull) == false)
        {
            throw new IllegalArgumentException("Invalid hull.");
        }
        hull = inHull;
    }
    /**************************************************************************
    *  PURPOSE To set maxDepth                                                *
    *  IMPORT double inDepth                                                  *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public void setMaxDepth(double inDepth)
    {
        
        if (inDepth < -500.0 || inDepth > 0)
        {
            throw new IllegalArgumentException("Invalid depth.");
        }
        maxDepth = inDepth;
    }
    /**************************************************************************
    *  PURPOSE To validate hull of sub                                        *
    *  IMPORT String inHull                                                   *
    *  EXPORT boolean valid                                                   *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    private boolean validHull(String inHull)
    {
        // verifys hull
        inHull = inHull.toUpperCase();
        boolean valid = false;
        for(int i = 0; i < 3; i++)
        {
            if (inHull.equals(VALIDHULL[i]))
            {
                valid = true;
            }
        }
        return valid;
    }
    /**************************************************************************
    * PURPOSE To calculate hours taken by sub
    * DATE 11/05/2019
    * IMPORT integer Distance
    * EXPORT hoursTaken (Real)
    **************************************************************************/
    @Override
    public double calcDistance(int distance)
    {
        return (((double)distance / (double)super.getEngine().getCylinders()) * 
               (1 / (10 + (maxDepth * -1))));
    }

    /**************************************************************************
 *  PURPOSE: Gets the file string of submarine/
 *  DATE 19/05/2019
 *  IMPORT none
 *  EXPORT type (String)
 */
    @Override
    public String toFileString()
    {
        return new String("S," + super.getSerialNum() + "," + super.getYear()
        + "," + super.getEngine().getCylinders() + "," + super.getEngine().getFuel() 
        +  "," + hull + "," + maxDepth);
        // this will return a string of submarine, serialnum, year, engine, fuel
        // hull, maxdepth
    }
}
