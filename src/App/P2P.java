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



		int AvResTime=0;



		Peer peer = new Peer(ID);


		while(true) {
			System.out.println("1-Add Data");
			System.out.println("2-Search");
			System.out.println("3-Add Friend ");
			System.out.println("4-Remove Friend ");
			System.out.println("5-check failure rate ");
			System.out.println("6- Average Responce Time ");
			System.out.println("enter a num");
			int num=s.nextInt();
			switch(num) {
			case 1:{
				System.out.println("enter data to store");
				Scanner s1 = new Scanner(System.in);
				String Data=s1.nextLine();
				String Message = peer.AddDataValues(Data, Data,ID);
				System.out.println(Message);
				break;
			}
			case 2:{

				System.out.println("enter data to find");
				Scanner s11 = new Scanner(System.in);
				String h11=s11.nextLine();

				long start = System.currentTimeMillis(); 
				peer.setSearchCounter(peer.getSearchCounter()+1);
				if(!peer.actLikeClient(ID,h11).equals("fail")) {
					System.out.println("found on "+peer.actLikeClient(ID,h11));

				}
				else {
					System.out.println("Searching Failed");
					peer.setFailureCounter(peer.getFailureCounter()+1);
				}
				long end = System.currentTimeMillis(); 
				System.out.println("Searching takes " + 
						(end - start) + "ms");
				AvResTime+=(end - start);
				if(peer.getFailureCounter()!=0) {
					System.out.println("Failure Rate is " + 
							peer.getFailureCounter()/peer.getSearchCounter() );
				}

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
			case 5:{
				System.out.println("Failure Rate is " + 
						peer.getFailureCounter()/peer.getSearchCounter() );

				break;
			}
			case 6:{
				System.out.println("Average Responce Time : " + 
						AvResTime/peer.getSearchCounter() );

				break;
			}
			}
		}
	}

}

