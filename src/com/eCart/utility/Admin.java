package com.eCart.utility;

import java.util.Scanner;

import com.eCart.adminfunctions.CheckQuantity;
import com.eCart.adminfunctions.CheckUserDetails;
import com.eCart.adminfunctions.CheckUserHistory;
import com.eCart.adminfunctions.UpdateProductQuantity;

public class Admin {
	public static boolean AdminLoginStatus = false;

	public static void AdminChoice() {
		// boolean status=false;
			Scanner scan = new Scanner(System.in);
			System.out.println("Admin username-> Admin");
			System.out.println("Enter admin password->");
			String password = scan.next();
			if (password.equalsIgnoreCase("admin")) {
				AdminLoginStatus = true;
		
		}
	}

	public static void AdminActions() {
		// boolean status = AdminChoice();
		Scanner scan = new Scanner(System.in);
		if (AdminLoginStatus == true) {
			System.out.println("------------------------------------------------------");
			System.out.println(
					"1. Add New Products \n2. Check Quantity \n3. Check Registered User \n4. Check User History \n5. Update Quantity \n6. Logout");
			System.out.println("------------------------------------------------------");
			System.out.println("Enter your choice->");
			try {
			int AdminChoiceSelection = scan.nextInt();
			switch (AdminChoiceSelection) {

			case 1:
				System.out.println("How many products you want to add in product list->");
				int i = scan.nextInt();
				
				for (int j = 0; j < i; j++) {
					Services s = new Services();
					s.AddProducts();
				}
				AdminActions();
				isContinueAdmin();
				break;
			case 2:
				CheckQuantity quantity = new CheckQuantity();
				quantity.ProductQuantity();
				AdminActions();
				isContinueAdmin();
				break;
			case 3:
				CheckUserDetails user = new CheckUserDetails();
				user.GetUserDetails();
				AdminActions();
				isContinueAdmin();
				break;
			case 4:
				CheckUserHistory history = new CheckUserHistory();
				history.GetUserHistory();
				AdminActions();
				isContinueAdmin();
				break;
			case 5 :
				UpdateProductQuantity update = new UpdateProductQuantity();
				update.UpdateQuantity();
				AdminActions();
				isContinueAdmin();
				break;
			case 6:
				AdminLoginStatus = false;
				System.out.println("Thanks for using application");
				System.out.println("------------------------------------------------------");
				Test.HomePage();
				break;
			default:
				System.out.println("Enter correct choice");
				isContinueAdmin();
			}
		} catch (Exception e) {
			System.out.println("Enter correct choice");
			AdminActions();
		}

		} else {
			System.out.println("Wrong password,Login failed");
			AdminChoice();
		}
	}
	public static void isContinueAdmin() { 
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to continue enter 'y/Y' for yes and 'n/N for no");
		String choice = scan.next();
		if(choice.charAt(0)=='y' || choice.charAt(0)=='Y') {
			AdminActions();
		}else if(choice.charAt(0)=='n' || choice.charAt(0)=='N') {
			System.out.println("Thank You");
		}else {
			System.out.println("Please enter correct choice");
			isContinueAdmin();
		}
	}

}
