package secondyear;

import java.util.Scanner;
public class prime {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter number:");
		int n=sc.nextInt();

		if(n<=1) {
			System.out.println("not a prime ");
		}
		else {
			int count=0;
		
			for(int i=2;i<=Math.sqrt(n);i++) {
				if(n%i==0) {
					count++;
				}
		}
			if(count==0) {
				System.out.println("prime");
			}
			else {
				System.out.println("not a prime ");
			}
		}
	  sc.close();
	  }

	}
	


