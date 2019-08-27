class ShipStorageTestHarness
{
    public static void main(String[] args)
    {
    try
    {
        ShipStorage storage = new ShipStorage();
        
        storage.addShip(new FighterJet());
        storage.addShip(new FighterJet());
        storage.addShip(new FighterJet());
        storage.addShip(new Submarine());
        storage.addShip(new Submarine());
        storage.addShip(new Submarine());
        System.out.println(storage.destinationCheck(1)); 
        System.out.println(storage.findDuplicates());
        System.out.println(storage.viewShips());
        ShipStorage compare = new ShipStorage();
        assert !storage.equals(compare) : " Equals";
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
    }
    }
}
