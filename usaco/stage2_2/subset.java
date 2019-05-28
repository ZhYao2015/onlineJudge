/*
ID:l_coder1
LANG:JAVA
TASK:subset
*/

//package stage2_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class subset {
	
	public static int dp[][];
	
	//dp[i][j] 
	
	public static int calc(int N){
		dp[0][0]=1;
		int S=(1+N)*N/4;
	    for(int i=1;i<=N;i++)
	    	for(int j=1;j<=S;j++)//j>=i
	    		if(j>=i){
	    			dp[i][j]=dp[i-1][j-i]+dp[i-1][j];
	    		}else{
	    			dp[i][j]=dp[j][j];
	    		}
	    
	    return dp[N][S];
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("subset.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int N=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		dp=new int[40][801];
		int total=(1+N)*N/2;
		if(total%2!=0){
			out.println("0");
		}else{
			out.println(Integer.toString(calc(N)));
		}
		out.close();
	}
}
