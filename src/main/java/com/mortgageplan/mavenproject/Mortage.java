package com.mortgageplan.mavenproject;

public class Mortage {
	
	private String customer;
	private float totLoan;
	private float interest;
	private int years;
	private double monthlyPay;
	
	//Constructor
	public Mortage(String customer, float totLoan, float interest, int years) {
		
		this.customer = customer;
		this.totLoan = totLoan;
		this.interest = interest;
		this.years = years;
		
	}
	
	
	public double toPower(double base, int power) {
		
		double num = 1;
		for(int i = 0; i < power; i++) {
			num = num * base;
		}
		
		return num;
	}
	
	public double round2Dec(double sum) {
		
		sum *= 100.0;
		sum += 0.5;
		sum = (int)sum;
		sum /= 100.0;
		
		return sum;
	}
	
	//Get the total amount they should pay each month
	public void calcMonthlyPay() {
		
		double U = totLoan;
		int p = years * 12;
		double b = (interest / 100) / 12;
		double E = U * ((b * toPower(1+b, p)) / (toPower(1+b, p) - 1));
		
		this.monthlyPay = round2Dec(E);
	}
	
	//Getters and setters
	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String cust) {
		this.customer = cust;
	}
	
	public float getTotLoan() {
		return totLoan;
	}
	
	public void setTotLoan(float tot) {
		this.totLoan = tot;
	}
	
	public float getInterest() {
		return interest;
	}
	
	public void setInterest(float inter) {
		this.interest = inter;
	}
	
	public int getYears() {
		return years;
	}
	
	public void setYears(int year) {
		this.years = year;
	}
	
	public double getMonthlyPay() {
		return monthlyPay;
	}

}
