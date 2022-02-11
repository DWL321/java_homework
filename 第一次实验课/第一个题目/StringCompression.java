package StringCompression;

import java.util.Scanner;

public class StringCompression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String s= sc.next();
		int a=s.length();
		int i=0;
		int[] c= new int[26];
		while(i<26) {
			c[i]=0;
			i++;
		}
		i=0;
		while(i<a) {
			c[s.charAt(i)-'a']++;
			i++;
		}
		i=0;
		int b=0;
		while(i<26) {
			if(c[i]>0) {
				b++;
				if(c[i]>1)b++;
				if(c[i]>9) {
					b++;
					if(c[i]>99) {
						b++;
						if(c[i]>999) {
							b++;
						}
					}
				}
			}
			i++;
		}
		System.out.print(b + ","+"\"");
		i=0;
		while(i<26) {
			if(c[i]>0) {
				char bb=(char) (i+'a');
				System.out.print(bb);
				if(c[i]>1)System.out.print(c[i]);
			}
			i++;
		}
		System.out.println("\"");
	}

}
