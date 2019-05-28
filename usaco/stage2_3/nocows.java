/*
ID:l_coder1
LANG:JAVA
TASK:nocows
*/

//package stage2_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class nocows {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out=new PrintWriter(new FileWriter("nocows.out"));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		
		//N nodes [3,200)  K height (1,100)
		
		//dp[i][j] total number of distinct such trees with i nodes <=j height
		
		//Vorsicht bei der Indices
		long dp[][]=new long[201][101];
		for(int i=1;i<101;i++){
			dp[1][i]=1;
		}
		//remove the root, two subtrees left
		for(int i=1;2*i+1<=N;i++){
			for(int j=2;j<=K;j++){
				long max=0;
				for(int t=1;t<2*i;t++){
					max+=dp[t][j-1]*dp[2*i-t][j-1];
				}
				dp[2*i+1][j]=max%9901;
			}
		}
		//?????dp[2*i+1][j]=max(dp[p][j-1]*dp[q][j-1]) p+q+1=2*i+1
		
		out.println((dp[N][K]-dp[N][K-1]+9901)%9901);
		out.close();
	}

}
