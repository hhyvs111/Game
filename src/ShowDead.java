import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * ��һ����GUI��Ŀ����һ��������Ҫ���������Ϣ����������쳣��Ϣ ���Զ��������ڣ���ʾ���棬�û���������ȥ�رգ�����û�û�������رմ��ڣ��ô���30����Զ��رա�
 * ��Ϊֻ�Ǽ򵥵���ʾ�����ӦJDialog��JOptionPane����ʡ�ܶ���룬��JOptionPane��û�з������������Զ��رա�
 * ����һ��API���ֿ�������JOptionPane������JDialog��������ʡȥ�ܶ����ˡ�
 * @author 
 * @since 
 */
public class ShowDead {

    /**
     * ���ԶԻ����Զ��ر�
     * 
     * @param args
     */
    public static void main(String[] args) {

        JOptionPane op = new JOptionPane("û�пɽ�����Ԫ����",JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = op.createDialog("��ʾ");
        
        // ����һ���¼�ʱ��
        Timer timer = new Timer();

        // 30�� ��ִ�и�����
        timer.schedule(new TimerTask() {
            public void run() {
                dialog.setVisible(false);
                dialog.dispose();
            }
        }, 1000);
        Dimension screen = dialog.getToolkit().getScreenSize();
        dialog.setLocation((screen.width - dialog.getSize().width) / 2,
                (screen.height - dialog.getSize().height) / 2);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.setModal(false);
        dialog.setVisible(true);
    }

}
