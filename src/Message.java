import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;


public class Message extends JFrame {
	public static JButton back = new JButton("�˳���¼");
	public static JButton begin = new JButton("��ʼ��Ϸ");
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
        	JOptionPane.showMessageDialog(this, "�û���������.", "֪ͨ", JOptionPane.ERROR_MESSAGE);
        }
        else if(i == 1){
        	JOptionPane.showMessageDialog(this, "�û������������.", "֪ͨ", JOptionPane.ERROR_MESSAGE);
        }
        else{
        	this.setTitle("��¼�ɹ�");
        	List<Rank> list = RankOperate.queryUserMax(name);
        	JLabel news = null;
        	if(list.isEmpty()){
        	 news = new JLabel("��ӭ���� "+name+"������δ���й���ϷŶ��",JLabel.CENTER);
        	}
        	else{
        		Rank rank = list.get(0);
        		news = new JLabel("��ӭ���� "+name+"����Ŀǰ����߷�Ϊ��" +rank.getScore() ,JLabel.CENTER);
        	
        	}
        	JLabel label1 = new JLabel("��ע�����Ͻǵĵ���ʱ��Ŷ��",JLabel.CENTER);
        	ImageIcon Iocn = new ImageIcon("image//tiger.png");
        	JLabel label2 = new JLabel();
        	JLabel label3 = new JLabel("��ͬʱ����4��������ͬС��������ϻ����֡�");
        	JLabel label4 = new JLabel("�����ϻ���С�������ϻ���Ե�����С��Ŷ��");
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
				System.out.println("��ʼ��Ϸ");
				dispose();
				Main start= new Main();
				start.main(name);  //������Ϸ��壬��name��ֵ��main������
			}
			if(e.getSource() == back){
				System.out.println("�˳���¼");
				dispose();
				Login login = new Login();
				
			}
		}
	}
}

