package game;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import udp.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.TimerTask;



//import multiplayer.info;
import udp.network;

public class Optionpane extends JPanel {
	// Connect status constants
	public final static int NULL = 0;
	public final static int DISCONNECTED = 1;
	public final static int DISCONNECTING = 2;
	public final static int BEGIN_CONNECT = 3;
	public final static int CONNECTED = 4;
	public network net;
	// Other constants
	public final static String statusMessages[] = {
			" Error! Could not connect!", " Disconnected", " Disconnecting...",
			" Connecting...", " Connected" };

	public final static String END_CHAT_SESSION = new Character((char) 0)
			.toString(); // Indicates the end of a session

	// Connection atate info
	public static String hostIP = "localhost";
	public static int port = 1234;
	public static int connectionStatus = DISCONNECTED;
	public static boolean isHost = true;
	public static String statusString = statusMessages[connectionStatus];
	public static StringBuffer toAppend = new StringBuffer("");
	public static StringBuffer toSend = new StringBuffer("");

	// Various GUI components and info
	public static JFrame mainFrame = null;
	public static JTextArea chatText = null;
	public static JTextField chatLine = null;
	public static JPanel statusBar = null;
	public static JLabel statusField = null;
	public static JTextField statusColor = null;
	public static JTextField ipField = null;
	public static JTextField nameField = null;
	public static JTextField portField = null;
	public static JTextField hnameField = null;
	public static JTextField hportField = null;
	public static JTextField maxPlayerField = null;
	public static JTextField playerJoinedField = null;
	public static JRadioButton hostOption = null;
	public static JRadioButton guestOption = null;
	public static JButton connectButton = null;
	public static JButton disconnectButton = null;

	JPanel pane = null;
	ActionAdapter buttonListener = null;

	public Optionpane(GridLayout gridLayout) {
		initpane();
	}

