package Server;

import java.rmi.*;
import java.util.ArrayList;


public class AuctionServerImpl implements IAuctionServer {
  
  ArrayList<Item> items;
    
  public AuctionServerImpl() throws RemoteException {
    super();
    items = new ArrayList<Item>(); // 1000 is max for this system
    items.add(new Item("Jan Kowalski", "Dildo analne", "Bardzo dobre", 20.0, 14));
    items.add(new Item("Jan Kowalski", "Dildo analne", "Bardzo dobre", 20.0, 14));
  }
  
  public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime) 
	 throws RemoteException {

  }
  
  public void bidOnItem(String bidderName, String itemName, double bid) 
		throws RemoteException{
			
	}
	
	public ArrayList<Item> getItems() throws RemoteException{
		return items;
	}

}
 
