package Server;

import Client.IAuctionListener;
import java.io.Serializable;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class AuctionServerImpl extends Observable implements IAuctionServer{

    private class WrappedObserver implements Observer, Serializable {
        private static final long serialVersionUID = 1L;
        private IAuctionListener ro = null;

        public WrappedObserver(IAuctionListener ro) {
            this.ro = ro;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
                ro.update((Item)arg);
            } catch (RemoteException err) {
                System.out.println("Remote exception observer:" + this);
            }
        }
    }
    
    private ArrayList<Item> items;
    
    public AuctionServerImpl() throws RemoteException {
        super();
        items = new ArrayList<>();
        items.add(new Item("Jan Kowalski", "Dildo analne", "Bardzo dobre", 20.0, 30.0, 14));
    }

    @Override
    public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, double maxBid, int auctionTime) throws RemoteException {
        items.add(new Item(ownerName, itemName, itemDesc, startBid, maxBid, auctionTime));
    }

    @Override
    public void bidOnItem(String bidderName, String itemName, double bid) throws RemoteException {
        for(Item item:items){
            if(item.getItemName().matches(itemName)  && item.getCurrentBid() < bid){
                item.setCurrentBid(bid);
                item.setWinnerName(bidderName);
                if(item.getMaxBid() <= bid){
                    item.setAuctionTime(0);
                }
            }
        }
    }

    @Override
    public ArrayList<Item> getItems() throws RemoteException {
        return items;
    }

    @Override
    public void registerListener(IAuctionListener al, String itemName) throws RemoteException {
        System.out.println("Registered new client for: "+itemName);
        WrappedObserver mo = new WrappedObserver(al);
        addObserver(mo);
        setChanged();
        notifyObservers(items.get(0));
    }
}
