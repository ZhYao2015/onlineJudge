/*
ID:l_coder1
LANG:JAVA
TASK:holstein
*/
//package stage2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class holstein {

	private static int V;
	private static int G;
	private static int []v;
	private static int [][]sc;
	private static int minans=15;
	private static int []rec=new int[15];
	private static int []s=new int[15];
	private static int []p=new int[25];
	
	public static void dfs(int n,int ans){//search by depth
		
		
		boolean flag=true;
		for(int i=0;i<V;i++){
			if(p[i]<v[i]){
				flag=false;
				break;
			}
		}
		if(flag){
			if(ans<minans){
				for(int i=0;i<G;i++){
					rec[i]=s[i];
				}
				minans=ans;
			}
			return;
		}
		
		if(n==G){
			return;
		}
		
		for(s[n]=0;s[n]<=1;s[n]++){
			for(int i=0;i<V;i++){
				p[i]+=sc[n][i]*s[n];
			}
			ans+=s[n];
			dfs(n+1,ans);
			ans-=s[n];
			for(int i=0;i<V;i++){
				p[i]-=sc[n][i]*s[n];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		V=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		v=new int[25];
		StringTokenizer sv=new StringTokenizer(f.readLine());
		for(int i=0;i<V;i++){
			v[i]=Integer.parseInt(sv.nextToken());
		}
		G=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		sc=new int[15][25];
		for(int i=0;i<G;i++){
			StringTokenizer scc=new StringTokenizer(f.readLine());
			for(int j=0;j<V;j++){
				sc[i][j]=Integer.parseInt(scc.nextToken());
			}
		}
		
		dfs(0,0);
		out.print(Integer.toString(minans));
		for(int i=0;i<G;i++){
			
			if(rec[i]==1){
				out.print(" "+Integer.toString(i+1));
			}
			//out.print(" "+Integer.toString(rec[i]));
		}
		out.println();
		
		out.close();
	}

}
