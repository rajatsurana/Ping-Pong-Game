package udp;

import game.CustomBoard;
import game.CustomBoard2;
import game.Optionpane;
import game.PlayGame;

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
	            	if(!network.listofpeers.get(i).port.equals(port)){
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
		    System.out.println("received in client" + received);
		
			
		}
	}
	public void process(String line){
		if(CustomBoard.paddle!=null){
			processCust(line);
		}else{
			processCust2(line);
		}
		
		if(line.startsWith("joined")){
			//System.out.println(line);
			   String[] lines =line.split(" ");
			   Optionpane.playerJoinedField.setText(lines[1]);
			   //String[] s=lines[2].split(" ");
			   Optionpane.maxPlayerField.setText(lines[2].trim());
		   }else if(line.startsWith("start")){
			   System.out.println(line);
			   String[] lines =line.split(" ");
			   int max=Integer.valueOf(lines[1]);
//			   String[] s=lines[2].split(" ");
//			   System.out.println(s[0].charAt(0));
			   int peers=Integer.valueOf(lines[2].trim());//(String.valueOf(s[0].charAt(0)));
			   
			   
		   } 
		if(line.startsWith("helo")){
				System.out.println(line);
				String[] s=line.split(" ");
				name=s[1];
				System.out.println("name set as" +s[1]);}
//				s[2]=s[2].substring(0, 4);
//				if(Integer.valueOf(s[2])==port){name=s[1];System.out.println("name set as " +s[1]+" "+port+" "+s[2]);}
		}
		
	
	private void processCust(String line) {
		// TODO Auto-generated method stub
		
		if(line.startsWith("Left")||line.startsWith("Right")){
			System.out.println(line.trim().length() +" :len");
			   String[] lines =line.trim().split(" ");
			   if(!lines[1].equals("") && !lines[2].equals("")){
				   int x=Integer.valueOf(lines[1]);
				   int dx =Integer.valueOf(lines[2]);
				   String name =lines[3];
				   System.out.println(name+" "+CustomBoard.paddle.getName()+" "+CustomBoard.paddle2.getName());
				   if(CustomBoard.paddle.getName().equals(name)){
					   CustomBoard.paddle.setX(x);
					   CustomBoard.paddle.setDx(dx);
					   
				   }
				   else if(CustomBoard.paddle2.getName().equals(name)){
					   
					   CustomBoard.paddle2.setX(x);//(400-x-150);
					   CustomBoard.paddle2.setDx(dx);//(-dx);
				   }
//				   else{
//					   CustomBoard.paddle2.setX(x);//(400-x-150);
//					   CustomBoard.paddle2.setDx(dx);//(-dx);
//				   }
				   
			   }
			   
		   }
		//else
//		   if(line.startsWith("L2")||line.startsWith("R2")){
//			   String[] lines =line.split(" ");
//			   if(!lines[1].equals("") && !lines[2].equals("")){
//				   int x=Integer.valueOf(lines[1]);
//				   int dx =Integer.valueOf(lines[2]);
//				   CustomBoard.paddle.setX(400-x-150);
//				   CustomBoard.paddle.setDx(-dx);
//				   
//			   }
//			   
//		   }
//		   if(line.startsWith("BVel")){
//			   String[] lines =line.split(" ");
//			   if(!lines[1].equals("") && !lines[2].equals("")){
//				   int vx=Integer.valueOf(lines[1]);
//				   int vy=Integer.valueOf(lines[2]);
//				   //CustomBoard.ball.setVel(-vx, -vy);
//			   }
//			  		   
//		   }
//		   if(line.startsWith("BXY")){
//			   String[] lines =line.split(" ");
//			 if(!lines[1].equals("") && !lines[2].equals("")){
//			   int x=Integer.valueOf(lines[1]);
//			   int y=Integer.valueOf(lines[2]);
//			   //CustomBoard.ball.setXY(400-x-25,400-y-25);
//			 }
//		   }
		   if(line.startsWith("Ballx")){
			   String[] lines =line.trim().split(" ");
			   System.out.println(line.trim());
				 if(!lines[1].equals("") && !lines[2].equals("") && !lines[3].equals("") && !lines[4].equals("")){
				   int x=Integer.valueOf(lines[1]);
				   int y=Integer.valueOf(lines[2]);
				   int z = Integer.valueOf(lines[3]);
				   int w = Integer.valueOf(lines[4]);
				   System.out.println(x+":"+y+":"+z+":"+w);			   
					   CustomBoard.ball.setX(x);
					   CustomBoard.ball.setY(y);
					   CustomBoard.ball.setVel(z,w);
					  // CustomBoard.ball.setVY(w);
//			
				  
				   //CustomBoard.ball.setXY(400-x-25,400-y-25);
				 }  
		   }
		
	}
	
	private synchronized void processCust2(String line) {
		// TODO Auto-generated method stub
		
		if(line.startsWith("Left")||line.startsWith("Right")){
			System.out.println(line.trim().length() +" :len");
			   String[] lines =line.trim().split(" ");
			   if(!lines[1].equals("") && !lines[2].equals("") && !lines[3].equals("")){
				   int x=Integer.valueOf(lines[1]);
				   int dx =Integer.valueOf(lines[2]);
				   String name =lines[3];
				   System.out.println(name+" "+CustomBoard2.paddle4.getName()+" "+CustomBoard2.paddle5.getName());
				   if(CustomBoard2.paddle4.getName().equals(name)){
					   CustomBoard2.paddle4.setX(x);
					   CustomBoard2.paddle4.setDx(dx);
					   
				   }
				   else if(CustomBoard2.paddle5.getName().equals(name)){
					   
					   CustomBoard2.paddle5.setX(x);//(400-x-150);
					   CustomBoard2.paddle5.setDx(dx);//(-dx);
				   }
//				   else{
//					   CustomBoard.paddle2.setX(x);//(400-x-150);
//					   CustomBoard.paddle2.setDx(dx);//(-dx);
//				   }
				   
			   }
			   
		   }
		if(line.startsWith("UP")||line.startsWith("DOWN")){
			   String[] lines =line.trim().split(" ");
			   System.out.println(line.trim() +" :lenraj");
			   if(!lines[1].equals("") && !lines[2].equals("") && !lines[3].equals("")){
				   int y=Integer.valueOf(lines[1]);
				   int dy =Integer.valueOf(lines[2]);
				   String name =lines[3];
				   System.out.println(name+" "+CustomBoard2.paddle1.getName()+" "+CustomBoard2.paddle3.getName());
				   if(CustomBoard2.paddle1.getName().equals(name)){
					   CustomBoard2.paddle1.setY(y);
					   CustomBoard2.paddle1.setDy(dy);
					   
				   }else if(CustomBoard2.paddle3.getName().equals(name)){
					   
					   CustomBoard2.paddle3.setY(y);//(400-x-150);
					   CustomBoard2.paddle3.setDy(dy);//(-dx);
				   }else{
					   CustomBoard2.paddle3.setY(y);//(400-x-150);
					   CustomBoard2.paddle3.setDy(dy);//(-dx);
				   }
				   
			   }
			   
		   }
		   if(line.startsWith("Ballx")){
			   String[] lines =line.trim().split(" ");
			   System.out.println(line.trim());
				 if(!lines[1].equals("") && !lines[2].equals("") && !lines[3].equals("") && !lines[4].equals("")){
				   int x=Integer.valueOf(lines[1]);
				   int y=Integer.valueOf(lines[2]);
				   int z = Integer.valueOf(lines[3]);
				   int w = Integer.valueOf(lines[4]);
				   System.out.println(x+":"+y+":"+z+":"+w);			   
					   CustomBoard2.ball3.setX(x);
					   CustomBoard2.ball3.setY(y);
					   CustomBoard2.ball3.setVel(z,w);
				   
				   
					  // CustomBoard.ball.setVY(w);
//			
				  
				   //CustomBoard.ball.setXY(400-x-25,400-y-25);
				 }  
		   }
		
	}
	public String connectedto(){
		return name;
	}
}
	

