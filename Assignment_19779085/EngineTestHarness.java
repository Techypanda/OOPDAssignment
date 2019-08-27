// java.util not needed.
class EngineTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            Engine[] engines = new Engine[4];
            // creation of the objects
            engines[0] = new Engine(); // Default Constructor
            engines[1] = new Engine("BIO", 2); // Alternate
            engines[2] = new Engine(engines[1]);
            engines[3] = engines[1].clone();

            // prints out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for (int i = 0; i < engines.length; i++)
            {
                System.out.println("engines[" + i + "]: " + engines[i].toString());
            }

            // equals method
            System.out.println("\nEquals Method Tests:");
            System.out.println("Equals (object) expected TRUE: " +
                           engines[1].equals(engines[3]));
            System.out.println("Equals (object) expected FALSE: " +
                           engines[0].equals(engines[3]));       

            // getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            engines[0].setFuel(engines[1].getFuel());
            System.out.println(engines[0].getFuel() + " = " + engines[1].getFuel());
            engines[0].setCylinders(engines[1].getCylinders());
            System.out.println(engines[0].getCylinders() + " = " + engines[1].getCylinders());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
