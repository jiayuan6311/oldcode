import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DebugGraphics;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingUtilities;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;




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

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private static  JComboBox jComboBox1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton jButton4;
	private JTextField jTextField3;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel9;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JButton jButton3;
	private JButton jButton2;
	private JButton jButton1;
	private JCheckBox jCheckBox1;
	private JLabel jLabel12;
	private JSpinner jSpinner1;
	private JComboBox jComboBox6;
	private JComboBox jComboBox5;
	private JTextArea jTextArea1;
	public static HashMap<String,tixing> m=new HashMap<String,tixing>();           //用于存放tixing对象的图
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
			getContentPane().setLayout(null);
			this.setTitle("\u65e5\u7a0b\u7cbe\u7075");
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("\u65e5\u7a0b\u6807\u9898\uff1a");
				jLabel1.setBounds(27, 30, 79, 21);
			}
			{
				
				jComboBox1 = new JComboBox();
				jComboBox1.addItem("(空)");
				getContentPane().add(jComboBox1);
			//	jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setBounds(110, 28, 336, 23);
				jComboBox1.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
				jComboBox1.setEditable(true);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("\u5185      \u5bb9\uff1a");
				jLabel2.setBounds(27, 73, 71, 22);
			}
			{
				jTextArea1 = new JTextArea();
				getContentPane().add(jTextArea1);
				jTextArea1.setBounds(110, 73, 336, 123);
				jTextArea1.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("\u65e5       \u671f\uff1a");
				jLabel3.setBounds(31, 209, 85, 21);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("\u65f6       \u95f4\uff1a");
				jLabel4.setBounds(31, 250, 78, 17);
			}
			{
				ComboBoxModel jComboBox5Model = 
					new DefaultComboBoxModel(
							new String[] { "01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24" });
				jComboBox5 = new JComboBox();
				getContentPane().add(jComboBox5);
				jComboBox5.setModel(jComboBox5Model);
				jComboBox5.setBounds(112, 246, 54, 24);
			}
			{   
				ComboBoxModel jComboBox6Model = 
					new DefaultComboBoxModel(
							new String[] { "1", "2","3","4","5","6","7","8","9","10","11", "12","13","14","15","16","17","18","19","20","21", "22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40",
									"41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60"});
				jComboBox6 = new JComboBox();
				getContentPane().add(jComboBox6);
				jComboBox6.setModel(jComboBox6Model);
				jComboBox6.setBounds(196, 246, 44, 24);
			}
			{
				jCheckBox1 = new JCheckBox();
				getContentPane().add(jCheckBox1);
				jCheckBox1.setText("\u63d0       \u9192");
				jCheckBox1.setBounds(12, 293, 82, 21);
			}
			{
				SpinnerListModel jSpinner1Model = 
					new SpinnerListModel(
							new String[] { "1", "2","3","4","5","6","7","8","9","10","11", "12","13","14","15","16","17","18","19","20","21", "22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40",
									"41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60" });
				jSpinner1 = new JSpinner();
				getContentPane().add(jSpinner1);
				jSpinner1.setModel(jSpinner1Model);
				jSpinner1.setBounds(188, 292, 114, 24);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("\u63d0    \u524d");
				jLabel5.setBounds(111, 295, 47, 17);
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("\u9ad8\u7ea7\u8bbe\u7f6e");
				jButton1.setBounds(31, 351, 99, 24);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton1ActionPerformed(evt);

					}
				});
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("\u521b  \u5efa");
				jButton2.setBounds(277, 412, 75, 24);
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton2ActionPerformed(evt);
					}
				});
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3);
				jButton3.setText("\u5220  \u9664");
				jButton3.setBounds(387, 412, 76, 24);
				jButton3.setSize(75, 24);
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton3ActionPerformed(evt);
					}
				});
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("\u5e74");
				jLabel6.setBounds(176, 211, 28, 17);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("\u6708");
				jLabel7.setBounds(244, 211, 29, 17);
			}
			{
				jLabel8 = new JLabel();
				getContentPane().add(jLabel8);
				jLabel8.setText("\u65f6");
				jLabel8.setBounds(176, 250, 27, 17);
			}
			{
				jLabel9 = new JLabel();
				getContentPane().add(jLabel9);
				jLabel9.setText("\u65e5");
				jLabel9.setBounds(316, 211, 20, 17);
			}
			{
				jLabel10 = new JLabel();
				getContentPane().add(jLabel10);
				jLabel10.setText("\u5206");
				jLabel10.setBounds(244, 250, 29, 17);
			}
			{
				jLabel11 = new JLabel();
				getContentPane().add(jLabel11);
				jLabel11.setText("\u5206    \u949f");
				jLabel11.setBounds(320, 295, 53, 17);
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(113, 208, 55, 24);
			}
			{
				jTextField2 = new JTextField();
				getContentPane().add(jTextField2);
				jTextField2.setBounds(194, 208, 42, 24);
			}
			{
				jTextField3 = new JTextField();
				getContentPane().add(jTextField3);
				jTextField3.setBounds(263, 208, 44, 24);
			}
			{
				jButton4 = new JButton();
				getContentPane().add(jButton4);
				jButton4.setText("\u67e5  \u8be2");
				jButton4.setBounds(169, 412, 75, 24);
				jButton4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton4ActionPerformed(evt);
					}
				});
			}
			{
				jLabel12 = new JLabel();
				getContentPane().add(jLabel12);
				jLabel12.setBounds(203, 351, 214, 24);
			}
			pack();
			this.setSize(507, 490);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {                 //高级设置按钮按下时的动作
	
		shezhi inst = new shezhi();
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
		inst.setEnabled(true);   //TODO add your code for jButton1.actionPerformed
	}
	
	private void jButton3ActionPerformed(ActionEvent evt) {                    //删除按钮按下时的动作
	
		if(!jComboBox1.getSelectedItem().toString().equals("(空)")){
			jComboBox1.removeItem(jComboBox1.getSelectedItem());//TODO add your code for jButton3.actionPerformed
		    jTextArea1.setText("");
		    jTextField1.setText("");
		    jTextField2.setText("");
		    jTextField3.setText("");
		    jComboBox5.setSelectedItem("01");
		    jComboBox6.setSelectedItem("1");
		}else jLabel12.setText("列表已经为空。");
		
	}
	
	private void jButton2ActionPerformed(ActionEvent evt) {                      //添加按钮按下时的动作
		
		int j=0;
		if(!jComboBox1.getSelectedItem().toString().equals("(空)")){
			for(int i=0;i<jComboBox1.getItemCount();i++){
				if(jComboBox1.getSelectedItem().toString().equals(jComboBox1.getItemAt(i))){
					jLabel12.setText("该标题已经存在。");
					j=1;
				}
			}
			if(j==0){
				jComboBox1.addItem(jComboBox1.getSelectedItem().toString());
			    tixing t=new tixing();
			    t.setTitle(jComboBox1.getSelectedItem().toString());
			    t.setContent(jTextArea1.getText());
			    try{
			    	t.setMinute(jComboBox6.getSelectedIndex()+1);
		            t.setHour(jComboBox5.getSelectedIndex()+1);
		            t.setDay(Integer.parseInt(jTextField3.getText()));
		            t.setMonth(Integer.parseInt(jTextField2.getText()));
		            t.setYear(Integer.parseInt(jTextArea1.getText()));
		            }catch(NumberFormatException e){}
		            m.put(jComboBox1.getSelectedItem().toString(),t);
			}
			}else jLabel12.setText("不能添加空标题。");
	    
	}
	

	public static JComboBox getjComboBox1(){
		return jComboBox1;
	}
	
	private void jButton4ActionPerformed(ActionEvent evt) {                             //查询按钮按下时的动作
		jTextArea1.setText(m.get(jComboBox1.getSelectedItem().toString()).getContent());
		jComboBox6.setSelectedItem(Integer.toString((m.get(jComboBox1.getSelectedItem().toString()).getMinute())));
		String s=Integer.toString((m.get(jComboBox1.getSelectedItem().toString()).getHour()));
		if(s.length()==1)s="0"+s;
		jComboBox5.setSelectedItem(s);
		jTextField3.setText(Integer.toString((m.get(jComboBox1.getSelectedItem().toString()).getDay())));
		jTextField2.setText(Integer.toString((m.get(jComboBox1.getSelectedItem().toString()).getMonth())));
		jTextField1.setText(Integer.toString((m.get(jComboBox1.getSelectedItem().toString()).getYear())));
	//	shezhi.getjTextField4().setText((Integer.toString(m.get(jComboBox1.getSelectedItem().toString()).getCfc())));
	}

}

