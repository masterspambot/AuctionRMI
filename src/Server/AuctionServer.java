package Server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.RemoteServer;
import java.net.MalformedURLException;

public class AuctionServer {
  public static void main(String[] args) {
    //RemoteServer.setLog(System.err);
    try {
      AuctionServerImpl servImpl = new AuctionServerImpl();
      IAuctionServer servStub = (IAuctionServer) UnicastRemoteObject.exportObject(servImpl);
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("auction", servStub);
      System.out.println("Auction Server ready.");
    } catch (Exception e){  e.printStackTrace(); }
  }
}
