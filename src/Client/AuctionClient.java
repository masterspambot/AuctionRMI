package Client;

import java.rmi.*;
import java.net.*;
import Server.IAuctionServer;
import Server.Item;
import java.util.ArrayList;

public class AuctionClient {
  Remote ro;
  IAuctionServer ser;

  public AuctionClient(String uri) throws RemoteException, MalformedURLException, NotBoundException{
    ro = Naming.lookup(uri);
    ser = (IAuctionServer) ro;
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

  public static void main(String args[]) {
    if (args.length == 0 || !args[0].startsWith("rmi:")) {
      System.err.println("Usage: java AuctionClient rmi://host.domain:port/auction");
      return;   
    }         

    try {      
        AuctionClient client = new AuctionClient(args[0]);
        client.getItems();
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
  }

}
