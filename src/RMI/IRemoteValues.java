package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import App.Peer;


public interface IRemoteValues extends Remote{
   String Find(String Key) throws RemoteException;
   void Store(String KeyData,String ValueData) throws RemoteException;
}