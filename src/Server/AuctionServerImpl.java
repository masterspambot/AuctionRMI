package Server;

import Client.IAuctionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Implements server methods responsible for auction system.
 * 
 */
final class AuctionServerImpl extends Observable implements IAuctionServer{
    
   /**
    * Represents an observer (client).
    * 
    * @author Patryk Orwat
    */
    private class WrappedObserver implements Observer, Serializable {
        private static final long serialVersionUID = 1L;
        private IAuctionListener ro = null;
        private String itemName = null;

        public WrappedObserver(IAuctionListener ro, String name) {
            this.ro = ro;
            this.itemName = name;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
                if(this.itemName.equals(((Item)arg).getItemName())){
                    ro.update((Item)arg);
                }
            } catch (RemoteException err) {
                System.out.println("Remote exception observer:" + this);
            }
        }
    }
    
    private static volatile AuctionServerImpl instance = null;
    private ArrayList<Item> items;
    private static final String authCode = "DFER#CT%$$@GEFXEG";
    private static final String logFileName = "auctionRMI.log";
    
    private BufferedWriter logFileWriter;
    private static final Logger logger = Logger.getLogger("AuctionRMI");
    private FileHandler loggerFileHandler;
    
    /** 
     * Singleton pattern implementation.
     * source: http://en.wikipedia.org/wiki/Singleton_pattern
     * "should not be used prior to J2SE 5.0"
     * 
     * @return AuctionServerImpl the only object in the application
     * @throws RemoteException 
     * @throws IOException
     */
    public static AuctionServerImpl getInstance() throws RemoteException, IOException {
        if (instance == null) {
            synchronized (AuctionServerImpl.class) {
                if (instance == null) {
                    instance = new AuctionServerImpl();
                }
            }
        }
        return instance;
    }
    
    /**
     * Private constructor of the class to prevent the creation of more than one
     * object (singleton pattern).
     * 
     * @throws RemoteException 
     * @throws IOException
     */
    private AuctionServerImpl() throws RemoteException, IOException {
        super();
        this.loggerFileHandler = new FileHandler(logFileName, true);
        logger.addHandler(this.loggerFileHandler);
        logger.setLevel(Level.ALL);
        this.loggerFileHandler.setFormatter(new SimpleFormatter());
        
        items = new ArrayList<>();
        items.add(new Item("Jan Marcinowski", "Budzik", "Dobry budzik.", 20.0, 30.0, 14));
        items.add(new Item("Piotr Piotrowski", "Laptop", "Kiepski laptop z 2007 roku.", 10.0, 30.0, 30));
        items.add(new Item("Krzysztof Jackowski", "Kubek", "Bardzo pojemny pojemnik na ciecze, zele i zole.", 1.0, 30.0, 30));
        
        logger.log(Level.INFO, "Server started");
    }
    
    /**
     * Creates a new Item object and adds to the list of items.
     * 
     * @param authCode  authorisation code
     * @param ownerName owner name
     * @param itemName  item name
     * @param itemDesc  iten description
     * @param startBid  start bid
     * @param maxBid    max bid
     * @param auctionTime aution time
     * @throws RemoteException 
     */
    @Override
    public void placeItemForBid(String authCode, String ownerName, String itemName, String itemDesc, double startBid, double maxBid, int auctionTime) throws RemoteException {
        if(!authCode.equals(AuctionServerImpl.authCode)){
            throw new RemoteException("Authorisation error!");
        }        
        checkUniqueName(itemName);
        items.add(new Item(ownerName, itemName, itemDesc, startBid, maxBid, auctionTime));
        logger.log(Level.INFO, "Added new item: {0} by {1}", new Object[]{itemName, ownerName});
    }
    
    /**
     * Bids one Item. If an Item is changed, observers are noitfied.
     * 
     * @param authCode  authorisation code
     * @param bidderName    name of the bidder
     * @param itemName      name of the item
     * @param bid           value of the bid
     * @throws RemoteException 
     */
    @Override
    public void bidOnItem(String authCode, String bidderName, String itemName, double bid) throws RemoteException {
        if(!authCode.equals(AuctionServerImpl.authCode)){
            throw new RemoteException("Authorisation error!");
        }
        for(Item item:items){
            if(item.getItemName().matches(itemName)){
                if (item.getCurrentBid() < bid){
                    item.setCurrentBid(bid);
                    item.setWinnerName(bidderName);
                    if(item.getMaxBid() <= bid){
                        item.setAuctionTime(0);
                    }
                    setChanged();
                    notifyObservers(item);
                    logger.log(Level.INFO, "Bidded item: {0} by {1}", new Object[]{itemName, bidderName});
                }
                break;
            }
        }
    }
    
    /**
     * Returns a list of items.
     * 
     * @param authCode  authorisation code
     * @return ArrayList<Item> list of items
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Item> getItems(String authCode) throws RemoteException {
        if(!authCode.equals(AuctionServerImpl.authCode)){
            throw new RemoteException("Authorisation error!");
        }
        return items;
    }
    
    /**
     * Register a client to observe an Item (observer pattern).
     * 
     * @param authCode  authorisation code
     * @param al       client object
     * @param itemName Item name
     * @throws RemoteException 
     */
    @Override
    public void registerListener(String authCode, IAuctionListener al, String itemName) throws RemoteException {
        if(!authCode.equals(AuctionServerImpl.authCode)){
            throw new RemoteException("Authorisation error!");
        }
        System.out.println("Registered new client for: "+itemName);
        WrappedObserver mo = new WrappedObserver(al, itemName);
        addObserver(mo);
        setChanged();
        notifyObservers(items.get(0));
    }
    
    /**
     * Checks if there is an item with the same itemName. If so, a
     * RemoteException is throwed.
     * 
     * @param name  the name to be check
     * @throws RemoteException 
     */
    public void checkUniqueName(String name) throws RemoteException {
        for(Item item:items){
            if(item.getItemName().equals(name)){
                throw new RemoteException("The name is not unique!");
            }
        }
    }
}
