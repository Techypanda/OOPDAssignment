// java.util not needed.
class SubmarineTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Submarine[] subs = new Submarine[4];
            // creation of the objects
            subs[0] = new Submarine(); // Default Constructor
            subs[1] = new Submarine(1950, "STEEL", -250.0, "100.001",
                                    new Engine()); // Alternate
            subs[2] = new Submarine(subs[1]);
            subs[3] = subs[1].clone();

            // prints out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for (int i = 0; i < subs.length; i++)
            {
                System.out.println("Submarine[" + i + "]: " + subs[i].toString());
            }

            // equals method
            System.out.println("\nEquals Method Tests:");
            System.out.println("Equals (object) expected TRUE: " +
                           subs[1].equals(subs[3]));
            System.out.println("Equals (object) expected FALSE: " +
                           subs[0].equals(subs[3]));       

            // getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            subs[0].setYear(subs[1].getYear());
            System.out.println(subs[0].getYear() + " = " + subs[1].getYear());
            subs[0].setHull(subs[1].getHull());
            System.out.println(subs[0].getHull() + " = " + subs[1].getHull());
            subs[0].setMaxDepth(subs[1].getMaxDepth());
            System.out.println(subs[0].getMaxDepth() + " = " + subs[1].getMaxDepth());
            subs[0].setSerialNum(subs[1].getSerialNum());
            System.out.println(subs[0].getSerialNum() + " = " + subs[1].getSerialNum());
            subs[0].setEngine(subs[1].getEngine());
            System.out.println(subs[0].getEngine() + " = " + subs[1].getEngine());

            // Destination Calculation
            System.out.println("\nDestination Check");
            System.out.println(subs[1].calcDistance(4) + " = " + (4/2 * (1 / (10 + (subs[1].getMaxDepth() * -1)))));
            System.out.println(subs[1].calcDistance(8) + " = " + (8/2 * (1 / (10 + (subs[1].getMaxDepth() * -1)))));
            System.out.println(subs[1].calcDistance(12) + " = " + (12/2 * (1 / (10 + (subs[1].getMaxDepth() * -1)))));
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
