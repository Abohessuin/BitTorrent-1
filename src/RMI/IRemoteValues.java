package RMI;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import App.Peer;


public interface IRemoteValues extends Remote{
	String Find(String Key) throws RemoteException;
	boolean Store(String keyData, String valueData, int i) throws AccessException, RemoteException, NotBoundException;
	void AddFriend(String FriendID)throws RemoteException;
	void RemoveFriend(String FriendID)throws RemoteException;
}  
