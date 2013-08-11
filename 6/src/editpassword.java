import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class editpassword extends javax.swing.JFrame {
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JButton jButton1;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel1;
	private JTextField jTextField2;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editpassword inst = new editpassword();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public editpassword() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("\u4fee\u6539\u5bc6\u7801");
			getContentPane().setLayout(null);
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(157, 54, 144, 24);
			}
			{
				jTextField2 = new JTextField();
				getContentPane().add(jTextField2);
				jTextField2.setBounds(157, 103, 144, 24);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("\u8f93\u5165\u65b0\u5bc6\u7801");
				jLabel1.setBounds(45, 55, 83, 22);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("\u518d\u6b21\u8f93\u5165");
				jLabel2.setBounds(45, 106, 83, 17);
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("\u786e\u5b9a");
				jButton1.setBounds(169, 210, 73, 24);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton1ActionPerformed(evt);
					}
				});
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("\u4fee\u6539\u6210\u529f");
				jLabel3.setBounds(285, 213, 118, 17);
				jLabel3.setVisible(false);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("\u4e24\u6b21\u5bc6\u7801\u4e0d\u540c\uff0c\u8bf7\u518d\u6b21\u8f93\u5165\u3002");
				jLabel4.setBounds(261, 213, 156, 17);
				jLabel4.setVisible(false);
			}
			pack();
			this.setSize(453, 400);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {
		System.out.println("jButton1.actionPerformed, event="+evt);         //TODO add your code for jButton1.actionPerformed
		if(jTextField1.getText().equals(jTextField2.getText())){
			jLabel3.setVisible(true);
			jLabel4.setVisible(false);
			try {
				String[] temp;
				String newtemp;
				FileReader fr = new FileReader("file0.txt");
			    BufferedReader br = new BufferedReader(fr);
			    FileWriter fw= new FileWriter("file0.txt",true);
			    BufferedWriter bw=new BufferedWriter(fw);
			    for(int i=0;i<NewJFrame.usernum;i++){
			    	  temp=br.readLine().split(" ");
			    	  if(temp[0].equals(NewJFrame.getJTextField1().getText())){
			    		  temp[1]=jTextField1.getText();
			    		  newtemp=temp[0]+" "+temp[1];
			    		  bw.write(newtemp);
			    		  bw.flush();
			    	  }
			    	  
			    }
			    
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			jLabel4.setVisible(true);
			jLabel3.setVisible(false);
		}
	}

}
