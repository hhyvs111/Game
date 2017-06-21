
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Over extends JFrame{
	JButton buttona = new JButton("�鿴���а�");
	JButton buttonb = new JButton("�˳���Ϸ");
	public Over(){ 
		int grade = MyGames.grade;
		String name = Main.Name;
		this.setTitle("��Ϸ����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane=new JPanel();//����
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		this.setContentPane(contentPane);
		//��������ָ�������������Լ����ˮƽ������һ���������񲼾֡�
		contentPane.setLayout(new GridLayout(3,1,5,5));//3��һ��
		JLabel label = new JLabel("��Ϸ������"+ name +"�����ķ���Ϊ: " + grade,JLabel.CENTER);
		List<Rank> list = RankOperate.queryTen();
		int size = list.size();
		//�ж��Ƿ�������а�
		if(size < 10){
			JLabel labela = new JLabel("��ϲ������ǰ10��",JLabel.CENTER);
			contentPane.add(labela);
		}
		else{
			int i = 0;
		while(true){
			Rank ra = list.get(i);
			int gradeMin = ra.getScore();
			if(grade > gradeMin && i < 10){
				JLabel labela = new JLabel("��ϲ���������а�",JLabel.CENTER);
				contentPane.add(labela);
				break;
			}
			else
				i++;
			if(i == 10)
			{
				JLabel labela = new JLabel("�ܿ�ϧ����һ��ͽ������а�",JLabel.CENTER);
				contentPane.add(labela);
				break;
			}
		}
	}
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		contentPane.add(label);
		contentPane.add(panel);
		panel.add(buttona);
		panel.add(buttonb);
		MyListener mylisten = new MyListener();
		buttona.addActionListener(mylisten);
		buttonb.addActionListener(mylisten);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
    public static void main(String[]args){
    	Over over = new Over();
    	over.setSize(250, 180);
        Dimension screen = over.getToolkit().getScreenSize();
        over.setLocation((screen.width - over.getSize().width) / 2,
                (screen.height - over.getSize().height) / 2);
    }
    class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttona) {
				System.out.println("�鿴���а�");
				setVisible(false);
				RankList.main(null);
//				System.exit(1);
			}
			if(e.getSource() == buttonb){
				System.out.println("������Ϸ");
				System.exit(1);
			}
		}
	}
}
