package CMS.CruiseInfo;

//Importing Libraries
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class App extends Methods //Inheritance
{
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException 
	{
		Scanner sc = new Scanner(System.in);
	try { //try start
			int EventChoice;
			char choice;
			int totalcount = 0;
			int count = 0;
			int readFirst = 0; // Count for read data first


			//
			List<Customer> aList = new ArrayList<Customer>();
			List<Booke> aNew = new ArrayList<Booke>();

			System.out.println("[Smart Cruise Management System]");
			System.out.println("---------- MAIN MENU ----------");
			// DoWhile
			do {
				// Show Main Menu options to user
				System.out.println(
						"[1] Read Customer data\n[2] Display All Customers\n[3] Search for Customer by name\n[4] Search for Customer by Id\n[5] Allow Customer to book cruise\n[6] View Booked Customer\n[7] Save Customers to file\n[0] Exit");
				System.out.println();
				System.out.println("Enter Your Choice");// Take input from user
				EventChoice = sc.nextInt();
				String fname=" ";
				// Switch Start
				switch (EventChoice) {
				case 1:
					try {
						int countRecord = 0;
						int total = 0;
						System.out.println("Enter File Name");
						fname=sc.next();
							String path = ("src/main/resources/" + fname + ".csv");
							CSVReader reader = new CSVReader(new FileReader(path));
							String[] temp;
							while ((temp = reader.readNext()) != null) {
								total++;
								//Email Validation
								if ((temp[2].endsWith("gmail.com") || temp[2].endsWith(".in"))
										|| temp[2].endsWith("yahoo.com") & temp[2].contains("@")) {
									Customer user = new Customer();
									user.setId(temp[0]);
									user.setName(temp[1]);
									user.setMail(temp[2]);
									aList.add(user);
									countRecord++;
								}
							}
							System.out.println("Number of record read : " + countRecord);
							int balCount = total - countRecord;
							System.out.println("Number of records not added due to invalid Email: " + balCount);
						
						} catch (Exception e) {
						System.out.println(fname + " Not Found!! Enter Valid name.");
					}
					readFirst++;
					break; // case 1 End

				case 2:
					if (readFirst != 0) {
						System.out.println("Your Appointments Are as follow");
						System.out.println("Cust_Id" + "\t" + "Cust_Name");
						for (Customer c : aList) {
							System.out.println(c.id + "\t" + c.name);
						}
					} else {
						System.out.println("Read Customer First(Option [1])");
					}

					break; // case 2 End

				case 3://Search by Name and Name Validation
					if (readFirst != 0) {
						System.out.println("Enter Name for Search Records ");
						String customer_name = sc.next();
						for (Customer c : aList) {
							if (c.getName().contains(customer_name)) {
								System.out.println("Cust_Id" + "\t" + "Cust_Name");
								System.out.println(c.id + "\t" + c.name);
								break;
							}
							// Validation for not equal name
							else {
								System.out.println("Name is Not matching! Please try later");
								break;
							}
						}
					} else {
						System.out.println("Read Customer First(Option [1])");
					}
					break; // case 3 End

				case 4://Search by Id
					if (readFirst != 0) 
					{
						System.out.println("Enter id for Search Records ");
						String customer_id = sc.next();
						int counter=0;
						System.out.println("Cust_Id" + "\t" + "Cust_Name");
							for (Customer c : aList) 
							{
								if(c.getId().contains(customer_id) & counter==0 )
								{
									System.out.println(c.getId()+"\t"+c.getName());
									counter++;
									break;
								}
								
							}
					}
					else 
					{
						System.out.println("Read Customer First(Option [1])");
					}
					break; // case 4 End

				case 5://booking and Date validation
					if (readFirst != 0) {
						Booke b = new Booke();
						System.out.println("Enter Customer name: ");
						String newCustomerName = sc.next();
						for (Customer c : aList) {
							if (c.getName().equals(newCustomerName)) {
								System.out.println("Enter Date in DD/MM/YYYY format");
								System.out.println("Enter Year");
								int year = sc.nextInt();
								while (year < 1000 || year > 2022) {
									System.out.println("Enter correct year");
									year = sc.nextInt();
								}
								System.out.println("Enter Month");
								int month = sc.nextInt();
								while (month > 12 || month <= 0) {
									System.out.println("Enter correct month");
									month = sc.nextInt();
								}
								System.out.println("Enter Day");
								int day = sc.nextInt();
								if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
										|| month == 12) {
									while (day <= 0 || day > 31) {
										System.out.println("Enter correct Day");
										day = sc.nextInt();
									}
								} else if (month == 2) {
									if (year % 4 == 0) {
										while (day <= 0 || day > 29) {
											System.out.println("Enter correct Day");
											day = sc.nextInt();
										}
									} else {
										while (day <= 0 || day > 28) {
											System.out.println("Enter correct Day");
											day = sc.nextInt();
										}
									}

								}
								String datefile = (day + "/" + month + "/" + year);
								b.setNewid(c.getId());
								b.setNewname(c.getName());
								b.setNewmail(c.getMail());
								b.setNewdate(datefile);
								aNew.add(b);
							}
						}
						System.out.println("Booking of " + newCustomerName + " Confirmed!!");
						System.out.println();
						System.out.println("Email Structure of :- ");
						System.out.println("Eamil to:- " + newCustomerName);
						System.out.println("Booking Date:- " + b.getNewdate());
					} else {
						System.out.println("Read Customer First(Option [1])");
					}
					break;

				case 6://Search by date  & date Validation
					Methods m = new Methods();
					if (readFirst != 0) {
						System.out.println("Enter Date in DD/MM/YYYY format");
						System.out.println("Enter Year");
						int year = sc.nextInt();
						while (year < 1000 || year > 2022) {
							System.out.println("Enter correct year");
							year = sc.nextInt();
						}
						System.out.println("Enter Month");
						int month = sc.nextInt();
						while (month > 12 || month <= 0) {
							System.out.println("Enter correct month");
							month = sc.nextInt();
						}
						System.out.println("Enter Day");
						int day = sc.nextInt();
						if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
								|| month == 12) {
							while (day <= 0 || day > 31) {
								System.out.println("Enter correct Day");
								day = sc.nextInt();
							}
						} else if (month == 2) {
							if (year % 4 == 0) {
								while (day <= 0 || day > 29) {
									System.out.println("Enter correct Day");
									day = sc.nextInt();
								}
							} else {
								while (day <= 0 || day > 28) {
									System.out.println("Enter correct Day");
									day = sc.nextInt();
								}
							}

						}
						String datefile = (day + "/" + month + "/" + year);
						int attendees = 0;
						System.out.println("Cust_Id" + "\t" + "Cust_Name");
						for (Booke c1 : aNew) {
							if (c1.getNewdate().equals(datefile)) {
								System.out.println(c1.getNewid() + "\t" + c1.getNewname() + "\t" + c1.getNewdate());
								attendees++;
							}
						}
						System.out.println("Number of attendees " + attendees);
					} else {
						System.out.println("Read Customer First(Option [1])");
					}
					break;

				case 7: //Search by date and date validations
					System.out.println("Enter Date in DD/MM/YYYY format");
					System.out.println("Enter Year");
					int year = sc.nextInt();
					while (year < 1000 || year > 2022) {
						System.out.println("Enter correct year");
						year = sc.nextInt();
					}
					System.out.println("Enter Month");
					int month = sc.nextInt();
					while (month > 12 || month <= 0) {
						System.out.println("Enter correct month");
						month = sc.nextInt();
					}
					System.out.println("Enter Day");
					int day = sc.nextInt();
					if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) {
						while (day <= 0 || day > 31) {
							System.out.println("Enter correct Day");
							day = sc.nextInt();
						}
					} else if (month == 2) {
						if (year % 4 == 0) {
							while (day <= 0 || day > 29) {
								System.out.println("Enter correct Day");
								day = sc.nextInt();
							}
						} else {
							while (day <= 0 || day > 28) {
								System.out.println("Enter correct Day");
								day = sc.nextInt();
							}
						}
					}
					String datefile = (day + "/" + month + "/" + year);
					List<Booke> save = new ArrayList<Booke>();
					for (Booke list : aNew) {
						if (list.getNewdate().equals(datefile)) {
							save.add(list);
						}
					}
					String path = "src/main/resources/" + day + month + year + ".csv";
					File file = new File(path);
					file.createNewFile();
					FileWriter fio = new FileWriter(file);
					fio.append("Cust_Id,Cust_name,Booking_Data\n");
					for (Booke cust : save) {
						fio.append(cust.getNewid() + "," + cust.getNewname() + "," + cust.getNewdate());
					}
					fio.close();
					break;

				case 0:
					System.out.println("Thank You!!Have a Good Day");
					System.exit(EventChoice);
					break;
				}// Switch End

				// For While
				System.out.println();
			} while (EventChoice>0);// DoWhile End

	} catch (Exception e)// InputMismatchException
	{
		System.out.println("Input Mismatch! Please Put right Input");
		System.out.println("Please Enter Option again"); //After Mismatch Exception to continue the program
	}//try catch End
	
  }
	
}
