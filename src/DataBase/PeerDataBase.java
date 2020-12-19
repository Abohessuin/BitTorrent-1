package DataBase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import App.Peer;

public class PeerDataBase {

	private static ArrayList<Peer> MyPeersFriends;
	private static Map<String,String> MyData;
	public static final int Max_storage_capacity = 3;


	public PeerDataBase() {
		MyPeersFriends = new ArrayList<>();
		MyData = new HashMap<String,String>(); 
	}

	public static ArrayList<Peer> getMyPeersFriends() {
		return MyPeersFriends;
	}

	public static void setMyPeersFriends(ArrayList<Peer> myNodesFriends) {
		MyPeersFriends = myNodesFriends;
	}

	public static Map<String, String> getMyData() {
		return MyData;
	}

	public static void setMyData(Map<String, String> myData) {
		MyData = myData;
	}

	public static int getMaxStorageCapacity() {
		return Max_storage_capacity;
	}

	public void AddFriend(Peer Friend) {
		this.MyPeersFriends.add(Friend);
	}

	public void RemoveFriend(Peer Friend) {
		this.MyPeersFriends.remove(Friend);
	}

	public void StoreData(String KeyData , String ValueData) {
		if(MyData.size()<this.Max_storage_capacity) {
			this.MyData.put(KeyData,ValueData);  
		}
		else {
			//Peer.getPDB().StoreData(KeyData, ValueData);
		}
	}

	public void RemoveData(String KeyData) {
		this.MyData.remove(KeyData);
	}

	public boolean IsHeMyFriend(Peer Someone) {
		for (Peer Friend : MyPeersFriends) { 		      
			if(Friend.getIDconnection().equals(Someone.getIDconnection()))		{
				return true;
			}
		}
		return false;
	}

	public boolean IsiTMyData(String DataName) {
		for (Map.Entry<String,String> Data : MyData.entrySet())  {
			if(DataName.equals(Data.getValue())) {
				return true;
			}
		}
		return false;

	}
	public Peer GetRandomFriend(){
		Random rand = new Random();
		int FriendIndex = rand.nextInt(this.getMyPeersFriends().size());
		return this.getMyPeersFriends().get(FriendIndex);
	}
	public Peer GetPeerFriend(String Name) {
		for(Peer Friend : MyPeersFriends) {
			if(Friend.getIDconnection().equals(Name)) {
				return Friend;
			}
		}
		return null;
	}

}
