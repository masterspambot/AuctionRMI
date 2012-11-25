/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.*;
import java.util.ArrayList;
/**
 *
 * @author Ijin
 */
class AuctionServerImpl extends java.rmi.server.UnicastRemoteObject implements IAuctionServer{
    
    private ArrayList<Item> items;
    
    public AuctionServerImpl() throws RemoteException {
          super();
        ArrayList<Item> items = new ArrayList(); // 1000 is max for this system
        items.add(new Item("Jan Kowalski", "Dildo analne", "Bardzo dobre", 20.0, 14));
        items.add(new Item("Jan Kowalski", "Dildo analne", "Bardzo dobre", 20.0, 14));
    }

    @Override
    public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void bidOnItem(String bidderName, String itemName, double bid) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Item> getItems() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerListener(AuctionServerImpl al, String itemName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
