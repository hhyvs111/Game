import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//JTable�и�����ʾ����TableCellRenderer.��JTableĬ����DefaultTableCellRenderer����Ⱦ����ʵ���Ǹ�JLable������Կ�һ��DefaultTableCellRenderer��API��  
//public Component getTableCellRendererComponent(JTable table, Object value,
//boolean isSelected, boolean hasFocus, int row, int column)
//���������������ʾ��Ч����
//��Ĭ��ֻ�ǰ�value toString()�ˡ�
public class SupportIconTableCellRenderer extends DefaultTableCellRenderer {

@Override
//��дdefulttablecellrenderer�࣡
public Component getTableCellRendererComponent(JTable table, Object value,  
        boolean isSelected, boolean hasFocus, int row, int column) { 
            //����column�ж��ǲ�����Ҫ��ʾͼƬ���У��ǵĻ��͸���value����ImageIcon��������Ӧ��JLabel
            if(column == 0 && row == 0 ){//��
                return new JLabel(new ImageIcon("image//NO1.png"));
            }
            else if(column == 0 && row == 1){
            	return new JLabel(new ImageIcon("image//NO2.png"));
            }
            else if(column == 0 && row == 2){
            	return new JLabel(new ImageIcon("image//NO3.png"));
            }
            else{//��
                return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
            }
     }
}

