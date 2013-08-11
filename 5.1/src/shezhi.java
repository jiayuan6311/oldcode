import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
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
public class shezhi extends javax.swing.JFrame {
	private static JRadioButton jRadioButton1;
	

	private  JRadioButton jRadioButton2;
	private  JRadioButton jRadioButton3;
	private  JRadioButton jRadioButton4;
	private JButton jButton1;
	private JLabel jLabel2;
	private JLabel jLabel8;
	private  JTextField jTextField4;
	private JLabel jLabel7;
	private JLabel jLabel1;
	private ButtonGroup buttonGroup1;
	

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				shezhi inst = new shezhi();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public shezhi() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("\u9ad8\u7ea7\u8bbe\u7f6e");
			{
				jRadioButton1 = new JRadioButton();
				getContentPane().add(jRadioButton1);
				jRadioButton1.setText("每天");
				jRadioButton1.setBounds(28, 54, 100, 21);
			}
			{
				jRadioButton2 = new JRadioButton();
				getContentPane().add(jRadioButton2);
				jRadioButton2.setText("每周");
				jRadioButton2.setBounds(158, 54, 100, 21);
			}
			{
				jRadioButton3 = new JRadioButton();
				getContentPane().add(jRadioButton3);
				jRadioButton3.setText("每月");
				jRadioButton3.setBounds(28, 94, 100, 21);
			}
			{
				jRadioButton4 = new JRadioButton();
				getContentPane().add(jRadioButton4);
				getContentPane().add(getJLabel1());
				getContentPane().add(getJLabel7());
				getContentPane().add(getJTextField4());
				getContentPane().add(getJLabel8());
				getContentPane().add(getJButton1());
				getContentPane().add(getJLabel2());
				jRadioButton4.setText("每年");
				jRadioButton4.setBounds(158, 94, 100, 21);
			}
			pack();
			this.setSize(281, 327);
			getButtonGroup1().add(jRadioButton1);
			getButtonGroup1().add(jRadioButton2);
			getButtonGroup1().add(jRadioButton3);
			getButtonGroup1().add(jRadioButton4);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}

	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
		}
		return buttonGroup1;
	}
	
	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("\u91cd \u590d \u65b9 \u5f0f");
			jLabel1.setBounds(22, 19, 81, 17);
		}
		return jLabel1;
	}

	private JLabel getJLabel7() {
		if(jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("\u91cd\u590d\u6b21\u6570");
			jLabel7.setBounds(22, 154, 69, 17);
		}
		return jLabel7;
	}
	
	private JTextField getJTextField4() {
		if(jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setText("3");
			jTextField4.setBounds(103, 151, 45, 24);
		}
		return jTextField4;
	}
	
	private JLabel getJLabel8() {
		if(jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("\u6b21");
			jLabel8.setBounds(187, 154, 26, 17);
		}
		return jLabel8;
	}
	
	private JButton getJButton1() {
		if(jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("\u786e  \u5b9a");
			jButton1.setBounds(91, 240, 73, 24);
			jButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButton1ActionPerformed(evt);
				}
			});
		}
		return jButton1;
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {
	try{	
		if(jRadioButton1.isSelected())   {                    //确定按钮按下时的动作
		   NewJFrame.m.get(NewJFrame.getjComboBox1().getSelectedItem().toString()).setCff(1);
		  //System.out.println(NewJFrame.m.get(NewJFrame.getjComboBox1().getSelectedItem().toString()).getCff());
		}else if(jRadioButton2.isSelected()){
			NewJFrame.m.get(NewJFrame.getjComboBox1().getSelectedItem().toString()).setCff(2);
		}else if(jRadioButton3.isSelected()){
			NewJFrame.m.get(NewJFrame.getjComboBox1().getSelectedItem().toString()).setCff(3);
		}else if(jRadioButton4.isSelected()){
			NewJFrame.m.get(NewJFrame.getjComboBox1().getSelectedItem().toString()).setCff(4);

		}                                                                                 //设置重复频率
		NewJFrame.m.get(NewJFrame.getjComboBox1().getSelectedItem().toString()).setCfc(Integer.parseInt(jTextField4.getText()));                                               //设置重复次数
	}catch(NullPointerException e){jLabel2.setText("请先在主窗口创建一个日程提醒。");}
	
	}
	
	private JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(34, 202, 228, 26);
		}
		return jLabel2;
	}

}
