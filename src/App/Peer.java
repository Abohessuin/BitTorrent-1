package App;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import DataBase.PeerDataBase;
import RMI.IRemoteValues;
import RMI.ValuesClient;
import RMI.ValuesServer;



public class Peer {
	private static PeerDataBase PDB;
	private String IDconnection;
	private ValuesClient VC;
	private ValuesServer VS;
	private Registry registry;
	static private ArrayList<String>vistedNodes;
	private int FailureCounter;
	private int SearchCounter;


	public int getSearchCounter() {
		return SearchCounter;
	}



	public void setSearchCounter(int searchCounter) {
		SearchCounter = searchCounter;
	}



	public int getFailureCounter() {
		return FailureCounter;
	}



	public void setFailureCounter(int failureCounter) {
		FailureCounter = failureCounter;
	}



	public Registry getRegistry() {
		return registry;
	}



	public void setRegistry(Registry registry) {
		this.registry = registry;
	}



	public static PeerDataBase getPDB() {
		return PDB;
	}



	public static void setPDB(PeerDataBase pDB) {
		PDB = pDB;
	}



	public String getIDconnection() {
		return IDconnection;
	}



	public void setIDconnection(String iDconnection) {
		IDconnection = iDconnection;
	}



	public ValuesClient getVC() {
		return VC;
	}



	public void setVC(ValuesClient vC) {
		VC = vC;
	}



	public ValuesServer getVS() {
		return VS;
	}



	public void setVS(ValuesServer vS) {
		VS = vS;
	}



	public Peer(String iDconnection) throws RemoteException, AlreadyBoundException {
		super();
		this.registry = LocateRegistry.getRegistry("localhost");
		IDconnection = iDconnection;
		VC=new ValuesClient(registry);
		VS=new ValuesServer();
		PDB=new PeerDataBase(this.registry,iDconnection);
		VS.startPeerLocalServer(iDconnection,this.PDB);
		this.FailureCounter=0;
		this.SearchCounter=0;
	}



	public IRemoteValues actLikeClient(String FriendID) throws RemoteException, NotBoundException {
		IRemoteValues IV=VC.getService(FriendID);
		return  IV;
	}

	public String actLikeClient(String FriendID,String Key) throws RemoteException, NotBoundException {
		IRemoteValues IV=VC.getService(FriendID);
		this.vistedNodes=new ArrayList<>();
		return IV.Find(Key,vistedNodes);
	}

	public String AddDataValues(String key,String value,String ID) throws RemoteException, NotBoundException {
		IRemoteValues IV=actLikeClient(ID);
		if( IV.Store(value, value,0)) {
			return "added to your server or your friends server";
		}else {
			return "no space in your server or your friends server";
		}

	}


	public void actLikeServerAdd(String FriendID,String MyID) throws RemoteException, NotBoundException {
		IRemoteValues IV=VC.getService(MyID);
		IRemoteValues IVV=VC.getService(FriendID);
		IV.AddFriend(FriendID);
		IVV.AddFriend(MyID);
	}
	public void actLikeServerRemove(String FriendID,String MyID) throws RemoteException, NotBoundException {
		IRemoteValues IV=VC.getService(MyID);
		IRemoteValues IVV=VC.getService(FriendID);
		IV.RemoveFriend(FriendID);
		IVV.RemoveFriend(MyID);
	}



}
