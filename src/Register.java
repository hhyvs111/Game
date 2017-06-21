import java.awt.*; 
import java.awt.event.*;
import java.util.List;

import javax.swing.*; 

public class Register extends JFrame implements ActionListener { 
	
    // ������Ҫ����� 
    public JTextField name = new JTextField();  //�˻�
    public JTextField password = new JTextField();  //����
    public JTextField phone = new JTextField();   //�ֻ���
    
    public JButton buttona = new JButton("ע��");
    public JButton buttonb = new JButton("ȡ��");
    JPasswordField jpf1;//�����
    public Register(){
    	this.setTitle("ע��");// ���ڱ���
    	this.setLayout(new FlowLayout());
		this.setSize(270, 200);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2,
                (screen.height - this.getSize().height) / 2);
        JPanel panel1 = new JPanel();//���û����������
		panel1.setLayout(new FlowLayout());
		JLabel JL1 = new JLabel("�� �� �� �� �� ��:");
		panel1.add(JL1);
		name.setColumns(10);
		panel1.add(name);
		
		JPanel panel2 = new JPanel();//������������
		panel2.setLayout(new FlowLayout());
		JLabel JL2 = new JLabel("��  ��  ��  ��  ��:");
		panel2.add(JL2); 
		password.setColumns(10);
		panel2.add(password);
		
		JPanel panel3 = new JPanel();//������������
		panel3.setLayout(new FlowLayout());
		JLabel JL3 = new JLabel("�� �� �� �� �� ��:");
		panel3.add(JL3);
		phone.setColumns(10);
		panel3.add(phone);
		
		JPanel panel4 = new JPanel();//��ע��ͷ���
		panel4.setLayout(new FlowLayout());
		panel4.add(buttona);
		panel4.add(buttonb);
		buttona.addActionListener(this);// ��Ӷ�����Ӧ��
		buttonb.addActionListener(this);// ��Ӷ�����Ӧ��
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }
    
    private void clearText() {//����ı���, ���������� 
        name.setText(""); 
        password.setText(""); 
        phone.setText("");
    }
    //������Ӧ
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buttona){
			List<User> list = UserOperate.queryUser(name.getText());
			//�����û����Ƿ��ظ�
			if(phone.getText().length() != 11){
				JOptionPane.showMessageDialog(this, "��������ȷ���ֻ�����", "֪ͨ", JOptionPane.ERROR_MESSAGE);
			}
			else{
			if(list.isEmpty()){
				User user = new User();
				user.setName(name.getText());
				user.setPassword(password.getText());
				user.setPhone(phone.getText());
				if(UserOperate.add(user)){
					System.out.println("ע��ɹ�");
					JOptionPane.showMessageDialog(this, "ע��ɹ���");
					setVisible(false);
					Login login = new Login();
				}
//				message.main(0);
			}
			else{
				JOptionPane.showMessageDialog(this, "�û����Ѵ���.", "֪ͨ", JOptionPane.ERROR_MESSAGE);
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
