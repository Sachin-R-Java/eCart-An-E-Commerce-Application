package com.eCart.adminfunctions;

import java.util.Scanner;

import com.eCart.utility.Services;

public class CheckUserHistory {
	public void GetUserHistory() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter username-> ");
		String username = scan.next();
		Services service = new Services();
		service.GetUserHistory(username);
	}

}
