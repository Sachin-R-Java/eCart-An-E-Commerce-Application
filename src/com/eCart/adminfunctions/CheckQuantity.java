package com.eCart.adminfunctions;

import java.sql.SQLException;
import java.util.Scanner;

import com.eCart.utility.Services;

public class CheckQuantity {
	
	public void ProductQuantity() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Product Id-> ");
		int productId = scan.nextInt();
		Services.GetQuantity(productId);
	}

}
