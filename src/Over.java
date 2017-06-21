
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
	JButton buttona = new JButton("查看排行榜");
	JButton buttonb = new JButton("退出游戏");
	public Over(){ 
		int grade = MyGames.grade;
		String name = Main.Name;
		this.setTitle("游戏结束");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane=new JPanel();//容器
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		this.setContentPane(contentPane);
		//创建具有指定行数、列数以及组件水平、纵向一定间距的网格布局。
		contentPane.setLayout(new GridLayout(3,1,5,5));//3行一列
		JLabel label = new JLabel("游戏结束！"+ name +"，您的分数为: " + grade,JLabel.CENTER);
		List<Rank> list = RankOperate.queryTen();
		int size = list.size();
		//判断是否进入排行榜
		if(size < 10){
			JLabel labela = new JLabel("恭喜您进入前10！",JLabel.CENTER);
			contentPane.add(labela);
		}
		else{
			int i = 0;
		while(true){
			Rank ra = list.get(i);
			int gradeMin = ra.getScore();
			if(grade > gradeMin && i < 10){
				JLabel labela = new JLabel("恭喜您进入排行榜！",JLabel.CENTER);
				contentPane.add(labela);
				break;
			}
			else
				i++;
			if(i == 10)
			{
				JLabel labela = new JLabel("很可惜，差一点就进入排行榜！",JLabel.CENTER);
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
				System.out.println("查看排行榜");
				setVisible(false);
				RankList.main(null);
//				System.exit(1);
			}
			if(e.getSource() == buttonb){
				System.out.println("结束游戏");
				System.exit(1);
			}
		}
	}
}
