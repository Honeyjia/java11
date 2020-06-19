package saolei;

import java.util.Scanner;

//有雷为'1',无雷为'0';
public class jj {
	final int Row = 10;
	final int Col = 10;
//定义两个数组  12x12
	char[][] Mine = new char[Row+4][Col+4];
	char[][] Board = new char[Row+4][Col+4];
//游戏界面
	public void menu() {
		System.out.println("***************");
		System.out.println("*****Play******");
		System.out.println("***************");
	}
//雷盘的初始化
	public void initMine() {
		for(int i=1;i<Row+3;i++) {
			for(int j=1;j<Col+3;j++) {
				Mine[i][j]='0';
			}
		}
	}
//雷盘的初始化
	public void initBoard() {
		for(int i=1;i<Row+3;i++) {
			for(int j=1;j<Col+3;j++) {
				Board[i][j]='*';
			}
		}	
	}
//放雷     利用 Math.random()随机整数1-10
	public void setMine(){
	//通过循环放10个雷
		for(int count=10;count>0;count--){
			int i = (int)(Math.random()*10+2);//随机整数2-11
			int j = (int)(Math.random()*10+2);
	//随机产生的这个位置如果没有雷，就把这个位置放上雷
			if(Mine[i][j]=='0') {
				Mine[i][j]='1';
			}
	//如果这个位置上有雷，就不能再放雷了，于是count就加1
			else {
				count++;
			}
		}
	}
//计算每个空周围类的个数  '0'的ASC码为48,0为0，即整型数字与字符型数字的ASC码差了48
	public int calcuMine(int x,int y) {
		return (Mine[x-1][y-1]-'0'+Mine[x][y-1]-'0'+Mine[x+1][y-1]-'0'+Mine[x-1][y]-'0'+Mine[x+1][y]-'0'+Mine[x][y+1]-'0'+Mine[x-1][y+1]-'0'+Mine[x+1][y+1]-'0');
	}
//展示雷盘
	public void showBoard(int x,int y) {
		Board[x-1][y-1] = (char) (calcuMine(x-1,y-1)+'0');
		Board[x][y-1] = (char) (calcuMine(x,y-1)+'0');
		Board[x+1][y-1] = (char) (calcuMine(x+1,y-1)+'0');
		Board[x-1][y] = (char) (calcuMine(x-1,y)+'0');
		Board[x+1][y] = (char) (calcuMine(x+1,y)+'0');
		Board[x][y+1] = (char) (calcuMine(x,y+1)+'0');
	    Board[x-1][y+1] = (char) (calcuMine(x-1,y+1)+'0');
	    Board[x+1][y+1] = (char) (calcuMine(x+1,y+1)+'0');
	}
//展示玩家雷盘
	public void Display(char[][] board) {
		System.out.println("   1 2 3 4 5 6 7 8 9 10");
		System.out.println("-----------------------");
		for(int i=2;i<Row+2;i++) {
			System.out.printf("%-2d",i-1);
			System.out.print("|");
			for(int j=2;j<Col+2;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}
//玩家扫雷
	public boolean player(int count) {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入坐标: ");
		int x = 0;
	    int y = 0;
		while(true) {
			x = in.nextInt()+1;
			y = in.nextInt()+1;
			if(x<1 || x>Row+2 || y<1 || y>Col+2) {
				System.out.println("坐标输入错误，请新输入:");
			}
	//玩家第一次不能被雷炸死
			else {
				if(count==1) {
					if(Mine[x][y]=='1') {
						Mine[x][y]='0';
						while(true) {
							int a = (int)(Math.random()*10+2);
							int b = (int)(Math.random()*10+2);
							if(Mine[a][b]=='0') {
								Mine[a][b]='1';
								System.out.println("更新后的雷盘界面为:");
								Display(Mine);
								break;
							}
						}
					}
				}
				if(Board[x][y]!='*') {
					System.out.println("此处已被扫过，请重新输入:");
				}
				else
					break;
			}
		}
			if(Mine[x][y]=='1') {
				Board[x][y]='1';
				Display(Board);
				System.out.println("hahaha~你踩到雷了！");
				return false;
			}
			else {
				Board[x][y]=(char) (calcuMine(x,y)+'0');//计算周围雷的个数
//当前格和周围的几个格是不是0，若是则展开
				if(calcuMine(x,y)==0) {
					showBoard(x,y);
				}
				Display(Board);
			return true;
		}
	}
	
	//判断是否赢     当最后玩家界面剩10个 * 时，玩家胜
	public int winPlayer() {
		int count = 0;
		for(int i=2;i<Row+2;i++) {
			for(int j=2;j<Col+2;j++) {
				if(Board[i][j]=='*') {
					count++;
				}
			}
		}
		if(count==10) {
			System.out.println("恭喜呀~雷已经被你扫完啦！");
			return 1;
		}
		return 0;
	}
	public void game() {
		initMine();
		System.out.println("---显示雷盘---");
		setMine();
		Display(Mine);
		initBoard();
		Display(Board);
		boolean ret ;
		int count = 1;
		while(true) {
			ret = player(count++);
			if(!ret) {
				break;
			}
			if(winPlayer()==1) {
				Display(Board);
				break;
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("***1.开始扫雷游戏      0.退出游戏***");
		//制造类的对象
		jj sl = new jj(); 
		int player;
		do {
			sl.menu();
			System.out.println("请输入你的选择 ~ 1或0: ");
			player = in.nextInt();
			switch(player) {
			case 1:sl.game();
			case 0:System.out.println("退出游戏！");break;
			default:System.out.println("输入错误，请退出游戏");break;
			}
		}while(player==1);
	}

}
