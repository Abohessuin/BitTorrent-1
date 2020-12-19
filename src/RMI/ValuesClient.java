package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ValuesClient {

	private Registry r;
	
	
     public Registry getR() {
		return r;
	}

    public ValuesClient (Registry r) {
    	this.r=r;
    }

	

	public void setR(Registry r) {
		this.r = r;
	}




     
     

	public IRemoteValues getService(String serverLookupID) throws RemoteException, NotBoundException {
       // Registry registry = LocateRegistry.getRegistry("localhost");
		IRemoteValues remoteServerValues = (IRemoteValues) this.r.lookup(serverLookupID);
		return remoteServerValues;


	}

}
