package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;



public class multicastserver extends Thread{
	list data=new list();
	MulticastSocket socket=null;
	String ip;
	Integer port;
	InetAddress group = null;
	public multicastserver(String ip, Integer port){
		this.ip=ip;
		this.port=port;
	}
	public void close(){
		socket.close();
	}
	public void run() {
		try{
		socket=new MulticastSocket(port);
		socket.setInterface(InetAddress.getLocalHost());
	//	System.out.println(InetAddress.getLocalHost().getHostAddress());
		group = InetAddress.getByName(ip);
		socket.joinGroup(group);
		System.out.println("started");}
		  catch (IOException e) {
	            e.printStackTrace();
	        }
	    while (true) {
	    	senddata(data.next());
	    }
	   // socket.close();
	}
	public void senddata(obj o){
		 try {
	            byte[] buf = new byte[256];
	            // don't wait for request...just send a quote
	            buf = o.data.getBytes();

	           // InetAddress group = InetAddress.getByName(ip);
	            DatagramPacket packet;
	            packet = new DatagramPacket(buf, buf.length, group, port);
	            socket.send(packet);
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
	}
}
