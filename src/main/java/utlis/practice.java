package utlis;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class practice {
//	public static void main(String[]args) {
//		String str = "java java is the best language";
//		Integer count =1;
//		Map<String,Integer> map = new HashMap<String,Integer>();
//		String[] arr = str.split(" ");
//		for(int i =0;i<arr.length;i++) {
//			if(!map.containsKey(arr[i])) {
//				map.put(arr[i], count);
//			}else {
//				map.put(arr[i], map.get(arr[i])+1);
//			}	
//		}
//		
//	for(String x:map.keySet()) {
//		System.out.println("the count of word:"+x+" = "+map.get(x));
//	}
//	public static void main(String[]args) {
//		String str1="stop";
//		String str2="topq";
//		char[] arr1= str1.toLowerCase().toCharArray();
//		char[] arr2 = str2.toLowerCase().toCharArray();
//		Arrays.sort(arr1);
//		Arrays.sort(arr2);
//		if(Arrays.equals(arr1, arr2)) {
//			System.out.println("String is anagram");
//		}else {
//			System.out.println("String is not anagram");
//		}
//	public static void main(String[]args) {
//		String str ="Java Java is the best";
//		Integer count = 1;
//		Map<String,Integer> map = new HashMap<String, Integer>();
//	public static void main(String[]args) {
//		String str = "Saurav";
//		int len = str.length();
//		String result =" ";
//		for(int i = len-1;i>=0;i--) {
//			result = result+str.charAt(i);
//		}
//		System.out.println(result);
//	public static void main(String[]args) {
//		String name ="saurav";
//		int len = name.length();
//		String rev="";
//		for(int i = len-1;i>=0;i--) {
//			rev=rev+name.charAt(i);
//		}
//		System.out.println(rev);
//	public static void main(String[]args) {
//		int num =121;
//		int temp = num;
//		while(temp>0) {
//			int rev=temp%10;
//			temp=temp/10;
//			System.out.println(rev);
//			
//		}
//	public static void main(String[]args) {
//		int num=121;
//		int tem=num;
//		int rev=0,rem;
//		while(tem>0) {
//		     rem=tem%10;
//		     rev=rev*10+rem;
//			tem=tem/10;
////			System.out.println(rev);
//		}
//		if(num==rev) {
//			System.out.println("number is pallindrome");
//		}else {
//			System.out.println("Number is not pallindrome");
//		}
//		public static void main(String[]args) {
//			int num =1222;
//			int rev=0,rem;
//			int temp=num;
//			while(temp!=0) {
//				rem = temp%10;
//				rev=rev*10+rem;
//				temp= temp/10;
//			}
//			if(num==rev) {
//				System.out.println("Number is pallindrome");
//			}else {
//				System.out.println("Number is not pallindrome");
//			}
//	public static void main(String[]args) {
//		int num =234;
//		int temp = num;
//		while(temp!=0) {
//			int rev = temp%10;
//			temp=temp/10;
//			System.out.println(rev);
//		}
//	public static void main(String[]args) {
//		String name ="saurav";
//		String rev="";
//		for(int i = name.length()-1;i>=0;i--) {
//			rev =rev+name.charAt(i);
//		}
//		System.out.println(rev);
//		if(name.equals(rev)) {
//			System.out.println("Pallindrome String");
//		}else {
//			System.out.println("Not a pallindrome number");
//		}
//	public static void main(String[]args) {
//		String name = "Java";
//		String rev="";
//		for(int i=name.length()-1;i>=0;i--) {
//			rev=rev+name.charAt(i);
//		}
//		System.out.println(rev);
//		if(name.equals(rev)) {
//			System.out.println("Pallindrome String");
//		}else {
//			System.out.println("Not a pallindrome String");
//		}
//	public static void main(String[]args) {
//		int num=121;
//		int rev=0,rem;
//		int temp=num;
//		while(temp>0) {
//			rem=temp%10;
//			rev=rev*10+rem;
//			temp=temp/10;
//			}
//		if(num==rev) {
//			System.out.println("number is pallidrome");
//		}else {
//			System.out.println("Not a pallindrome number");
//		}
//	public static void main(String[]args) {
//		int num=5;
//		int fact=1;
//		for(int i=1;i<=num;i++) {
//			fact=fact*i;
//		}
//		System.out.println(fact);
//	public static void main(String[]args) {
//		int num =5;
//		int fact=1;
//		for(int i=1;i<=num;i++) {
//			fact=fact*i;
//		}
//		System.out.println(fact);
	
//	public static int test(int a[]) {
//		int max =0;
//		for(int i=0;i<a.length;i++) {
//			if(a[i]>max) {
//				max=a[i];
//				
//			}
//		}
//		return max;
//	}
//	
//	public static void main(String[]args) {
//	String s = "Swiss";
//		System.out.println(test(new int[] {2,1,0,5,3}));
//	public static void main(String[]args) {
//		Scanner scn = new Scanner(System.in);
//		int n = scn.nextInt();
//		for(int i =0;i<n;i++) {
//			int count =0;
//			int t= scn.nextInt();
//			for(int div=2;div*div<=t;div++) {
//				if(t%div==0) {
//					count++;
//				}
//			}if(count==0) {
//				System.out.println("Prime number");
//			}else {
//				System.out.println("Not prime");
//			}
//		}
//	public static void main(String[]args) {
//		Scanner scn = new Scanner(System.in);
//		int n = scn.nextInt();
//		for(int i =0;i<n;i++) {
//			int t = scn.nextInt();
//			int count =0;
//			for(int div=2;div*div<=t;div++) {
//				if(t%div==0) {
//					count++;
//				}
//			}if(count==0) {
//				System.out.println("Prime Number");
//			}else {
//				System.out.println("Not Prime");
//			}
//		}
//	public static void main(String[]args) {
//		Scanner scn = new Scanner(System.in);
//		int low = scn.nextInt();
//		int high = scn.nextInt();
//		for(int i = low;i<=high;i++) {
//			int count =0;
//			for(int div=i;div*div<=i;div++)
//		}
//	public static void main(String[]args) {
//		int []A= {1,2,3,4,5};
//		int n =5;
//		for(int i =0;i<n;i++) {
//	public static void main(String[]args) {
//		System.out.println("Hello World");
//		Scanner scn = new Scanner(System.in);
//		int n = scn.nextInt();
//		for(int i =0;i<=n;i++) {
//			int t = scn.nextInt();
//			int count =0;
//			for(int div=2;div*div<=t;div++) {
//				if(t%div==0) {
//					count++;
//				}
//			}if(count==0) {
//				System.out.println("Prime Number");
//			}else {
//				System.out.println("Not Prime");
//			}
//			
//		}
//	public static void main(String[]args) {
//		Scanner scn = new Scanner(System.in);
//		int n = scn.nextInt();
//		for(int i =0;i<=n;i++) {
//			
//		}
//	public static void main(String[]args) {
//		int a=0;
//		int c=0;
//		for(int i =1;i<=9;i++) {
//			c = a+ i;
//			a=c;
//		}
//		System.out.println(c);
//		int n = 123456789;
//		Numsum(n);
//		
//		}
//		
//	public static void Numsum(int n) {
//		if(n/10==0) {
//			System.out.println(n);
//		}
//		
//		int temp=0;
//		temp= n%10+Numsum(n/10);
//		
//		System.out.println(temp);
//	}
//	public static void main(String[]args) {
//		int num =123456789;
//		int sum=0;
//		while(num>0) {
//			int digit =num%10;    
//			sum +=digit;
//			num=num/10;
//		}
//		System.out.println(sum);
//	public static void main(String[]args) {
//		int num =123456789;
//		int sum =0;
//		while(num>0) {
//			int digit = num%10;
//			sum += digit;
//			num = num/10;
//		}
//		System.out.println(sum);
//	public static void main(String[]args) {
//		Scanner scn = new Scanner(System.in);
//		int n = scn.nextInt();
//		for(int i =0;i<=n;i++) {
//			int t = scn.nextInt();
//			int count =0;
//			for(int div=2;div*div<=t;div++) {
//				if(t%div==0) {
//					count++;
//					break;
//				}
//			}if(count==0) {
//				System.out.println("Prime Number");
//			}else {
//				System.out.println("Not prime number");
//			}
//		}
//	public static void main(String[]args) {
//	
//	String[][] data = {
//            {"saurav", "10"},
//            {"aman", "20"},
//            {"nikhil", "30"},
//            {"sanchit", "40"}
//        };
//
//        int sum = 0;
//        int count = data.length;
//
//        // Loop through each row to extract and add the numbers
//        for (int i = 0; i < count; i++) {
//            // Convert the string number to integer
//            int number = Integer.parseInt(data[i][1]);
//            sum += number;
//        }
//
//        // Calculate average
//        double average = (double) sum / count;
//
//        // Print result
//        System.out.println("Average: " + average);
//	public static void main(String[]args) {
//		List<List<String>> data = new ArrayList<>();
	public static void main(String[]args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		for(int i =0;i<=n;i++) {
			int t = scn.nextInt();
			for(int div=2;div*div<=t;div++) {
				
			}
		}
		
		
		
		
		
	
	
	
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
