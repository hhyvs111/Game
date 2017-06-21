import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//JTable中负责显示的是TableCellRenderer.而JTable默认是DefaultTableCellRenderer来渲染，其实就是个JLable。你可以看一下DefaultTableCellRenderer的API，  
//public Component getTableCellRendererComponent(JTable table, Object value,
//boolean isSelected, boolean hasFocus, int row, int column)
//这个方法决定了显示的效果。
//而默认只是把value toString()了。
public class SupportIconTableCellRenderer extends DefaultTableCellRenderer {

@Override
//重写defulttablecellrenderer类！
public Component getTableCellRendererComponent(JTable table, Object value,  
        boolean isSelected, boolean hasFocus, int row, int column) { 
            //根据column判断是不是需要显示图片的列，是的话就根据value生成ImageIcon，返回相应的JLabel
            if(column == 0 && row == 0 ){//是
                return new JLabel(new ImageIcon("image//NO1.png"));
            }
            else if(column == 0 && row == 1){
            	return new JLabel(new ImageIcon("image//NO2.png"));
            }
            else if(column == 0 && row == 2){
            	return new JLabel(new ImageIcon("image//NO3.png"));
            }
            else{//否
                return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
            }
     }
}

