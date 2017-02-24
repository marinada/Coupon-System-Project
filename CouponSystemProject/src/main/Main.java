package main;
import java.sql.Date;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import connection.CouponSystem;
import connection.DailyCouponExpirationTask;
import enum_package.CouponType;
import exceptions.DBException;
import exceptions.InvalidLoginException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

import java.util.Scanner;
		/**
		 * 
		 * @author Marina Daich
		 *
		 */
		public class Main {

			public static void main(String[] args) throws InvalidLoginException, DBException, InterruptedException{
			
				Scanner scan = new Scanner(System.in);
				AdminFacade adminFacade = null;
				CustomerFacade customerFacade = null;
				CompanyFacade companyFacade = null;
System.out.println("please insert user name");
String admin_user = scan.nextLine();
System.out.println("please insert user password");
String admin_psswrd = scan.nextLine();
				
				
//	adminFacade =  (AdminFacade)CouponSystem.getInstance().login("Admin", "1234", "Admin");
adminFacade =  (AdminFacade)CouponSystem.getInstance().login(admin_user,admin_psswrd, "Admin");

//Create new company
////////////////////////
System.out.println("please insert company name");
String comp_name = scan.nextLine();
System.out.println("please insert company password");
String comp_password = scan.nextLine();
System.out.println("please insert company email");
String comp_email = scan.nextLine();
Company company = new Company(0,comp_name, comp_password,comp_email, null);
//	Company company1= new Company(0, "TEVA", "teva11", "teteva1@gmail.com", null);			
//	Company company2 = new Company(0, "CELLCOM", "cellcom", "cellcom@gmail.com", null);
//	Company company3 = new Company(0, "GINGER", "ginger1", "ginger1@gmail.com", null);		

adminFacade.createCompany(company);

adminFacade.updateCompany(company);

// adminFacade.removeCompany(company);
System.out.println(adminFacade.getAllCompanies());
////////////////////////////////////////////////
System.out.println("please insert company name");
String company_name = scan.nextLine();
System.out.println("please insert company password");
String company_psswrd = scan.nextLine();
companyFacade = (CompanyFacade)CouponSystem.getInstance().login(company_name, company_psswrd, "Company");
						
////////////////////////////
System.out.println("please insert new coupon name");
String coupon_name = scan.nextLine();
System.out.println("please insert valid  start coupon  date YYYY-MM-DD");
Date start_date =  Date.valueOf(scan.next());
System.out.println("please insert valid  end coupon  date YYYY-MM-DD");
Date end_date =  Date.valueOf(scan.next());
System.out.println("please insert amount of coupons");
int amount =  scan.nextInt();
CouponType coupon_type = CouponType.BEAUTY;
System.out.println(coupon_type);
System.out.println("please insert message about your coupon");
String message = scan.next();
System.out.println("please insert price");
double price = scan.nextDouble();
//image is null for now
Coupon coupon1 = new Coupon(0,coupon_name,start_date
							,end_date,amount,coupon_type,message,price,null);

companyFacade.createCoupon(coupon1);		

System.out.println(companyFacade.getCouponByType(CouponType.BEAUTY));
companyFacade.getAllCoupon();						
System.out.println(companyFacade.getAllCoupon());
////////////////////////////////////////////////////				 	
//								
//Coupon coupon3 = new Coupon(0,"TEVA",Date.valueOf("2017-01-04"),Date.valueOf("2017-02-20"),10,
//									CouponType.HEALTH,"new vitamins on sale",200,null);
//										companyFacade.createCoupon(coupon3);
//
//				Coupon coupon4 = new Coupon(0,"PARTNER",Date.valueOf("2017-02-04"),Date.valueOf("2017-02-20"),10,
//						CouponType.CELULAR,"router for 16X36 ",84,null);
//						companyFacade.createCoupon(coupon4);

						System.out.println(companyFacade.getAllCoupon());
						companyFacade.updateCoupon(coupon1);
//						companyFacade.removeCoupon(coupon1);			
//	
/////////////////////////////////////////////////////////////
System.out.println("please insert customer name");
String customer_name = scan.nextLine();
System.out.println("please insert customer password");
String customer_psswrd = scan.nextLine();
customerFacade = (CustomerFacade)CouponSystem.getInstance().login(customer_name, customer_psswrd, "Customer");

Customer cust = new Customer(0,customer_name,customer_psswrd);
//						Customer cust1 = new Customer(0,"two","two2@gmail.com");
//						Customer cust2 = new Customer(0,"three","three3@gmail.com");
//						Customer cust3 = new Customer(0,"four","four4@gmail.com");
//						Customer cust4 = new Customer(0,"five","five5@gmail.com");

adminFacade.createCustomer(cust);
//						adminFacade.createCustomer(cust1);
//						adminFacade.createCustomer(cust2);
//						adminFacade.createCustomer(cust3);
//						adminFacade.createCustomer(cust4);
//					    adminFacade.removeCustomer(cust1);		
customerFacade.purchaseCoupon(coupon1);
//customerFacade.getAllPurchasedCouponsByType(CouponType.BEAUTY);
//customerFacade.getAllPurchasedCoupons();
//customerFacade.getAllPurchasedCouponsByPrice(1000);
						
						
		
		DailyCouponExpirationTask.startTask();
		
			}
		

	}


