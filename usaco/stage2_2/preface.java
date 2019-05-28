/*
ID:l_coder1
LANG:JAVA
TASK:preface
*/
//package stage2_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class preface {
	
	public static final String b[]={"I","V","X","L","C","D","M"};
	public static final String[][] encode={{"","M","MM","MMM"},
			{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
			{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
			{"","I","II","III","IV","V","VI","VII","VIII","IX"}};
	
	public static int[] calc_base(int dec){
		int base[]=new int[4];
		int i=0;
		while(dec!=0){
			base[i]=(int) (dec/(Math.pow(10, 3-i)));
			dec=(int) (dec%(Math.pow(10, 3-i)));
			i++;
		}
		return base;
	}
	
	public static String toRoma(int dec){
		String str="";
		int base[]=calc_base(dec);
		for(int i=0;i<4;i++){
			str+=encode[i][base[i]];
		}
		return  str;
	}
	
	public static int[] count_base(int dec){
		String result=toRoma(dec);
		int c[]=new int[7];
		for(int i=0;i<result.length();i++){
			char p=result.charAt(i);
			if(p=='I'){
				c[0]++;
			}else if(p=='V'){
				c[1]++;
			}else if(p=='X'){
				c[2]++;
			}else if(p=='L'){
				c[3]++;
			}else if(p=='C'){
				c[4]++;
			}else if(p=='D'){
				c[5]++;
			}else if(p=='M'){
				c[6]++;
			}else{
			}
		}
		return c;
	}

	
	public static void main(String args[]) throws IOException{
		BufferedReader f=new BufferedReader(new FileReader("preface.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		StringTokenizer st=new StringTokenizer(f.readLine());
		int N=Integer.parseInt(st.nextToken());
		int  []prev=new int[7];
		for(int i=1;i<=N;i++){
			int []result=count_base(i);
			for(int j=0;j<7;j++){
				prev[j]+=result[j];
			}
		}
		
		for(int i=0;i<7;i++){
			if(prev[i]!=0){
				out.println(b[i]+" "+Integer.toString(prev[i]));
			}
		}
		out.close();
		
	}
}
