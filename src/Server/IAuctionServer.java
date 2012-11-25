package Server;

import java.rmi.*;
import java.util.ArrayList;

public interface IAuctionServer extends Remote {
	public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime) 
		throws RemoteException;
		
	public void bidOnItem(String bidderName, String itemName, double bid) 
		throws RemoteException;
	
	public ArrayList<Item> getItems() throws RemoteException;
		
	//public void registerListener(IAuctionListener al, String itemName) throws RemoteException;
}
