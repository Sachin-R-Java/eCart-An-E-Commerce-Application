package com.eCart.utility;

import java.util.Scanner;

import com.eCart.test.Test;
import com.eCart.userfunctions.UserLogin;
import com.eCart.userfunctions.ViewProducts;

public class User {
	public static void userChoices() {
		Scanner scan = new Scanner(System.in);
		System.out.println("------------------------------------------------------");
		System.out.println("1. User Registration \n2. User Login \n3. Back");
		System.out.println("Enter your choice->");
		System.out.println("------------------------------------------------------");
		try {
			int choice = scan.nextInt();
			switch (choice) {

			case 1:
				Services s = new Services();
				s.AddUser();
				//isContinueuserChoices();
				break;
			case 2:
				User u = new User();
				u.Login();
				userLogin();
				//isContinueuserChoices();
				break;
			case 3:
				Test t = new Test();
				t.HomePage();
				break;

			default:
				System.out.println("Enter correct choice");
				userChoices();
				//isContinueuserChoices();
			}

		} catch (Exception e) {
			System.out.println("Enter correct choice");
			userChoices();
		}
	}

	static boolean loginStatus;

	public void Login() {
		boolean status = false;
		UserLogin user = new UserLogin();
		status = user.LoginToApplication();
		this.loginStatus = status;

	}

	public static void userLogin() {

		if (loginStatus == true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("------------------------------------------------------");
			System.out.println("1. View Products \n2. Add to Cart \n3. View Cart \n4. Purchase Item \n5. LogOut");
			System.out.println("Enter your choice->");
			System.out.println("------------------------------------------------------");
			try {
				int choice = scan.nextInt();
				switch (choice) {
				case 1:
					viewProduct();
					isContinueuserLogin();
					break;
				case 2:
					addToCart();
					isContinueuserLogin();
					// isContinueUserLogin();
					break;
				case 3:
					viewCart();
					isContinueuserLogin();
					
					break;
				case 4:
					purchaseItem();
					isContinueuserLogin();
					break;
				case 5:
					logOut();
					//userChoices();
					break;
				default:
					System.out.println("Enter Correct Choice");
					userLogin();
				}

			} catch (Exception e) {
				System.out.println("Enter correct choice");
				userLogin();
			}
		} else {
			User u = new User();
			u.Login();
		}

	}

	public static void viewProduct() {
		Scanner scan = new Scanner(System.in);
		ViewProducts view = new ViewProducts();
		view.ViewAllProducts();
		System.out.println("Do you want to add products into cart? Press 'Y/y' for Yes and any key to go Back");
		String option = scan.next();
		if (option.charAt(0) == 'Y' || option.charAt(0) == 'y') {
			addToCart();
		} else {
			userLogin();
		}
	}

	public static void addToCart() {
		Scanner scan = new Scanner(System.in);
		Services service = new Services();
		ViewProducts view = new ViewProducts();
		view.ViewAllProducts();
		Services.AddToCart();
		System.out.println("Do you want to View Cart? Press 'Y/y' for Yes and any key to go Back");
		String option2 = scan.next();
		if (option2.charAt(0) == 'Y' || option2.charAt(0) == 'y') {
			viewCart();
		} else {
			userLogin();
		}
	}

	public static void viewCart() {
		Scanner scan = new Scanner(System.in);
		Services service = new Services();
		int i = service.ViewCart();
		if (i > 0) {
			System.out.println("Do you want to purchase the item? Press 'Y/y' for Yes and any key to go Back");
			String option2 = scan.next();
			if (option2.charAt(0) == 'Y' || option2.charAt(0) == 'y') {
				Services.DisplayBill();
			} else {
				userLogin();
			}
		} else {
			viewProduct();
		}
	}

	public static void purchaseItem() {
		viewCart();
	}

	public static void logOut() {
		loginStatus = false;
		userChoices();
	}

	public static void isContinueuserChoices() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to continue enter 'y/Y' for yes and 'n/N for no");
		String choice = scan.next();
		if (choice.charAt(0) == 'y' || choice.charAt(0) == 'y') {
			userChoices();
		} else if (choice.charAt(0) == 'n' || choice.charAt(0) == 'N') {
			System.out.println("Thank You");
		} else {
			System.out.println("please enter correct choice");
			isContinueuserChoices();
		}
	}
	public static void isContinueuserLogin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to continue enter 'y/Y' for yes and 'n/N for No");
		String choice = scan.next();
		if (choice.charAt(0) == 'y' || choice.charAt(0) == 'y') {
			userLogin();
		} else if (choice.charAt(0) == 'n' || choice.charAt(0) == 'N') {
			System.out.println("Thank You");
		} else {
			System.out.println("please enter correct choice");
			isContinueuserLogin();
		}
	}
}
