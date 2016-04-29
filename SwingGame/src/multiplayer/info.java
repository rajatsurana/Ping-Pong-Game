package multiplayer;

public class info {
	String add;
	public String name;
	Integer port=null;
	public info(String add,String name,Integer port){
		this.add=add;
		this.name=name;
		this.port=port;
		}
	public info(String add,String name){
		this.add=add;
		this.name=name;
		this.port=3128;
	}
	public static info information(String in){
		System.out.println(in);
		String[] s=in.split(" ");
		return new info(s[2],s[1],Integer.valueOf(s[3]));	}
}

