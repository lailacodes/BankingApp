/*
Laila Donaldson
December 2, 2022
COMP 167-001 
This program contains the dialogue which allows users to add an account
that will appear in the list of the main program
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class lndonaldson_AddAccount extends JDialog {

	private final JPanel _contentPanel = new JPanel();
	private JTextField _txtName;
	private JTextField _txtID;
	private JTextField _txtBalance;
	private JLabel _lblError;
	lndonaldson_GuiBanking gb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			lndonaldson_AddAccount dialog = new lndonaldson_AddAccount();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		} // close try/catch()
		
	}// close main()

	/**
	 * Create the dialog.
	 */
	public lndonaldson_AddAccount(lndonaldson_GuiBanking window) {
		gb = window;
		init();
	} // close constructor

	public lndonaldson_AddAccount() {
		init();
	} // close constructor

	/**
	 * Initialize the contents
	 */
	public void init() {
		setTitle("Add Account");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		_contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(_contentPanel, BorderLayout.CENTER);
		_contentPanel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(30, 50, 61, 16);
		_contentPanel.add(lblName);

		JLabel lblID = new JLabel("ID");
		lblID.setBounds(30, 90, 61, 16);
		_contentPanel.add(lblID);

		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(30, 130, 61, 16);
		_contentPanel.add(lblBalance);

		_txtName = new JTextField();
		_txtName.setBounds(103, 45, 130, 26);
		_contentPanel.add(_txtName);
		_txtName.setColumns(10);

		_txtID = new JTextField();
		_txtID.setBounds(103, 85, 130, 26);
		_contentPanel.add(_txtID);
		_txtID.setColumns(10);

		_txtBalance = new JTextField();
		_txtBalance.setBounds(103, 125, 130, 26);
		_contentPanel.add(_txtBalance);
		_txtBalance.setColumns(10);

		_lblError = new JLabel("");
		_lblError.setForeground(new Color(255, 38, 0));
		_lblError.setBounds(75, 163, 229, 16);
		_contentPanel.add(_lblError);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{ // button will Add an account to the main list in Banking Application
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// condition will check if all text fields have data entered, allowing the user to add the account
						if (!_txtName.getText().isEmpty() && !_txtID.getText().isEmpty() && !_txtBalance.getText().isEmpty()) {
							lndonaldson_BankAccount account = new lndonaldson_BankAccount(_txtName.getText(),_txtID.getText());
							
							/* try/catch will catch if a user enters a non numeric character in the balance 
							 * and display an error message */
							try {
								// condition checks if user tries to enter a negative number as their balance
								if(Integer.parseInt(_txtBalance.getText()) >= 0) {
								account.setBalance(Integer.parseInt(_txtBalance.getText()));
								_lblError.setText(null);
								} else {
									_lblError.setText("Cannot start account in the negative");
								} // close
							} catch (Exception NumberFormatException) {
								_lblError.setText("Use 0-9 to enter a balance");
								account.setBalance(Integer.parseInt(_txtBalance.getText()));
							} //close try/catch()

							gb.updateAccountData(account);
							dispose();
							_lblError.setText(null);
						} else { 
							// Error message will display if any text fields are left blank
							_lblError.setText("One or more fields are empty");
						} // close outer condition
							
					} // close actionPerformed
					
				}); // close okButton actionListener()
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}// close content pane

			{// button will close Add Account window
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}// close actionPerformed()
					
				}); // close cancelButton actionListener()
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			} // close 
			
		}// close panel ()

	}//close init()

}// close class()
