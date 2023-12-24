package com.eCart.utility;

import java.util.Scanner;

import com.eCart.userfunctions.ViewProducts;

public class Guest {
	public static void GuestChoice() {
		Scanner scan = new Scanner(System.in);
		System.out.println("------------------------------------------------------");
		System.out.println("1. View Products \n2. Back");
		System.out.println("------------------------------------------------------");
		System.out.println("Enter your name->");
		String name = scan.next();
		System.out.println("Hi " + name + " Enter your choice->");
		try {
			int GuestChoiceSelection = scan.nextInt();
			switch (GuestChoiceSelection) {
			case 1:
				ViewProducts view = new ViewProducts();
				view.ViewAllProducts();
				// choices.HomePage();
				isContinueGuest();
				break;
			case 2:
				Test t = new Test();
				t.HomePage();
				break;
			default:
				System.out.println("Enter correct choice");
				isContinueGuest();
			}
		} catch (Exception e) {
			System.out.println("Enter correct choice");
			GuestChoice();
		}
	}

	public static void isContinueGuest() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to continue enter 'y/Y' for yes and 'n/N for no");
		String choice = scan.next();
		if (choice.charAt(0) == 'y' || choice.charAt(0) == 'Y') {
			GuestChoice();
		} else if (choice.charAt(0) == 'n' || choice.charAt(0) == 'N') {
			System.out.println("Thank You");
		} else {
			System.out.println("Please enter correct choice");
			isContinueGuest();
		}
	}
}
