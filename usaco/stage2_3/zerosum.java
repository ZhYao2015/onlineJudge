/*
ID:l_coder1
LANG:JAVA
TASK:zerosum
*/

//package stage2_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class zerosum {

	static int N=0;
	static String result="1";
	static PrintWriter out;
	static List<String> arr=new ArrayList<String>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("zerosum.in"));
		out=new PrintWriter(new FileWriter("zerosum.out"));
		
		N=Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		
		//we still need dp I think
		//probably not...
		dfs(0);
		Collections.sort(arr);
		for(String s:arr){
			out.println(s);
		}
		//out.println(eval("1-2+3+4-5+6-7"));
		//System.out.println(eval("1-2+3-4+56-78+9"));
		
		//DFS?
		
		//PLEASE DONT FORGET AGAIN
		out.close();
		
	}
	
	public static int eval(String str){
		str=str.replaceAll(" ", "");
		int sum=0;
		char opr='+';
		int i=0;
		while(i<str.length()){
			if(str.charAt(i)<='9'&&str.charAt(i)>='1'){
				int num=0;
				
				while(i<str.length()&&str.charAt(i)<='9'&&str.charAt(i)>='1'){
					num=num*10+str.charAt(i)-'0';
					i++;
				}
				
				if(opr=='+'){
					sum+=num;
				}else{
					sum-=num;
				}
			}else{
				opr=str.charAt(i);
				i++;
			}
				
		}
		return sum;
	}
	
	//the s-th opr, index-s: 2*s-1, N total digits

	public static void dfs(int s){
		dfs(1,'+');
		dfs(1,'-');
		dfs(1,' ');
	}
	
	public static void dfs(int s,char c){
		String pre_result=new String(result);
		result+=Character.toString(c)+Integer.toString(s+1);
		if(s==N-1){
			
			
			if(eval(result)==0){
				arr.add(result);
			}

		}else{
			
			dfs(s+1,'+');
			dfs(s+1,'-');
			dfs(s+1,' ');
		}
		//restoration of dfs variables
		result=pre_result;
	}

	

}
