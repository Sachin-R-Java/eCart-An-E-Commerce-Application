package com.eCart.userfunctions;

import java.util.Scanner;  
import com.eCart.utility.Services;

public class UserLogin {
	public static boolean status=false;
	public static String username=null;
	public boolean LoginToApplication() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Username->");
		String username = scan.next();
		System.out.println("Enter Password");
		String password = scan.next();
		this.username=username;
		Services service = new Services();
		service.Login(username, password);
		boolean status = service.Login(username,password);
		this.status=status;
		this.username=username;
		return status;
	}

}
