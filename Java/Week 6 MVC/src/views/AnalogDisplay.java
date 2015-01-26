package views;
//simplified by Monica  from Sun's Clock applet demo


import interfaces.Observer;
import java.awt.*;
import javax.swing.*;
import model.Clock;
public class AnalogDisplay extends JPanel implements Observer
{
	private Clock clockdata;

	private int lastxm,lastym, lastxh, lastyh;  // Dimensions used to draw hands 
    private Font clockFaceFont;          // Font for number display on clock
    private Color handColor;             // Color of main hands and dial
    private Color numberColor;           // Color of numbers
    private int xcenter = 125, ycenter = 75; // Center position
    private int xh, yh, xm, ym;	
	public AnalogDisplay (Clock clock)
	{
		super();
		this.clockdata = clock;	
		clock.registerObserver(this);
        lastxm = lastym = lastxh = lastyh = 0;
        xh = yh = xm = ym = 0;
        clockFaceFont = new Font("Serif", Font.BOLD, 14);
        handColor = Color.BLUE;
        numberColor = Color.BLACK;
        this.setSize(150,150);
        update();
	}
	
	public void update()
	{ 
        int m = clockdata.getRemMins();
        int h = clockdata.getWholeHours();
        // Set position of the ends of the hands
        xm = (int) (Math.cos(m * Math.PI / 30 - Math.PI / 2) * 40 + xcenter);
        ym = (int) (Math.sin(m * Math.PI / 30 - Math.PI / 2) * 40 + ycenter);
        xh = (int) (Math.cos((h*30 + m / 2) * Math.PI / 180 - Math.PI / 2) * 30
                   + xcenter);
        yh = (int) (Math.sin((h*30 + m / 2) * Math.PI / 180 - Math.PI / 2) * 30
                   + ycenter);
        repaint();
	}	
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.setFont(clockFaceFont);
		// Erase if necessary
		this.setOpaque(false);
		g.setColor(getBackground());
		if (xm != lastxm || ym != lastym) {
			g.drawLine(xcenter, ycenter-1, lastxm, lastym);
			g.drawLine(xcenter-1, ycenter, lastxm, lastym); 
		}
		if (xh != lastxh || yh != lastyh) {
			g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
			g.drawLine(xcenter-1, ycenter, lastxh, lastyh); 
		}

        // Draw the circle and numbers
        g.setColor(handColor);
        g.drawOval(xcenter-50, ycenter-50, 100, 100);
        g.setColor(numberColor);
        g.drawString("9", xcenter-45, ycenter+3); 
        g.drawString("3", xcenter+40, ycenter+3);
        g.drawString("12", xcenter-5, ycenter-37);
        g.drawString("6", xcenter-3, ycenter+45);
        
		// Draw date and hands
		g.setColor(numberColor);
		g.setColor(handColor);
		g.drawLine(xcenter, ycenter-1, xm, ym);
		g.drawLine(xcenter-1, ycenter, xm, ym);
		g.drawLine(xcenter, ycenter-1, xh, yh);
		g.drawLine(xcenter-1, ycenter, xh, yh);
		lastxm = xm; lastym = ym;
		lastxh = xh; lastyh = yh;
	}
}
