package RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import DataBase.PeerDataBase;


public class ValuesServer {


	public void startPeerLocalServer(String iDconnection, PeerDataBase pDB) throws RemoteException, AlreadyBoundException {
        IRemoteValues remoteValues=new RemoteValuesServant(pDB);
        Registry registry = LocateRegistry.getRegistry();
        registry.bind(iDconnection, remoteValues);
        System.out.println("peer"+iDconnection+" server ready");

    }

}
