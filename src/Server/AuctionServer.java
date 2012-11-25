package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Main class responsible for running the server.
 * 
 */
public class AuctionServer {

    /**
     *  Gets AuctionServerImpl object, makes a stub and registers it.
     * 
     * @param args system arguments (not used)
     */
    public static void main(String[] args) {
        try {
            AuctionServerImpl servImpl = AuctionServerImplFactory.makeAuctionServerImpl();
            IAuctionServer servStub = (IAuctionServer) UnicastRemoteObject.exportObject(servImpl);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("auction", servStub);
            System.out.println("Auction Server ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
