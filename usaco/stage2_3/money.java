/*
ID:l_coder1
LANG:JAVA
TASK:money
*/

//package stage2_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//Knapsack
public class money {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("money.in"));
		PrintWriter out=new PrintWriter(new FileWriter("money.out"));
		StringTokenizer st=new StringTokenizer(f.readLine());
		
		int V=Integer.parseInt(st.nextToken());
		int N=Integer.parseInt(st.nextToken());

		
		int coin[]=new int [30];
		int count=0;
		String read="";
		while((read=f.readLine())!=null){
			StringTokenizer smn=new StringTokenizer(read);
			while(smn.hasMoreTokens()){
				coin[count]=Integer.parseInt(smn.nextToken());
				count++;
			}
		}
		
		
		//dp i,j  total amount of j constructed by first - i+1 type of coins
		
		//here we use 200000, because the value of element may be really large
		long dp[][]=new long [30][25003];
		
		dp[0][coin[0]]=1;
		
		for(int i=0;i<V;i++){
			dp[i][0]=1;
		}
		//base case must be carefully ensured
		//base case maybe accidentally rewritten
		//problem with indices!!!!!!!
		for(int i=0;i<V;i++){
			for(int j=0;j<=N;j++){
				if(i==0){
					if(j>=coin[i]){
						dp[i][j]=dp[i][j-coin[i]];
					}
				}else{
					if(j>=coin[i]){
						dp[i][j]=dp[i-1][j]+dp[i][j-coin[i]];
					}else{
						dp[i][j]=dp[i-1][j];
					}
				}
			}
		}
		
		out.println(dp[V-1][N]);
		out.close();
	}

}
