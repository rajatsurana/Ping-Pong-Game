
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class client extends Thread {
//	public interface funct{
//		boolean isalreadyconnected(Integer port);
//		void addclient(client client);
//		void broadcast(String s);
//		data[] dost();
//	}
	String ip;
	Integer port;
	String servername;
	Integer serverport;
	String name=null;
	MulticastSocket socket;
	InetAddress group;
	public client(String servername,Integer serverport,String ip,Integer port){
		this.servername=servername;
		this.serverport=serverport;
		this.ip=ip;
		this.port=port;
		connect();
		start();
	}
	public void connect(){
		try{
	socket = new MulticastSocket(port);
	group = InetAddress.getByName(ip);
	socket.setInterface(InetAddress.getLocalHost());
	socket.joinGroup(group);
	System.out.println("connnected");}
	catch(IOException e){
		System.out.println("exception");
	}}
	public void run(){
		 try {
	            byte[] buf = new byte[256];
	            String o="helo "+servername+" "+serverport.toString();
	            // don't wait for request...just send a quote
	            buf = o.getBytes();

	           // InetAddress group = InetAddress.getByName(ip);
	            DatagramPacket packet;
	            packet = new DatagramPacket(buf, buf.length, group, port);
	            socket.send(packet);
	            for(int i=0;i<network.listofpeers.size();i++){
	            	if(!(network.listofpeers.get(i).port==port)){
		            	o="helo "+network.listofpeers.get(i).name+" "+network.listofpeers.get(i).port.toString();
		            	buf=o.getBytes();
		            	packet = new DatagramPacket(buf, buf.length, group, port);
			            socket.send(packet);
	            	}
	            }
	            
	           // Thread.sleep(1000);
//	         try {
//	                Thread.sleep(1000);
//	            } 
//	            catch (InterruptedException e) { }
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            //moreQuotes = false;
	        }
		while(true){
			DatagramPacket packet;
		
		    byte[] buf = new byte[256];
		    packet = new DatagramPacket(buf, buf.length);
		    try {
				socket.receive(packet);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		    String received = new String(packet.getData());
		    process(received);
		    System.out.println("received in client " + received);
		
			
		}
	}
	public void process(String line){
		if(line.startsWith("helo")){
				System.out.println(line);
				String[] s=line.split(" ");
				name=s[1];
		}
	}
	public String connectedto(){
		return name;
	}
}
	

