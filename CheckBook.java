//Eric Wong
//CSC20 Project
//Professor Wang
//Insert Date
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class CheckBook implements ActionListener, Serializable {
   static String AccountName;
   static int AccountBalance;
   //Main Window Buttons
   static JButton newAccount; 
   static JButton loadTransactions;
   static JButton newTransactions;
   static JButton searchTransactions; //optional
   static JButton sortTransactions; //optional
   static JButton Transactions;
   static JButton backupTransactions;
   static JButton exit;
   static JLabel accountName;
   static JLabel accountBalance;
   static JTextField enterAccountName;
   static JTextField enterAccountBalance;
   //New Account Window
   static JButton createNewButton;
   static JButton cancelNewButton;
   static JLabel newAccountText;
   static JTextField newAccountTextField;
   static JLabel initialBalanceTextLabel;
   static JTextField initialBalanceTextField;
   //Load Transaction Window
   static JButton loadButton;
   static JButton cancelLoadButton;
   static JLabel loadTransTextLabel;
   static JTextField loadTransTextField;
   //Add New Transactions Window
   static JButton saveNewTransButton;
   static JButton newTransTopMenuButton;
   static JTextField dateTextField;
   static JTextField transTypeTextField;
   static JTextField checkNoTextField;
   static JTextField transDescriptionTextField;
   static JTextField paymentDebitTextField;
   static JTextField despositCreditTextField;
   //Search Transactions Window
   static JButton searchButton;
   static JButton searchTopMenuButton;
   //Sort Transactions Window
   static JButton sortButton;
   static JButton sortTopMenuButton;
   //View/Delete Window
   static JButton deleteSelectedButton;
   static JButton viewDeleteTopMenuButton;
   static JScrollPane scrollPane;
   //Backup Window
   static JButton backupButton;
   static JButton backupTopMenuButton;
   //JLabel Headers for Each Window
   static JLabel newAccountTextLabel; //New Account Window Text
   static JLabel loadTransText; //Load Transactions Window Text
   static JLabel searchTransText; //Search Transactions Window Text
   static JLabel sortTransText; //Sort Transactions Window Text
   static JLabel Text; //View/Delete Transactions Window Text
   //Card Layout
   static JPanel contentPane;
   static CardLayout contentPaneLayout;
   static AccountList[] Accs;
   static FileOutputStream fos;
   static ObjectOutputStream oos;
   static FileInputStream fis;
   static ObjectInputStream ois;
   //Table Variables
   static String tableVariables[] = {"Date", "Trans. Type", "Check No.", "Trans. Description", "Payment/Debit(-)", "Deposit/Credit(+)", "Balance"};
   static JTable abtable;
   
   public static void main(String[] args) {
	  Accs = new AccountList[99];
	  AccountBalance = 0;
      ActionListener AL = new CheckBook();
      JFrame frm = new JFrame("Eric Wong's Checkbook");
      //Panels for each page
      JPanel main = new JPanel(new BorderLayout());
      JPanel newAcc = new JPanel(new BorderLayout());
      JPanel loadTrans = new JPanel(new BorderLayout());
      JPanel newTrans = new JPanel(new BorderLayout());
      JPanel searchTrans = new JPanel(new BorderLayout());
      JPanel sortTrans = new JPanel(new BorderLayout());
      JPanel viewDelete = new JPanel(new BorderLayout());
      JPanel backupTrans = new JPanel(new BorderLayout());
      
      //Main Screen
      JLabel header = new JLabel("Use The Buttons Below to Manage Transactions", JLabel.CENTER);
      main.add(header, BorderLayout.NORTH);
      JPanel buttons = new JPanel(new GridLayout(2,4,2,2));
      JPanel text = new JPanel();
      main.add(buttons, BorderLayout.SOUTH);
      main.add(text, BorderLayout.CENTER);
      
      text.add(accountName = new JLabel("Account Name: "));
      text.add(enterAccountName = new JTextField(AccountName ,15));
      text.add(accountBalance = new JLabel("Balance: "));
      text.add(enterAccountBalance = new JTextField("0.0" + AccountBalance , 15));
      enterAccountName.setEditable(false);
      enterAccountBalance.setEditable(false);
      
      buttons.add(newAccount = new JButton("Create a new account"));
      buttons.add(loadTransactions = new JButton("Load transactions from a file"));
      buttons.add(newTransactions = new JButton("Add new transactions"));
      buttons.add(searchTransactions = new JButton("Search transactions"));
      buttons.add(sortTransactions = new JButton("Sort transactions"));
      buttons.add(Transactions = new JButton("View/Delete transactions"));
      buttons.add(backupTransactions = new JButton("Backup transactions"));
      buttons.add(exit = new JButton("Exit"));
      newAccount.addActionListener(AL);
      loadTransactions.addActionListener(AL);
      newTransactions.addActionListener(AL);
      searchTransactions.addActionListener(AL);
      sortTransactions.addActionListener(AL);
      Transactions.addActionListener(AL);
      backupTransactions.addActionListener(AL);
      exit.addActionListener(AL);
            
      //Create a New Account
      JLabel newAccountText = new JLabel("Create a New Account",JLabel.CENTER);
      newAcc.add(newAccountText, BorderLayout.NORTH);
      	
      	//Create a New Account Buttons
      	JPanel accountWindowButtons = new JPanel();
	    accountWindowButtons.add(createNewButton = new JButton("Create"));
	    createNewButton.addActionListener(AL);
	    accountWindowButtons.add(cancelNewButton = new JButton("Cancel"));
	    cancelNewButton.addActionListener(AL);
	    newAcc.add(accountWindowButtons, BorderLayout.SOUTH);
	      
	    //Create a New Account Text and Label
	    JPanel accountWindowText = new JPanel();
	    newAccountTextLabel = new JLabel("Account Name:");
	    newAccountTextField = new JTextField(15);
	    initialBalanceTextLabel = new JLabel("Initial Balance:");
	    initialBalanceTextField = new JTextField(15);
	    accountWindowText.add(newAccountTextLabel); accountWindowText.add(newAccountTextField);
	    accountWindowText.add(initialBalanceTextLabel); accountWindowText.add(initialBalanceTextField);
	    newAcc.add(accountWindowText, BorderLayout.CENTER);
	      
      //Load Transactions From a file
      JLabel loadTransText = new JLabel("Load Transactions From a File",JLabel.CENTER);
      loadTrans.add(loadTransText, BorderLayout.NORTH);
      	
      	//Load Transactions From a file Buttons
      	JPanel loadWindowButtons = new JPanel();
      	loadWindowButtons.add(loadButton = new JButton("Load"));
      	loadButton.addActionListener(AL);
      	loadWindowButtons.add(cancelLoadButton = new JButton("Cancel"));
      	cancelLoadButton.addActionListener(AL);
      	loadTrans.add(loadWindowButtons, BorderLayout.SOUTH);
      	
      	//Load Transactions From a file Text and Label
      	JPanel loadWindowText = new JPanel();
      	loadTransTextLabel = new JLabel("Account Name:");
      	loadTransTextField = new JTextField(15);
      	loadWindowText.add(loadTransTextLabel); loadWindowText.add(loadTransTextField);
      	loadTrans.add(loadWindowText, BorderLayout.CENTER);
      
      //Add New Transactions 
      JLabel newTransText = new JLabel("Add New Transactions",JLabel.CENTER);
      newTrans.add(newTransText, BorderLayout.NORTH);
      
        //Add New Transactions Text and Label
        JPanel addNewTransGrid = new JPanel(new GridLayout(6,2,2,2));
        JLabel Date = new JLabel("Date", JLabel.RIGHT);
        JLabel transType = new JLabel("Trans. Type", JLabel.RIGHT);
        JLabel checkNo = new JLabel("Check No.", JLabel.RIGHT);
        JLabel transDescription = new JLabel("Trans. Description", JLabel.RIGHT);
        JLabel paymentDebit = new JLabel("Payment/Debit(-)", JLabel.RIGHT);
        JLabel depositCredit = new JLabel("Deposit/Credit(+)", JLabel.RIGHT);
        addNewTransGrid.add(Date);
        addNewTransGrid.add(dateTextField = new JTextField());
        addNewTransGrid.add(transType);
        addNewTransGrid.add(transTypeTextField = new JTextField()); //Drag down for choices
        addNewTransGrid.add(checkNo);
        addNewTransGrid.add(checkNoTextField = new JTextField()); //Disable unless transtype = Check
        addNewTransGrid.add(transDescription);
        addNewTransGrid.add(transDescriptionTextField = new JTextField());
        addNewTransGrid.add(paymentDebit);
        addNewTransGrid.add(paymentDebitTextField = new JTextField());
        addNewTransGrid.add(depositCredit);
        addNewTransGrid.add(despositCreditTextField = new JTextField()); //Disable unless transtype = Other
        newTrans.add(addNewTransGrid, BorderLayout.CENTER);
        
    	//Add New Transactions Buttons
      	JPanel newTransButtons = new JPanel();
      	newTransButtons.add(saveNewTransButton = new JButton("Save New Transaction"));
        saveNewTransButton.addActionListener(AL);
        newTransButtons.add(newTransTopMenuButton = new JButton("Top Menu"));
        newTransTopMenuButton.addActionListener(AL);
        newTrans.add(newTransButtons, BorderLayout.SOUTH);
        
      //Search Transactions
      JLabel searchTransText = new JLabel("Search Transactions by Transaction Date/Type/Check No./Description",JLabel.CENTER);
      searchTrans.add(searchTransText, BorderLayout.NORTH);
      	
      	//Search Transactions Buttons
      	JPanel searchTransButtons = new JPanel();
      	searchTransButtons.add(searchButton = new JButton("Search"));
      	searchButton.addActionListener(AL);
      	searchTransButtons.add(searchTopMenuButton = new JButton("Top Menu"));
      	searchTopMenuButton.addActionListener(AL);
      	searchTrans.add(searchTransButtons, BorderLayout.SOUTH);
      	
      //Sort Transactions
      JLabel sortTransText = new JLabel("Sort Transactions",JLabel.CENTER);
      sortTrans.add(sortTransText, BorderLayout.NORTH);
      
      	//Sort Transactions Buttons
      	JPanel sortTransButtons = new JPanel();
      	sortTransButtons.add(sortButton = new JButton("Sort"));
      	sortButton.addActionListener(AL);
      	sortTransButtons.add(sortTopMenuButton = new JButton("Top Menu"));
      	sortTopMenuButton.addActionListener(AL);
      	sortTrans.add(sortTransButtons, BorderLayout.SOUTH);
      
      //View/Delete Transactions
      JLabel Text = new JLabel("Transactions Currently In The Checkbook",JLabel.CENTER);
      viewDelete.add(Text, BorderLayout.NORTH);
      	
      	//View/Delete Labels and Text
      	scrollPane = new JScrollPane();
      	viewDelete.add(scrollPane, BorderLayout.CENTER);
      	
      	//View/Delete Buttons
      	JPanel viewDeleteButtons = new JPanel();
      	viewDeleteButtons.add(deleteSelectedButton = new JButton("Delete Selected"));
      	deleteSelectedButton.addActionListener(AL);
      	viewDeleteButtons.add(viewDeleteTopMenuButton = new JButton("Top Menu"));
      	viewDeleteTopMenuButton.addActionListener(AL);
      	viewDelete.add(viewDeleteButtons, BorderLayout.SOUTH);
      
      //Backup Transactions
      JLabel backupText = new JLabel("Backup Transactions",JLabel.CENTER);
      backupTrans.add(backupText, BorderLayout.NORTH);
      
      	//Backup Transactions Buttons
      	JPanel backupButtons = new JPanel();
      	backupButtons.add(backupButton = new JButton("Backup"));
      	backupButton.addActionListener(AL);
      	backupButtons.add(backupTopMenuButton = new JButton("Top Menu"));
      	backupTopMenuButton.addActionListener(AL);
      	backupTrans.add(backupButtons, BorderLayout.SOUTH);
      	    
      //Card Layout
      contentPane = (JPanel)frm.getContentPane();
      contentPane.setLayout(contentPaneLayout = new CardLayout());
      contentPane.add("Main", main);
      contentPane.add("New Account", newAcc);
      contentPane.add("Load Transactions", loadTrans);
      contentPane.add("New Transactions", newTrans);
      contentPane.add("Search Transactions", searchTrans);
      contentPane.add("Sort Transactions", sortTrans);
      contentPane.add("View/Delete Transactions", viewDelete);
      contentPane.add("Backup Transactions", backupTrans);
      
      //Frame Specifications
      frm.pack();
      frm.setSize(700,240);
      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frm.setResizable(false);
      frm.setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if(source == newAccount) contentPaneLayout.show(contentPane, "New Account");
      if(source == loadTransactions) contentPaneLayout.show(contentPane, "Load Transactions");
      if(source == newTransactions) contentPaneLayout.show(contentPane, "New Transactions");
      if(source == searchTransactions) contentPaneLayout.show(contentPane, "Search Transactions");
      if(source == sortTransactions) contentPaneLayout.show(contentPane, "Sort Transactions");
      if(source == Transactions){
    	  String [][]temp = tableViewThing (Accs);
    	  contentPaneLayout.show(contentPane, "View/Delete Transactions");
    	  abtable = new JTable(temp, tableVariables);
    	  JScrollPane tmp = new JScrollPane(abtable);
    	  scrollPane.setViewport(tmp.getViewport());
      }
      if(source == backupTransactions) contentPaneLayout.show(contentPane, "Backup Transactions");
      if(source == exit) System.exit(0);
   }
   
   String[][] tableViewThing(AccountList Accs[]) {
	   String[][] temp = new String[AccountBalance][7];
	   for (int i = 0; i < AccountBalance; i++) {
		   temp[i][0] = (String) Accs[i].theDate;
		   temp[i][1] = (String) Accs[i].theTransType;
		   temp[i][2] = (String) Accs[i].theCheckNo;
		   temp[i][3] = (String) Accs[i].theTransDesc;
		   temp[i][4] = (String) Accs[i].thePaymentDebit;
		   temp[i][5] = (String) Accs[i].theDepositCheck;
		   temp[i][6] = (String) Accs[i].theBalance;
	   }
		return temp;	   
   }
   
   class AccountList implements Serializable {
	   String theDate;
	   String theTransType;
	   String theCheckNo;
	   String theTransDesc;
	   String thePaymentDebit;
	   String theDepositCheck;
	   String theBalance;
	   public AccountList(String date, String transType, String checkNo, String transDesc, String paymentDebit, String depositCheck, String balance){
		   this.theDate = date;
		   this.theTransType = transType;
		   this.theCheckNo = checkNo;
		   this.theTransDesc = transDesc;
		   this.thePaymentDebit = paymentDebit;
		   this.theDepositCheck = depositCheck;
		   this.theBalance = balance;
	   }
	   
   }
}