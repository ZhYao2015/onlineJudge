/*
ID:l_coder1
LANG:JAVA
TASK:hamming
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hamming {

	public static int set[];
	public static int N;
	public static int B;
	public static int D;
	public static PrintWriter out;
	public static boolean flag=false;
	
	public static int calc_dist(int x1,int x2){
		int d=x1^x2;
		int r=0;
		int count=0;
		while(d!=0){
			r=d%2;
			d=d/2;
			if(r==1){
				count++;
			}
		}
		return count;
	}

	public static void dfs(int n,int cc,int exp){
		if(cc==0){
			set[cc]=n;
		}else if(cc==N){
			for(int i=0;i<cc;i++){
				out.print(Integer.toString(set[i]));
				if(((i+1)%10==0&&i!=0)||i==cc-1){
					out.println();
				}else if(i!=cc-1){
					out.print(" ");
				}else{
					
				}
				flag=true;
			}
			return;
		}else{
			for(int i=0;i<cc;i++){
				if(calc_dist(set[i],n)<D){
					return;
				}
			}
			set[cc]=n;
		}
		for(int i=n+1;i<=Math.pow(2,exp+1)-1;i++){
			if(flag){
				break;
			}else{
				dfs(i,cc+1,exp);
			}
		}
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("hamming.in"));
		out=new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		N=Integer.parseInt(st.nextToken());
		B=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		set=new int[64];
		dfs(0,0,B);
		out.close();
	}
}