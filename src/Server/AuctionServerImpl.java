/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.IAuctionListener;
import java.rmi.*;
import java.util.ArrayList;
/**
 *
 * @author Ijin
 */
class AuctionServerImpl implements IAuctionServer{
    
    private ArrayList<Item> items;
    
    public AuctionServerImpl() throws RemoteException {
        super();
        items = new ArrayList<>();
        items.add(new Item("Jan Kowalski", "Dildo analne", "Bardzo dobre", 20.0, 14));
    }

    @Override
    public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime) throws RemoteException {
        items.add(new Item(ownerName, itemName, itemDesc, startBid, auctionTime));
    }

    @Override
    public void bidOnItem(String bidderName, String itemName, double bid) throws RemoteException {
        throw new RemoteException("Not supported yet.");
    }

    @Override
    public ArrayList<Item> getItems() throws RemoteException {
        return items;
    }

    @Override
    public void registerListener(IAuctionListener al, String itemName) throws RemoteException {
        System.out.println("Registered new client for: "+itemName);
    }
}
