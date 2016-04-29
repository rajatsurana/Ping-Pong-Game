package multiplayer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class pendingpeer extends Thread implements active.newpeer{
	public interface activepeersupport
    {
        boolean isalreadyconnected(info info);

        void addactivepeer(info peer, InputStream pInputStream , OutputStream pOutputStream );
    }
	public list peers=new list();
	private activepeersupport activepeer = null;
	public void start(activepeersupport activepeer)
    {
       this.activepeer=activepeer;
        start();
    }
	 public void addnewpeer(info info)
	    {
	        peers.add( null , info);
	    }
	public void add(Socket soc){
		System.out.println("socket added");
		InetAddress add = soc.getInetAddress();
	    String hostname = add.getHostName();
	    String ip = add.getHostAddress();
	    Integer port=soc.getPort();
	    peers.add(soc,new info(ip,hostname,port));
	    }

	public void run(){
	        while ( true )
	        {
	            handleNewPeerClient(peers.next());
	        }
	    }
	    private void handleNewPeerClient(obj node)
	    {
	        info peer2 = node.info;
	        System.out.println(peer2.name+" "+peer2.add+" "+peer2.port);
	        if (activepeer.isalreadyconnected(peer2)){
	        	System.out.println(peer2.name+" "+peer2.add+" "+peer2.port);
	        	System.out.println("is already connected");
	            return;}

	        Socket socket = node.soc;
	        if ( socket == null )
	        	try{
	            if ( null == (socket = new Socket(peer2.add,peer2.port))){
	                System.out.println("connection failed");
	                return;
	            }}catch(IOException ignore){
	            	 System.out.println("connection failed");
		                return;
	            }
	        InputStream inputStream = null;
	        OutputStream outputStream = null;
	        try
	        {
	            inputStream = socket.getInputStream();
	            outputStream = socket.getOutputStream();
	            System.out.println("connected");
	        }
	        catch ( IOException e )
	        {
	            System.out.println("coonection failed");
	            try
	            {
	                socket.close();
	            }
	            catch ( IOException ignore )
	            {
	            }
	            return;
	        }
	        activepeer.addactivepeer( peer2, inputStream , outputStream );
	        
	        //connection true;
	    }
}

