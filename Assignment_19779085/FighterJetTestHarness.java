// java.util not needed.
class FighterJetTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            FighterJet[] jets = new FighterJet[4];
            // creation of the objects
            jets[0] = new FighterJet(); // Default Constructor
            jets[1] = new FighterJet(1951, "Description", 5, "100.005", new Engine()); // Alternate
            jets[2] = new FighterJet(jets[1]);
            jets[3] = jets[1].clone();

            // prints out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for (int i = 0; i < jets.length; i++)
            {
                System.out.println("Jets[" + i + "]: " + jets[i].toString());
            }

            // equals method
            System.out.println("\nEquals Method Tests:");
            System.out.println("Equals (object) expected TRUE: " +
                           jets[1].equals(jets[3]));
            System.out.println("Equals (object) expected FALSE: " +
                           jets[0].equals(jets[3]));       

            // getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            jets[0].setYear(jets[1].getYear());
            System.out.println(jets[0].getYear() + " = " + jets[1].getYear());
            jets[0].setOrdanace(jets[1].getOrdanace());
            System.out.println(jets[0].getOrdanace() + " = " + jets[1].getOrdanace());
            jets[0].setWingSpan(jets[1].getWingSpan());
            System.out.println(jets[0].getWingSpan() + " = " + jets[1].getWingSpan());
            jets[0].setSerialNum(jets[1].getSerialNum());
            System.out.println(jets[0].getSerialNum() + " = " + jets[1].getSerialNum());
            jets[0].setEngine(jets[1].getEngine());
            System.out.println(jets[0].getEngine() + " = " + jets[1].getEngine());
        
            // DESTINATION CHECK
            System.out.println("\nDESITNATIN CHECK");
            System.out.println(jets[0].calcDistance(4) + " = " + (4 / (jets[0].getWingSpan() * 2 * 150)));
            System.out.println(jets[0].calcDistance(8) + " = " + (8 / (jets[0].getWingSpan() * 2 * 150)));
            System.out.println(jets[0].calcDistance(12) + " = " + (12 / (jets[0].getWingSpan() * 2 * 150)));

        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
