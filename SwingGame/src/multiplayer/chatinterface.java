package multiplayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class chatinterface extends Thread{
	peer peer =null;
	BufferedReader br = null;
	info[] friends;
	public chatinterface(peer peer){
		this.peer=peer;
		br = new BufferedReader(new InputStreamReader(System.in));
		//sendpeers();
		start();
	}
	public void run(){
		   try{
			   while(true){
				   String s = br.readLine();
				   peer.sendtoall(s);
			   }
		   }catch(IOException e){
			   
		   }
	   }
}
