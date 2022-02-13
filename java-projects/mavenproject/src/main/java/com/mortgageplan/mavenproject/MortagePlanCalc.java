package com.mortgageplan.mavenproject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MortagePlanCalc {
	
	//Method for opening and reading the prospects.txt file and put the contents into an arraylist
	public ArrayList<String> readFile(String path){
		
		ArrayList<String> rows = new ArrayList<String>();
		
		try {
			File inputFile = new File(path);
			FileReader fr = new FileReader(inputFile);
			BufferedReader br = new BufferedReader(fr);
			
			String row;
			
			while((row = br.readLine()) != null) {
				rows.add(row);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	//Method to create mortage objects from the data recieved from the input file, including the name, loan, interest and 
	//how many years they wish to pay of the loan
	public ArrayList<Mortage> createMortageObjects(ArrayList<String> file){
		
		ArrayList<Mortage> mortages = new ArrayList<Mortage>();
		
		String[] headers = file.remove(0).split(",");
		
		for(String row : file) {
			
			String[] entity = row.split("(?!\".*)(,)(?!.*\")");
			
			
			if(entity.length < 4) {
				continue;
			}
			
			String customer = fixCustomerName(entity[0]);
			float totLoan = Float.parseFloat(entity[1]);
			float interest = Float.parseFloat(entity[2]);
			int years = Integer.parseInt(entity[3]);
			
			Mortage mortage = new Mortage(customer, totLoan, interest, years);
			mortages.add(mortage);
		}
		return mortages;
	}
	//Fixes the names, takes away to a desireble format
	public String fixCustomerName(String name) {
		name = name.replace(",", " ");
		name = name.replaceAll("[^a-öA-Ö\\s]", "");
		return name;
	}
	
	public void printOutMortage(Mortage mortage, int i) {
		System.out.println("Prospect " + i + ": " +
                    mortage.getCustomer() + " wants to borrow " +
                    mortage.getTotLoan() + " € for a period of " +
                    mortage.getYears() + " years and pay " +
                    String.format("%.2f", mortage.getMonthlyPay()) + "€ each month");
	}
	
	public MortagePlanCalc(String path) {
		ArrayList<String> file = readFile(path);
		ArrayList<Mortage> mortages = createMortageObjects(file);
		
		System.out.println("****************************************************************************************************");
        for(Mortage mortage : mortages) {
            mortage.calcMonthlyPay();
            System.out.println(mortage.getCustomer() + " wants to borrow " +
                    mortage.getTotLoan() + " € for a period of " +
                    mortage.getYears() + " years and pay " +
                    mortage.getMonthlyPay() + "€ each month");
        }
        System.out.println("****************************************************************************************************");
	}
	
}
