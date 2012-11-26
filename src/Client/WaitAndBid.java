package Client;

import Server.IAuctionServer;
import Server.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * Class responisible for creating biding thread for WaitAndBid strategy
 */
public class WaitAndBid extends AuctionClient implements ActionListener, Runnable{
    private Item item;
    private String bidderName;
    
    /**
     * Constructor of the class
     * 
     * @param item Item to bid
     * @param bidderName Author of the bid
     * @throws RemoteException
     */
    public WaitAndBid(IAuctionServer s, Item item, String bidderName) throws RemoteException{
        this.ser = s;
        this.item = item;
        this.bidderName = bidderName;
        Thread t = new Thread(this);
        t.start();
    }
    
    /**
     * Perform bid
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            bidOnItem(this.bidderName, this.item.getItemName(), this.item.getMaxBid());
            System.out.println("Auction: " + item.getItemName()+ " bidded to maxBid!");
        } catch (RemoteException ex) {
            Logger.getLogger(WaitAndBid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates timer in new thread
     */
    @Override
    public void run() {
        Timer t = new Timer((item.getAuctionTime()-1)*1000, this);
        t.start();
    }
}
