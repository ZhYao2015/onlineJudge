/*
ID:l_coder1
LANG:JAVA
TASK:prefix
*/

//package stage2_3;

//try Trie... Not necessary

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class prefix {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out=new PrintWriter(new FileWriter("prefix.out"));
		
		String elist[]=new String[200];
		
		
		String line;
		int elen=0;
		//Here we should construct a trie
		while(!(line=f.readLine()).startsWith(".")){
			StringTokenizer st=new StringTokenizer(line);
			while(st.hasMoreTokens()){
				elist[elen]=st.nextToken();
				elen++;
			}
		}
		
		String str="";
		while((line=f.readLine())!=null){
			str=str.concat(line);
		}
		

		//dp array
		int N=str.length();
		int dp[]=new int[200001];
		//int visited[][]=new int [N][10];
		
		//dp[k]: the longest prefix starting from index k 
		dp[N]=0;
		
		//The really important part is string comparison
		for(int i=N-1;i>=0;i--){
			int max=dp[i];
			boolean flag=false;
			int j=0;
			while(j<elen){
				String word=elist[j];
				int len=word.length();
				boolean test=true;
				for(int k=0;k<len;k++){
					if(i+k>=N){
						test=false;
						break;
					}else{
						if(str.charAt(i+k)!=word.charAt(k)){
							test=false;
						}
					}
				}
				
				if(test){
					flag=true;
					int temp=len+dp[i+len];
					if(temp>max){
						max=temp;
					}
				}
				j++;
			}
			if(!flag){
				dp[i]=0;
			}
			dp[i]=max;
		}
		
		out.println(dp[0]);
		
		//dp[i]: the size of the longest prefix which can be constructed by S1,...,Si

		out.close();
	}

}
