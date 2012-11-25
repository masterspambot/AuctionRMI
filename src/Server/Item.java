package Server;

import java.io.*;

public class Item implements Serializable {

    private String ownerName;
    private String itemName;
    private String itemDesc;
    private double startBid;
    private double maxBid;
    private double currentBid;
    private int auctionTime;
    
    public Item() {
        ownerName = "";
        itemName = "";
        itemDesc = "";
        startBid = .0;
        auctionTime = 0;
    }

    public Item(String ownerName, String itemName, String itemDesc, double startBid, double maxBid, int auctionTime) {
        this.ownerName = ownerName;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.startBid = startBid;
        this.maxBid = maxBid;
        this.currentBid = startBid;
        this.auctionTime = auctionTime;
    }

    public void setOwnerName(String val) {
        this.ownerName = val;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setItemName(String val) {
        this.itemName = val;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemDesc(String val) {
        this.itemDesc = val;
    }

    public String getItemDesc() {
        return this.itemDesc;
    }

    public void setStartBid(double val) {
        this.startBid = val;
    }

    public double getStartBid() {
        return this.startBid;
    }
    
    public void setMaxBid(double val) {
        this.maxBid = val;
    }

    public double getMaxBid() {
        return this.maxBid;
    }
    
    public void setCurrentBid(double val) {
        this.currentBid = val;
    }

    public double setCurrentBid() {
        return this.currentBid;
    }  

    public void setAuctionTime(int val) {
        this.auctionTime = val;
    }

    public int getAuctionTime() {
        return this.auctionTime;
    }
}
