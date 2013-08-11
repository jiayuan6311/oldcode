import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

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
public class personaltext extends javax.swing.JFrame {
	private JTextArea jTextArea1;
	private JButton jButton1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				personaltext inst = new personaltext();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public personaltext() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("\u4e2a\u4eba\u6587\u6863");
			getContentPane().setLayout(null);
			{
				jTextArea1 = new JTextArea();
				String s1="",s2;
			    FileReader fr=new FileReader("file"+NewJFrame.getJTextField1().getText()+".txt");
			    BufferedReader br = new BufferedReader(fr);
			    while((s2=br.readLine())!=null){	
			    	s1+=(s2+"\n");	
			    }
				getContentPane().add(jTextArea1);
				jTextArea1.setBounds(0, 0, 573, 412);
				jTextArea1.setText(s1);
				fr.close();
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("\u4fdd\u5b58\u4fee\u6539");
				jButton1.setBounds(439, 424, 96, 24);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton1ActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(589, 497);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {
		System.out.println("jButton1.actionPerformed, event="+evt);
		try {
			FileWriter fw=new FileWriter("file"+NewJFrame.getJTextField1().getText()+".txt");      //TODO add your code for jButton1.actionPerformed
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(jTextArea1.getText());
			bw.flush();
			fw.close();
		}catch (Exception e) {}
		
	}

}
