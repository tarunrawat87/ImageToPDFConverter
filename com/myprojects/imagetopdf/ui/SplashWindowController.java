package com.myprojects.imagetopdf.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class SplashWindow extends JWindow
{
private Container container;
private ImageIcon imageIcon;
private String Text[]={"Loading..","Wait..","Almost Done..","Here we go !"};
private JLabel imageLabel;
SplashWindow(ImageIcon imageIcon)
{
Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();

setShape(new RoundRectangle2D.Double(5,5,256,256,50,50));
setLayout(new BorderLayout());
container=getContentPane();
imageLabel=new JLabel();
this.imageIcon=imageIcon;
imageLabel.setIcon(imageIcon);
container.add(imageLabel);
setSize(350,256);
imageLabel.setHorizontalTextPosition(JLabel.CENTER);
imageLabel.setVerticalTextPosition(JLabel.CENTER);

setLocation(dimension.width/2-this.getWidth()/2,dimension.height/2-this.getHeight()/2);

setVisible(true);
}
public void setText(int x)
{
imageLabel.setText(Text[x]);
}
public void setImage()
{

}
public void setProgressBar()
{

}

}


class SplashWindowThread extends Thread
{
private SplashWindow splashWindow;
SplashWindowThread(SplashWindow splashWindow)
{
this.splashWindow=splashWindow;
start();
}
public void run()
{
for(int x=0;x<=3;x++)
{
try
{
splashWindow.setText(x);
Thread.sleep(1500);
}catch(InterruptedException ie)
{

}
}
}
}

public class SplashWindowController
{
private ImageIcon imageIcon;
public SplashWindowController(ImageIcon imageIcon)
{
this.imageIcon=imageIcon;
try
{
SplashWindow splashWindow=new SplashWindow(imageIcon);
SplashWindowThread splashWindowThread=new SplashWindowThread(splashWindow);
splashWindowThread.join();
splashWindow.setVisible(false);
System.out.println("Done");
}catch(InterruptedException ie)
{

}

}
}
