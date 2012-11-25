package Server;

import java.rmi.RemoteException;

/**
 * AuctionServerImpl's Factory.
 * 
 */
public class AuctionServerImplFactory {

    /**
     * Returns an AuctionServerImpl object. AuctionServerImpl class uses 
     * singleton pattern and always the same object is returned.
     * 
     * @return AuctionServerImpl 
     * @throws RemoteException
     */
    public static AuctionServerImpl makeAuctionServerImpl() throws RemoteException {
        return AuctionServerImpl.getInstance();
    }
}
