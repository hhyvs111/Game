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
	int num = Main.num;//NUM�׾���
	public static int id ;
	private JPanel panel1 = new JPanel();//��Ź�����
	private JButton buttona = new JButton("��ʼ");
	private JLabel label1 = new JLabel("����");
	private JTextField textarea1 = new JTextField(10); //�ı���
	private JLabel buttonc = new JLabel("ʱ��");
	private JProgressBar jindu = new JProgressBar();
	private Timer timer;
	private Timer downAnimal = new Timer(800, new TimeListener1());
	private Timer updateAnimal = new Timer(800, new TimeListener2());
	private Timer wait = new Timer(500, new TimeListener3());
	private Timer reset = new Timer(1500, new TimeListener4());
	private JButton buttonb = new JButton("�˳�");
	private JButton buttond = new JButton("ȫ��");
	private JPanel panel2 = new JPanel();//��Ŵ���
	private JButton button[][] = new JButton[num][num];
	private int animal[][] = new int[num][num];
	private ImageIcon Iocn[] = new ImageIcon[9];
	// ����Ƿ����������ϵ�����
	private final int EMPTY = 0;// ��Ϊ0����Ϊ1
	// �����
	//	private Random rand = new Random();// new һ��������
	// ����Ƿ����������ϵ�����
	private boolean isThreeLinked;
	// ��ǵ�������
	private boolean isDoubleClicked = false;	
	private boolean isDoubleClickedTiger = false;
	private int x1;// ��¼��һ�α������ť��X����	
	private int y1;// ��¼��һ�α������ť��Y����
	public static  int grade = 0; //�÷�   ,static ���Դ�ֵ
	private boolean isDownAnimal = false;

	MyGames() {
		// ����ͼƬ
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
		this.add(panel1, BorderLayout.NORTH);	//������������������NORTH	
		panel2.setLayout(new GridLayout(num, num, 1, 1));		
		MyListener mylisten = new MyListener();
		int m;
		// ��ʼ����������
		for (int i = 0; i < num; i++)
			for (int j = 0; j < num; j++) {
				m = (int) (Math.random() * 7) +1 ; //����
				button[i][j] = new JButton(Iocn[m]);
				animal[i][j] = m;
				button[i][j].setSize(50, 50);
				button[i][j].addActionListener(mylisten);				
				button[i][j].setEnabled(false); //ͼ�ΰ�ť��Ч
				panel2.add(button[i][j]);
			}
		for(int i = 0;i < num;i++){
			for(int j = 0;j < num;j++)
				System.out.print(animal[i][j]+"  ");
			System.out.println();
		}
			
		this.add(panel2, BorderLayout.CENTER);	//�������м�	
		buttona.addActionListener(mylisten);
		buttonb.addActionListener(mylisten);
		buttond.addActionListener(mylisten);
	}

	// ��ʼ����������
	private void initAnimalMatrix() {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				// ���ѡȡ����
				animal[i][j] = (int) (Math.random() * 7) +1 ;
			}
		}
	}
	private void removeL(int x,int y,int status){
		if(animal[x][y] == EMPTY)return; 
		int tmp;
		int linked = 1;
		//L������
		if(status == 1){
			if (x - 1 >= 0) {
				tmp = x - 1;
				while (tmp >= 0 && animal[x][y] == animal[tmp][y]) {
					System.out.print("x������һ����x���,����Ϊ"+tmp+","+y+"��ǰ����Ϊ");
					printAnimal(tmp,y);
					animal[tmp][y] = EMPTY;
					linked++;
					tmp--;
					
				}
			}
			if (y + 1 < num) {
				tmp = y + 1;
				while (tmp < num && animal[x][y] == animal[x][tmp]) {
					System.out.print("���xy�ұߵ����,����Ϊ"+x+","+tmp+"��ǰ����Ϊ");
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
		// ��L����������_I
		if(status == 2){
			if (x - 1 >= 0) {
				tmp = x - 1;
				while (tmp >= 0 && animal[x][y] == animal[tmp][y]) {
					System.out.print("x������һ����x���,����Ϊ"+tmp+","+y+"��ǰ����Ϊ");
					printAnimal(tmp,y);
					animal[tmp][y] = EMPTY;
					linked++;
					tmp--;
					
				}
			}
			if (y - 1 >= 0) {
				tmp = y - 1;
				while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
					System.out.print("���xy��ߵ����,����Ϊ"+x+","+tmp+"��ǰ����Ϊ");
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
		//7��������
		if(status == 3){
			if (x + 1 < num) {
				tmp = x + 1;
				while (tmp < num && animal[x][y] == animal[tmp][y]) {
					System.out.print("x������һ����x���,����Ϊ"+tmp+","+y+"��ǰ����Ϊ");
					printAnimal(tmp,y);
					animal[tmp][y] = EMPTY;
					linked++;
					tmp++;
				}
			}
			if (y - 1 >= 0) {
				tmp = y - 1;
				while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
					System.out.print("���xy��ߵ����,����Ϊ"+x+","+tmp+"��ǰ����Ϊ");
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
		//��7������������1`
		if(status == 4){
			if (x + 1 < num) {
				tmp = x + 1;
				while (tmp < num && animal[x][y] == animal[tmp][y]) {
					System.out.print("x������һ����x���,����Ϊ"+tmp+","+y+"��ǰ����Ϊ");
					printAnimal(tmp,y);
					animal[tmp][y] = EMPTY;
					linked++;
					tmp++;
				}
			}
			if (y + 1 < num) {
				tmp = y + 1;
				while (tmp < num && animal[x][y] == animal[x][tmp]) {
					System.out.print("���xy�ұߵ����,����Ϊ"+x+","+tmp+"��ǰ����Ϊ");
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
	//������������ȥ����
	private void removeLinked(int x, int y) {
		if(animal[x][y] == EMPTY)return; //���շ���
		int n = 0; 
		int tmp;
		int linked = 1;             //��ͬ�Ķ�����
		//ѡ���ĵ㲻�����½磬���ѡ���·��Ķ�����ѡ����ͬ��linked++
		System.out.print("����removeLinked,����Ϊ"+x+","+y+"��ǰ����Ϊ");
		printAnimal(x,y);
		if (x + 1 < num) {
			tmp = x + 1;
			while (tmp < num && animal[x][y] == animal[tmp][y]) {
				System.out.print("x������һ����x���,����Ϊ"+tmp+","+y+"��ǰ����Ϊ");
				printAnimal(tmp,y);
				linked++;
				tmp++;
			}
		}
		//ѡ���ĵ㲻�����Ͻ磬���ѡ���Ϸ��Ķ�����ѡ����ͬ��linked++
		if (x - 1 >= 0) {
			tmp = x - 1;
			while (tmp >= 0 && animal[x][y] == animal[tmp][y]) {
				System.out.print("x������һ����x���,����Ϊ"+tmp+","+y+"��ǰ����Ϊ");
				printAnimal(tmp,y);
				linked++;
				tmp--;
			}
		}
		//�����ж����¶������Ƿ���3��������ͬ�ģ���ͬ������Ҳ������Ϊ��
		if (linked >= 3) {
			System.out.println("�������ĵ�Ϊ:" + x + "," + y);
			n = n+linked; 
			tmp = x + 1;
			//��X,Y��������
			while (tmp < num && animal[tmp][y] == animal[x][y]) {				
//				button[tmp][y].setEnabled(false);
				System.out.println(tmp+","+y+"������");
				printAnimal(tmp,y);
				printAnimal(x,y);
//				print();
				animal[tmp][y] = EMPTY;
				tmp++;
			}
			tmp = x - 1;
			//��X,Y��������
			while (tmp >= 0 && animal[tmp][y] == animal[x][y]) {
				System.out.println(tmp+","+y+"������");
				printAnimal(tmp,y);
				printAnimal(x,y);
				animal[tmp][y] = EMPTY;
				tmp--;
			}
			// ��ǰ���������ĵ㣬�Լ�Ҳ��Ϊ��
//			button[x][y].setEnabled(false);
//			print();
			if(linked >= 4){
				animal[x][y] = 8;  //��Ϊ�ϻ�����������������
			}
			else{
				animal[x][y] = EMPTY;
			}
			print();
			downAnimal.start();
		}
		tmp = 0;
		linked = 1;
		//ѡ���ĵ㲻�����ҽ磬���ѡ���ҷ��Ķ�����ѡ����ͬ��linked++
		if (y + 1 < num) {
			tmp = y + 1;
			while (tmp < num && animal[x][y] == animal[x][tmp]) {
				System.out.print("���xy�ұߵ����,����Ϊ"+x+","+tmp+"��ǰ����Ϊ");
				printAnimal(x,tmp);
				linked++;
				tmp++;
			}
		}
		//ѡ���ĵ㲻������磬���ѡ���󷽵Ķ�����ѡ����ͬ��linked++
		if (y - 1 >= 0) {
			tmp = y - 1;
			while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
				System.out.print("���xy��ߵ����,����Ϊ"+x+","+tmp+"��ǰ����Ϊ");
				printAnimal(x,tmp);
				linked++;
				tmp--;
			}
		}
		//�ٴ��ж���������������
		if (linked >= 3) {
			System.out.println("�������ĵ�Ϊ:" + x + "," + y);
			n = n+linked; 
			tmp = y + 1;
			while (tmp < num && animal[x][y] == animal[x][tmp]) {
				
//				button[x][tmp].setEnabled(false);
				System.out.println(x+","+tmp+"������");
				printAnimal(x,tmp);
				printAnimal(x,y);

//				print();
				animal[x][tmp] = EMPTY;
				tmp++;
			}
			tmp = y - 1;
			while (tmp >= 0 && animal[x][y] == animal[x][tmp]) {
				
//				button[x][tmp].setEnabled(false);
				System.out.println(x+","+tmp+"������");
				printAnimal(x,tmp);
				printAnimal(x,y);
				animal[x][tmp] = EMPTY;
				tmp--;
			}
			// ��ǰ���������ĵ�
			if(linked >= 4){
				animal[x][y] = 8;  //��Ϊ�ϻ�����������������
			}
			else{
				animal[x][y] = EMPTY;
				
			}
			print();
			downAnimal.start();
		}
		//�ӷ�
		grade += n*10;
		textarea1.setText(Integer.toString(grade));
//		System.out.println(grade + " ======"+x+" "+y +"�÷�:"+n*10);
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
	
	//ȫ������һ�飬�������ͬ��������
	private boolean globalSearch(int flag) {
		if (1 == flag) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (isThreeLinked(i, j)) {
						System.out.println("������������Ϊ��"+i+","+j);
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
					// ɾ�����������ĵ�
//					
					if (isThreeLinked(i, j)) {
					removeLinked(i, j);
					System.out.println("ɾ��ȫ������");
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

	// �Ƿ��������������ӵĵ�
	private boolean isThreeLinked(int x, int y) {
		int tmp;
		int linked = 1;
		//����
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
				//���ҽ���
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
				//���󽻻�
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
				//���½���
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
	

	// ���¶������
	private void updateAnimal() {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (animal[i][j] == EMPTY) {
					// ��������Ϊ��ֵ�ĵ��ٽ��������ֵ 1��7
					animal[i][j] = (int) (Math.random() * 7) +1 ;
					button[i][j].setEnabled(true);
				}
			}
		}
		System.out.println("��������");
	}

	// �����½���ȫ��ɨ�裬���Ϊ�������Ϸ���Ԫ���½�
	private boolean downAnimal() {
		int tmp;
		int flag = 0;
		//����������
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
		System.out.println("�½�����");
		return true;
		}
		else
			return false;
	}
	private void printAnimal(int i,int j){
		if(animal[i][j] == 1)
			System.out.println(animal[i][j]+"��");
		else if(animal[i][j] == 2)
			System.out.println(animal[i][j]+"ţ");
		else if(animal[i][j] == 3)
			System.out.println(animal[i][j]+"èͷӥ");
		else if(animal[i][j] == 4)
			System.out.println(animal[i][j]+"����");
		else if(animal[i][j] == 5)
			System.out.println(animal[i][j]+"����");
		else if(animal[i][j] == 6)
			System.out.println(animal[i][j]+"��");
		else if(animal[i][j] == 7)
			System.out.println(animal[i][j]+"���");
		else{
			System.out.println("Ϊ��");
		}
	}
	//������ʾ��ťͼ��
	
	private void print() { 
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				button[i][j].setIcon(Iocn[animal[i][j]]);
			}
		}
	}
	
//	private Runnable run = null;//����������߳�
			
			int flag = 0;  //�ϻ���־
	//��������
	private void swapAnimal(int x, int y) {		
		if ((x >= 0 && x <= num) && (y >= 0 && y <= num)) {
			// ������Ķ��������
    		int x2;
    		int y2;
    		//��������������ϻ��������Ժ���������������һ������Ȼ��ը
    		if(animal[x][y] == 8 || flag == 1){
    			if(!isDoubleClickedTiger){
    				isDoubleClickedTiger = true;
    				flag = 1;
    				x1 = x;
					y1 = y;
					System.out.println("�ϻ���һ�α������ĵ������ = " + x1 + "," + y1);
    			}
    			else{
    				x2 = x;
		    		y2 = y;
		    		flag = 0;
	    			isDoubleClickedTiger = false;
	    			System.out.println("�ϻ��ڶ��α������ĵ������ = " + x2 + "," + y2);
	//    			printAnimal(y2,x2);
	    			//�������������ľ���ֵ����1ʱ��Ϊ���ڵ�����
	    			if (1 == Math.abs(x2 - x1) && y2 == y1
	    					|| 1 == Math.abs(y2 - y1)&& x2 == x1){
	    				removeAll(x2,y2,x1,y1);
//	    				print();
//	    				downAnimal();
//	    				updateAnimal();
//						print();
//	    				updateAnimal.start();
//						if(isDeadMatrix()){
//	   						System.out.println("��������");
//	   						while (globalSearch(1)) {
//	   	  						//ȫ��ɨ�������������������ĵ�
//	   	  						globalSearch(2);
//	   	  						downAnimal();
//	   	  						//�ٴθ��¶������
//	   	 						updateAnimal();
//	   	 						print();
//	   	 					}  
//	   					}
//	   					else{
//	   						System.out.println("����");
//	   					}
	    			}
    			}
    		}
    		else{
				if (!isDoubleClicked || (x == x1 && y == y1)) { //��һ�ε���
					isDoubleClicked = true;
					x1 = x;
					y1 = y;
					System.out.println("��һ�α������ĵ������ = " + x1 + "," + y1);
					printAnimal(x1,y1);
					
				} else{               //��2�ε���
		    		x2 = x;
		    		y2 = y;
	    			isDoubleClicked = false;
	    			System.out.println("�ڶ��α������ĵ������ = " + x2 + "," + y2);
	    			printAnimal(x2,y2);
	    			//�������������ľ���ֵ����1ʱ��Ϊ���ڵ�����
	    			if (1 == Math.abs(x2 - x1) && y2 == y1
	    					|| 1 == Math.abs(y2 - y1)&& x2 == x1) {
	    				//-------------�������������ڵ������ֵ--------------
	    				int tmp;
	    				tmp = animal[x2][y2];
	    				animal[x2][y2] = animal[x1][y1];
	    				animal[x1][y1] = tmp;
	    				System.out.println("�Ѿ�����");
	    				print();
	//    				-----------------------------------------------------
	    				//�ж��Ƿ���7������
	    				if(isDown(x2,y2) && isLeft(x2,y2) || isDown(x1,y1) && isLeft(x1,y1)){
	    					System.out.println("7������");
	    					if(isDown(x2,y2) && isLeft(x2,y2))
	    						removeL(x2,y2,3);
	    					if(isDown(x1,y1) && isLeft(x1,y1))
	    						removeL(x1,y1,3);
	    				}
	    				//�ж��Ƿ��Ƿ�7������
	    				else if(isDown(x2,y2) && isRight(x2,y2) || isDown(x1,y1) && isRight(x1,y1)){
	    					System.out.println("��7������");
	    					if(isDown(x2,y2) && isRight(x2,y2))
	    						removeL(x2,y2,4);
	    					if(isDown(x1,y1) && isRight(x1,y1))
	    						removeL(x1,y1,4);
	    				}
	    				//�ж��Ƿ���L������
	    				else if(isUp(x2,y2) && isRight(x2,y2) || isUp(x1,y1) && isRight(x1,y1)){
	    					System.out.println("L������");
	    					if(isUp(x2,y2) && isRight(x2,y2))
	    						removeL(x2,y2,1);
	    					if(isUp(x1,y1) && isRight(x1,y1))
	    						removeL(x1,y1,1);
	    				}
	    				//�ж��Ƿ��Ƿ�L������
	    				else if(isUp(x2,y2) && isLeft(x2,y2) || isUp(x1,y1) && isLeft(x1,y1)){
	    					System.out.println("��L������");
	    					if(isUp(x2,y2) && isLeft(x2,y2))
	    						removeL(x2,y2,2);
	    					if(isUp(x1,y1) && isLeft(x1,y1))
	    						removeL(x1,y1,2);
	    				}
	    				else{
	    				if (isThreeLinked(x2, y2)||isThreeLinked(x1, y1)){
	    					System.out.println("��������");
	    					if(isThreeLinked(x2, y2)){
	    						removeLinked(x2, y2);
//	    						timer1.start();
	    					}
	    					if(isThreeLinked(x1, y1)){
	    						removeLinked(x1, y1);
//	    						timer1.start();
	    					}
							
	    					System.out.println("��ӡ��");	
//	    					wait.start();
//	    					 updateAnimal.start();
	//    					//����ȥ���Ϸ��Ķ����½�
//	    					downAnimal.start();
//							timer1.start();
	    					//�����Ϸ�������������µĶ�����¶������
	   					
//	   					if(isDeadMatrix()){
//	   						System.out.println("��������");
//	   						while(globalSearch(1)){
//	   	  						//ȫ��ɨ�������������������ĵ�
//	   							System.out.println("��ȫ������");
//	   	  						globalSearch(2);
//	   	  						downAnimal();
//	   	  						updateAnimal();
//	   	  						updateAnimal.start();
////   	  						//�ٴθ��¶������
////   	 						updateAnimal();
////	   	 						print();
//	   						}
//	   	 					
//	   					}
//	   					else{
//	   						System.out.println("����");
//	   					}
	   					
	    			}
	    				else {//û������������ͬ�ĵ㣬��������  
	    					System.out.println("��������");
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
	
	//���³�ʼ��,ֱ��û��һ���Ѿ������ľ���
	public void init() {
		do {
			System.out.println("���³�ʼ��");
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

	//������
	// ������,����¼�
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
						button[i][j].setEnabled(true); //ͼ�ΰ�ť��Ч
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
						System.out.println("��" + i + " " + j + "��");
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
					System.out.println("��������");
					while(globalSearch(1)){
 						//ȫ��ɨ�������������������ĵ�
						System.out.println("��ȫ������");
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
					System.out.println("����");
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
			System.out.println("time4 ��������");
			
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
				//��ʱ������
				for (int i = 0; i < num; i++)
					for (int j = 0; j < num; j++) {
						button[i][j].setEnabled(false); //ͼ�ΰ�ť��Ч
					}
				buttona.setEnabled(true);
				Rank rank = new Rank();
				String name = Main.Name;
				Date time= new java.sql.Date(new java.util.Date().getTime());
				rank.setName(name);
				rank.setScore(grade);
				rank.setTime((java.sql.Date) time);
				if(RankOperate.add(rank)){
					System.out.println("����ɹ���");
					//id������������ľ���id�����Ǹ�
					List<Rank> list = RankOperate.queryMax();
					Rank ra = list.get(0);
					System.out.println(ra.getId());
					id = ra.getId();
					//��Ϸ����������壡
					setVisible(false);
					Over.main(null);
				}
				else
					System.out.println("����ʧ��");
			}
		}
	}
}
