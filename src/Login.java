
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
	
	JButton buttona = new JButton("��¼");
	JButton buttonb = new JButton("ע��");
	
	public static JTextField name = new JTextField();
	public static JPasswordField password = new JPasswordField();
	public Login(){
		this.setSize(300, 200);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2,
                (screen.height - this.getSize().height) / 2);
		this.setTitle("��¼");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(4,0));
		//��������ָ�������������Լ����ˮƽ������һ���������񲼾֡�
		JLabel label1 = new JLabel("��ӭ���������֣��������ʺſ�ʼ��Ϸ��",JLabel.CENTER); 
		JLabel label2=new JLabel("�� �� ����");
		JLabel label3=new JLabel("��     �룺");
		name.setColumns(10);
		password.setColumns(10);
		JPanel panel1 = new JPanel();//���û����������
		panel1.setLayout(new FlowLayout());
		panel1.add(label2);
		panel1.add(name);
		JPanel panel2 = new JPanel();//������������
		panel2.setLayout(new FlowLayout());
		panel2.add(label3);
		panel2.add(password);
		
		JPanel panel3 = new JPanel();   //�ŵ�¼
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
					 * ��Ҫ�޸�Ƥ���Ļ���ֻ��Ҫ���ģ�������������Ĳ���������ĳ�ʲô�������Դ򿪣�Referenced Libraries -> ���substance.jar -> �ҵ�org.jvnet.substance.skin�����  -> �������SubstanceDustCoffeeLookAndFeel �滻�� �ոմ򿪵İ��µ�����һ����Substance....LookAndFeel������
					 */
					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
					//���簴������Ĳ��裬���Կ���һ���У�"SubstanceOfficeBlue2007LookAndFeel.class"����Ҫ�滻�����Ƥ�����Ϳ�������������д
					//UIManager
					//.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// ����һ�£�Ƥ���Ϳ��Ի���
					// ��Ҫ��ϸ�˽��ͬѧ������ȥ�ٶ��������������substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Login login=new Login();
				login.setVisible(true);
			}
		});
    	
    	
    }
    private void clearText() {//����ı���, ���������� 
//        name.setText(""); 
        password.setText(""); 
    }
    class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttona) {
				List<User> list = UserOperate.queryUser(name.getText());
				if(list.isEmpty()){
					Message message = new Message(1);//����message������Ϣ����
					System.out.println("�û�������");
					clearText();//����ı��� ����������
				}
				else{
					String key = new String(password.getPassword());// ȡ������
					User user = list.get(0);
					if(user.getPassword().equals(key)){
						setVisible(false);
						Message message = new Message(2);//����message������Ϣ����
						System.out.println("��¼�ɹ�");
					}
					else{
						Message message = new Message(1);
						System.out.println("�������");
						clearText();//����ı��� ����������
					}
				}
			}
			if(e.getSource() == buttonb){
				Register re = new Register();
				System.out.println("ǰ��ע��");
				setVisible(false);
			}
		}
	}
}
