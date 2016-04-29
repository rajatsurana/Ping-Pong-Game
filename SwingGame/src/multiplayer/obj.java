package multiplayer;
import java.net.Socket;

public class obj{
	info info;
	Socket soc;
	obj node;
	public obj(Socket soc, info info){
		this.info=info;
		this.soc=soc;
		this.node=null;
	}
}