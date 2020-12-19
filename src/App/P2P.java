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
		
		
		
		

		System.out.println("1-add");
		System.out.println("2-search");
		
		
		Peer peer = new Peer(ID);
	
		int h1=s.nextInt();
		switch(h1) {
		case 1:{
			IRemoteValues RemoteValues = (IRemoteValues) peer.getRegistry().lookup(ID);
	 	 		Scanner s1 = new Scanner(System.in);
	 			String Data=s1.nextLine();
	 			RemoteValues.Store(Data, Data);
	 			 System.out.println("added");
			break;
		}
		case 2:{
			 System.out.println("to");
	 			Scanner s2 = new Scanner(System.in);
	 	 		 String h=s2.nextLine();
	 	 		Scanner s11 = new Scanner(System.in);
	 			String h11=s11.nextLine();
	 	 		// peer.actLikeClient(h,h11);
			System.out.println(peer.actLikeClient(h,h11));
			
			break;
		}
		}
	}
	
}
