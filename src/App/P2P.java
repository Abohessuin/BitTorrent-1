package App;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


import RMI.IRemoteValues;
import RMI.ValuesClient;
import RMI.ValuesServer;

public class P2P {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
		Scanner s = new Scanner(System.in);

		System.out.println("my ID");
		String ID=s.nextLine();
		
		
		
		

		
		
		Peer peer = new Peer(ID);
	
		
		while(true) {
			System.out.println("1-Add Data");
			System.out.println("2-Search");
			System.out.println("3-Add Friend ");
			System.out.println("4-Remove Friend ");
			System.out.println("enter a num");
			int h1=s.nextInt();
		switch(h1) {
		case 1:{
			System.out.println("enter data to store");
	 	 		Scanner s1 = new Scanner(System.in);
	 			String Data=s1.nextLine();
	 			String Message = peer.AddDataValues(Data, Data,ID);
	 			 System.out.println(Message);
			break;
		}
		case 2:{
			 System.out.println("to");
	 			Scanner s2 = new Scanner(System.in);
	 	 		 String h=s2.nextLine();
	 	 		System.out.println("enter data to search");
	 	 		Scanner s11 = new Scanner(System.in);
	 			String h11=s11.nextLine();
	 	 		// peer.actLikeClient(h,h11);
			System.out.println(peer.actLikeClient(h,h11));
			
			break;
		}
		case 3:{
			System.out.println("enter friend name to add");
			Scanner s2 = new Scanner(System.in);
	 		 String FriendID=s2.nextLine();
	 		 
	 		 peer.actLikeServerAdd(FriendID, ID);
	 		System.out.println("added a friend");
			break;
		}
		case 4:{
			System.out.println("enter friend name to remove");
			Scanner s2 = new Scanner(System.in);
	 		 String FriendID=s2.nextLine();
	 		 
	 		 peer.actLikeServerRemove(FriendID, ID);
	 		System.out.println("removed a friend");
			break;
		}
		}
	}
	}
	
}

