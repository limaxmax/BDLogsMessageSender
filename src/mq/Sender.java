package mq;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.swing.JOptionPane;

import org.apache.activemq.xbean.PooledBrokerFactoryBean;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import msgVerify.StringToJson;

public class Sender {

	protected Shell shlmqv;
	private Text name;

	/**
	 * Launch the application.
	 * @param args
	 */
	SenderFun senderFun = new SenderFun();
	private static boolean IsRun = true;
	ExecutorService pool = Executors. newSingleThreadExecutor();
	private Text mqip;
	
	
	public static void main(String[] args) {
//		ParseFolder pf1=new ParseFolder();
//		pf1.parseString("E:\\Thinkpad S2_bak\\Files\\报文模板\\天津6现场报文\\BDSLogs\\2017-03-11\\MSS_MMS_ALARM_INFO\\201703110257.txt","3.32.160.208");
//		
//		
	//	ParseFile pf=new ParseFile();
//		Scanner out = new Scanner(System.in);
//		String path=out.nextLine();
		//pf.readFile("D:\\MSS_VOBC_TRAINRUN_INFO\\201709042358.txt","3.32.160.208");
//		
		try {
			Sender window = new Sender();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlmqv.open();
		shlmqv.layout();
		while (!shlmqv.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlmqv = new Shell();
		shlmqv.setSize(396, 226);
		Thread thread = new Thread();
		
		name = new Text(shlmqv, SWT.BORDER);
		name.setBounds(140, 38, 160, 23);
		
		Label label = new Label(shlmqv, SWT.NONE);
		label.setBounds(98, 41, 36, 17);
		label.setText("路径");
		
		Button sendButton1and2 = new Button(shlmqv, SWT.NONE);
		sendButton1and2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e)  {
				try {
					IsRun = true;
					String nameText =  name.getText();
//					int countText = Integer.parseInt(count.getText());
//					int gapText = Integer.parseInt(gap.getText());
//					String contentText1 = content1.getText();
//					String contentText2 = content2.getText();
				
					//senderFun.sendInTurn(nameText,countText,gapText,contentText1,contentText2,mqip.getText());
						
					
					ParseFolder pf1=new ParseFolder();
					
					pf1.parseString(nameText,mqip.getText());
					JOptionPane.showMessageDialog(null, "发送完成","message", JOptionPane.INFORMATION_MESSAGE);
				}catch(InterfaceException e1){
//					String errorStr=e1.getMessage().split(",")[0];
//					System.out.println(errorStr.replaceAll("class ", ""));
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, e1.getMessage(),"message", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "","��ʾ", JOptionPane.INFORMATION_MESSAGE);
					e2.printStackTrace();
				} 
				
				}
		});
		sendButton1and2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		sendButton1and2.setText("发送");
		sendButton1and2.setBounds(167, 121, 80, 27);
		
		mqip = new Text(shlmqv, SWT.BORDER);
		mqip.setBounds(140, 80, 160, 23);
		
		Label lblMq = new Label(shlmqv, SWT.NONE);
		lblMq.setBounds(84, 83, 48, 17);
		lblMq.setText("mq\u5730\u5740");
		
		
		
	}
}
