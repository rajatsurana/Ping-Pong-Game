package udp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class chatinterface extends Thread{
	network net =null;
	BufferedReader br = null;
	public chatinterface(network net){
		this.net=net;
		br = new BufferedReader(new InputStreamReader(System.in));
		//sendpeers();
		start();
	}
	public void run(){
		   try{
			   while(true){
				   String s = br.readLine();
				   net.broadcast(s);}}
				   catch(IOException e){}
	   }
}
