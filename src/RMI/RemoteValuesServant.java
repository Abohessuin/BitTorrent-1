package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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



	public void Store(String KeyData,String ValueData) {
	
     this.PDB.StoreData(KeyData, ValueData);
	}

}
