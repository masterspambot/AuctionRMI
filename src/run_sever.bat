del /S *.class
start rmiregistry
javac Server/AuctionServerImpl.java
rmic Server.AuctionServerImpl
rem copy Fib\Server\AuctionServerImpl_Stub.class Client\AuctionServerImpl_Stub.class
javac Server/AuctionServer.java
javac Client/AuctionClient.java
start /B java Server.AuctionServer
@echo.
@echo Wait until AuctionServer is ready before connecting to it!
@echo.
@pause