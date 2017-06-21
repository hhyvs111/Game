import java.awt.Dimension;

import javax.swing.JFrame;
public class Main {
	public static int num ;
	public static String Name;
	public static void main(String name) {
		num = 8;
		Name = name;
		JFrame frame = new JFrame();
		frame.setSize(400, 300);
		MyGames frame1 = new MyGames();
		String title = "消消乐，" + name+" 欢迎您";
		frame1.setTitle(title);
		frame1.setSize(500, 500);
		Dimension screen = frame1.getToolkit().getScreenSize();
		frame1.setLocation((screen.width - frame1.getSize().width) / 2,
                (screen.height - frame1.getSize().height) / 2);
		frame1.setVisible(true);
		//关闭窗口的同时关闭进程，减少内存消耗！
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.init();
	}

}
