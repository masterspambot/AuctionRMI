package Server;

import java.rmi.*;
import java.util.ArrayList;


public class AuctionServerImpl extends java.rmi.server.UnicastRemoteObject implements IAuctionServer{
  
  ArrayList<Item> items;
    
  public AuctionServerImpl() throws RemoteException {
    super();
    ArrayList<Item> items = new ArrayList(); // 1000 is max for this system
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

    public void registerListener(IAuctionListener al, String itemName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
 
