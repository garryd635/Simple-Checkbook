/**
 * Garry Dominique
 * AppUI
 * 	Displays a graphical interface for the user to interact with the system.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

public class AppUI {
	
	//Instance variables
	private JButton deposit,widthdrawl,back,submit,load,save;
	private JPanel buttonPanelMain,buttonPanelTrans,bPDeposit, bPWidth,depositInput,widthdrawlInput,layout;
	private JLabel nameLabel,nameLabel2, amtLabel, amtLabel2, balanceLabel;
	private JTextField nameText, nameText2, amtText, amtText2;
	private JFrame frame,popup;
	private JScrollPane scrollPane;
	private CheckbookManager cbManager;
	private String state;
	
	/**
	 * Constructor
	 */
	public AppUI(){
		cbManager = new CheckbookManager();
		frame = new JFrame("Checkbook Application");	
		popup = new JFrame("Error");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Initiate Buttons
		deposit = new JButton("Deposit Check");
		widthdrawl = new JButton("Widthdrawl Check");
		back = new JButton("Back");
		submit = new JButton("Submit");
		save = new JButton("Save Checkbook");
		load = new JButton("Load Checkbook");
		
		//Initiate Labels and Text fields
		nameLabel = new JLabel("Name:");
		amtLabel = new JLabel("Amount:");
		
		nameLabel2 = new JLabel("Name:");
		amtLabel2 = new JLabel("Amount:");
		
		//Have CheckbookManager calculate balance
		balanceLabel = new JLabel("Current Balance: " + cbManager.calculateBalance());
		
		nameText = new JTextField();
		amtText = new JTextField();
		nameText2 = new JTextField();
		amtText2 = new JTextField();
		
		
		//Action Listeners for each button.
		/**
		 * Deposit: If Deposit is pressed, change the state varible to deposit and 
		 * display components needed to make a deposit.
		 */
		deposit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				state = "deposit";
				bPDeposit.add(back);
				bPDeposit.add(submit);
				layout.remove(buttonPanelMain);
				layout.add(depositInput, BorderLayout.NORTH);
				layout.add(bPDeposit, BorderLayout.SOUTH);
				layout.revalidate();
				layout.repaint();
				scrollPane.setVisible(false);
			}
		});
		
		/**
		 * withdrawal: If withdrawal is pressed, change the state varible to deposit and 
		 * display components needed to make a deposit.
		 */
		widthdrawl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				state = "widthdrawl";
				bPWidth.add(back);
				bPWidth.add(submit);
				layout.remove(buttonPanelMain);
				layout.add(widthdrawlInput, BorderLayout.NORTH);
				layout.add(bPWidth, BorderLayout.SOUTH);
				layout.revalidate();
				layout.repaint();
				scrollPane.setVisible(false);	
			}
		});
		
		/**
		 * When back is pressed, revert back to the components of the main screen
		 */
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (state == "deposit"){
					scrollPane.setVisible(true);
					layout.remove(bPDeposit);
					layout.remove(depositInput);
					layout.add(buttonPanelMain, BorderLayout.SOUTH);
					layout.revalidate();
					layout.repaint();
				}
				else if(state == "widthdrawl"){
					scrollPane.setVisible(true);
					layout.remove(bPWidth);
					layout.remove(widthdrawlInput);
					layout.add(buttonPanelMain, BorderLayout.SOUTH);
					layout.revalidate();
					layout.repaint();
				}
				
			}
		});
		
		/**
		 * When submit is pressed, process the transaction whether it is a deposit
		 * or withdrawal
		 */
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (state == "deposit"){
					
					layout.remove(bPDeposit);
					layout.remove(depositInput);
					layout.add(buttonPanelMain,BorderLayout.SOUTH);
					cbManager.create(nameText.getText(), Double.parseDouble(amtText.getText()), "Deposit");
					nameText.setText("");
					amtText.setText("");
					balanceLabel.setText("Current Balance: " + cbManager.calculateBalance());
					depositInput.revalidate();
					depositInput.repaint();
					scrollPane = new JScrollPane(cbManager.getTable());
					scrollPane.revalidate();
					scrollPane.repaint();
					scrollPane.setVisible(true);
					layout.revalidate();
					layout.repaint();
					}
				
				if (state == "widthdrawl"){
					layout.remove(bPWidth);
					layout.remove(widthdrawlInput);
					layout.add(buttonPanelMain, BorderLayout.SOUTH);
					cbManager.create(nameText2.getText(), Double.parseDouble(amtText2.getText()), "Withdrawal");
					nameText2.setText("");
					amtText2.setText("");
					balanceLabel.setText("Current Balance: " + cbManager.calculateBalance());
					widthdrawlInput.revalidate();
					widthdrawlInput.repaint();
					scrollPane = new JScrollPane(cbManager.getTable());
					scrollPane.revalidate();
					scrollPane.repaint();
					scrollPane.setVisible(true);
					layout.revalidate();
					layout.repaint();
				}
			}
		});
		
		/**
		 * If load is pressed
		 */
		load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cbManager.loadCheckBook();
				cbManager.initialCreate();
				scrollPane = new JScrollPane(cbManager.getTable());
				balanceLabel.setText("Current Balance: " + cbManager.calculateBalance());
				scrollPane.revalidate();
				scrollPane.repaint();
			}
		});
		
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					cbManager.saveCheckbook();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		//PANELS
		buttonPanelMain = new JPanel(new FlowLayout());
		buttonPanelTrans = new JPanel(new FlowLayout());
		bPDeposit = new JPanel(new FlowLayout());
		bPWidth = new JPanel(new FlowLayout());
		depositInput = new JPanel(new GridLayout(0,2));
		widthdrawlInput = new JPanel(new GridLayout(0,2));
		layout = new JPanel(new BorderLayout());
		
		//Insert componenets into panels.
		buttonPanelMain.add(deposit);
		buttonPanelMain.add(widthdrawl);
		buttonPanelMain.add(load);
		buttonPanelMain.add(save);
		depositInput.add(nameLabel);
		depositInput.add(nameText);
		depositInput.add(amtLabel);
		depositInput.add(amtText);
		widthdrawlInput.add(nameLabel2);
		widthdrawlInput.add(nameText2);
		widthdrawlInput.add(amtLabel2);
		widthdrawlInput.add(amtText2);
		
		layout.add(balanceLabel, BorderLayout.NORTH);
		scrollPane = new JScrollPane(cbManager.getTable());
		layout.add(scrollPane,BorderLayout.CENTER);
		layout.add(buttonPanelMain, BorderLayout.SOUTH);
		frame.add(layout);	
	}//Constructor
	
	/**
	 * Method: Run
	 * 	Makes the frame visible
	 */
	public void run(){
		frame.setSize(700,500);
		frame.setVisible(true);
	}//run()
}//AppUI
