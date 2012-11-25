/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Ijin
 */
public class WaitAndBid extends AuctionClient implements ActionListener, Runnable{
    private Item item;
    private String bidderName;
    
    public WaitAndBid(Item item, String bidderName) throws RemoteException{
        this.item = item;
        this.bidderName = bidderName;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            bidOnItem(this.bidderName, this.item.getItemName(), this.item.getMaxBid());
        } catch (RemoteException ex) {
            Logger.getLogger(WaitAndBid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Timer t = new Timer((item.getAuctionTime()-1)*1000, this);
        t.start();
    }
}
