import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

 
public class RankList  extends JFrame{
 
    private static JTable table;
    private static DefaultTableModel model;
    JButton buttona = new JButton();
    JButton buttonb = new JButton();
    String name = Main.Name;
    int grade = MyGames.grade;
    static int Id = MyGames.id;
    static int Row = -1;
    SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
    /**
     * @param args
     */
    RankList(){
    	Id = MyGames.id;
    	System.out.println("当前id为"+ Id);
    	this.setTitle("排行榜, "+name+" 您的分数为："+grade);
    	this.setSize(800, 400);
    	String[] columns = { "排名", "姓名", "分数","时间" };
        //数据对象,10行，4列
        Object[][] data = new Object[10][4];  
        // 把数据库查出来的数据放到表格里
        List<Rank> list = RankOperate.queryTen();
        for (int i = 0; i < 10; i++) {
        	if(i < list.size()){
        		Rank ra = list.get(i);	
            	System.out.println(ra.getId() + " "+ ra.getName() + " "+ ra.getScore());
            	if(Id == ra.getId()){
            		Row = i;
            		System.out.println(Row+" 变红"+Id+" "+ra.getId());
            	}
           data[i][0] = i+1;
 		   data[i][1] = ra.getName();
 		   data[i][2] = ra.getScore();
 		   data[i][3] = ra.getTime();
        	}
// 		  model.addRow(new String[] {Integer.toString(i+1), 
// 				  				ra.getName(),
// 				  				Integer.toString(ra.getScore()),sdf.format(ra.getTime())});
        	else{
        		data[i][0] = i+1;
        		data[i][1] = data[i][2] = data[i][3] = "-";
        	}
 		  }
        model = new DefaultTableModel(data, columns);
        table = new JTable(model){
        	//这个代码是使整个JTable不可编辑。如果控制row和column的值，可以控制任何一个单元格可编辑或不可编辑。    
        	public boolean isCellEditable(int row, int column) {
        	     return false;
        	}
        	Color color = getForeground();  
        	//本次游戏的排名，查看自己的位置！
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {  
                Component component = super.prepareRenderer(renderer, row, column);  
                //当某一行与id匹配则单元格的背景 字体设为红色  
               if (row == Row) {  
                   component.setForeground(Color.RED);  
               }else{ //否则 则为之前的颜色  
                   component.setForeground(color);  
               }  
               return component;  
            }   
        };
         //内容居中
        SupportIconTableCellRenderer   r   =   new   SupportIconTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        table.setDefaultRenderer(Object.class,r);
        table.setRowHeight(30);
        JScrollPane panel1 = new JScrollPane(table);
        this.setLayout(new BorderLayout(1,1));
        this.add(panel1, BorderLayout.CENTER);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        buttona = new JButton("再玩一次");
        buttonb = new JButton("退出游戏");
        panel2.add(buttona);
        panel2.add(buttonb);
        this.add(panel2, BorderLayout.SOUTH); 
		MyListener mylisten = new MyListener();
		buttona.addActionListener(mylisten);
		buttonb.addActionListener(mylisten);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    public static void main(String[] args) {
    	RankList ranklist = new RankList();
        Dimension screen = ranklist.getToolkit().getScreenSize();
        ranklist.setLocation((screen.width - ranklist.getSize().width) / 2,
                (screen.height - ranklist.getSize().height) / 2);
    }
    class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttona) {
				System.out.println("再来一次");
				setVisible(false);
				Main.main(name);
//				System.exit(1);
			}
			if(e.getSource() == buttonb){
				System.out.println("结束游戏");
				System.exit(1);
			}
		}
	}

}
