package udp;

import java.util.LinkedList;

import javax.naming.Context;

public class network implements listerner.func{
	multicastserver multi;
	public static LinkedList<client> listofpeers;
	Integer port;
	String name;
	listerner listen;
	int max;
	public static network context;
    public network(String name,String ip,Integer port,int max){
    	this.name=name;
    	this.port=port;
    	this.max=max;
        multi=new multicastserver(ip,port);
        listofpeers=new LinkedList<client>();
        multi.start();
        listen=new listerner(name,ip,port,this);
        listen.start();
        
        }
    public void broadcast(String s){
    	context= this;
    	multi.data.add(s);
    }
    public synchronized String getName(){
    	return name;
    }
    public int no_of_players(){
    	return listofpeers.size();
    }
    public synchronized boolean isalreadyconnected(Integer p){
    	for(int i=0;i<listofpeers.size();i++){
    		if(listofpeers.get(i).port.equals(p)) return true;
    	}
    	if(p.equals(port)) return true;
    	return false;
    }
    public synchronized void addclient(client client){
    	listofpeers.add(client);
    	if(no_of_players()+1==max){
    		
    		try {
				Thread.sleep(1080);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		String te="helo "+name+" "+port.toString();
    		broadcast(te);
    		System.out.println("broad "+te);
    		listen.stop();}
    	//notifyall();
    }
    public synchronized int[] dost(){
    	context= this;
    	int[] ports=new int[listofpeers.size()];
    	for(int i=0;i<listofpeers.size();i++){
    		ports[i]=listofpeers.get(i).port;
    	}
    	return ports;
    }
    
}