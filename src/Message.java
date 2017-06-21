import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;


public class Message extends JFrame {
	public static JButton back = new JButton("退出登录");
	public static JButton begin = new JButton("开始游戏");
	public static String name = Login.name.getText();
	public Message(int i){
		this.setLayout(new FlowLayout());
//		this.setLayout(new GridLayout(5,1));
		this.setSize(320, 185);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2,
                (screen.height - this.getSize().height) / 2);
        JPanel p1 = new JPanel();
        if(i == 0){
        	JOptionPane.showMessageDialog(this, "用户名不存在.", "通知", JOptionPane.ERROR_MESSAGE);
        }
        else if(i == 1){
        	JOptionPane.showMessageDialog(this, "用户名或密码错误.", "通知", JOptionPane.ERROR_MESSAGE);
        }
        else{
        	this.setTitle("登录成功");
        	List<Rank> list = RankOperate.queryUserMax(name);
        	JLabel news = null;
        	if(list.isEmpty()){
        	 news = new JLabel("欢迎您！ "+name+"，您还未进行过游戏哦！",JLabel.CENTER);
        	}
        	else{
        		Rank rank = list.get(0);
        		news = new JLabel("欢迎您！ "+name+"，您目前的最高分为：" +rank.getScore() ,JLabel.CENTER);
        	
        	}
        	JLabel label1 = new JLabel("请注意右上角的倒计时栏哦！",JLabel.CENTER);
        	ImageIcon Iocn = new ImageIcon("image//tiger.png");
        	JLabel label2 = new JLabel();
        	JLabel label3 = new JLabel("当同时消除4个以上相同小动物会有老虎出现→");
        	JLabel label4 = new JLabel("若将老虎与小猪交换，老虎会吃掉所有小猪哦！");
        	label2.setIcon(Iocn);
        	this.add(news);
        	this.add(label1);
        	this.add(label3);
        	this.add(label2);
        	this.add(label4);
        	this.add(begin);
        	this.add(back);
        	

//        	this.add(begin);
        	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		this.setVisible(true);
        	MyListener mylisten = new MyListener();
    		begin.addActionListener(mylisten);
    		back.addActionListener(mylisten);
        }
	
	}
    class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == begin){
				System.out.println("开始游戏");
				dispose();
				Main start= new Main();
				start.main(name);  //调用游戏面板，将name传值到main函数里
			}
			if(e.getSource() == back){
				System.out.println("退出登录");
				dispose();
				Login login = new Login();
				
			}
		}
	}
}

