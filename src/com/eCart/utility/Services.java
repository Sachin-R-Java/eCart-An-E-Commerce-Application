package com.eCart.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.eCart.adminfunctions.CheckQuantity;
import com.eCart.adminfunctions.Products;
import com.eCart.userfunctions.UserLogin;
import com.eCart.userfunctions.UserRegistration;

public class Services {
	public static DatabaseConnector con = new DatabaseConnector();
	public static PreparedStatement statement;
	public static PreparedStatement statement1;
	public static PreparedStatement statement2;
	public static PreparedStatement statement3;
	public static ResultSet rs;
	public static ResultSet rs2;
	static ResultSet r;

	public static boolean Login(String username, String password) {
		boolean status = false;
		String query = "select * from users where username =? and password=?";
		try {
			statement = con.DBConnection().prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();
			if (rs.next()) {
				status = true;
			} else
				System.out.println("Login failed, Please check username and password");
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return status;
		// System.out.println(status);
		// System.out.println(username);
	}

	// Insert new product into database
	public static void AddProducts() {
		String query = "insert into products values(?,?,?,?,?)";
		try {
			Scanner scan = new Scanner(System.in);
			Products product = new Products();
			System.out.println("---------------Enter product details---------------");
			System.out.println("Enter product id->");
			product.setProductId(scan.nextInt());
			scan.nextLine();
			System.out.println("Enter product name->");
			product.setProductName(scan.nextLine());
			System.out.println("Enter product description->");
			product.setDescription(scan.nextLine());
			System.out.println("Enter price->");
			product.setPrice(scan.nextFloat());
			System.out.println("Enter quantity->");
			product.setQuantity(scan.nextInt());
			statement = con.DBConnection().prepareStatement(query);
			statement.setInt(1, product.getProductId());
			statement.setString(2, product.getProductName());
			statement.setString(3, product.getDescription());
			statement.setFloat(4, product.getPrice());
			statement.setInt(5, product.getQuantity());
			int i = statement.executeUpdate();
			if (i == 1) {
				System.out.println("Product added successfully");
			} else
				System.out.println("Failed to add product, Product Id already present");

		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				con.DBConnection().close();
				statement.close();
			} catch (SQLException e) {
				e.getMessage();
			}

		}
	}

