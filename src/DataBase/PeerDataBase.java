package DataBase;


import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import App.Peer;

public class PeerDataBase {

	private  ArrayList<String> MyPeersFriends;
	private  Map<String,String> MyData;
	public static final int Max_storage_capacity = 1;
    private Registry registry;

	public PeerDataBase(Registry registry) {
		MyPeersFriends = new ArrayList<>();
		MyData = new HashMap<String,String>(); 
		this.registry=registry;
	}
   
	 



	public void setRegistry(Registry registry) {
		this.registry = registry;
	}



	public ArrayList<String> getMyPeersFriends() {
		return MyPeersFriends;
	}



	public void setMyPeersFriends(ArrayList<String> myPeersFriends) {
		MyPeersFriends = myPeersFriends;
	}



	public Map<String, String> getMyData() {
		return MyData;
	}



	public void setMyData(Map<String, String> myData) {
		MyData = myData;
	}



	public static int getMaxStorageCapacity() {
		return Max_storage_capacity;
	}

	public void AddFriend(String Friend) {
		this.MyPeersFriends.add(Friend);
	}

	public void RemoveFriend(String Friend) {
		this.MyPeersFriends.remove(Friend);
	}

	public void StoreData(String KeyData , String ValueData) {
			this.MyData.put(KeyData,ValueData);  
		
	}

	public void RemoveData(String KeyData) {
		this.MyData.remove(KeyData);
	}

	
   public boolean IsiTMyData(String DataName) {
		for (Map.Entry<String,String> Data : MyData.entrySet())  {
			if(DataName.equals(Data.getValue())) {
				return true;
			}
		}
		return false;

	}
	
   
	public Peer GetPeerFriend(String Name) {
		return null;
	}



	public Registry getRegistry() {
		// TODO Auto-generated method stub
	return this.registry;
	}

}
