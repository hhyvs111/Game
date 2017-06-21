import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.swing.*;

public class MyGames extends JFrame {
	int num = Main.num;//NUM阶矩阵
	public static int id ;
	private JPanel panel1 = new JPanel();//存放功能栏
	private JButton buttona = new JButton("开始");
	private JLabel label1 = new JLabel("分数");
	private JTextField textarea1 = new JTextField(10); //文本框
	private JLabel buttonc = new JLabel("时间");
	private JProgressBar jindu = new JProgressBar();
	private Timer timer;
	private Timer downAnimal = new Timer(800, new TimeListener1());
	private Timer updateAnimal = new Timer(800, new TimeListener2());
	private Timer wait = new Timer(500, new TimeListener3());
	private Timer reset = new Timer(1500, new TimeListener4());
	private JButton buttonb = new JButton("退出");
	private JButton buttond = new JButton("全局");
	private JPanel panel2 = new JPanel();//存放代码
	private JButton button[][] = new JButton[num][num];
	private int animal[][] = new int[num][num];
	private ImageIcon Iocn[] = new ImageIcon[9];
	// 标记是否有三个以上的连接
	private final int EMPTY = 0;// 无为0，有为1
	// 随机数
	//	private Random rand = new Random();// new 一个监听器
	// 标记是否有三个以上的连接
	private boolean isThreeLinked;
	// 标记单击次数
	private boolean isDoubleClicked = false;	
	private boolean isDoubleClickedTiger = false;
	private int x1;// 记录第一次被点击按钮的X坐标	
	private int y1;// 记录第一次被点击按钮的Y坐标
	public static  int grade = 0; //得分   ,static 可以传值
	private boolean isDownAnimal = false;

	MyGames() {
		// 加载图片
		Iocn[1] = new ImageIcon("image//dog.png");
		Iocn[2] = new ImageIcon("image//cattle.png");
		Iocn[3] = new ImageIcon("image//owl.png");
		Iocn[4] = new ImageIcon("image//fox.png");
		Iocn[5] = new ImageIcon("image//monkey.png");
		Iocn[6] = new ImageIcon("image//pig.png");
		Iocn[7] = new ImageIcon("image//qi.png");
		Iocn[8] = new ImageIcon("image//tiger.png");
		panel1.setLayout(new FlowLayout());
		panel1.add(buttona);
		panel1.add(label1);
		panel1.add(textarea1);
		textarea1.setEditable(false);
		textarea1.setText(Integer.toString(grade));//
		panel1.add(buttonc);
		jindu.setMaximum(100);
		panel1.add(jindu);
		panel1.add(buttonb);
//		panel1.add(buttond);
		this.setLayout(new BorderLayout());
		this.add(panel1, BorderLayout.NORTH);	//将功能栏放在最上面NORTH	
		panel2.setLayout(new GridLayout(num, num, 1, 1));		
		MyListener mylisten = new MyListener();
		int m;
		// 初始化动物数组
		for (int i = 0; i < num; i++)
			for (int j = 0; j < num; j++) {
				m = (int) (Math.random() * 7) +1 ; //产生
				button[i][j] = new JButton(Iocn[m]);
				animal[i][j] = m;
				button[i][j].setSize(50, 50);
				button[i][j].addActionListener(mylisten);				
				button[i][j].setEnabled(false); //图形按钮无效
				panel2.add(button[i][j]);
			}
		for(int i = 0;i < num;i++){
			for(int j = 0;j < num;j++)
				System.out.print(animal[i][j]+"  ");
			System.out.println();
		}
			
		this.add(panel2, BorderLayout.CENTER);	//将放在中间	
		buttona.addActionListener(mylisten);
		buttonb.addActionListener(mylisten);
		buttond.addActionListener(mylisten);
	}

