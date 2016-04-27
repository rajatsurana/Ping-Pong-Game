package game;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import multiplayer.info;
import multiplayer.network;

public class Optionpane extends JPanel {
	// Connect status constants
	public final static int NULL = 0;
	public final static int DISCONNECTED = 1;
	public final static int DISCONNECTING = 2;
	public final static int BEGIN_CONNECT = 3;
	public final static int CONNECTED = 4;

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
		nameField.setEnabled(false);
		nameField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

				// ipField.selectAll();
				// Should be editable only when disconnected
				/*
				 * if (connectionStatus != DISCONNECTED) { changeStatusNTS(NULL,
				 * true); } else { hostIP = ipField.getText(); }
				 */
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

						// ipField.selectAll();
						// Should be editable only when disconnected
						/*
						 * if (connectionStatus != DISCONNECTED) { changeStatusNTS(NULL,
						 * true); } else { hostIP = ipField.getText(); }
						 */
					}
				});
				pane.add(hnameField);
				add(pane);
				
		// Port input
		pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane.add(new JLabel("Port:"));
		portField = new JTextField(10);
		portField.setEnabled(false);
		portField.setText("");
		portField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				// should be editable only when disconnected
				/*
				 * if (connectionStatus != DISCONNECTED) { changeStatusNTS(NULL,
				 * true); } else { int temp; try { temp =
				 * Integer.parseInt(portField.getText()); port = temp; } catch
				 * (NumberFormatException nfe) { portField.setText((new
				 * Integer(port)).toString()); mainFrame.repaint(); } }
				 */
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
						// should be editable only when disconnected
						/*
						 * if (connectionStatus != DISCONNECTED) { changeStatusNTS(NULL,
						 * true); } else { int temp; try { temp =
						 * Integer.parseInt(portField.getText()); port = temp; } catch
						 * (NumberFormatException nfe) { portField.setText((new
						 * Integer(port)).toString()); mainFrame.repaint(); } }
						 */
					}
				});
				pane.add(hportField);
				add(pane);

		// Host/guest option
		buttonListener = new ActionAdapter() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * if (connectionStatus != DISCONNECTED) { changeStatusNTS(NULL,
				 * true); } else { isHost = e.getActionCommand().equals("host");
				 * 
				 * // Cannot supply host IP if host option is chosen if (isHost)
				 * { ipField.setEnabled(false); ipField.setText("localhost");
				 * hostIP = "localhost"; } else { ipField.setEnabled(true); } }
				 */
				isHost = e.getActionCommand().equals("host");
				if (isHost) {
					ipField.setEnabled(false);
					
					nameField.setEnabled(false);
					portField.setEnabled(false);
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

		// Connect/disconnect buttons
		JPanel buttonPane = new JPanel(new GridLayout(1, 2));
		buttonListener = new ActionAdapter() {
			public void actionPerformed(ActionEvent e) {
				// Request a connection initiation
				/*
				 * if (e.getActionCommand().equals("connect")) {
				 * changeStatusNTS(BEGIN_CONNECT, true); } // Disconnect else {
				 * changeStatusNTS(DISCONNECTING, true); }
				 */
				String name, ip, port,hostport,hostname,hostip;
				//name = nameField.getText();
				//ip="";
				//port = portField.getText();
				

				//System.out.println(name + " " + ip + " " + port);
				if (isHost) {
					try {
						System.out.println(Inet4Address.getLocalHost().getHostAddress());
						ip=Inet4Address.getLocalHost().getHostAddress();
						port=hportField.getText();
						name=hnameField.getText();
						info my =new info(ip, name,Integer.valueOf(port));
						//info peer =new info("192.168.1.17", "arpit", 1478);//"saurabh",4534);
						new network().start(my,null);
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
						info my =new info(hostip,hostname, Integer.valueOf(hostport));
						info peer =new info(ip, name,Integer.valueOf(port));
						new network().start(peer,my);
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

}

class ActionAdapter implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	}

}