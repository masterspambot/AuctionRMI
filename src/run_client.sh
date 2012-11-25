find ./Client -name "*.class" -exec rm -rf {} \;
javac Client/AuctionClient.java && \
echo "Compiled." && \
java Client.AuctionClient rmi://localhost/auction