	// 初始化动物数组
	private void initAnimalMatrix() {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				// 随机选取动物
				animal[i][j] = (int) (Math.random() * 7) +1 ;
			}
		}
	}
	private void removeL(int x,int y,int status){
		if(animal[x][y] == EMPTY)return; 
		int tmp;
		int linked = 1;
		//L型消除
		if(status == 1){
			if (x - 1 >= 0) {
				tmp = x - 1;
				while (tmp >= 0 && animal[x][y] == animal[tmp][y]) {
					System.out.print("x的上面一个与x相等,坐标为"+tmp+","+y+"当前动物为");
					printAnimal(tmp,y);
					animal[tmp][y] = EMPTY;
					linked++;
					tmp--;
					
				}
			}
			if (y + 1 < num) {
				tmp = y + 1;
				while (tmp < num && animal[x][y] == animal[x][tmp]) {
					System.out.print("与点xy右边的相等,坐标为"+x+","+tmp+"当前动物为");
					printAnimal(x,tmp);
					animal[x][tmp] = EMPTY;
					linked++;
					tmp++;
					
				}
			}
			animal[x][y] = EMPTY;
			print();
			downAnimal.start();
		}
		// 反L消除类似与_I
		if(status == 2){
			if (x - 1 >= 0) {
				tmp = x - 1;
				while (tmp >= 0 && animal[x][y] == animal[tmp][y]) {
					System.out.print("x的上面一个与x相等,坐标为"+tmp+","+y+"当前动物为");
					printAnimal(tmp,y);
					animal[tmp][y] = EMPTY;
					linked++;
					tmp--;
					
				}
			}
			if (y - 1 >= 0) {
				tmp = y - 1;
				while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
					System.out.print("与点xy左边的相等,坐标为"+x+","+tmp+"当前动物为");
					printAnimal(x,tmp);
					animal[x][tmp] = EMPTY;
					linked++;
					tmp--;
					
				}
			}
			animal[x][y] = EMPTY;
			print();
			downAnimal.start();
		}
		//7字形消除
		if(status == 3){
			if (x + 1 < num) {
				tmp = x + 1;
				while (tmp < num && animal[x][y] == animal[tmp][y]) {
					System.out.print("x的下面一个与x相等,坐标为"+tmp+","+y+"当前动物为");
					printAnimal(tmp,y);
					animal[tmp][y] = EMPTY;
					linked++;
					tmp++;
				}
			}
			if (y - 1 >= 0) {
				tmp = y - 1;
				while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
					System.out.print("与点xy左边的相等,坐标为"+x+","+tmp+"当前动物为");
					printAnimal(x,tmp);
					animal[x][tmp] = EMPTY;
					linked++;
					tmp--;
					
				}
			}
			animal[x][y] = EMPTY;
			print();
			downAnimal.start();
		}
		//反7字消除，类似1`
		if(status == 4){
			if (x + 1 < num) {
				tmp = x + 1;
				while (tmp < num && animal[x][y] == animal[tmp][y]) {
					System.out.print("x的下面一个与x相等,坐标为"+tmp+","+y+"当前动物为");
					printAnimal(tmp,y);
					animal[tmp][y] = EMPTY;
					linked++;
					tmp++;
				}
			}
			if (y + 1 < num) {
				tmp = y + 1;
				while (tmp < num && animal[x][y] == animal[x][tmp]) {
					System.out.print("与点xy右边的相等,坐标为"+x+","+tmp+"当前动物为");
					printAnimal(x,tmp);
					animal[x][tmp] = EMPTY;
					linked++;
					tmp++;
					
				}
			}
			animal[x][y] = EMPTY;
			print();
			downAnimal.start();
		}
	}
	//消除函数，消去连接
	private void removeLinked(int x, int y) {
		if(animal[x][y] == EMPTY)return; //若空返回
		int n = 0; 
		int tmp;
		int linked = 1;             //相同的动物数
		//选定的点不超过下界，如果选点下方的动物与选点相同则linked++
		System.out.print("进入removeLinked,坐标为"+x+","+y+"当前动物为");
		printAnimal(x,y);
		if (x + 1 < num) {
			tmp = x + 1;
			while (tmp < num && animal[x][y] == animal[tmp][y]) {
				System.out.print("x的下面一个与x相等,坐标为"+tmp+","+y+"当前动物为");
				printAnimal(tmp,y);
				linked++;
				tmp++;
			}
		}
		//选定的点不超过上界，如果选点上方的动物与选点相同则linked++
		if (x - 1 >= 0) {
			tmp = x - 1;
			while (tmp >= 0 && animal[x][y] == animal[tmp][y]) {
				System.out.print("x的上面一个与x相等,坐标为"+tmp+","+y+"当前动物为");
				printAnimal(tmp,y);
				linked++;
				tmp--;
			}
		}
		//这是判断上下动物数是否有3个以上相同的，相同的消除也就是置为空
		if (linked >= 3) {
			System.out.println("可消除的点为:" + x + "," + y);
			n = n+linked; 
			tmp = x + 1;
			//向X,Y的下面找
			while (tmp < num && animal[tmp][y] == animal[x][y]) {				
//				button[tmp][y].setEnabled(false);
				System.out.println(tmp+","+y+"已消除");
				printAnimal(tmp,y);
				printAnimal(x,y);
//				print();
				animal[tmp][y] = EMPTY;
				tmp++;
			}
			tmp = x - 1;
			//向X,Y的上面找
			while (tmp >= 0 && animal[tmp][y] == animal[x][y]) {
				System.out.println(tmp+","+y+"已消除");
				printAnimal(tmp,y);
				printAnimal(x,y);
				animal[tmp][y] = EMPTY;
				tmp--;
			}
			// 当前交换过来的点，自己也变为空
//			button[x][y].setEnabled(false);
//			print();
			if(linked >= 4){
				animal[x][y] = 8;  //变为老虎可以消除任意点击的
			}
			else{
				animal[x][y] = EMPTY;
			}
			print();
			downAnimal.start();
		}
		tmp = 0;
		linked = 1;
		//选定的点不超过右界，如果选点右方的动物与选点相同则linked++
		if (y + 1 < num) {
			tmp = y + 1;
			while (tmp < num && animal[x][y] == animal[x][tmp]) {
				System.out.print("与点xy右边的相等,坐标为"+x+","+tmp+"当前动物为");
				printAnimal(x,tmp);
				linked++;
				tmp++;
			}
		}
		//选定的点不超过左界，如果选点左方的动物与选点相同则linked++
		if (y - 1 >= 0) {
			tmp = y - 1;
			while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
				System.out.print("与点xy左边的相等,坐标为"+x+","+tmp+"当前动物为");
				printAnimal(x,tmp);
				linked++;
				tmp--;
			}
		}
		//再次判断左右相连动物数
		if (linked >= 3) {
			System.out.println("可消除的点为:" + x + "," + y);
			n = n+linked; 
			tmp = y + 1;
			while (tmp < num && animal[x][y] == animal[x][tmp]) {
				
//				button[x][tmp].setEnabled(false);
				System.out.println(x+","+tmp+"已消除");
				printAnimal(x,tmp);
				printAnimal(x,y);

//				print();
				animal[x][tmp] = EMPTY;
				tmp++;
			}
			tmp = y - 1;
			while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
				
//				button[x][tmp].setEnabled(false);
				System.out.println(x+","+tmp+"已消除");
				printAnimal(x,tmp);
				printAnimal(x,y);
				animal[x][tmp] = EMPTY;
				tmp--;
			}
			// 当前交换过来的点
			if(linked >= 4){
				animal[x][y] = 8;  //变为老虎可以消除任意点击的
			}
			else{
				animal[x][y] = EMPTY;
				
			}
			print();
			downAnimal.start();
		}
		//加分
		grade += n*10;
		textarea1.setText(Integer.toString(grade));
