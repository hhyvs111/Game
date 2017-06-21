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
    	System.out.println("��ǰidΪ"+ Id);
    	this.setTitle("���а�, "+name+" ���ķ���Ϊ��"+grade);
    	this.setSize(800, 400);
    	String[] columns = { "����", "����", "����","ʱ��" };
        //���ݶ���,10�У�4��
        Object[][] data = new Object[10][4];  
        // �����ݿ����������ݷŵ������
        List<Rank> list = RankOperate.queryTen();
        for (int i = 0; i < 10; i++) {
        	if(i < list.size()){
        		Rank ra = list.get(i);	
            	System.out.println(ra.getId() + " "+ ra.getName() + " "+ ra.getScore());
            	if(Id == ra.getId()){
            		Row = i;
            		System.out.println(Row+" ���"+Id+" "+ra.getId());
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
        	//���������ʹ����JTable���ɱ༭���������row��column��ֵ�����Կ����κ�һ����Ԫ��ɱ༭�򲻿ɱ༭��    
        	public boolean isCellEditable(int row, int column) {
        	     return false;
        	}
        	Color color = getForeground();  
        	//������Ϸ���������鿴�Լ���λ�ã�
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {  
                Component component = super.prepareRenderer(renderer, row, column);  
                //��ĳһ����idƥ����Ԫ��ı��� ������Ϊ��ɫ  
               if (row == Row) {  
                   component.setForeground(Color.RED);  
               }else{ //���� ��Ϊ֮ǰ����ɫ  
                   component.setForeground(color);  
               }  
               return component;  
            }   
        };
         //���ݾ���
        SupportIconTableCellRenderer   r   =   new   SupportIconTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        table.setDefaultRenderer(Object.class,r);
        table.setRowHeight(30);
        JScrollPane panel1 = new JScrollPane(table);
        this.setLayout(new BorderLayout(1,1));
        this.add(panel1, BorderLayout.CENTER);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        buttona = new JButton("����һ��");
        buttonb = new JButton("�˳���Ϸ");
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
				System.out.println("����һ��");
				setVisible(false);
				Main.main(name);
//				System.exit(1);
			}
			if(e.getSource() == buttonb){
				System.out.println("������Ϸ");
				System.exit(1);
			}
		}
	}

}
