import java.awt.*; 
import java.awt.event.*;
import java.util.List;

import javax.swing.*; 

public class Register extends JFrame implements ActionListener { 
	
    // 申明需要的组件 
    public JTextField name = new JTextField();  //账户
    public JTextField password = new JTextField();  //密码
    public JTextField phone = new JTextField();   //手机号
    
    public JButton buttona = new JButton("注册");
    public JButton buttonb = new JButton("取消");
    JPasswordField jpf1;//密码框
    public Register(){
    	this.setTitle("注册");// 窗口标题
    	this.setLayout(new FlowLayout());
		this.setSize(270, 200);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2,
                (screen.height - this.getSize().height) / 2);
        JPanel panel1 = new JPanel();//放用户名和输入框
		panel1.setLayout(new FlowLayout());
		JLabel JL1 = new JLabel("请 输 入 用 户 名:");
		panel1.add(JL1);
		name.setColumns(10);
		panel1.add(name);
		
		JPanel panel2 = new JPanel();//放密码和输入框
		panel2.setLayout(new FlowLayout());
		JLabel JL2 = new JLabel("请  输  入  密  码:");
		panel2.add(JL2); 
		password.setColumns(10);
		panel2.add(password);
		
		JPanel panel3 = new JPanel();//放密码和输入框
		panel3.setLayout(new FlowLayout());
		JLabel JL3 = new JLabel("请 输 入 手 机 号:");
		panel3.add(JL3);
		phone.setColumns(10);
		panel3.add(phone);
		
		JPanel panel4 = new JPanel();//放注册和返回
		panel4.setLayout(new FlowLayout());
		panel4.add(buttona);
		panel4.add(buttonb);
		buttona.addActionListener(this);// 添加动作响应器
		buttonb.addActionListener(this);// 添加动作响应器
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }
    
    private void clearText() {//清空文本框, 密码框的输入 
        name.setText(""); 
        password.setText(""); 
        phone.setText("");
    }
    //动作相应
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buttona){
			List<User> list = UserOperate.queryUser(name.getText());
			//检验用户名是否重复
			if(phone.getText().length() != 11){
				JOptionPane.showMessageDialog(this, "请输入正确的手机号码", "通知", JOptionPane.ERROR_MESSAGE);
			}
			else{
			if(list.isEmpty()){
				User user = new User();
				user.setName(name.getText());
				user.setPassword(password.getText());
				user.setPhone(phone.getText());
				if(UserOperate.add(user)){
					System.out.println("注册成功");
					JOptionPane.showMessageDialog(this, "注册成功！");
					setVisible(false);
					Login login = new Login();
				}
//				message.main(0);
			}
			else{
				JOptionPane.showMessageDialog(this, "用户名已存在.", "通知", JOptionPane.ERROR_MESSAGE);
				clearText();
			}
			
		}
		}
		if(e.getSource() == buttonb){
			setVisible(false);
			Login login = new Login();
		}
		
	}
}