//		System.out.println(grade + " ======"+x+" "+y +"得分:"+n*10);
	}
	

	private void removeAll(int x ,int y,int x1,int y1){
		int all = animal[x][y];
		int n = 1;
		for(int i = 0;i < num;i++){
			for(int j = 0;j < num;j++){
				if(animal[i][j] == all){
					n++;
					animal[i][j] = EMPTY;
				}
			}
		}
		animal[x1][y1] = EMPTY;
		print();
		downAnimal.start();
		grade += n*10;
		textarea1.setText(Integer.toString(grade));
		System.out.println(grade + " ======"+x+" "+y);
	}
	
	//全部搜索一遍，如果有相同的则消除
	private boolean globalSearch(int flag) {
		if (1 == flag) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (isThreeLinked(i, j)) {
						System.out.println("有三连的坐标为："+i+","+j);
						printAnimal(i,j);
						return true;
					}
//					if(isUp(i,j))
//						return true;
//					else if(isDown(i,j))
//						return true;
//					else if(isLeft(i,j))
//						return true;
//					else if(isRight(i,j))
//						return true;
				}
			}
		} 
		else if (2 == flag) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					// 删除三个相连的点
//					
					if (isThreeLinked(i, j)) {
					removeLinked(i, j);
					System.out.println("删除全局三连");
//					print();
					return true;
					}
