import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
public class mainFrame {
	public mainFrame() {
		 JFrame frame = new JFrame("");
		frame.setSize(665, 562);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] title = {"姓名", "生日", "家庭住址", "班号"};
		final DefaultTableModel dtm = new DefaultTableModel(new Object[][] {}, title);
		final JTable table = new JTable(dtm);
		final JScrollPane pane = new JScrollPane(table);
		pane.setBounds(10, 15, 627, 315);
		table.setVisible(true);
		frame.add(pane);
		
		JButton add = new JButton("添加记录");
		JButton delete = new JButton("删除记录");
		JButton save = new JButton("保存修改");
		JButton exit = new JButton("退出");
		add.setBounds(48, 357, 120, 40);
		frame.add(add);
		add.setText("\u6dfb\u52a0\u8bb0\u5f55");
		delete.setBounds(250, 357, 120, 40);
		frame.add(delete);
		delete.setText("\u5220\u9664\u8bb0\u5f55");
		save.setBounds(459, 357, 120, 40);
		frame.add(save);
		exit.setBounds(250, 460, 120, 40);
		frame.add(exit);
		
		try {
			
			String driver="com.mysql.jdbc.Driver";
			String url=
		            "localhost:3306";
			String userName="root";
			String password="123";
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,userName,password);
			final Statement sql = conn.createStatement();
			
					try {

						ResultSet rst = sql.executeQuery("SELECT * FROM student");
						String[] Name = new String[100];
						Date[] Birthday = new Date[100];
						String[] HomeAddress = new String[100];
						String[] ClassNum = new String[100];
						int i=0;
						int c = dtm.getRowCount();
						for (i=0;i<c;i++) {
							dtm.removeRow(0);
						}
						i=0;
						while(rst.next()) {
							Name[i] = rst.getString("Name");
							Birthday[i] = rst.getDate("Birthday");
							HomeAddress[i] = rst.getString("HomeAddress");
							ClassNum[i] = rst.getString("ClassNum");
							dtm.addRow(new Object[] {Name[i], Birthday[i].toString(), HomeAddress[i], ClassNum[i]});
							i++;
						}
						
					} catch (Exception e) {
						System.out.print(e);
					}
			
			add.addActionListener(new ActionListener() {				//按下add按钮的动作
				public void actionPerformed(ActionEvent ex) {
					dtm.addRow(new Object[] {"","","",""});
					final JDialog add = new JDialog();
					add.setSize(500, 300);
					add.setLayout(null);
					JLabel Name = new JLabel("姓名");
					JLabel birthday = new JLabel("生日");
					JLabel homeAddress = new JLabel("家庭住址");
					JLabel classNum = new JLabel("班号");
					add.add(Name);
					add.add(birthday);
					add.add(homeAddress);
					add.add(classNum);
					Name.setBounds(15, 10, 60, 30);
					birthday.setBounds(15, 50, 60, 30);
					homeAddress.setBounds(15, 90, 60, 30);
					classNum.setBounds(15, 130, 60, 30);
					
					final JTextField Name1 = new JTextField();
					Name.setBounds(80, 10, 100, 30);
					add.add(Name);
					
					String[] years = new String[31];
					for (int i=1970;i<2001;i++) {
						years[i-1970] = String.valueOf(i);
					}
					final JComboBox year = new JComboBox(years); 						
					year.setSelectedIndex(0);
					add.add(year);
					year.setBounds(80, 50, 60, 30);
					JLabel yr = new JLabel("年");
					yr.setBounds(145, 50, 15, 30);
					add.add(yr);
					String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
					final JComboBox month = new JComboBox(months); 						
					month.setSelectedIndex(0);
					month.setBounds(165, 50, 45, 30);
					add.add(month);
					JLabel mth = new JLabel("月");
					mth.setBounds(215, 50, 15, 30);
					add.add(mth);
					String[] days = new String[31];
					for (int i=1;i<32;i++) {
						days[i-1] = String.valueOf(i);
					}
					final JComboBox day = new JComboBox(days);			
					day.setSelectedIndex(0);
					add.add(day);
					day.setBounds(235, 50, 45, 30);
					JLabel d = new JLabel("日");
					d.setBounds(285, 50, 15, 30);
					add.add(d);
					
					final JTextField HomeAddress = new JTextField();
					HomeAddress.setBounds(80, 90, 390, 30);
					add.add(HomeAddress);
					
					final JTextField ClassNum = new JTextField();
					ClassNum.setBounds(80, 130, 100, 30);
					add.add(ClassNum);
					
					JButton save = new JButton("保存");
					JButton cancel = new JButton("取消");
					save.setBounds(150, 200, 70, 30);
					cancel.setBounds(250, 200, 70, 30);
					add.add(save);
					add.add(cancel);
					add.setVisible(true);
				}
			});
			
		
			
			save.addActionListener(new ActionListener() {				//按下save按钮的动作
				public void actionPerformed(ActionEvent ex) {
					try {
						String key = null;
						String[] Name = new String[100];
						Date[] Birthday = new Date[100];
						String[] Address = new String[100];
						String[] Classnum = new String[100];
						ResultSet rs = sql.executeQuery("SELECT * FROM student");
						int i=0;
						while(rs.next()) {
							Name[i] = rs.getString("name");
							Birthday[i] = rs.getDate("birthday");
							Address[i] = rs.getString("homeAddress");
							Classnum[i] = rs.getString("classNum");
							i++;
						}
						sql.execute("delete from student");
						if (table.isEditing()) {
							int row = table.getEditingRow();
							int column = table.getEditingColumn();
							table.getCellEditor(row, column).stopCellEditing();
						}
						try {
							for (i=0;i<table.getRowCount();i++) {
								System.out.println();
								if (dtm.getValueAt(i, 0).toString()!="") {
									sql.execute("insert into student values('"+dtm.getValueAt(i, 0).toString()+"','"+dtm.getValueAt(i, 1).toString()+"','"+dtm.getValueAt(i, 2).toString()+"','"+dtm.getValueAt(i, 3).toString()+"')");
								}
							}
							new JFrame(){
								{JOptionPane.showMessageDialog(this, "修改成功!");}
							};
						} catch(final Exception exception) {
							new JFrame(){
								{JOptionPane.showMessageDialog(this, exception);}
							};
							sql.execute("delete from student");
							i=0;
							while (Name[i] != null) {
								sql.execute("insert into student values('"+Name[i]+"','"+Birthday[i].toString()+"','"+Address[i]+"','"+Classnum[i]+"')");
								i++;
							}
						}
						
						
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			});
			
			delete.addActionListener(new ActionListener() {				//按下delete按钮的动作
				public void actionPerformed(ActionEvent ex) {
					try {
						int index = table.getSelectedRow();
						String key = dtm.getValueAt(index, 0).toString();
						dtm.removeRow(index);
						sql.execute("delete from student where name=\""+key+"\"");
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			});
			exit.addActionListener(new ActionListener() {				//按下exit按钮的动作
				public void actionPerformed(ActionEvent ex) {
					System.exit(0);
				}
			});
			
		} catch(Exception ex) {
			System.out.println(ex);
		}
		
		frame.setVisible(true);
		frame.setPreferredSize(new java.awt.Dimension(665, 562));
	}
	public static void main(String[] args) {
		mainFrame frame = new mainFrame();
	}
}