	// Insert new user into database
	public static void AddUser() {
		String query = "insert into users values(?,?,?,?,?,?,?);";
		try {
			UserRegistration reg = new UserRegistration();
			Scanner scan = new Scanner(System.in);
			System.out.println("---------------Enter User Details---------------");
			System.out.println("Enter first name->");
			reg.setFirstName(scan.next());
			System.out.println("Enter last name->");
			reg.setLastName(scan.next());
			System.out.println("Enter Username->");
			reg.setUserName(scan.next());
			System.out.println("Enter Password->");
			reg.setPassword(scan.next());
			System.out.println("Enter City->");
			reg.setCity(scan.next());
			System.out.println("Enter Mail Id->");
			reg.setMailId(scan.next());
			System.out.println("Enter Mobile Number->");
			reg.setMobileNumber(scan.nextLong());
			statement = con.DBConnection().prepareStatement(query);
			statement.setString(1, reg.getFirstName());
			statement.setString(2, reg.getLastName());
			statement.setString(3, reg.getUserName());
			statement.setString(4, reg.getPassword());
			statement.setString(5, reg.getCity());
			statement.setString(6, reg.getMailId());
			statement.setLong(7, reg.getMobileNumber());
			int i = statement.executeUpdate();
			if (i == 1)
				System.out.println("User registered successfully");
			else
				System.out.println("User registration failed");
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			try {
				con.DBConnection().close();
				statement.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}

	public static void GetAllProducts() {
		String query = "select * from products order by productId";
		try {
			statement = con.DBConnection().prepareStatement(query);
			rs = statement.executeQuery();
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------");
			System.out.println("Product ID" + "\t" + "Product Name" + "\t" + "Description" + "\t\t\t\t\t" + "Unit Price"
					+ "\t\t" + "Available Stock");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {

				System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t\t"
						+ rs.getInt(4) + "\t\t\t" + rs.getInt(5));
				/*
				 * System.out.println("Product ID-> " + rs.getInt(1));
				 * System.out.println("Product Name-> " + rs.getString(2));
				 * System.out.println("Product Description-> " + rs.getString(3));
				 * System.out.println("Unit Price-> " + rs.getInt(4));
				 * System.out.println("Available Stock-> " + rs.getInt(5));
				 */
			}
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			try {
				con.DBConnection().close();
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}

	public static void GetQuantity(int ProductId) {
		String query = "select * from products where productId =" + ProductId;
		try {
			statement = con.DBConnection().prepareStatement(query);
			rs = statement.executeQuery();
			if (rs.next()) {
				System.out.println("Available Stock->" + rs.getInt(5));
			} else {
				System.out.println(ProductId + " is not a valid product id, please enter valid product id");
				CheckQuantity check = new CheckQuantity();
				check.ProductQuantity();
			}
		} catch (SQLException e) {
			e.getMessage();
			System.out.println("Product Id not found, Please enter correct product id");
		} finally {
			try {
				con.DBConnection().close();
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.getMessage();
			}

		}
	}

	public void GetUserDetails(String username) {
		String query = "select firstName,lastName,city,mailId,mobileNumber from users where username =?";
		try {
			statement = con.DBConnection().prepareStatement(query);
			statement.setString(1, username);
			rs = statement.executeQuery();

			if (rs.next()) {
				System.out.println(
						"---------------------------------------------------------------------------------------------------------");
				System.out.println("First Name" + "\t" + "Last Name" + "\t" + "City" + "\t\t\t" + "Mail Id" + "\t\t\t\t"
						+ "Mobile Number");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------");
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t"
						+ rs.getString(4) + "\t\t" + rs.getLong(5));
			} else
				System.out.println("User is not registered");
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public static void AddToCart() {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many products you want to add in cart");
		int numberOfProducts = scan.nextInt();
		for (int i = 0; i < numberOfProducts; i++) {
			System.out.println("Enter product id->");
			int id = scan.nextInt();
			System.out.println("Enter quantity->");
			int quantity = scan.nextInt();
			UserLogin user = new UserLogin();
			if (user.status == true) {
				String username = user.username;
				String query = "select productId,productName,price,quantity from products where productId=?";
				String query1 = "insert into cart values(?,?,?,?,?)";
				String query2 = "select quantity from cart where productId=? and userId=?";
				try {
					statement = con.DBConnection().prepareStatement(query);
					statement.setInt(1, id);
					rs = statement.executeQuery();
					PreparedStatement s = con.DBConnection().prepareStatement(query2);
					s.setInt(1, id);
					s.setString(2,username);
					r = s.executeQuery();
					//ResultSet r = s.executeQuery();
					int qty=0;
					while(r.next()) {
						int cartQty = r.getInt(1);
						qty = cartQty+quantity;
					}
					
					if (rs.next()) {
						if (quantity <= rs.getInt(4) && qty<=rs.getInt(4)) {

							statement1 = con.DBConnection().prepareStatement(query1);
							statement1.setString(1, username);
							statement1.setInt(2, rs.getInt(1));
							statement1.setString(3, rs.getString(2));
							statement1.setFloat(4, rs.getFloat(3));
							statement1.setInt(5, quantity);
							int j = statement1.executeUpdate();
							if (j == 1) {
								System.out.println("Product added to cart successfully");
								// System.out.println(quantity);
								// System.out.println(rs.getInt(4));
							} else
								System.out.println("Failed to add product in cart");
						} else {
							System.out.println("Only " + rs.getInt(4) + " quantites available and you are asking "+qty);
						System.out.println("Please check your cart, you might added this product in cart");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else
				System.out.println("User not logged in");
		}
	}

	public static int ViewCart() {
		UserLogin user = new UserLogin();
		int i = 0;
		if (user.status == true) {
			String username = user.username;
			String query = "select productId,productName,price,quantity from cart where userId =?";

			try {
				statement = con.DBConnection().prepareStatement(query);
				statement.setString(1, username);
				rs = statement.executeQuery();
				System.out.println(
						"---------------------------------------------------------------------------------------------------------");
				System.out
						.println("Product Id" + "\t" + "Product Name" + "\t\t" + "Unit Price" + "\t\t\t" + "Quantity");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------");
				// int i = 0;
				while (rs.next()) {
					System.out.println(
							rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getFloat(3) + "\t\t" + rs.getInt(4));
					i++;
				}
				if (i == 0) {
					System.out.println("Cart is Empty");
				}

			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		return i;
	}

	public static void DisplayBill() {
		UserLogin user = new UserLogin();
		if (user.status == true) {
			String username = user.username;
			String query = "select * from cart where userId=?";
			String query1 = "insert into orderHistory values(?,?,?,?,?)";
			String query2 = "delete from cart where userId=?";
			String query3 = "select price,quantity from cart where userId=?";
			String query4 = "select *from cart";
			String query5 = "select productId, quantity from products";
			String query6 = "update products set quantity=? where productId=?";

			try {
				statement = con.DBConnection().prepareStatement(query3);
				statement.setString(1, username);
				rs = statement.executeQuery();
				int i = 0;
				float sum = 0;
				while (rs.next()) {
					float unitPrice = rs.getFloat(1);
					int quantity = rs.getInt(2);
					sum = sum + (unitPrice * quantity);
					i++;
				}
				if (i > 0) {
					statement1 = con.DBConnection().prepareStatement(query);
					statement1.setString(1, username);
					rs2 = statement1.executeQuery();
					int j = 0;
					while (rs2.next()) {
						statement2 = con.DBConnection().prepareStatement(query1);
						statement2.setString(1, rs2.getString(1));
						statement2.setInt(2, rs2.getInt(2));
						statement2.setString(3, rs2.getString(3));
						statement2.setFloat(4, rs2.getFloat(4));
						statement2.setFloat(5, rs2.getFloat(5));
						j = statement2.executeUpdate();
						j++;
					}
					int k = 0;
					if (j > 0) {
						PreparedStatement statement4 = con.DBConnection().prepareStatement(query4);
						ResultSet rs3 = statement4.executeQuery();
						PreparedStatement statement5 = con.DBConnection().prepareStatement(query5);
						ResultSet rs4 = statement5.executeQuery();
						int id;
						int qty;
						while (rs3.next()) {
							id = rs3.getInt(2);
							qty = rs3.getInt(5);
							while (rs4.next()) {
								int pId = rs4.getInt(1);
								int pQty = rs4.getInt(2);
								if (id == pId) {
									int newQty = pQty - qty;
									PreparedStatement statement6 = con.DBConnection().prepareStatement(query6);
									statement6.setInt(1, newQty);
									statement6.setInt(2, pId);
									statement6.executeUpdate();
								}
							}
						}
						statement3 = con.DBConnection().prepareStatement(query2);
						statement3.setString(1, username);
						k = statement3.executeUpdate();
					}
					if (k > 0) {
						System.out.println("Order Placed Successfully");
						System.out.println("Total bill amount-> " + sum);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public static void GetUserHistory(String username) {
		String query = "select productId,productName,unitPrice,quantity from orderHistory where userId =?";
		try {
			statement = con.DBConnection().prepareStatement(query);
			statement.setString(1, username);
			rs = statement.executeQuery();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------");
			System.out.println("Product Id" + "\t" + "Product Name" + "\t\t" + "Unit Price" + "\t\t\t" + "Quantity");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------");
			int i = 0;
			while (rs.next()) {
				System.out.println(
						rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getFloat(3) + "\t\t\t" + rs.getInt(4));
				i++;
			}
			if (i == 0) {
				System.out.println("User History Not Available");
			}

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public static void UpdateQuantity(int productId, int quantity) {
		String query = "update products set quantity=? where productId=?";
		try {
			PreparedStatement statement = con.DBConnection().prepareStatement(query);
			statement.setInt(1, quantity);
			statement.setInt(2, productId);
			int i = statement.executeUpdate();
			if (i > 0) {
				System.out.println("Quantity updated");
			} else {
				System.out.println("Product Id not matching");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}