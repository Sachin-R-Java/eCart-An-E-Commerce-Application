package com.eCart.test;

import java.util.Scanner;

import com.eCart.utility.Admin;
import com.eCart.utility.DatabaseConnector;
import com.eCart.utility.Guest;
import com.eCart.utility.User;

public class Test {
	public static void main(String[] args) {
		HomePage();
	}

	public static void isContinue() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to continue enter 'y/Y' for yes and 'n/N for no");
		String choice = scan.next();
		if (choice.charAt(0) == 'y' || choice.charAt(0) == 'Y') {
			main(null);
		} else if (choice.charAt(0) == 'n' || choice.charAt(0) == 'N') {
			System.out.println("Thank You");
		} else {
			System.out.println("please enter correct choice");
			isContinue();
		}
	}

	public static void HomePage() {
		Scanner scan = new Scanner(System.in);
		System.out.println("------Welcome to Console Based eCart Application------");
		System.out.println("------------------------------------------------------");
		System.out.println("1. User \n2. Admin \n3. Guest \n4. Exit");
		System.out.println("Who you are?, Please enter your choice->");
		System.out.println("------------------------------------------------------");

		try {
			int choice = scan.nextInt();
			switch (choice) {

			case 1:
				User u = new User();
				u.userChoices();
				// isContinue();
				break;
			case 2:
				Admin a = new Admin();
				a.AdminChoice();
				a.AdminActions();
				// isContinue();
				break;
			case 3:
				Guest g = new Guest();
				g.GuestChoice();
				// isContinue();
				break;
			case 4:
				System.out.println("Thank You for using the application.");
				// scan.reset();
				// main(null);
				break;

			default:
				System.out.println("enter correct choice");
				// HomePage();
			}
		} catch (Exception e) {
			System.out.println("Please enter correct choice");
			HomePage();
			// main(null);
		}
	}
}