	private void initpane() {

		// IP address input
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane.add(new JLabel("Host IP:"));
		ipField = new JTextField(10);
		ipField.setText(hostIP);
		ipField.setEnabled(false);
		ipField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

				// ipField.selectAll();
				// Should be editable only when disconnected
				/*
				 * if (connectionStatus != DISCONNECTED) { changeStatusNTS(NULL,
				 * true); } else { hostIP = ipField.getText(); }
				 */
			}
		});
		pane.add(ipField);
		add(pane);

		// Name input
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane.add(new JLabel("Name:"));
		nameField = new JTextField(10);
		nameField.setText("");
		nameField.setEnabled(true);
		nameField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

			}
		});
		pane.add(nameField);
		add(pane);
		
		// host name
		// Name input
				pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				pane.add(new JLabel("Host name:"));
				hnameField = new JTextField(10);
				hnameField.setText("");
				hnameField.setEnabled(true);
				hnameField.addFocusListener(new FocusAdapter() {
					public void focusLost(FocusEvent e) {

					}
				});
				//pane.add(hnameField);
				//add(pane);
				
		// Port input
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane.add(new JLabel("Port:"));
		portField = new JTextField(10);
		portField.setEnabled(true);
		portField.setText("");
		portField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				
			}
		});
		pane.add(portField);
		add(pane);
		
		// host Port input
				pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				pane.add(new JLabel("Host port:"));
				hportField = new JTextField(10);
				hportField.setEnabled(true);
				hportField.setText("");
				hportField.addFocusListener(new FocusAdapter() {
					public void focusLost(FocusEvent e) {
						
					}
				});
				//pane.add(hportField);
				//add(pane);

		// Host/guest option
		buttonListener = new ActionAdapter() {
			public void actionPerformed(ActionEvent e) {
				
				isHost = e.getActionCommand().equals("host");
				if (isHost) {
					ipField.setEnabled(false);
					maxPlayerField.setEnabled(true);
					maxPlayerField.setText("");
					playerJoinedField.setEnabled(false);
					nameField.setEnabled(true);
					portField.setEnabled(true);
					ipField.setText("localhost");
					hostIP = "localhost";
				} else {
					ipField.setText("");
					ipField.setEnabled(true);
					//hnameField.setEditable(true);
					nameField.setEnabled(true);
					nameField.setEnabled(true);
					portField.setEnabled(true);
					hportField.setEnabled(true);
					maxPlayerField.setText("10");
					maxPlayerField.setEnabled(false);
					
					playerJoinedField.setEnabled(false);
				}
			}
		};
		ButtonGroup bg = new ButtonGroup();
		hostOption = new JRadioButton("Host", true);
		hostOption.setMnemonic(KeyEvent.VK_H);
		hostOption.setActionCommand("host");
		hostOption.addActionListener(buttonListener);
		guestOption = new JRadioButton("Guest", false);
		guestOption.setMnemonic(KeyEvent.VK_G);
		guestOption.setActionCommand("guest");
		guestOption.addActionListener(buttonListener);
		bg.add(hostOption);
		bg.add(guestOption);
		pane = new JPanel(new GridLayout(1, 2));
		pane.add(hostOption);
		pane.add(guestOption);
		add(pane);
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane.add(new JLabel("MaxPlayers:"));
		maxPlayerField = new JTextField(10);
		maxPlayerField.setText("");
		maxPlayerField.setEnabled(true);
		maxPlayerField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

			}
		});
		pane.add(maxPlayerField);
		add(pane);
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane.add(new JLabel("Joined:"));
		playerJoinedField = new JTextField(10);
		playerJoinedField.setText("0");
		playerJoinedField.setEnabled(false);
		playerJoinedField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

			}
		});
		pane.add(playerJoinedField);
		add(pane);
		// Connect/disconnect buttons
		JPanel buttonPane = new JPanel(new GridLayout(1, 2));
		buttonListener = new ActionAdapter() {
			public void actionPerformed(ActionEvent e) {
			
				String name, ip, port,hostport,hostname,hostip,max;
				
				if (isHost) {
					try {
						System.out.println(Inet4Address.getLocalHost().getHostAddress());
						ip=Inet4Address.getLocalHost().getHostAddress();
						port=hportField.getText();
						name=hnameField.getText();
						///
//						port="1234";
//						name="rajat";
//						ip="192.168.1.17";
						maxPlayerField.setText("4");
						port = portField.getText();
						name = nameField.getText();
						max = maxPlayerField.getText();
						///
						//net=new network(name,"237.0.0.1",1237,Integer.valueOf(max));
						net=new network("kapoor-UP","237.0.0.1",1237,4);
						
//						info my =new info(ip, name,Integer.valueOf(port));
//						network =new network();
//						network.start(my,null);			 
						
						Thread td = new Thread(new Runnable() {
							@Override
							public void run() {
								int maxPlayers= Integer.valueOf(maxPlayerField.getText());
								boolean running =true;
								while(running){
									System.out.println(network.listofpeers.size());
									playerJoinedField.setText(""+network.listofpeers.size());
									if(net.listofpeers.size()<maxPlayers){
										net.broadcast("joined "+net.listofpeers.size()+" "+maxPlayers);
										//network.peermanage.sendtoall("max "+network.peermanage.no_of_peers());
									}
									if(net.listofpeers.size()==maxPlayers-1){
										running =false;
										//Thread.sleep(1000);
										net.broadcast("start "+ maxPlayers + " " + network.listofpeers.size());
										try {
											Thread.sleep(5000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										//int max=Integer.valueOf(maxPlayers);
										//int peers=Integer.valueOf(lines[2]);
										
										PlayGame ex = new PlayGame(maxPlayers,network.listofpeers.size(),net,"UP");
										ex.setVisible(true);
									}
									
									
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
						td.start();
						
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else{
					hostip = ipField.getText();
					hostname="";
					hostport="";
					try {
						System.out.println(Inet4Address.getLocalHost().getHostAddress());
						ip=Inet4Address.getLocalHost().getHostAddress();
						hostport=hportField.getText();
						hostname=hnameField.getText();
						hostip=ipField.getText();
						port=portField.getText();
						name=nameField.getText();
						///
//						port="1235";
//						name="raja";
//						hostport="1234";
//						hostname="rajat";
//						hostip="127.0.0.1";
						
//						port = portField.getText();
//						name = nameField.getText();
//						max = maxPlayerField.getText();
//						///
//						net=new network(name,"237.0.0.1",Integer.valueOf(port),Integer.valueOf(max));
//						net.addclient(new client(name,Integer.valueOf(port),"237.0.0.1",1237));
//						net=new network("jay-LEFT","237.0.0.1",1235,4);
//						net.addclient(new client("jay-LEFT",1235,"237.0.0.1",1237));
//						net=new network("jaya-DOWN","237.0.0.1",1236,4);
//						net.addclient(new client("jaya-DOWN",1236,"237.0.0.1",1237));
						net=new network("ja-RIGHT","237.0.0.1",1234,4);
						net.addclient(new client("ja-RIGHT",1234,"237.0.0.1",1237));

						Thread td = new Thread(new Runnable() {
							@Override
							public void run() {
								boolean running =true;
								while(running){
									System.out.println(network.listofpeers.size());
									
									int maxPlayers=Integer.valueOf(maxPlayerField.getText());
									if(network.listofpeers.size()==maxPlayers-1){
										running =false;
										try {
											Thread.sleep(5000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										PlayGame ex = new PlayGame(maxPlayers,network.listofpeers.size(),net,"DOWN");
										   ex.setVisible(true);
//										PlayGame ex = new PlayGame(2,0);
//										ex.setVisible(true);
									}
									
									
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
						td.start();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}

		};
		connectButton = new JButton("Connect");
		connectButton.setMnemonic(KeyEvent.VK_C);
		connectButton.setActionCommand("connect");
		connectButton.addActionListener(buttonListener);
		connectButton.setEnabled(true);
		disconnectButton = new JButton("Disconnect");
		disconnectButton.setMnemonic(KeyEvent.VK_D);
		disconnectButton.setActionCommand("disconnect");
		disconnectButton.addActionListener(buttonListener);
		disconnectButton.setEnabled(false);
		buttonPane.add(connectButton);
		// buttonPane.add(disconnectButton);
		add(buttonPane);
	}
//	int counter = 0;
//    Timer timer;
}

class ActionAdapter implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	}

}