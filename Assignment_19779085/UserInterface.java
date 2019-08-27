import java.util.*;
/*
 * Purpose: To handle user input and output
 * DATE: 11/05/2019
 * Author: Jonathan Wright
 */
class UserInterface
{
    // class constants
    public static final int EXIT = 7;
    // none
    // class fields
    private ShipStorage shipStorage;
    // class methods

/******************************************************************************
 *  Purpose: Default Constructor
 *  Date: 11/05/2019
 *  Import: None
 *  Export: None
 *****************************************************************************/
    public UserInterface()
    {
        shipStorage = new ShipStorage();
    }
/******************************************************************************
 *  Purpose: Displays the main menu
 *  Date: 11/05/2019
 *  Import: none
 *  Export: none
 *****************************************************************************/
    public void mainMenu()
    {
        int selection = 0;
        String prompt = new String("Input your selection");
        do
        {
            try
            {
                System.out.println("*****************************************");
                System.out.println("The program can currently do this:       ");
                System.out.println("You currently have " + 
                shipStorage.getShipCount() + " Ships");
                System.out.println("                                         ");
                System.out.println("1. addShips (Enter 1)                    ");
                System.out.println("2. view all ships (Enter 2)              ");
                System.out.println("3. DestinationCheck (Enter 3)            ");
                System.out.println("4. FindDuplicates (Enter 4)              ");
                System.out.println("5. File Input (Enter 5)                  ");
                System.out.println("6. File Output (Enter 6)                 ");
                System.out.println("7. Exit (Enter 7)                        ");
                System.out.println("*****************************************");
                selection = integerInput(prompt); // will clear input
                methodCaller(selection);
            }
            catch (InputMismatchException e)
            {
                prompt = "Invalid Selection! \n Input your selection:";
            }
        }
        while(selection != EXIT);
    }
/******************************************************************************
 *  Purpose: Takes in selection and calls method based off input
 *  Date: 11/05/2019
 *  Import: int Selection
 *  Export: none
 *****************************************************************************/
    private void methodCaller(int selection)
    {
        switch (selection)
        {
            case 1:
                addShipSelection();
                break;
            /*case 2:
                modifyShipSelection();
                break;*/
            /*case 3:
                viewShipSelection();
                break;*/
            case 2:
                //if (shipStorage.getShipsCount() == 0)
                //{
                //    System.out.println("There are no ships!");
                //} // if no ships 
                //else
                //{
                System.out.println(shipStorage.viewShips());
                //}
                break;
            case 3:
                // if (shipStorage.getShipsCount() == 0)
                // { // if no ships
                  //  System.out.println("There are no ships!");
                // }
                //else
                //{ // just quick validation to recieve distance to check
                boolean valid = false; 
                int distance = 0;
                do
                {
                    try
                    {
                        distance = integerInput("Input distance");
                        if (distance <= 0)
                        {
                            throw new IllegalArgumentException();
                        }
                            valid = true;
                    }
                    catch (IllegalArgumentException | InputMismatchException
                           e)
                    {
                        Error.showError("Invalid distance!");
                    }
                }
                while (!valid);
                System.out.println(shipStorage.destinationCheck(distance));
                break;
            case 4:
                String duplicates = shipStorage.findDuplicates();
                System.out.println(duplicates);
                break;
            case 5:
                fileInput();
                break;
            case 6:
                fileOutput();
                break;
            case EXIT:
                System.out.println("Goodbye User!");
                break; // user should end program
            default:
                Error.showError("Invalid Selection!");
                // uncertain what happened, invalid selection
        }
    }
/******************************************************************************
 *  Purpose: Selects which ship to add.
 *  Date: 11/05/2019
 *  Import: None
 *  Export: None
 *****************************************************************************/
    private void addShipSelection()
    {
        try
        {
            shipStorage.full();
            int selection = -1;
            String prompt = new String("Do you wish to add a jet (Enter 1)"
                                + "or a submarine? (Enter 2)");
            do
            {
                try
                {
                    selection = integerInput(prompt);
                    if (selection == 1)
                    {
                        addJet();
                    }
                    else if (selection == 2)
                    {
                        addSub();
                    }
                    else
                    {
                        Error.showError("Invalid Input");
                    }
                }
                catch (InputMismatchException e)
                {
                    Error.showError("Invalid Input!");
                }
            }
            while (selection < 0 || selection > 2);
        }
        catch (IllegalArgumentException e)
        {
            Error.showError(e.getMessage());
        }
    } // refactored
/******************************************************************************
 *  Purpose: Adds a jet to the ship array
 *  Date: 11/05/2019
 *  Import: None
 *  Export: None
 *****************************************************************************/
    private void addJet()
    {
        shipStorage.addShip( // adds ship if there is room.
            new FighterJet(
                yearValidation("What is the comission year of FighterJet?"
                + "\nA value between 1950 and 2022"), // gets year
                ordanaceValidation("What is the Ordanace of "
                + "FighterJet?"), // gets ordanace
                wingSpanValidation("What is the wingSpan of "
                + "FighterJet?" + "\nReal value between " + "2.2 and 25.6"),
                // gets wingspan
                serialNumValidation("What is SerialNum of"
                + " FighterJet? \n(XXX.YYY)" + " (Real)"), // gets serialNum
                addEngine() // gets engine.
            ) // constructs Jet
        );   
    } // REFACTORED
/******************************************************************************
 *  Purpose: Adds a sub to ship array
 *  Date: 11/05/2019
 *  Import: None
 *  Export: None
 *****************************************************************************/
    private void addSub()
    {
     
    shipStorage.addShip( // adds ship if there is room.
            new Submarine(
                yearValidation("What is the commission year of Submarine?"
                + "\nA value between 1950 and 2022"), // gets year
                hullValidation("What is Hull of Submarine? (String)"
                + "\nSteel, Alloy, Titanium"), // gets Hull
                maxDepthValidation("What is maxDepth of Submarine?"
                + "\n(-value) (Real)"), // gets maxDepth
                serialNumValidation("What is SerialNum of"
                + " Submarine?" + "\n(XXX.YYY) (Real)"), // gets serialNum
                addEngine() // gets engine
            ) // constructs submarine
        );
    } // REFACTORED
/******************************************************************************
 * PURPOSE: To construct a engine based off user input
 * DATE: 11/05/2019
 * IMPORT: Nne
 * EXPORT: Engine engine
 *****************************************************************************/
    private Engine addEngine()
    {
        String fuel = fuelValidation("Please input the fuel the engine uses"
                                    +" (Battery, Bio, Diesel)");
        int cylinders = cylinderValidation("Please input the cylinders the "
                        + "engine has (Integer number between 2 and 20)");
        Engine engine = new Engine(fuel, cylinders);
        return engine;
    }
/******************************************************************************
 *  Purpose: Outputs the current submarines and gets which sub/jet to modify
 *  Date: 11/05/2019
 *  Import: none
 *  Export: none
 *****************************************************************************/
   /*private void modifyShipSelection()
    {
        int selection = 0; // init and declare
        do
        {
            try
            {
                selection = integerInput("Would you like to modify a submarine?"
                                         + " (Enter 1) or a FighterJet"
                                         + " (Enter 2)");
                if (selection == 1) 
                {
                    if (shipStorage.getSubmarineCount() > 0)
                    {
                        modifySubmarine();
                    }
                    else
                    {
                        Error.showError("There is no submarines!");
                    }
                }
                else if (selection == 2)
                {
                    if (shipStorage.getFighterCount() > 0)
                    {
                        modifyJet();
                    }
                    else
                    {
                        Error.showError("There is no fighter jets!");
                    }
                }
                else
                {
                    Error.showError("Invalid Input!");
                }
            }
            catch (InputMismatchException e)
            {
                Error.showError("Invalid Input!");
            }   
        }
        while (selection < 1 || selection > 2);
    } // REFACTORED
/******************************************************************************
 *  Purpose: Modifys current submarine
 *  Date: 11/05/2019
 *  Import: None
 *  Export: None
 *****************************************************************************/
    /*private void modifySubmarine()
    {
        int selectedValue = 0;
        Submarine sub = new Submarine();
        System.out.println(shipStorage.viewSubmarines()); // updated
        boolean valid = false;
        do
        {
            try
            {
                sub = shipStorage.getSubmarine(
                        integerInput("What is the number of the sub "
                                     + "you wish to edit?"));
                valid = true;
            }
            catch (NullPointerException | InputMismatchException e)
            {
                Error.showError("Invalid Submarine!");
            }
        }
        while (valid == false);
        do {
            try
            {
                selectedValue = integerInput("What value would you like to "
                                + " change? \nCommission Year (Enter 1)"
                                + "\nHull (Enter 2)"
                                + "\nmaxDepth (Enter 3)"
                                + "\nserialNum (Enter 4)"
                                + "\nfuel (Enter 5)"
                                + "\ncylinders (Enter 6)");
                if (selectedValue > 6 || selectedValue < 1)
                {
                    Error.showError("Invalid Input!"); // skipped if exception thrown
                }
            }
            catch (InputMismatchException e)
            {
                Error.showError("Invalid input!");
            }
        }
        while (selectedValue > 6 || selectedValue < 1);
        switch (selectedValue)
        {
            case 1:
                int year = yearValidation("Input commission year (Integer)");
                sub.setYear(year);
                break;           
            case 2:
                String hull = hullValidation("Input Hull (String)");
                sub.setHull(hull);
                break;
            case 3:
                double maxDepth = maxDepthValidation("Innput maxDepth (Real)");
                sub.setMaxDepth(maxDepth);
                break;
            case 4:
                String serialNum = serialNumValidation("Input serialNum (Real)");
                sub.setSerialNum(serialNum);
                break;
            case 5:
                String fuel = fuelValidation("Input fuel (String)");
                sub.getEngine().setFuel(fuel);
                break;
            case 6:
                int cylinders = cylinderValidation("Input Cylinders (Integer)");
                sub.getEngine().setCylinders(cylinders);
                break;
            default:
                // not needed
        }
    } // REFACTORED i hate refactoring
/******************************************************************************
 *  Purpose: Modify current jet
 *  Date: 11/05/2019
 *  Import: None
 *  Export: None
 *****************************************************************************/
    /*private void modifyJet()
    {
        FighterJet jet = new FighterJet();
        int selection = 0;
        System.out.println(shipStorage.viewFighterJets());
        boolean valid = false;
        do
        {
            try
            {
                jet = shipStorage.getFighterJet(integerInput(
                      "What is the number of the jet you wish to edit?"));
                valid = true; // skipped if excception thrown
            }
            catch (NullPointerException | InputMismatchException e)
            {
                Error.showError("Invalid fighterJet!");
            }
        }
        while (valid == false);
        do
        {
            try
            {
                selection = integerInput("What value would you like to change?"
                                         + "\nCommission Year (Enter 1)"
                                         + "\nOrdanace (Enter 2)"
                                         + "\nWingSpan (Enter 3)"
                                         + "\nserialNum (Enter 4)"
                                         + "\nfuel (Enter 5)"
                                         + "\nCylinders (Enter 6)");
            }
            catch (InputMismatchException e)
            {
                Error.showError("Invalid input!");
            }
        }
        while (selection < 1 || selection > 6);
        switch (selection)
        {
            case 1:
                int year = yearValidation("Input Commission Year (Integer)");
                jet.setYear(year);
                break;
            case 2:
                String ordanace = ordanaceValidation("Input Ordanace (String)");
                jet.setOrdanace(ordanace);
                break;
            case 3:
                double wingSpan = wingSpanValidation("Input WingSpan (Real)");
                jet.setWingSpan(wingSpan);
                break;
            case 4:
                String serialNum = serialNumValidation("Input serialNum (Real)");
                jet.setSerialNum(serialNum);
                break;
            case 5:
                String fuel = fuelValidation("Input fuel (String)");
                jet.getEngine().setFuel(fuel);
                break;
            case 6:
                int cylinders = cylinderValidation("Input cylinders (Integer)");
                jet.getEngine().setCylinders(cylinders);
                break;
            default:
                Error.showError("Somehow you got a invalid value."); // Not possible
                break;
        }
    } // REFACTORED
/******************************************************************************
 *  Purpose: Gets which ship it wants to select (Sub or Jet)
 *  Date: 11/05/2019
 *  Import: none
 *  Export: none
 *****************************************************************************/
    /*private void viewShipSelection()
    {
        int selection = 0;
        do
        {
            try
            {
                selection = integerInput("Select FighterJet (Enter 1) or "
                                       + "Submarine (Enter 2)");
                if (selection < 1 || selection > 2)
                {
                    Error.showError("Invalid Input");
                }
            }
            catch (InputMismatchException e)
            {
                Error.showError("invalid Input");
            }
        }
        while (selection < 1 || selection > 2);
        if (selection == 1) 
        {
            System.out.println(shipStorage.viewShips());
            boolean valid = false;
            do
            {
                try
                {
                    int arrayNum = integerInput("Enter number associated with"
                                          + " FighterJet");
                    System.out.println(shipStorage.getFighterJet(arrayNum).toString());
                    valid = true; // skipped if exception is thrown
                }
                catch (NullPointerException | InputMismatchException e)
                {
                    Error.showError("Invalid FighterJet");
                }
            }
            while (valid == false);
        }
        else if (selection == 2)
        {
            System.out.println(shipStorage.viewSubmarines());
            boolean valid = false;
            do
            {
                try
                {
                    int arrayNum = integerInput("Enter number associated with"
                                            + " Submarine");
                    System.out.println(shipStorage.getSubmarine(arrayNum).toString());
                    valid = true; // skipped if exception is thrown
                }
                catch (InputMismatchException | NullPointerException e)
                {
                    Error.showError("Invalid Submarine");
                }
            }
            while (valid == true);
        }
    } // refactored
/******************************************************************************
 *  Purpose: Inputs a file
 *  Date: 11/05/2019
 *  Import: none
 *  Export: none
 *****************************************************************************/
    private void fileInput()
    {
        String filename = stringInput("Input filename to recieve input from");
        String extension = stringInput("Enter extension of file if any "
        + " e.g. csv, doc, java (dont enter .)");
        if (extension.equals(""))
        { // no extension
            filename = filename + extension;   
        }
        else
        { // if extension
            filename = filename + "." + extension;   
        }
        FileManager.input(filename,shipStorage);
    } // refactored
/******************************************************************************
 *  Purpose: Outputs a file
 *  Date: 11/05/2019
 *  Import: none
 *  Export: none
 *****************************************************************************/
    private void fileOutput()
    {
        String filename = stringInput("Input filename to output to");
        String extension = stringInput("Enter extension of file if any "
        + " e.g. csv, doc, java (dont enter .)");
        if (extension.equals(""))
        { // no extension
            filename = filename + extension;   
        }
        else
        { // if extension
            filename = filename + "." + extension;   
        }
        FileManager.output(filename,shipStorage);
    } // refactored
/******************************************************************************
 *  Purpose: inputs a integer using a scanner
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: integer
 *****************************************************************************/
    private int integerInput(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        int intOut = sc.nextInt();
        return intOut;
    }
/******************************************************************************
 *  Purpose: inputs a double using a scanner
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: double realOut
 *****************************************************************************/
    private double realInput(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        double realOut = sc.nextDouble();
        return realOut;
    }
/******************************************************************************
 *  Purpose: inputs a string using a scanner
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: String stringOut
 *****************************************************************************/
    private String stringInput(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String stringOut = sc.nextLine();
        return stringOut;
    }
/******************************************************************************
 *  Purpose: validates a year (Should be in a seperate class but not allowed?
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: boolean validation
 *****************************************************************************/
    private int yearValidation(String prompt)
    {
        int year = -1;
        boolean valid = false;
        do
        {
            try
            {
                year = integerInput(prompt);
                if (year < 1950 || year > 2022)
                {
                    throw new IllegalArgumentException("Invalid Year");
                }
                valid = true; // skipped if exception thrown
            }
            catch(IllegalArgumentException | InputMismatchException e)
            {
                prompt = "That is a invalid value for year, please enter"
                       + " a integer number between 1950 and 2022";
            }   
        }
        while (valid == false);
        return year;
    }
/******************************************************************************
 *  Purpose: ordanace validated (should be seperate class not alloewd?)
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: boolean validation
 *****************************************************************************/
    private String ordanaceValidation(String prompt)
    {
        String ordanace = new String();
        boolean valid = false;
        do
        {
            try
            {
                ordanace = stringInput(prompt);
                if (ordanace.equals("") || ordanace.trim().length() == 0
                    || ordanace == null)
                { // if its empty
                    throw new IllegalArgumentException("Invalid String");
                }
                valid = true; // will be skipped if exception thrown.
            }
            catch (IllegalArgumentException | InputMismatchException e)
            {
                prompt = "That is a invalid string for ordanace, please"
                         + " try again.";
            }
        }
        while (valid == false);
        return ordanace;
    }
/******************************************************************************
 *  Purpose: wingSpan validated (should be in seperate class not allowed?)
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: boolean validation
 *****************************************************************************/
    private double wingSpanValidation(String prompt)
    {
        double wingSpan = -1.1;
        boolean valid = false;
        do
        {
            try
            {
                wingSpan = realInput(prompt);
                if (wingSpan < 2.2 || wingSpan > 25.6)
                {
                    throw new IllegalArgumentException("invalid Wingspan");
                }
                valid = true; // skipped  if exception thrown
            }
            catch (IllegalArgumentException | InputMismatchException e)
            {
                prompt = "That is a invalid wingSpan"
                       + "\nIt should be between 2.2 and 25.6";
            }
        }
        while (valid == false);
        return wingSpan;
    }
/******************************************************************************
 *  Purpose: serialnumber validated (Should be in seperate class not allowed?)
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: boolean validation
 *****************************************************************************/
    private String serialNumValidation(String prompt)
    {
        String serialNum = new String();
        boolean valid = false;
        do
        {
            try
            {
                serialNum = stringInput(prompt);
                if (validSerial(serialNum) == false)
                {
                    throw new IllegalArgumentException("Invalid serial Num");
                }
                valid = true; // skipped if exception thrown
            }
            catch (IllegalArgumentException | InputMismatchException e)
            {
                prompt = "That is a invalid serial number!"
                       + "\nIt should be (XXX.YYY) and of type Real";
            }
        }
        while (valid == false);
        return serialNum;       
    }
/******************************************************************************
 *  Purpose: hull validated (SHould be in sepearte class not allowed?)
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: boolean validation
 *****************************************************************************/
    private String hullValidation(String prompt)
    {
        String hull = new String();
        boolean valid = false;
        do
        {
            try
            {
                hull = stringInput(prompt);
                if (validHull(hull) == false)
                {
                    throw new IllegalArgumentException("Invalid hull");
                }
                valid = true; // skipped if exception thrown   
            }
            catch(IllegalArgumentException | InputMismatchException e)
            {
                prompt = "This is a invalid hull!"
                       + "\nSteel, Alloy, Titanium";
            }
        }
        while (valid == false);
        return hull;
    }
/******************************************************************************
 *  Purpose: port from other class (Should be in seperate class)
 *  Date: 11/05/2019
 *  Import: String inHull
 *  Export: boolean validation
 *****************************************************************************/
    private boolean validHull(String inHull)
    {
        inHull = inHull.toUpperCase();
        System.out.println(inHull);
        boolean valid = false;
        for (int i = 0; i < 3; i++)
        {
            // DEBUG PURPOSES: System.out.println(Submarine.VALIDHULL[i]);
            if (inHull.equals(Submarine.VALIDHULL[i]))
            {
                valid = true;
            }
        }
        return valid;
    }
/******************************************************************************
 *  Purpose: serial validation port from other class
 *  Date: 11/05/2019
 *  Import: String inSerialNum
 *  Export: boolean validation
 *****************************************************************************/
    private boolean validSerial(String inSerialNum)
    {
        boolean valid = false;
        if (inSerialNum.length() == 7)
        {
            String[] serials = inSerialNum.split("\\.");
            if (Integer.parseInt(serials[0]) > 99
                && Integer.parseInt(serials[0]) < 301
                && Integer.parseInt(serials[1]) > 0
                && Integer.parseInt(serials[1]) < 1000)
            {
                valid = true;
                // System.out.println("VALID!");
            }
            else
            {
                System.out.println("Error: " + serials[0] + " " + serials[1]);
            }
        }
        return valid;
    }
/******************************************************************************
 *  Purpose: maxDepth validated
 *  Date: 11/05/2019
 *  Import: String prompt
 *  Export: boolean validation
 *****************************************************************************/
    private double maxDepthValidation(String prompt)
    {
        double maxDepth = 10000;
        boolean valid = false;
        do
        {
            try
            {
                maxDepth = realInput(prompt);
                if (maxDepth < -500.0 || maxDepth > 0.0)
                {
                    throw new IllegalArgumentException("Invalid MaxDepth");
                }
                valid = true; // skipped if exception caught
            }
            catch (IllegalArgumentException | InputMismatchException e)
            {
                prompt = "That is a invalid depth!"
                       + "\nMust be between -500 and 0";
            }
        }
        while (valid == false);
        return maxDepth;
    }
/******************************************************************************
 * PURPOSE: To validate the fuel and return it
 * DATE: 11/05/2019
 * IMPORT: String prompt
 * EXPORT: String fuel
 * ***************************************************************************/
    private String fuelValidation(String prompt)
    {
        String fuel = new String();
        boolean valid = false;
        do
        {
            try
            {
                fuel = stringInput(prompt);
                if (!validFuel(fuel))
                {
                    throw new IllegalArgumentException("Invalid Fuel");
                }
                valid = true;
            }
            catch (IllegalArgumentException | InputMismatchException e)
            {
                Error.showError("That is a invalid fueltype!"
                                + "\nMust be battery, bio or diesel!");
            }
        }
        while (!valid);
        return fuel;
    }
/******************************************************************************
 * PURPOSE: to validate the fuel and return true or false
 * DATE: 12/05/2019 12:00AM the joke is 1 minute has passed so it is now a new day
 * IMPORT: String inFuel
 * EXPORT: boolean valid
 * ***************************************************************************/
    private boolean validFuel(String inFuel)
    {
        boolean valid = false;
        inFuel = inFuel.toUpperCase();
        for (int i = 0; i < 3; i++)
        {
            if (inFuel.equals(Engine.FUELTYPES[i]))
            {
                valid = true;
            }
        }
        return valid;
    }
/******************************************************************************
 * PURPOSE: to validate the cylinders and return it
 * DATE: 12/05/2019 12:00AM the joke is 1 minute has passed so it is now a new day
 * IMPORT: String prompt
 * EXPORT: integer cylinders
 * ***************************************************************************/
    private int cylinderValidation(String prompt)
    {
        boolean valid = false;
        int cylinders = 0;
        do
        {   
            try
            {
                cylinders = integerInput(prompt);
                if (cylinders < 2 || cylinders > 20)
                {
                    throw new IllegalArgumentException("Invalid Cylinders");
                }
                valid = true; // skipped if invalid
            }
            catch (IllegalArgumentException | InputMismatchException e)
            {
                Error.showError("Invalid Cylinders Input!, must be greater"
                + " than 2 less than 20");
            }
        }
        while (!valid);
        return cylinders;
    }
}
