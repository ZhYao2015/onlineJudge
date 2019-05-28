package stage2_4;
/*
ID:l_coder1
LANG:JAVA
TASK:ttwo
*/

//package stage_2_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

//array coordinator is different from the decartes coordinator!!!

public class ttwo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out=new PrintWriter("ttwo.out");
		char [][] mat=new char[10][10];
		
		//not the decartes coordinator
		int di[][]={{-1,0},{0,1},{1,0},{0,-1}};
		int read;
		int i=0;
		
		int posCX=-1,posCY=-1,diC=0,posFX=-1,posFY=-1,diF=0;
		
		while((read=f.read())!=-1){
			if((char)read!='\r'&&(char)read!='\n'){
				if((char)read=='C'){
					posCX=i/10;
					posCY=i%10;
				}else if((char)read=='F'){
					posFX=i/10;
					posFY=i%10;
				}else{
					
				}
				mat[i/10][i%10]=(char)read;
				i++;
			}
		}
		
		//C & F
		int visite[][][][][][]=new int [4][10][10][4][10][10];

		//simulation but how to check if they will never meet?
		//probably a circulation  pos*RX=pos*X, pos*RY=pos*Y,di*R=di*
		int count=0;
		boolean flag=false;
		while(true){
			//simulate
			if(posCX==posFX&&posCY==posFY){
				flag=true;
				break;
			}
			
			
			if(visite[diC][posCX][posCY][diF][posFX][posFY]==1){
				break;
			}
			
			//start the extension,mark the current state as visited
			visite[diC][posCX][posCY][diF][posFX][posFY]=1;
			
			//try move
			int cx=posCX+di[diC][0];
			int cy=posCY+di[diC][1];
			int fx=posFX+di[diF][0];
			int fy=posFY+di[diF][1];
			
			//check if it is able to move
			//cow
			if(cx>=10||cx<0||cy>=10||cy<0){
				//out of bound
				diC=(diC+1)%4;
			}else{
				//in bound
				char cc=mat[cx][cy];
				//but
				if(cc=='*'){
					diC=(diC+1)%4;
				}else{
					posCX=cx;
					posCY=cy;
				}
			}
			
			//farmer move

			if(fx>=10||fx<0||fy>=10||fy<0){
				//out of bound
				diF=(diF+1)%4;
			}else{
				//in bound
				char cf=mat[fx][fy];
				//but
				if(cf=='*'){
					diF=(diF+1)%4;
				}else{
					posFX=fx;
					posFY=fy;
				}
			}
			
			
			
			count++;
		}
		
		if(flag){
			out.println(count);
		}else{
			out.println(0);
		}
		
		out.close();
	}

}
