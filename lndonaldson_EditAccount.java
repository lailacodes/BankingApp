/*
Laila Donaldson
December 2, 2022
COMP 167-001 
This program contains the dialogue which allows users to edit an account,
allowing them to make transactions.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.SwingConstants;

public class lndonaldson_EditAccount extends JDialog {

	private final JPanel _contentPanel = new JPanel();
	private JTextField _txtAmount;
	private DefaultListModel _prevTransaction = new DefaultListModel();
	private JLabel _lblName;
	private JLabel _lblID;
	private JLabel _lblBalance;
	private JList _list;
	private JLabel _lblError;
	lndonaldson_BankAccount savedBA = new lndonaldson_BankAccount("Test User", "Test Id"); // clone bank account 
	lndonaldson_BankAccount ba;
	lndonaldson_GuiBanking gb;
	lndonaldson_AddAccount aa;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			lndonaldson_EditAccount dialog = new lndonaldson_EditAccount();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		} // close try/catch()

	} // close main()

	/**
	 * Create the dialog.
	 */
	public lndonaldson_EditAccount(lndonaldson_BankAccount ba) {
		this.ba = ba;
		init();
		_lblName.setText(ba.getCustomerName());
		_lblID.setText(ba.getCustomerID());
		_lblBalance.setText(Integer.toString(ba.getBalance()));
		savedBA.setBalance(ba.getBalance()); // sets balance of clone account using amount from original bank account
	} // close constructor

	// test account to test dialogue without running main program
	public lndonaldson_EditAccount() { 
		ba = new lndonaldson_BankAccount("Test User", "Test Id");
		ba.setBalance(100);
		init();
		savedBA.setBalance(ba.getBalance()); // sets balance of clone account using amount from original bank account

		_lblName.setText(ba.getCustomerName());
		_lblID.setText(ba.getCustomerID());
		_lblBalance.setText(Integer.toString(ba.getBalance()));

		_lblError = new JLabel(" ");
		_lblError.setHorizontalAlignment(SwingConstants.CENTER);
		_lblError.setForeground(new Color(255, 38, 0));
		_lblError.setBounds(16, 148, 156, 16);
		_contentPanel.add(_lblError);
	}// close constructor

	/**
	 * Initialize the contents
	 */
	private void init() {
		setTitle("Edit Account");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		_contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(_contentPanel, BorderLayout.CENTER);
		_contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(35, 38, 61, 16);
		_contentPanel.add(lblNewLabel);

		_lblName = new JLabel("New label");
		_lblName.setBounds(108, 38, 122, 16);
		_contentPanel.add(_lblName);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(35, 66, 61, 16);
		_contentPanel.add(lblNewLabel_2);

		_lblID = new JLabel("New label");
		_lblID.setBounds(108, 66, 122, 16);
		_contentPanel.add(_lblID);

		JLabel lblNewLabel_4 = new JLabel("Balance");
		lblNewLabel_4.setBounds(35, 94, 61, 16);
		_contentPanel.add(lblNewLabel_4);

		_lblBalance = new JLabel("New label");
		_lblBalance.setBounds(108, 94, 122, 16);
		_contentPanel.add(_lblBalance);

		_txtAmount = new JTextField();
		_txtAmount.setBounds(35, 122, 122, 26);
		_contentPanel.add(_txtAmount);
		_txtAmount.setColumns(10);

		// button allows users to make a deposit to the account and adds amount to previous transactions
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// condition will check if the text field is left blank
				if (!_txtAmount.getText().isEmpty()) {
					/* try/catch will catch if user enters a non numeric character in text field
		 			and displays an error message */
					try { 
						// condition will check if the user tries to add zero dollars into the bank account
						if (Integer.parseInt(_txtAmount.getText()) != 0) {
							savedBA.deposit(Integer.parseInt(_txtAmount.getText()));
							savedBA.setPrevTransaction(Integer.parseInt(_txtAmount.getText()));
							_lblBalance.setText(Integer.toString(savedBA.getBalance()));
							_txtAmount.setText("");
							_prevTransaction.addElement("Deposited "+ savedBA.getPrevTransaction());
							_lblError.setText(null);
						} else {
							_lblError.setText("Deposit cannot be 0");
						}// close inner condition
						
					} catch (Exception NullPointerException) {
						_lblError.setText("Use 0-9 to enter amount");
					}// close try/catch()
					
				} else {
					_lblError.setText("Field is blank");
				} // close condition

			} // close actionPerformed

		}); //close actionListener
		btnDeposit.setBounds(35, 173, 117, 29);
		_contentPanel.add(btnDeposit);

		// button allows users to make a withdraw from the account and adds amount to previous transactions
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// condition will check if the text field is left blank
				if (!_txtAmount.getText().isEmpty()) {
					/* try/catch will catch if user enters a non numeric character in text field
		 			and displays an error message */
					try { 
						// condition will check if the user tries to take zero dollars from the bank account
						if (Integer.parseInt(_txtAmount.getText()) != 0) {
							savedBA.withdraw(Integer.parseInt(_txtAmount.getText()));					
							savedBA.setPrevTransaction(Integer.parseInt(_txtAmount.getText()));
							_lblBalance.setText(Integer.toString(savedBA.getBalance()));
							_txtAmount.setText("");
							_prevTransaction.addElement("Withdrew "+ savedBA.getPrevTransaction());
							_lblError.setText(null);
						} else {
							_lblError.setText("Withdraw cannot be 0");
						}// close inner condition
						
					} catch (Exception NullPointerException) {
						_lblError.setText("Use 0-9 to enter amount");
					} // close try/catch ()
					
				} else {
					_lblError.setText("Field is blank");
				} // close condition

			}// close actionPerformed

		}); //close btnWithdraw actionListener
		btnWithdraw.setBounds(35, 204, 117, 29);
		_contentPanel.add(btnWithdraw);

		_list = new JList(_prevTransaction);
		_list.setBounds(243, 38, 181, 171);
		_contentPanel.add(_list);

		JLabel lblPrevTransactions = new JLabel("Previous Transactions");
		lblPrevTransactions.setBounds(240, 10, 173, 16);
		_contentPanel.add(lblPrevTransactions);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{ // this button will transfer information from the clone account to the bank account and close the dialogue window
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ba.setBalance(savedBA.getBalance());
						dispose();
					} // close actionPerformed

				}); // close actionListener
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			} // close

			{ // this button will not save any updates/transactions made to the account and closes the dialogue window
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();	
					} // close actionPerformed()

				}); //close actionListener()

				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			} // close

		}// close pane

	}// close init()

}// close class()