//					if(isUp(i,j)){
//						return true;
////						removeL()
//					}
//						
//					else if(isDown(i,j)){
//						return true;
//					}
//						
//					else if(isLeft(i,j)){
//						return true;
//					}
//						
//					else if(isRight(i,j)){
//						return true;
//					}
//						
				}
			}
		}
		return false;
	}

	// 是否有三个以上连接的点
	private boolean isThreeLinked(int x, int y) {
		int tmp;
		int linked = 1;
		//向下
		if (x + 1 < num) {
			tmp = x + 1;
			while (tmp < num && animal[x][y] == animal[tmp][y]) {
				linked++;
				tmp++;
			}
		}
		//
		if (x - 1 >= 0) {
			tmp = x - 1;
			while (tmp >= 0 && animal[x][y] == animal[tmp][y]) {
				linked++;
				tmp--;
			}
		}
		if (linked >= 3) {
			return true;
		}
		linked = 1;
		if (y + 1 < num) {
			tmp = y + 1;
			while (tmp < num && animal[x][y] == animal[x][tmp]) {
				linked++;
				tmp++;
			}
		}
		if (y - 1 >= 0) {
			tmp = y - 1;
			while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
				linked++;
				tmp--;
			}
		}
		if (linked >= 3) {

			return true;
		}
		return false;
	}
	private boolean isDown(int x,int y){
		int tmp;
		int linked  = 1;
		if (x + 1 < num) {
			tmp = x + 1;
			while (tmp < num && animal[x][y] == animal[tmp][y]) {
				linked++;
				tmp++;
			}
		}
		if(linked == 2){
			return true;
		}
		else
			return false;
	}
	private boolean isUp(int x,int y){
		int tmp;
		int linked = 1;
		if (x - 1 >= 0) {
			tmp = x - 1;
			while (tmp >= 0 && animal[x][y] == animal[tmp][y]) {
				linked++;
				tmp--;
			}
		}
		if (linked == 2) {
			return true;
		}
		else
			return false;
	}
	private boolean isLeft(int x,int y){
		int tmp;
		int linked = 1;
		if (y - 1 >= 0) {
			tmp = y - 1;
			while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
				linked++;
				tmp--;
			}
		}
		if (linked == 2) {

			return true;
		}
		return false;
	}
	private boolean isRight(int x,int y){
		int tmp;
		int linked = 1;
		if (y + 1 < num) {
			tmp = y + 1;
			while (tmp < num && animal[x][y] == animal[x][tmp]) {
				linked++;
				tmp++;
			}
		}
		if(linked == 2){
			return true;
		}
		return false;
	}
	private boolean isDeadMatrix(){
		for(int i = 0;i < num ;i++){
			for(int j = 0; j < num;j++){
				int tmp;
				//向右交换
				if(j < num - 1){
					tmp = animal[i][j];
					animal[i][j] = animal[i][j+1];
					animal[i][j+1] = tmp;
					if(isThreeLinked(i,j) || isThreeLinked(i,j+1)){
						tmp = animal[i][j];
						animal[i][j] = animal[i][j+1];
						animal[i][j+1] = tmp;
						return true;
					}
					else{
						tmp = animal[i][j];
						animal[i][j] = animal[i][j+1];
						animal[i][j+1] = tmp;
					}
				}
				//向左交换
				if(j > 0){
					tmp = animal[i][j];
					animal[i][j] = animal[i][j-1];
					animal[i][j-1] = tmp;
					if(isThreeLinked(i,j) || isThreeLinked(i,j-1)){
						tmp = animal[i][j];
						animal[i][j] = animal[i][j-1];
						animal[i][j-1] = tmp;
						return true;
					}
					else{
						tmp = animal[i][j];
						animal[i][j] = animal[i][j-1];
						animal[i][j-1] = tmp;
					}
				}
				//向下交换
				if(i < num - 1){
					tmp = animal[i][j];
					animal[i][j] = animal[i+1][j];
					animal[i+1][j] = tmp;
					if(isThreeLinked(i,j) || isThreeLinked(i+1,j)){
						tmp = animal[i][j];
						animal[i][j] = animal[i+1][j];
						animal[i+1][j] = tmp;
						return true;
					}
					else{
						tmp = animal[i][j];
						animal[i][j] = animal[i+1][j];
						animal[i+1][j] = tmp;
					}
				}
				if(i > 0){
					tmp = animal[i][j];
					animal[i][j] = animal[i-1][j];
					animal[i-1][j] = tmp;
					if(isThreeLinked(i,j) || isThreeLinked(i-1,j)){
						tmp = animal[i][j];
						animal[i][j] = animal[i-1][j];
						animal[i-1][j] = tmp;
						return true;
					}
					else{
						tmp = animal[i][j];
						animal[i][j] = animal[i-1][j];
						animal[i-1][j] = tmp;
					}
				}
			}
		}
		return false;
	}
	private void reset(){
		for(int i = 0;i < num;i++){
			for(int j = 0;j < num;j++){
				animal[i][j] = EMPTY;
			}
		}
		print();
	}
	

	// 更新动物矩阵
	private void updateAnimal() {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (animal[i][j] == EMPTY) {
					// 将矩阵中为空值的点再进行随机赋值 1到7
					animal[i][j] = (int) (Math.random() * 7) +1 ;
					button[i][j].setEnabled(true);
				}
			}
		}
		System.out.println("更新完了");
	}

	// 动物下降，全局扫描，如果为空则将其上方的元素下降
	private boolean downAnimal() {
		int tmp;
		int flag = 0;
		//从下往上找
		for (int j = num - 1; j >= 0; j--) {
			for (int i = 0; i < num; i++) {
				if (animal[j][i] == EMPTY) {
					button[j][i].setEnabled(true);
					for (int k = j - 1; k >= 0; k--) {
						if (animal[k][i] != EMPTY) {
							tmp = animal[k][i];
							animal[k][i] = animal[j][i];
							animal[j][i] = tmp;
							flag = 1;
							break;
						}
					}
				}
			}
		}
		if(flag == 1){
		System.out.println("下降完了");
		return true;
		}
		else
			return false;
	}
	private void printAnimal(int i,int j){
		if(animal[i][j] == 1)
			System.out.println(animal[i][j]+"狗");
		else if(animal[i][j] == 2)
			System.out.println(animal[i][j]+"牛");
		else if(animal[i][j] == 3)
			System.out.println(animal[i][j]+"猫头鹰");
		else if(animal[i][j] == 4)
			System.out.println(animal[i][j]+"狐狸");
		else if(animal[i][j] == 5)
			System.out.println(animal[i][j]+"猴子");
		else if(animal[i][j] == 6)
			System.out.println(animal[i][j]+"猪");
		else if(animal[i][j] == 7)
			System.out.println(animal[i][j]+"企鹅");
		else{
			System.out.println("为空");
		}
	}
	//重新显示按钮图形
	
	private void print() { 
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				button[i][j].setIcon(Iocn[animal[i][j]]);
			}
		}
	}
	
