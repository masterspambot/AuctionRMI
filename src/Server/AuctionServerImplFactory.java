package Server;

import java.rmi.RemoteException;

public class AuctionServerImplFactory {
    public static AuctionServerImpl makeAuctionServerImpl() throws RemoteException{
        return AuctionServerImpl.getInstance();
    }
}
