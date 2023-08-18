package action;

import javax.swing.*;
import java.awt.*;

public class mineTimer extends JLabel implements Runnable {
    int width = 75, height = 75;
    int x = 200, y = 150;

    public static int mineSecond;

    public mineTimer(int second) {
        setOpaque(true);
        setBounds(x, y, width, height);
        setForeground(Color.BLUE);
        setText(second + "");
        setFont(new Font("맑은고딕", Font.PLAIN, 50));
        setHorizontalAlignment(JLabel.CENTER);

        this.mineSecond = second;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);	// 1초
            } catch (Exception e) {
                break;
            }

            if (mineSecond > 0) {
                mineSecond -= 1;		// 1초씩 줄어듦
                setText(mineSecond + "");
            } else {
                //System.out.println("종료");
                break;
            }
        }
    }
}
