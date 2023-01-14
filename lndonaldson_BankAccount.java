/*
Laila Donaldson
December 2, 2022
COMP 167-001 
This program contains the accessor methods and constructors for the banking application
 */

import java.util.Scanner;

public class lndonaldson_BankAccount {
	static Scanner scnr = new Scanner (System.in);

	private int _balance;
	private String _customerName;
	private String _customerID;
	private int _previousTransaction;

	// accessor methods
	public int getBalance() {
		return _balance;
	}// close getBalance()
	public void setBalance(int _balance) {
		this._balance = _balance;
	}// close setBalance()

	public String getCustomerName() {
		return _customerName;
	}// close getCustomerName()
	public void setCustomerName(String _customerName) {
		this._customerName = _customerName;
	}// close setCustomerName()

	public String getCustomerID() {
		return _customerID;
	}//close getCustomerID()
	public void setCustomerID(String _customerID) {
		this._customerID = _customerID;
	}// close setCustomerID()

	public int getPrevTransaction() {
		return _previousTransaction;
	}// close getPrevTransaction()
	public void setPrevTransaction(int _previousTransaction) {
		this._previousTransaction = _previousTransaction;
	}// close setPrevTransaction()

	// constructors
	public lndonaldson_BankAccount() {
		System.out.print("Customer Name: ");
		setCustomerName(scnr.nextLine());
		System.out.print("Customer ID: ");
		setCustomerID(scnr.nextLine());
	} // close constructor

	public lndonaldson_BankAccount(String customerName, String customerID) {
		_customerName = customerName;
		_customerID = customerID;
	} //close constructor

	// adds amount into bank account's balance
	public void deposit(int amount) {
		_balance = _balance + amount;
	}//close deposit()

	// subtracts amount from bank account's balance
	public void withdraw(int amount) {
		_balance = _balance - amount;
	} //close withdraw()
	
	@Override
	public String toString() {
		return this._customerName + " " + this._customerID;
	} // close toString()

}// close class
