package project1;

import java.util.Scanner;

public class project_one {
	public static void main(String[] args) {
		 int result = 0;
		 for(int i=0;i<6;i++) {
			 Scanner x=new Scanner(System.in);
			 System.out.print("s:");
			 String s=x.nextLine();
			 System.out.print("start:");
			 int start=x.nextInt();
			 System.out.print("end:");
			 int end=x.nextInt();
			 try {
				 result=binaryStringToInt(s,start,end);
				 System.out.println("result: "+result+"\n");
			 }
			 catch(ArithmeticException e) {
				 System.out.println("result: out of bits size of int.\n");
			 }
			 catch(StringIndexOutOfBoundsException e) {
				 System.out.println("result: string index out of bounds.\n");
			 }
			 catch(NumberFormatException e) {
				 System.out.println("result: incorrect binary number format.\n");
			 }
		 }
	}
	public static int binaryStringToInt(String s,int start,int end){
		int result=0;
		if(start<0||end>s.length())throw new StringIndexOutOfBoundsException();
		if(end-start>32)throw new ArithmeticException();
		for(int i=start;i<end;i++) {
			if(!(s.charAt(i)>='0'&&s.charAt(i)<='9'))
				throw new NumberFormatException();
			result*=2;
			result+=s.charAt(i)-'0';
		}
		return result;
	}
}
