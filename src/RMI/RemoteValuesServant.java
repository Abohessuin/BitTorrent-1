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
			System.out.println("here");
			int capictyOFThisServer=this.PDB.getMaxStorageCapacity();
			Registry registry=this.PDB.getRegistry();
			int realSize=this.PDB.getMyData().size();
			System.out.println(realSize+" "+capictyOFThisServer);
			if(realSize<capictyOFThisServer) {
				System.out.println("here again");
				this.PDB.StoreData(KeyData, ValueData);
				return true;
			}else {
				ArrayList<String>myFriendsPeer=this.PDB.getMyPeersFriends();
				System.out.println("here againnn");
				for(int i=0;i<myFriendsPeer.size();i++) {
					System.out.println(myFriendsPeer.get(i));
					System.out.println(registry);
					IRemoteValues remoteServerValues = (IRemoteValues) registry.lookup(myFriendsPeer.get(i));
					System.out.println(remoteServerValues);
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
		int realSize=this.PDB.getMyData().size();
		if(realSize<capictyOFThisServer) {
			this.PDB.StoreData(KeyData, ValueData);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public void AddFriend(String FriendID) {
		this.PDB.AddFriend(FriendID);
	}
	@Override
	public void RemoveFriend(String FriendID) {
		this.PDB.RemoveFriend(FriendID);
	}






}
