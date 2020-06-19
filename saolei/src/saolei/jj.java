package saolei;

import java.util.Scanner;

//����Ϊ'1',����Ϊ'0';
public class jj {
	final int Row = 10;
	final int Col = 10;
//������������  12x12
	char[][] Mine = new char[Row+4][Col+4];
	char[][] Board = new char[Row+4][Col+4];
//��Ϸ����
	public void menu() {
		System.out.println("***************");
		System.out.println("*****Play******");
		System.out.println("***************");
	}
//���̵ĳ�ʼ��
	public void initMine() {
		for(int i=1;i<Row+3;i++) {
			for(int j=1;j<Col+3;j++) {
				Mine[i][j]='0';
			}
		}
	}
//���̵ĳ�ʼ��
	public void initBoard() {
		for(int i=1;i<Row+3;i++) {
			for(int j=1;j<Col+3;j++) {
				Board[i][j]='*';
			}
		}	
	}
//����     ���� Math.random()�������1-10
	public void setMine(){
	//ͨ��ѭ����10����
		for(int count=10;count>0;count--){
			int i = (int)(Math.random()*10+2);//�������2-11
			int j = (int)(Math.random()*10+2);
	//������������λ�����û���ף��Ͱ����λ�÷�����
			if(Mine[i][j]=='0') {
				Mine[i][j]='1';
			}
	//������λ�������ף��Ͳ����ٷ����ˣ�����count�ͼ�1
			else {
				count++;
			}
		}
	}
//����ÿ������Χ��ĸ���  '0'��ASC��Ϊ48,0Ϊ0���������������ַ������ֵ�ASC�����48
	public int calcuMine(int x,int y) {
		return (Mine[x-1][y-1]-'0'+Mine[x][y-1]-'0'+Mine[x+1][y-1]-'0'+Mine[x-1][y]-'0'+Mine[x+1][y]-'0'+Mine[x][y+1]-'0'+Mine[x-1][y+1]-'0'+Mine[x+1][y+1]-'0');
	}
//չʾ����
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
//չʾ�������
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
//���ɨ��
	public boolean player(int count) {
		Scanner in = new Scanner(System.in);
		System.out.println("����������: ");
		int x = 0;
	    int y = 0;
		while(true) {
			x = in.nextInt()+1;
			y = in.nextInt()+1;
			if(x<1 || x>Row+2 || y<1 || y>Col+2) {
				System.out.println("�������������������:");
			}
	//��ҵ�һ�β��ܱ���ը��
			else {
				if(count==1) {
					if(Mine[x][y]=='1') {
						Mine[x][y]='0';
						while(true) {
							int a = (int)(Math.random()*10+2);
							int b = (int)(Math.random()*10+2);
							if(Mine[a][b]=='0') {
								Mine[a][b]='1';
								System.out.println("���º�����̽���Ϊ:");
								Display(Mine);
								break;
							}
						}
					}
				}
				if(Board[x][y]!='*') {
					System.out.println("�˴��ѱ�ɨ��������������:");
				}
				else
					break;
			}
		}
			if(Mine[x][y]=='1') {
				Board[x][y]='1';
				Display(Board);
				System.out.println("hahaha~��ȵ����ˣ�");
				return false;
			}
			else {
				Board[x][y]=(char) (calcuMine(x,y)+'0');//������Χ�׵ĸ���
//��ǰ�����Χ�ļ������ǲ���0��������չ��
				if(calcuMine(x,y)==0) {
					showBoard(x,y);
				}
				Display(Board);
			return true;
		}
	}
	
	//�ж��Ƿ�Ӯ     �������ҽ���ʣ10�� * ʱ�����ʤ
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
			System.out.println("��ϲѽ~���Ѿ�����ɨ������");
			return 1;
		}
		return 0;
	}
	public void game() {
		initMine();
		System.out.println("---��ʾ����---");
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
		System.out.println("***1.��ʼɨ����Ϸ      0.�˳���Ϸ***");
		//������Ķ���
		jj sl = new jj(); 
		int player;
		do {
			sl.menu();
			System.out.println("���������ѡ�� ~ 1��0: ");
			player = in.nextInt();
			switch(player) {
			case 1:sl.game();
			case 0:System.out.println("�˳���Ϸ��");break;
			default:System.out.println("����������˳���Ϸ");break;
			}
		}while(player==1);
	}

}
