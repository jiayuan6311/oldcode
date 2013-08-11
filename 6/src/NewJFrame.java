import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import java.io.*;

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
public class NewJFrame extends javax.swing.JFrame {
	private static JTextField jTextField1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jButton1;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JLabel jLabel1;
	private JTextField jTextField2;
	public static int usernum=10;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewJFrame inst = new NewJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public NewJFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("\u767b\u9646\u754c\u9762");
			getContentPane().setLayout(null);
			{
				jTextField1 = new JTextField();
				getContentPane().add(getJTextField1());
				jTextField1.setBounds(186, 48, 99, 24);
			}
			{
				jTextField2 = new JTextField();
				getContentPane().add(getJTextField2());
				jTextField2.setBounds(186, 89, 99, 24);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("\u7528\u6237\u540d");
				jLabel1.setBounds(115, 51, 66, 17);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("\u5bc6  \u7801");
				jLabel2.setBounds(115, 92, 32, 17);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("\u9a8c\u8bc1\u7801");
				jLabel3.setBounds(115, 131, 50, 17);
			}
			{
				jTextField3 = new JTextField();
				getContentPane().add(jTextField3);
				jTextField3.setBounds(186, 128, 99, 24);
			}
			{
				jTextField4 = new JTextField();
				Random rd1=new Random();
				getContentPane().add(getJTextField4());
				jTextField4.setBounds(335, 128, 53, 24);
				jTextField4.setText(String.valueOf(rd1.nextInt(8999)+1000));
				jTextField4.setEditable(false);
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("\u767b\u9646");
				jButton1.setBounds(186, 201, 99, 24);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton1ActionPerformed(evt);
					}
				});
			}
			pack();
			setSize(500, 400);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public static JTextField getJTextField1() {
		return jTextField1;
	}
	
	public JTextField getJTextField2() {
		return jTextField2;
	}
	
	public JTextField getJTextField4() {
		return jTextField4;
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {
		String[] temp=new String[2];
		System.out.println("jButton1.actionPerformed, event="+evt);
		try{
		      int sig=0;			
		      FileReader fr = new FileReader("file0.txt");
		      BufferedReader br = new BufferedReader(fr);
		      for(int i=0;i<usernum;i++){
		    	  temp=br.readLine().split(" ");
		    	  if(temp[0].equals(jTextField1.getText())&&temp[1].equals(jTextField2.getText())&&jTextField3.getText().equals(jTextField4.getText())){
		    		  NewJFrame1 inst = new NewJFrame1();
						inst.setLocationRelativeTo(null);
						inst.setVisible(true);
						sig=1;
						break;
						}
		    	  }
		      if(sig==0){
	    		  wrong inst = new wrong();
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
	    	  }
		      fr.close(); 
			}catch(FileNotFoundException e) {
		    	System.out.println("FileStreamsTest: "+e);
			}catch(IOException e) {
				System.err.println("FileStreamsTest: "+e);
			}
	}

}
