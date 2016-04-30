package udp;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class listerner extends Thread{
	public interface func{
		boolean isalreadyconnected(Integer port);
		void addclient(client client);
		void broadcast(String s);
		int[] dost();
	}
		String ip;
		func func;
		Integer port;
		InetAddress group;
		MulticastSocket socket;
		String servername;
		public listerner(String servername,String ip,Integer port,func func){
			this.servername=servername;
			this.func=func;
			this.ip=ip;
			this.port=port;
			connect();
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
			    System.out.println("Quote of the Moment: " + received);
			
				
			}
		}
		public void process(String line){
			if(line.startsWith("helo")){
					System.out.println(line);
					String[] s=line.split(" ");
					System.out.println(s[2]+"arpit");
					s[2]=s[2].substring(0, 4);
					System.out.println(s[2]+"arpit");
			//		s[2]=s[2].getInteger(s[2]))
					if(!func.isalreadyconnected(Integer.valueOf(s[2]))) {
						System.out.println("new connection");
						func.addclient(new client(servername,port,ip,Integer.valueOf(s[2])));
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						}
			}
		}
	}

