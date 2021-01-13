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

	private static PeerDataBase PDB;

	protected RemoteValuesServant(PeerDataBase PDB) throws RemoteException {
		super();
		this.PDB=PDB;
	}

	
	
	public String Find(String Key, ArrayList<String>vistedNodes) throws AccessException, RemoteException {
		if(!vistedNodes.contains(this.PDB.getPeerID())){
	//	System.out.println(this.PDB.getPeerID());
	}
		  if(!vistedNodes.contains(this.PDB.getPeerID())) {
        vistedNodes.add(this.PDB.getPeerID());
		  }
      Registry registry=this.PDB.getRegistry();
      System.out.println("1 :"+vistedNodes);
         if(searchKey(Key)!=null) {
              return this.PDB.getPeerID();
         }else {
             ArrayList<String>myFriends=this.PDB.getMyPeersFriends();
         	//System.out.println(myFriends);
             for(int i=0;i<myFriends.size();++i) {
            	 
                if(!vistedNodes.contains(myFriends.get(i))) {
                	vistedNodes.add(myFriends.get(i));
                	 System.out.println("2 :"+vistedNodes);
                    IRemoteValues remoteServerValues;
					try {
						remoteServerValues = (IRemoteValues) registry.lookup(myFriends.get(i));
						System.out.println("-> "+myFriends.get(i));
						  if(!remoteServerValues.Find(Key, vistedNodes).equals("fail")) {
							 
							 
							  
		                        return remoteServerValues.Find(Key, vistedNodes);
		                    }
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                  
                 }
             }
            System.out.println("3 :"+vistedNodes);
              return "fail";
         }



 }




	    

	

	@Override
	public boolean Store(String KeyData,String ValueData,int flag) throws AccessException, RemoteException, NotBoundException {
		
		if(flag==0) {
			
			int capictyOFThisServer=this.PDB.getMaxStorageCapacity();
			Registry registry=this.PDB.getRegistry();
			int realSize=this.PDB.getMyData().size();
		
			if(realSize<capictyOFThisServer) {
			
				this.PDB.StoreData(KeyData, ValueData);
				//DataReplication(KeyData,ValueData);
				return true;
			}else {
				ArrayList<String>myFriendsPeer=this.PDB.getMyPeersFriends();
			
				for(int i=0;i<myFriendsPeer.size();i++) {
			
					IRemoteValues remoteServerValues = (IRemoteValues) registry.lookup(myFriendsPeer.get(i));
			
					if(remoteServerValues.Store(KeyData, ValueData,1)) {
						return true;
					}

				}
				return false;
			}
		}
			
	else {

			return storeForFriend(KeyData, ValueData);
		}


	}

public void DataReplication(String KeyData,String ValueData) throws AccessException, RemoteException, NotBoundException{
	ArrayList<String>myFriendsPeer=this.PDB.getMyPeersFriends();
	int capictyOFThisServer=this.PDB.getMaxStorageCapacity();
	Registry registry=this.PDB.getRegistry();
	int realSize=this.PDB.getMyData().size();
	for(int i=0;i<myFriendsPeer.size();i++) {
		IRemoteValues remoteServerValues = (IRemoteValues) registry.lookup(myFriendsPeer.get(i));
		remoteServerValues.StoreDataReplication(KeyData, ValueData);
			
			
	}
}

public void StoreDataReplication(String KeyData, String ValueData) throws AccessException, RemoteException {
	int capictyOFThisServer=this.PDB.getMaxStorageCapacity();
	Registry registry=this.PDB.getRegistry();
	int realSize=this.PDB.getMyData().size();
	if(realSize<capictyOFThisServer) {
		System.out.println("here again");
		this.PDB.StoreData(KeyData, ValueData);
		System.out.println("data replication done");
	}
	else {
		try {
			DataReplication(KeyData,ValueData);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void AddFriend(String FriendID) throws AccessException, RemoteException {
		this.PDB.AddFriend(FriendID);

	}
	@Override
	public void RemoveFriend(String FriendID) throws AccessException, RemoteException {
		this.PDB.RemoveFriend(FriendID);
	
	}

	public String searchKey(String key) {
        return this.PDB.getKeyValue(key);

    }





}
