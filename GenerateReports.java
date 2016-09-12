
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.*;
import java.awt.Color;
public class GenerateReports {

	private JFrame frame;
	File f1,f2,f3,f4,f5,f6;
    static String ssn;
    static boolean ptsd,anx,dep;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateReports window = new GenerateReports(ssn,ptsd,anx,dep);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
   Connection c=null;
   Statement st=null;
   ResultSet rs=null;
	/**
	 * Create the application.
	 */
	public GenerateReports(String ssn,boolean ptsd,boolean anx,boolean dep) {
		this.ptsd=ptsd;
		this.anx=anx;
		this.dep=dep;
		this.ssn=ssn;
		initialize();
		c=sqliteConnection.dbconnector(this.getClass().getResource("/Diagnosis.db").toString());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 410, 227);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JButton btnGenerateReports = new JButton("Generate Reports");
		btnGenerateReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					
					try
					{   
						f1=new File("C:\\Diagnosis\\Reports\\"+ssn+".txt");
						BufferedWriter bw=new BufferedWriter(new FileWriter(f1,true));
						bw.append("SSN:"+ssn+"\n");
						
						st=c.createStatement();
						
						rs=st.executeQuery("select * from Patient where id ="+"'"+ssn+"'");
						//while(rs.next())
						//{
							
							bw.append("Name:");
							bw.append(rs.getString("name")+"\n");
							bw.append("Age:");
							bw.append(rs.getInt("age")+"\n");
						//}
						rs=st.executeQuery("select * from Experience where id ="+"'"+ssn+"'");
						//while(rs.next())
						//{
							bw.append("Recent Experiences:\n");
							if(rs.getBoolean("combat")) bw.append("Recent Combat Experience\n");
							if(rs.getBoolean("abuse")) bw.append("Abuse\n");
							if(rs.getBoolean("accident")) bw.append("Accident\n");
							if(rs.getBoolean("mugging")) bw.append("Mugging\n");
							if(rs.getBoolean("disaster")) bw.append("Disaster\n");
							if(rs.getBoolean("kidnap")) bw.append("Kidnap\n");
							if(rs.getBoolean("torture")) bw.append("Torture\n");
							if(rs.getBoolean("diagnosis")) bw.append("Recent critical diagnosis\n");
							if(rs.getBoolean("terror")) bw.append("Victim of terror attack\n");
							if(rs.getBoolean("work_fail")) bw.append("Failure at work/exam\n");
							if(rs.getBoolean("rel_fail")) bw.append("Relationship failure\n");
						//}
						rs=st.executeQuery("select * from Environment where id="+"'"+ssn+"'");
						//while(rs.next())
						//{
							bw.append("Patient's environment:\n");
							if(rs.getBoolean("trauma")) bw.append("Constant exposure to trauma\n");
							if(rs.getBoolean("death")) bw.append("Recent death of loved one\n");
							if(rs.getBoolean("neglect")) bw.append("Childhood neglect\n");
							if(rs.getBoolean("prob_home")) bw.append("Problems at home\n");
							if(rs.getBoolean("work_stress")) bw.append("Stressful workconditions\n");
						//}
						rs=st.executeQuery("select * from Medical where id ="+"'"+ssn+"'");
						//while(rs.next())
						//{
							bw.append("Patient's medical history:\n");
							if(rs.getBoolean("substance")) bw.append("Substance Abuse\n");
							if(rs.getBoolean("anx")) bw.append("Anxiety\n");
							if(rs.getBoolean("depr")) bw.append("Depression\n");
							if(rs.getBoolean("others")) bw.append("Other disorders\n");
							if(rs.getBoolean("fam_hist")) bw.append("Family history of PTSD/Depression\n");
						//}
						rs=st.executeQuery("select * from Tests where id ="+"'"+ssn+"'");
						//while(rs.next())
						//{
							bw.append("Based on above, tests were done for:\n");
							if(rs.getBoolean("ptsd")) bw.append("PTSD\n");
							if(rs.getBoolean("anx")) bw.append("Anxiety\n");
							if(rs.getBoolean("depr")) bw.append("Depression\n");
						//}
						rs=st.executeQuery("select * from FinalDiagnosis where id ="+"'"+ssn+"'");
						//while(rs.next())
						//{
							bw.append("Results of tests\n");
							if(rs.getString("ptsd").equalsIgnoreCase("Yes")) bw.append("PTSD is likely\n"); else if(rs.getString("ptsd").equalsIgnoreCase("No"))bw.append("No PTSD\n");
							if(rs.getString("anx").equalsIgnoreCase("Yes")) bw.append("Anxiety is likely\n"); else if(rs.getString("anx").equalsIgnoreCase("No"))bw.append("No Anxiety\n");
							if(rs.getString("depr").equalsIgnoreCase("Yes")) bw.append("Depression is likely\n"); else if(rs.getString("depr").equalsIgnoreCase("No")) bw.append("No Depression\n");
						//}
							bw.close();
					}
					catch(SQLException e1)
					{
						e1.printStackTrace();
					}
					//bw.write("Name: "+name+"\n");
					//bw.write("Age: "+age+"\n");
					/*BufferedReader br=new BufferedReader(new FileReader("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\2.txt"));
					String curr;
					while((curr=br.readLine())!=null)
					{
						bw.write(curr+"\n");
					}
					 br=new BufferedReader(new FileReader("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\3.txt"));
					while((curr=br.readLine())!=null)
					{
						bw.write(curr+"\n");
					}
					 br=new BufferedReader(new FileReader("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\4.txt"));
						while((curr=br.readLine())!=null)
						{
							bw.write(curr+"\n");
						}
						 br=new BufferedReader(new FileReader("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\5.txt"));
							while((curr=br.readLine())!=null)
							{
								bw.write(curr+"\n");
							}
							bw.write("Results of screening test:\n");
						
		                if((f2=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\6.txt")).exists())
		                	{br=new BufferedReader(new FileReader(f2));
		                	while((curr=br.readLine())!=null)
							{
		                		bw.write(curr+"\n");
							}}
		                
		                if((f3=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\7.txt")).exists())
		                	{br=new BufferedReader(new FileReader(f3));
		                	while((curr=br.readLine())!=null)
							{
		                		bw.write(curr+"\n");
							}}
		                
		                if((f4=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\8.txt")).exists())
		                		{
		                	br=new BufferedReader(new FileReader(f4));
		                	while((curr=br.readLine())!=null)
							{
		                		bw.write(curr+"\n");
							}
		                		}
		                if((f5=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\9.txt")).exists())
		                {
		                	br=new BufferedReader(new FileReader(f5));
		                	while((curr=br.readLine())!=null)
							{
		                		bw.write(curr+"\n");
							}}
		                
		                if((f6=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\10.txt")).exists())
		                {
		                	br=new BufferedReader(new FileReader(f6));
		                	while((curr=br.readLine())!=null)
							{
		                		bw.write(curr+"\n");
							}}
		                
						
					f2.delete();
					f3.delete();
					f4.delete();
					f5.delete();
					f6.delete();
					f2=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\2.txt");
					f2.delete();
					f2=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\3.txt");
					f2.delete();
					f2=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\4.txt");
					f2.delete();
					f2=new File("C:\\Program Files (x86)\\eclipse-jee-kepler-SR1-win32-x86_64\\eclipse\\Diagnosis\\Reports\\5.txt");
					f2.delete();
					bw.close();
					br.close();*/
					
				ShowReport s=new ShowReport(ssn);
					//Display d=new Display(ssn);
	                frame.dispose();
				}
				
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnGenerateReports.setBounds(87, 111, 143, 23);
		frame.getContentPane().add(btnGenerateReports);
		
		JButton btnRunAnotherTest = new JButton("Run another test");
		btnRunAnotherTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu m=new Menu(ptsd,anx,dep,ssn);
				frame.dispose();
			}
		});
		btnRunAnotherTest.setBounds(87, 45, 158, 23);
		frame.getContentPane().add(btnRunAnotherTest);
	}
}
