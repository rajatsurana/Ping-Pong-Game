
public class main {

	public static void main(String[] args) {
//		network net=new network("kapoor","237.0.0.1",1237);
//		net.addclient(new client("kapoor",1237,"237.0.0.1",1234));
//		new chatinterface(net);
		
		network net=new network("wagmare","237.0.0.1",1236,1);
		net.addclient(new client("wagmare",1236,"237.0.0.1",1234));
		new chatinterface(net);
//		
//		network net=new network("arpit","237.0.0.1",1234,1);
//		new chatinterface(net);

	}

}
