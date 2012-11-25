find ./Server -name "*.class" -exec rm -rf {} \;

killall rmiregistry 2>/dev/null
sleep 1
rmiregistry >/dev/null &

javac Server/AuctionServerImpl.java &&\
rmic Server.AuctionServerImpl &&\
\
javac Server/AuctionServer.java &&\
java Server.AuctionServer &&\
echo "Compiled and started."
