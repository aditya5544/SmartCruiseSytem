package CMS.CruiseInfo;

import java.util.*;

public class Methods {
	Scanner sc=new Scanner(System.in);
	
	
	void dateCheker()
	{
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
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
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

	}

}
