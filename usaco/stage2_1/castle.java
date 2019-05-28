/*
ID:l_coder1
LANG:JAVA
TASK:castle
*/

//package stage2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class castle {
	final static int MAX=51;
	private static int [][]maze=new int[MAX][MAX];
	private static int M;
	private static int N;
	private static int [][]component=new int[MAX][MAX];
	private static int direct[][] = {{0, -1},{-1, 0},{0, 1},{1, 0}};
	private static int [][][]hasWall=new int[MAX][MAX][4];
	private static int num_components;
	private static int max_room=0;
	private static int csize[];
	private static int bi,bj,bSize=0;
	private static String bChar;
	
	public static void getWall(int x,int y){
		int wall=maze[x][y];
		if(wall>=8){
			hasWall[x][y][3]=1;
			wall-=8;
		}
		if(wall>=4){
			hasWall[x][y][2]=1;
			wall-=4;
		}
		if(wall>=2){
			hasWall[x][y][1]=1;
			wall-=2;
		}
		if(wall>=1){
			hasWall[x][y][0]=1;
		}
	}
	
	public static int coloring(int new_component){
		int num_visited=0;
		boolean flag;
		do{
			flag=false;
			for(int i=0;i<N;i++){
				for(int j=0;j<M;j++){
					if(component[i][j]==-2){
						num_visited+=1;
						flag=true;
						component[i][j]=new_component;
						getWall(i,j);
						for(int k=0;k<4;k++){
							if(hasWall[i][j][k]==0){
								if(component[i+direct[k][0]][j+direct[k][1]]==0){
									component[i+direct[k][0]][j+direct[k][1]]=-2;
								}
							}
						}
					}
				}
			}
		}while(flag);
		
		return num_visited;
	}
	
	public static void classify(){
		num_components=0;
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(component[i][j]==0){
					num_components+=1;
					component[i][j]=-2;
					int n=coloring(num_components);
					csize[num_components]=n;
					if(n>max_room){
						max_room=n;
					}
				}
			}
		}
	}
	
	public static void break_the_wall(){
		for(int j=0;j<M;j++){
			for(int i=N-1;i>=0;i--){//out of bounds	
				if(i>=1&&component[i][j]!=component[i-1][j]){
					int col1=component[i][j];
					int col2=component[i-1][j];
					if(csize[col1]+csize[col2]>bSize){
						bi=i;
						bj=j;
						bChar="N";
						bSize=csize[col1]+csize[col2];
					}
				}
				
				if(component[i][j]!=component[i][j+1]&&j<M-1){
					int col1=component[i][j];
					int col2=component[i][j+1];
					if(csize[col1]+csize[col2]>bSize){
						bi=i;
						bj=j;
						bChar="E";
						bSize=csize[col1]+csize[col2];
					}
				}
				
				
				
				
				
			}
		}
	}
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader f=new BufferedReader(new FileReader("castle.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer s=new StringTokenizer(f.readLine());
		M=Integer.parseInt(s.nextToken());
		N=Integer.parseInt(s.nextToken());
		for(int i=0;i<N;i++){
			StringTokenizer sline=new StringTokenizer(f.readLine());
			for(int j=0;j<M;j++){
				maze[i][j]=Integer.parseInt(sline.nextToken());
			}
		}
		csize=new int [MAX*MAX];
		classify();
		break_the_wall();
		out.println(Integer.toString(num_components));
		out.println(Integer.toString(max_room));
		out.println(Integer.toString(bSize));
		out.println(Integer.toString(bi+1)+" "+Integer.toString(bj+1)+" "+bChar);
//		for(int i=0;i<N;i++){
//			for(int j=0;j<M;j++){
//				out.print(component[i][j]);
//				if(j==M-1){
//					out.println();
//				}else{
//					out.print(" ");
//				}
//				
//			}
//		}
		out.close();
	}

}