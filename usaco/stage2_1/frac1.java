/*
 ID:l_coder1
 LANG:JAVA
 TASK:frac1
*/
//package stage2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class frac1 {
	
	private static PrintWriter out;
	private static int N;
	//Stern-Brocot Tree and Farey sequence
	public static void printSternTree(int m,int n,int mp,int np){
		//pre-order
		if(n+np<=N){
			printSternTree(m,n,m+mp,n+np);
			out.println(Integer.toString(m+mp)+'/'+Integer.toString(n+np));
			printSternTree(m+mp,n+np,mp,np);
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("frac1.in"));
		out=new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		N=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		out.println("0/1");
		printSternTree(0,1,1,1);
		out.println("1/1");
		out.close();
	}

}
