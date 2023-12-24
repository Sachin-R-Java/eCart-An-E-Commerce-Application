package com.eCart.guestfunctions;


import java.sql.SQLException; 


import com.eCart.utility.Services;

public class ViewProducts {

	public void ViewAllProducts() {
		System.out.println("-----------------------Available Products------------------------");
		Services.GetAllProducts();
	}

}
