import java.io.*;

/******************************************************************************
 *  AUTHOR: Jonathan Wright
 *  PURPOSE: To read files and output/input
 *  DATE: 11/05/2019
 *
 *
 *
 ****************************************************************************/

class FileManager
{
/******************************************************************************
 * PURPOSE: To handle file input
 * DATE: 11/05/2019
 * IMPORT: String filename, ShipStorage shipStorage
 * EXPORT: none
 *****************************************************************************/
    public static void input(String filename, ShipStorage shipStorage)
    {
        FileInputStream fileStream = null; // init         
        String[] finalLineArray;
        try
        { // try for trying to add ship for that line.
            fileStream = new FileInputStream(filename); // open
            InputStreamReader reader = new InputStreamReader(fileStream); 
            // create reader
            BufferedReader bufferedReader = new BufferedReader(reader); 
            // create buffered
  
            String line = bufferedReader.readLine();
            if (line == null)
            {
                Error.showError("No ship was added");
            }
            while (line != null)
            {
                try
                {
                    String[] lineArray = line.split(",");
             /*   for (int i = 0; i < lineArray.length; i++)
                {
                    System.out.println(lineArray[i]);
                } */                
                    if (lineArray.length != 7)
                    {
                        throw new IllegalArgumentException("Invalid File Format");
                    }
                    if (lineArray[0].length() != 1)
                    {
                        throw new IllegalArgumentException("Type should be one char");
                    }

                    char ship = lineArray[0].charAt(0);
                    String serialNum = lineArray[1];
                    int year = Integer.parseInt(lineArray[2]);
                    int cylinders = Integer.parseInt(lineArray[3]);
                    String fuel = lineArray[4];

                    Engine outEngine = new Engine(fuel, cylinders); // alt cons
                    switch (Character.toUpperCase(ship))
                    {
                        case 'S':
                            String hull = lineArray[5];
                            double depth = Double.parseDouble(lineArray[6]);

                            Submarine outSubmarine =
                            new Submarine(year, hull, depth, serialNum, outEngine);

                            shipStorage.addShip(outSubmarine);
                            break;
                        case 'F':
                            double wingSpan = Double.parseDouble(lineArray[5]);
                            String ordanace = lineArray[6];
                        
                            FighterJet outJet =
                            new FighterJet(year, ordanace, wingSpan, serialNum,
                            outEngine);
    
                        // debug System.out.println(outJet.toString());
                            shipStorage.addShip(outJet);
                            break;
                        default:
                            throw new IllegalArgumentException("not valid ship");
                    }
                }
                catch (IllegalArgumentException e)
                {
                    Error.showError(e.getMessage()); // inform user.
                } // That line was invalid, try to get next
                line = bufferedReader.readLine();
            } 
            fileStream.close();
        }
        catch (IOException e)
        {
            if (fileStream != null) {
                try { fileStream.close(); } catch (IOException e2) {} //impossi
            }
            Error.showError(e.getMessage());
        }
    }
/******************************************************************************
 * PURPOSE: To handle file output
 * DATE: 11/05/2019
 * IMPORT: String filename, ShipStorage shipStorage
 * EXPORT: none
 *****************************************************************************/
    public static void output(String filename, ShipStorage shipStorage)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;
        try
        {
            fileStream = new FileOutputStream(filename);
            pw = new PrintWriter(fileStream);
            for (int i = 0; i < shipStorage.getShipCount(); i++)
            {
                pw.println(shipStorage.getShipArray()[i].toFileString());
                // ^ does the same as below.
                /*if (shipStorage.getShip(i).getType().equals("FighterJet"))
                {
                pw.println("F," + shipStorage.getFighter(i).getSerialNum()
                + "," + shipStorage.getFighter(i).getYear() + ","
                + shipStorage.getFighter(i).getEngine().getCylinders() + 
                "," + shipStorage.getFighter(i).getEngine().getFuel() + ","
                + shipStorage.getFighter(i).getWingSpan() + ","
                + shipStorage.getFighter(i).getOrdanace());
                } // if fighterJet
                else if (shipStorage.getShip(i).getType().equals("Submarine"))
                {
                pw.println("S," + shipStorage.getSubmarine(i).getSerialNum()
                + "," + shipStorage.getSubmarine(i).getYear() + ","
                + shipStorage.getSubmarine(i).getEngine().getCylinders() + "," + 
                shipStorage.getSubmarine(i).getEngine().getFuel() + ","
                + shipStorage.getSubmarine(i).getHull() + ","
                + shipStorage.getSubmarine(i).getMaxDepth());
                } // if submarine*/
            }
            pw.close();
        }
        catch (IOException e)
        {
            if (fileStream != null)
            {
                try { fileStream.close(); } catch (IOException ex2) {} // nil
            }
            Error.showError(e.getMessage());
        }
    }

}
