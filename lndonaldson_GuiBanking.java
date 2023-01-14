/*
Laila Donaldson
December 2, 2022
COMP 167-001 
This program runs the main application window for the banking application
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Color;

public class lndonaldson_GuiBanking {

	private DefaultListModel<lndonaldson_BankAccount> _dlmAccount = new DefaultListModel<lndonaldson_BankAccount>();
	private ArrayList<lndonaldson_BankAccount>_alAccounts;
	private JList <lndonaldson_BankAccount> _list;
	private JFrame _frmBankingApplication;
	static lndonaldson_GuiBanking window;
	private JButton _btnAdd;
	private JLabel _lblError;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new lndonaldson_GuiBanking();
					window._frmBankingApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // close try/catch

			} // close run()

		}); // close EventQueue()

	} // close main()

	/**
	 * Create the application.
	 */
	public lndonaldson_GuiBanking() {
		initialize();		
	}// close constructor

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		_alAccounts = new ArrayList<lndonaldson_BankAccount>();
		_frmBankingApplication = new JFrame();
		_frmBankingApplication.setTitle("Gui Banking");
		_frmBankingApplication.setBounds(100, 100, 450, 300);
		_frmBankingApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// button will allow users to add an account to the list, and displays Add Account Dialogue when clicked
		_btnAdd = new JButton("Add");
		_btnAdd.setBounds(30, 70, 89, 29);
		_btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lndonaldson_AddAccount lndonaldson_AddAccount = new lndonaldson_AddAccount(lndonaldson_GuiBanking.this);
				lndonaldson_AddAccount.setVisible(true);
			}// close actionPerformed()

		}); // close addActionListener()
		_frmBankingApplication.getContentPane().setLayout(null);
		_frmBankingApplication.getContentPane().add(_btnAdd);

		// button will allow user to edit information for a selected account
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(30, 111, 89, 29);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// try/catch block will catch if a user tries to edit without selecting an account and display error message 
				try {
					lndonaldson_EditAccount ea = new lndonaldson_EditAccount(_alAccounts.get(_list.getSelectedIndex()));
					ea.setVisible(true);
					_lblError.setText(null);
				} catch (Exception IndexOutOfBoundsException) {
					_lblError.setText("No Account Selected");
				}// close try/catch

			} // close actionPerformed()

		}); // close addActionListener()
		_frmBankingApplication.getContentPane().add(btnEdit);

		_list = new JList <lndonaldson_BankAccount>(_dlmAccount);
		_list.setBounds(145, 30, 275, 200);
		_frmBankingApplication.getContentPane().add(_list);

		_lblError = new JLabel(" ");
		_lblError.setForeground(new Color(255, 38, 0));
		_lblError.setBounds(220, 242, 133, 16);
		_frmBankingApplication.getContentPane().add(_lblError);
	}// close initialize()

	// Method to store creates accounts and add them to the list in GUI Banking
	public void updateAccountData(lndonaldson_BankAccount ba) {
		_alAccounts.add(ba);
		_dlmAccount.addElement(ba);
	}// close updateAccountData()

}// close class
