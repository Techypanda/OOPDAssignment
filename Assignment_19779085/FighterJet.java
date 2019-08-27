import java.text.DecimalFormat; // decimalformat

/******************
 * AUTHOR Jonathan Wright
 * Purpose Fighter Jet Class
 * Date 28/04/2019
 */


class FighterJet extends Ship
{
        // class fields
    private String ordanace; // string thats not empty
    private double wingSpan; // real between 2.20 and 25.6 (inclu
    // def constructor
    /**************************************************************************
    *  PURPOSE To be the default constructor                                  *
    *  IMPORT None                                                            *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public FighterJet()
    {
        super();
        ordanace = "Description";
        wingSpan = 2.20;
    }
    // alternate constructor
    /**************************************************************************
    *  PURPOSE To be the alterna constructor                                  *
    *  IMPORT int inYear String inordanace double inWingSpan double inSerialNum 
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public FighterJet(int inYear, String inOrdanace,
                      double inWingSpan, String inSerialNum,
                      Engine inEngine)
    {
        super(inSerialNum, inYear, inEngine);
        setOrdanace(inOrdanace);
        setWingSpan(inWingSpan);
    }
    /**************************************************************************
    *  PURPOSE To be the copy    constructor                                  *
    *  IMPORT FighterJet inFighterJet                                         *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public FighterJet(FighterJet inFighterJet)
    {
        super(inFighterJet);
        ordanace = inFighterJet.getOrdanace();
        wingSpan = inFighterJet.getWingSpan();
    }
    /**************************************************************************
    *  PURPOSE To clone a FighterJet                                          *
    *  IMPORT None                                                            *
    *  EXPORT FighterJet clone                                                *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    @Override
    public FighterJet clone()
    {
        FighterJet jetClone = new FighterJet(this);
        return jetClone;
    }
    /**************************************************************************
    *  PURPOSE To get ordanace of jet                                         *
    *  IMPORT None                                                            *
    *  EXPORT String ordanace                                                 *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public String getOrdanace()
    {
        return ordanace;
    }
    /**************************************************************************
    *  PURPOSE To get WingSpan                                                *
    *  IMPORT None                                                            *
    *  EXPORT double wingSpan                                                 *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public double getWingSpan()
    {
        return wingSpan;
    }
    /**************************************************************************
    *  PURPOSE To check object equal jet                                      *
    *  IMPORT Object inObject                                                 *
    *  EXPORT boolean isEqual                                                 *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        if (inObject instanceof FighterJet)
        {
            FighterJet inFighterJet = (FighterJet)inObject;
            if (super.equals(inFighterJet))
            {
                // System.out.println(inFighterJet.getYear() + " = " + this.getYear());
                if (ordanace.equals(inFighterJet.getOrdanace()))
                {
                    // System.out.println(inFighterJet.getOrdanace() + " = " + this.getOrdanace());
                    if (wingSpan == inFighterJet.getWingSpan())
                    {
                        // System.out.println(inFighterJet.getWingSpan() + " = " + this.getWingSpan());
                        isEqual = true;
                    }
                }
            }
        }
        return isEqual;
    }
    /**************************************************************************
    *  PURPOSE To set Ordanace of fighterjet                                  *
    *  IMPORT String inOrdanace                                               *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public void setOrdanace(String inOrdanace)
    {
        if (inOrdanace == null || inOrdanace.equals("") || 
            inOrdanace.trim().length() == 0)
        {
            throw new IllegalArgumentException("Invalid Ordanace");
        }
        ordanace = inOrdanace;
    }
    /**************************************************************************
    *  PURPOSE To set wingSpan of fighterjet                                  *
    *  IMPORT double inWingSpan                                               *
    *  EXPORT None                                                            *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/
    public void setWingSpan(double inWingSpan)
    {
        if (inWingSpan < 2.2 || inWingSpan > 25.6)
        {
            throw new IllegalArgumentException("Invalid Ordanace");
        }
        wingSpan = inWingSpan;
    }
    /**************************************************************************
    *  PURPOSE To turn jet deets into a string                                *
    *  IMPORT None                                                            *
    *  EXPORT String jetString                                                *
    *  DATE 30/04/2019                                                        *
    **************************************************************************/ 
    public String toString()
    {
        DecimalFormat twodp = new DecimalFormat("##.00");
        String jetString = super.toString() + " It is a fighter jet "
        + " with a wing span of " + twodp.format(wingSpan) + 
        " meters and equipped with " + ordanace + ".";
        return jetString;
    }
    /**************************************************************************
    * PURPOSE: distance will be calculated for fighterJet
    * DATE: 11/05/2019
    * IMPORT: integer distance
    * EXPORT: hoursTaken (REAL)
    **************************************************************************/
    @Override
    public double calcDistance(int distance)
    {
        return ((double)distance / (wingSpan * (double)super.getEngine().
        getCylinders() * 150.0));
        // returns equation from assignment
    }

    /**************************************************************************
 *  PURPOSE returns file string version
 *  DATE 19/05/2019
 *  IMPORT none
 *  EXPORT type (STRING)
 **/
    @Override
    public String toFileString()
    {
        return new String("F," + super.getSerialNum() + "," + super.getYear()
        + "," + super.getEngine().getCylinders() + "," + 
        super.getEngine().getFuel() + "," + wingSpan + "," + ordanace);
    }
}
