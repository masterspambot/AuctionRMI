package Client;

import Server.IAuctionServer;
import Server.Item;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.rmi.*;
import java.util.ArrayList;

public class AuctionClient {
  Remote ro;
  IAuctionServer ser;

  public AuctionClient(String uri) throws RemoteException, MalformedURLException, NotBoundException{
    ro = Naming.lookup(uri);
    ser = (IAuctionServer) ro;
  }
  
  public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime) throws RemoteException{
    ser.placeItemForBid(ownerName, itemName, itemDesc, startBid, auctionTime);
  }
  
  public void bidOnItem(String bidderName, String itemName, double bid) throws RemoteException{
      ser.bidOnItem(bidderName, itemName, bid);
  }
  public void registerListener(String al, String itemName) throws RemoteException{
      
  }
  
  public void getItems() throws RemoteException{
    
    ArrayList<Item> items = ser.getItems();
    
    for(Item item : items){
      System.out.println("Owner: "+item.getOwnerName());
      System.out.println("Item Name: "+item.getItemName());
      System.out.println("Item Description: "+item.getItemDesc());
      System.out.println("Start Bid: "+item.getStartBid());
      System.out.println("Auction Time: "+item.getAuctionTime());
      System.out.println("-----------------------------------------------");
    }
  }
  
  public void printHelp(){
      System.out.println("Type a command to proceed:");
      System.out.println("1 - Add a new auction");
      System.out.println("2 - Bid on an item");
      System.out.println("3 - Print items");
      System.out.println("q - Exit");
  }

  public static void main(String args[]) {
    System.out.println("Auction RMI v1");
    if (args.length == 0 || !args[0].startsWith("rmi:")) {
      System.err.println("Usage: java AuctionClient rmi://host.domain:port/auction");
      return;   
    }         

    try {   
        // The main app code
        AuctionClient client = new AuctionClient(args[0]);
        
        String CurLine, ownerName, itemName, itemDesc;
        double bid;
        int auctionTime;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            client.printHelp();
            CurLine = in.readLine();
            switch (CurLine) {
                case "1":
                    System.out.println("Put Owner name");
                    ownerName = in.readLine();
                    System.out.println("Put Item name");
                    itemName = in.readLine(); 
                    System.out.println("Put Item description");
                    itemDesc = in.readLine();
                    System.out.println("Put Starting bid");
                    bid = Double.parseDouble(in.readLine());
                    System.out.println("Put Time of auction (sec)");
                    auctionTime = Integer.parseInt(in.readLine()); 
                    client.placeItemForBid(ownerName, itemName, itemDesc, bid, auctionTime);
                    break;
                case "2":
                    System.out.println("Put Owner name");
                    ownerName = in.readLine();
                    System.out.println("Put Item name");
                    itemName = in.readLine();
                    System.out.println("Put bid");
                    bid = Double.parseDouble(in.readLine());
                    client.bidOnItem(ownerName, itemName, bid);
                    break;
                case "3":
                    client.getItems();
                    break;
                case "4":
                    System.out.println("Put Item name");
                    itemName = in.readLine();
                    client.registerListener(itemName, itemName);
                    break;
                case "q":
                    return;
                
            }
        }
        
    }
    catch (MalformedURLException ex) {
      System.err.println(args[0] + " is not a valid RMI URL");
    }
    catch (RemoteException ex) {
      System.err.println("Remote object threw exception " + ex);
    }
    catch (NotBoundException ex) {
      System.err.println("Could not find the requested remote object on the server");
    } 
    catch (IOException ex) {
      System.err.println("Error while reading input");
    } 
  }

}
