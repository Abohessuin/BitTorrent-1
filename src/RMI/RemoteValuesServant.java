package RMI;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;

import App.Peer;
import DataBase.PeerDataBase;


public class RemoteValuesServant extends UnicastRemoteObject implements IRemoteValues {


	private PeerDataBase PDB;

	protected RemoteValuesServant(PeerDataBase PDB) throws RemoteException {
		super();
		this.PDB=PDB;
	}

	@Override
	public String Find(String Key ) {
		// TODO Auto-generated method stub
		//Peer MyPeer  = peer.getPDB().GetPeerFriend(namepeer);
		Map<String,String> MyData = this.PDB.getMyData();
		if(MyData.containsKey(Key)) {
			return "Found";
		}
		else {
			return "Not Found";
		}


	}

	@Override
	public boolean Store(String KeyData,String ValueData,int flag) throws AccessException, RemoteException, NotBoundException {
		if(flag==0) {
			int capictyOFThisServer=this.PDB.getMaxStorageCapacity();
			Registry registry=this.PDB.getRegistry();
			int realSize=this.PDB.getMyPeersFriends().size();
			if(realSize<capictyOFThisServer) {
				this.PDB.StoreData(KeyData, ValueData);
				return true;
			}else {
				ArrayList<String>myFriendsPeer=this.PDB.getMyPeersFriends();
				for(int i=0;i<capictyOFThisServer;i++) {
					IRemoteValues remoteServerValues = (IRemoteValues) registry.lookup(myFriendsPeer.get(i));
					if(remoteServerValues.Store(KeyData, ValueData,1)) {
						return true;
					}
						
				}
				return false;
			}
		}else {
			return storeForFriend(KeyData, ValueData);
		}


	}


	public boolean storeForFriend(String KeyData,String ValueData) {
		int capictyOFThisServer=this.PDB.getMaxStorageCapacity();
		int realSize=this.PDB.getMyPeersFriends().size();
		if(realSize<capictyOFThisServer) {
			this.PDB.StoreData(KeyData, ValueData);
			return true;
		}else {
			return false;
		}
	}









}
