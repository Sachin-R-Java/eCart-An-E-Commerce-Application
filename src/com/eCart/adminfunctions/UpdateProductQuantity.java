package com.eCart.adminfunctions;

import java.util.Scanner;

import com.eCart.utility.Services;

public class UpdateProductQuantity {
	public void UpdateQuantity() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter product Id->");
		int id = scan.nextInt();
		Services service = new Services();
		System.out.println("Available Quantity for product Id-> "+id+" is->");
		service.GetQuantity(id);
		System.out.println("Enter quantity");
		int qty = scan.nextInt();
		service.UpdateQuantity(id, qty);
		System.out.println("Updated Quantity for product Id-> "+id+" is->");
		Services.GetQuantity(id);
		
	}

}
