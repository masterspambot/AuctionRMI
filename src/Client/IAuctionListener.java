package Client;

import Server.Item;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface implemented by AuctionClient to provide callbacks from server
 * side. Required by RMI.
 */
public interface IAuctionListener extends Remote{
    
    /**
     * Callback function which is called on the server side to inform the 
     * client that an Item (item param) has changed.
     * 
     * @param item item to be observable.
     * @throws RemoteException
     */
    public void update(Item item) throws RemoteException;
}
