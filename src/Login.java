
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame{
	
	JButton buttona = new JButton("登录");
	JButton buttonb = new JButton("注册");
	
	public static JTextField name = new JTextField();
	public static JPasswordField password = new JPasswordField();
	public Login(){
		this.setSize(300, 200);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2,
                (screen.height - this.getSize().height) / 2);
		this.setTitle("登录");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(4,0));
		//创建具有指定行数、列数以及组件水平、纵向一定间距的网格布局。
		JLabel label1 = new JLabel("欢迎来到消消乐！请输入帐号开始游戏！",JLabel.CENTER); 
		JLabel label2=new JLabel("用 户 名：");
		JLabel label3=new JLabel("密     码：");
		name.setColumns(10);
		password.setColumns(10);
		JPanel panel1 = new JPanel();//放用户名和输入框
		panel1.setLayout(new FlowLayout());
		panel1.add(label2);
		panel1.add(name);
		JPanel panel2 = new JPanel();//放密码和输入框
		panel2.setLayout(new FlowLayout());
		panel2.add(label3);
		panel2.add(password);
		
		JPanel panel3 = new JPanel();   //放登录
		panel3.setLayout(new FlowLayout());
		
		panel3.add(buttona);
		panel3.add(buttonb);
		this.add(label1);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		MyListener mylisten = new MyListener();
		buttona.addActionListener(mylisten);
		buttonb.addActionListener(mylisten);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
    public static void main(String[]args){
    	EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					/*
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced Libraries -> 点击substance.jar -> 找到org.jvnet.substance.skin这个包  -> 将下面的SubstanceDustCoffeeLookAndFeel 替换成 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
					//例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					//UIManager
					//.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// 运行一下，皮肤就可以换了
					// 想要详细了解的同学，可以去百度这个第三方包：substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Login login=new Login();
				login.setVisible(true);
			}
		});
    	
    	
    }
    private void clearText() {//清空文本框, 密码框的输入 
//        name.setText(""); 
        password.setText(""); 
    }
    class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttona) {
				List<User> list = UserOperate.queryUser(name.getText());
				if(list.isEmpty()){
					Message message = new Message(1);//调用message弹出消息窗口
					System.out.println("用户不存在");
					clearText();//清空文本框 密码框的输入
				}
				else{
					String key = new String(password.getPassword());// 取得密码
					User user = list.get(0);
					if(user.getPassword().equals(key)){
						setVisible(false);
						Message message = new Message(2);//调用message弹出消息窗口
						System.out.println("登录成功");
					}
					else{
						Message message = new Message(1);
						System.out.println("密码错误");
						clearText();//清空文本框 密码框的输入
					}
				}
			}
			if(e.getSource() == buttonb){
				Register re = new Register();
				System.out.println("前往注册");
				setVisible(false);
			}
		}
	}
}