//	private Runnable run = null;//更新组件的线程
			
			int flag = 0;  //老虎标志
	//交换函数
	private void swapAnimal(int x, int y) {		
		if ((x >= 0 && x <= num) && (y >= 0 && y <= num)) {
			// 被点击的动物的坐标
    		int x2;
    		int y2;
    		//如果被单击的是老虎，它可以和它上下左右任意一个交换然后爆炸
    		if(animal[x][y] == 8 || flag == 1){
    			if(!isDoubleClickedTiger){
    				isDoubleClickedTiger = true;
    				flag = 1;
    				x1 = x;
					y1 = y;
					System.out.println("老虎第一次被单击的点的坐标 = " + x1 + "," + y1);
    			}
    			else{
    				x2 = x;
		    		y2 = y;
		    		flag = 0;
	    			isDoubleClickedTiger = false;
	    			System.out.println("老虎第二次被单击的点的坐标 = " + x2 + "," + y2);
	//    			printAnimal(y2,x2);
	    			//两点的坐标相减的绝对值等于1时视为相邻的两点
	    			if (1 == Math.abs(x2 - x1) && y2 == y1
	    					|| 1 == Math.abs(y2 - y1)&& x2 == x1){
	    				removeAll(x2,y2,x1,y1);
//	    				print();
//	    				downAnimal();
//	    				updateAnimal();
//						print();
//	    				updateAnimal.start();
//						if(isDeadMatrix()){
//	   						System.out.println("不是死阵");
//	   						while (globalSearch(1)) {
//	   	  						//全局扫描消除三个以上相连的点
//	   	  						globalSearch(2);
//	   	  						downAnimal();
//	   	  						//再次更新动物矩阵
//	   	 						updateAnimal();
//	   	 						print();
//	   	 					}  
//	   					}
//	   					else{
//	   						System.out.println("死阵");
//	   					}
	    			}
    			}
    		}
    		else{
				if (!isDoubleClicked || (x == x1 && y == y1)) { //第一次单击
					isDoubleClicked = true;
					x1 = x;
					y1 = y;
					System.out.println("第一次被单击的点的坐标 = " + x1 + "," + y1);
					printAnimal(x1,y1);
					
				} else{               //第2次单击
		    		x2 = x;
		    		y2 = y;
	    			isDoubleClicked = false;
	    			System.out.println("第二次被单击的点的坐标 = " + x2 + "," + y2);
	    			printAnimal(x2,y2);
	    			//两点的坐标相减的绝对值等于1时视为相邻的两点
	    			if (1 == Math.abs(x2 - x1) && y2 == y1
	    					|| 1 == Math.abs(y2 - y1)&& x2 == x1) {
	    				//-------------交换矩阵中相邻的两点的值--------------
	    				int tmp;
	    				tmp = animal[x2][y2];
	    				animal[x2][y2] = animal[x1][y1];
	    				animal[x1][y1] = tmp;
	    				System.out.println("已经交换");
	    				print();
	//    				-----------------------------------------------------
	    				//判断是否是7字消除
	    				if(isDown(x2,y2) && isLeft(x2,y2) || isDown(x1,y1) && isLeft(x1,y1)){
	    					System.out.println("7字消除");
	    					if(isDown(x2,y2) && isLeft(x2,y2))
	    						removeL(x2,y2,3);
	    					if(isDown(x1,y1) && isLeft(x1,y1))
	    						removeL(x1,y1,3);
	    				}
	    				//判断是否是反7字消除
	    				else if(isDown(x2,y2) && isRight(x2,y2) || isDown(x1,y1) && isRight(x1,y1)){
	    					System.out.println("反7字消除");
	    					if(isDown(x2,y2) && isRight(x2,y2))
	    						removeL(x2,y2,4);
	    					if(isDown(x1,y1) && isRight(x1,y1))
	    						removeL(x1,y1,4);
	    				}
	    				//判断是否是L字消除
	    				else if(isUp(x2,y2) && isRight(x2,y2) || isUp(x1,y1) && isRight(x1,y1)){
	    					System.out.println("L字消除");
	    					if(isUp(x2,y2) && isRight(x2,y2))
	    						removeL(x2,y2,1);
	    					if(isUp(x1,y1) && isRight(x1,y1))
	    						removeL(x1,y1,1);
	    				}
	    				//判断是否是反L字消除
	    				else if(isUp(x2,y2) && isLeft(x2,y2) || isUp(x1,y1) && isLeft(x1,y1)){
	    					System.out.println("反L字消除");
	    					if(isUp(x2,y2) && isLeft(x2,y2))
	    						removeL(x2,y2,2);
	    					if(isUp(x1,y1) && isLeft(x1,y1))
	    						removeL(x1,y1,2);
	    				}
	    				else{
	    				if (isThreeLinked(x2, y2)||isThreeLinked(x1, y1)){
	    					System.out.println("可以消除");
	    					if(isThreeLinked(x2, y2)){
	    						removeLinked(x2, y2);
//	    						timer1.start();
	    					}
	    					if(isThreeLinked(x1, y1)){
	    						removeLinked(x1, y1);
//	    						timer1.start();
	    					}
							
	    					System.out.println("打印了");	
//	    					wait.start();
//	    					 updateAnimal.start();
	//    					//被削去处上方的动物下降
//	    					downAnimal.start();
//							timer1.start();
	    					//该列上方重新随机产生新的动物，更新动物矩阵
	   					
//	   					if(isDeadMatrix()){
//	   						System.out.println("不是死阵");
//	   						while(globalSearch(1)){
//	   	  						//全局扫描消除三个以上相连的点
//	   							System.out.println("有全局三连");
//	   	  						globalSearch(2);
//	   	  						downAnimal();
//	   	  						updateAnimal();
//	   	  						updateAnimal.start();
////   	  						//再次更新动物矩阵
////   	 						updateAnimal();
////	   	 						print();
//	   						}
//	   	 					
//	   					}
//	   					else{
//	   						System.out.println("死阵");
//	   					}
	   					
	    			}
	    				else {//没有三个以上相同的点，交换回来  
	    					System.out.println("交换回来");
	    					tmp = animal[x2][y2];
		    				animal[x2][y2] = animal[x1][y1];
		    				animal[x1][y1] = tmp;
	    					print();     	    			
	    				}
	    			}
	    				
	    			}
	    		}
	    	}
		}
    }
	
	//重新初始化,直到没有一个已经三连的矩阵
	public void init() {
		do {
			System.out.println("重新初始化");
			initAnimalMatrix();
		} while (globalSearch(1) || !isDeadMatrix());
		print();
		pack();
		setResizable(false);
		setVisible(true);
		for(int i = 0;i < num;i++){
			for(int j = 0;j < num;j++)
				System.out.print(animal[i][j]+"  ");
			System.out.println();
		}
	}

	//主函数
	// 监听器,鼠标事件
	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttona) {
				buttona.setEnabled(false);
				jindu.setStringPainted(true);
				jindu.setMaximum(100);
				jindu.setMinimum(0);
				timer = new Timer(800, new TimeListener());
				timer.start();
				grade = 0;
				textarea1.setText(Integer.toString(grade));
				for (int i = 0; i < num; i++)
					for (int j = 0; j < num; j++) {
						button[i][j].setEnabled(true); //图形按钮有效
					}
			}
			if (e.getSource() == buttonb) {
				System.out.println("end");
				System.exit(1);
			}
			if(e.getSource() == buttond){
				updateAnimal.start();
				
			}
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (e.getSource() == button[i][j]) {
						System.out.println("第" + i + " " + j + "键");
						swapAnimal(i, j);
				
					}
				}
			}
		}
	}
	

	class TimeListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
			System.out.println("time1");
			downAnimal();
			updateAnimal();
			print();
			downAnimal.stop();
			updateAnimal.start();
		}
	}
	class TimeListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
			System.out.println("time2");
			if(isDeadMatrix()){
					System.out.println("不是死阵");
					while(globalSearch(1)){
 						//全局扫描消除三个以上相连的点
						System.out.println("有全局三连");
 						if(globalSearch(2)){
 							wait.start();
 							downAnimal();
 	 						updateAnimal();
 						}
// 						downAnimal();
// 						updateAnimal();
//						print();
 						}
					wait.stop();
				}
				else{
					System.out.println("死阵");
					ShowDead.main(null);
					reset.start();
					reset();
				}
			updateAnimal.stop();
		}
	}
	class TimeListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
			System.out.println("time3");
			downAnimal();
			updateAnimal();
			print();
		}
	}
	class TimeListener4  implements ActionListener  {
		public void actionPerformed(ActionEvent e) { 
			System.out.println("time4 死阵消除");
			
			init();
			reset.stop();
		}
	}
	class TimeListener implements ActionListener {
		int times = 0;
		public void actionPerformed(ActionEvent e) {
			jindu.setValue(times++);
//			print();
			if (times > 10000) {
				if(times == 90){
					try {
						timer.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				timer.stop();
				//定时器结束
				for (int i = 0; i < num; i++)
					for (int j = 0; j < num; j++) {
						button[i][j].setEnabled(false); //图形按钮无效
					}
				buttona.setEnabled(true);
				Rank rank = new Rank();
				String name = Main.Name;
				Date time= new java.sql.Date(new java.util.Date().getTime());
				rank.setName(name);
				rank.setScore(grade);
				rank.setTime((java.sql.Date) time);
				if(RankOperate.add(rank)){
					System.out.println("插入成功！");
					//id自增，最后插入的就是id最大的那个
					List<Rank> list = RankOperate.queryMax();
					Rank ra = list.get(0);
					System.out.println(ra.getId());
					id = ra.getId();
					//游戏结束调用面板！
					setVisible(false);
					Over.main(null);
				}
				else
					System.out.println("插入失败");
			}
		}
	}
}
