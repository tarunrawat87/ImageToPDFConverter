package com.myprojects.imagetopdf.ui;

import com.myprojects.imagetopdf.converter.*;
import com.myprojects.imagetopdf.global.*;
import com.myprojects.imagetopdf.converter.exception .*;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
public class ConverterFrame extends JDialog 
{
private int count=0;
private InformationDisplayPanel informationdisplayPanel; 
private ViewPanel viewPanel;
private ButtonPanel buttonPanel;
private Container container;
private JFileChooser filechooser;
private JButton xyzButton;
private File imageFiles[];
private ImageIcon pdfImageIcon;
private int selectedIndex;


public ConverterFrame()
{
initComponents();
addListeners();
setAppearances();
setComponentsSetting();
Global.getFileName();
}
public void initComponents()
{
container=getContentPane();
informationdisplayPanel=new InformationDisplayPanel(); 
viewPanel=new ViewPanel();
buttonPanel=new ButtonPanel();





filechooser=new JFileChooser(".");
pdfImageIcon=new ImageIcon("C:\\project\\testcase\\resources\\images\\icon.png");

 xyzButton=new JButton("");
}
public void addListeners()
{
filechooser.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent av)
{


}
});
}
//W1
public void showFileChooserMessageDialog()
{
int selectedOption;
selectedOption=filechooser.showOpenDialog(ConverterFrame.this);
imageFiles=filechooser.getSelectedFiles();
System.out.println(imageFiles.length);
count+=imageFiles.length;

if(selectedOption==JFileChooser.APPROVE_OPTION)
{
viewPanel.populateList(imageFiles);

}


}


public void setAppearances()
{
int lm=10,tm=10;
setTitle("Image to PDF");
container.setLayout(null);
xyzButton.setIcon(pdfImageIcon);
setResizable(false);
setForeground(Color.black);

informationdisplayPanel.setBounds(lm+428,+tm+91,252,410);
viewPanel.setBounds(lm+5,tm+30+65,415,300);
buttonPanel.setBounds(lm+50,tm+15,580,70);

buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red));
informationdisplayPanel.setBorder(BorderFactory.createLineBorder(Color.red));
viewPanel.setBorder(BorderFactory.createLineBorder(Color.red));


container.add(informationdisplayPanel);
container.add(buttonPanel);
container.add(viewPanel);


//container.setBackground(Color.cyan);
Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
setSize(700,550);
setVisible(true);
setLocation(dimension.width/2-this.getWidth()/2,dimension.height/2-this.getHeight()/2);
setDefaultCloseOperation(DISPOSE_ON_CLOSE);
filechooser.setSize(100,100);
/*
try
{
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //
}catch(Exception classnotfound)
{
	
}
*/    

//setDefaultLookAndFeelDecorated(true);
}
public void updateCount(int count)
{
System.out.println("Count "+count);
}
public void updateList()
{
File files[];
files=imageFiles;
String filesName[]=viewPanel.list.getItems();
imageFiles=null;
for(int x=0;x<filesName.length;x++)
{
imageFiles[x]=new File(filesName[x]);
}

}

public void setComponentsSetting()
{
filechooser.setFileFilter(new FileNameExtensionFilter("jpg files","jpg"));
filechooser.setMultiSelectionEnabled(true);
filechooser.setAcceptAllFileFilterUsed(false);
filechooser.updateUI();
}
//Communication Methods between Classes

 public void nullifyImagePanel()
{
informationdisplayPanel.imageLabel.setIcon(null);

}

public void notifyImagePanel()
{
File file=new File(viewPanel.getSelectedItem());
informationdisplayPanel.setImageonPanel(file);
}
public void getConverter(File file[])
{
if(file.length>15) JOptionPane.showMessageDialog(this,"Only 15 Images are allowed");
Converter converter=new Converter();
System.out.println(file.length);
try{
converter.convertToPDF(file);
}catch(Exception converterException)
{
System.out.println(converterException +"Exception at Line 149");
}
}
public void clearImagePanel()
{
informationdisplayPanel.clearImagePanel();
}

//For ViewPanel
public void remove()
{
viewPanel.list.removeAll();
updateList();
}

public void removeItem()
{
if(viewPanel.list.getItemCount()!=0)
viewPanel.removeItem();
updateList();
}

public int numberOfItems()
{
return viewPanel.list.getItemCount();
}

public String getSelectedItem()
{
return viewPanel.list.getSelectedItem();
}
public String getItemAt(int index)
{
return viewPanel.list.getItem(index);
}
public int getSelectedIndex()
{
return viewPanel.getSelectedIndex();
}
public void replaceItem(String selectedItem,String upperItem,int currentIndex,int upperItemIndex)
{
viewPanel.list.replaceItem(selectedItem,upperItemIndex);
viewPanel.list.replaceItem(upperItem,currentIndex);

}
class ViewPanel extends JPanel
{
private List list;
private int selectedIndex;
ViewPanel()
{
initComponents();
addListeners();
setAppearances();


}

public int getSelectedIndex()
{
return list.getSelectedIndex();
}
public void removeItem()
{
 list.remove(list.getSelectedIndex());
}
public String getSelectedItem()
{
return list.getSelectedItem();
}
public void addListeners()
{
list.addItemListener(new ItemListener(){
public void itemStateChanged(ItemEvent ae)
{
System.out.println(ae.getStateChange());
notifyImagePanel();

}
});
list.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
System.out.println("Called");
}
});
}
public void getItem()
{

}
public void setAppearances()
{
setLayout(new BorderLayout());
add(list,BorderLayout.CENTER);
}
public void initComponents()
{
list=new List(15,false);
}
public void populateList(File file[])
{
int x=0;
while(x<file.length)
{
list.add(file[x].getAbsolutePath());
x++;
}


}

}



class InformationDisplayPanel extends JPanel
{
private JLabel documentTitleCaption;
private JTextField documentTitleTextField;
private JLabel savePathCaption;
private JTextField savePathTextField;
private JButton convertButton;
private JLabel imageLabel;
InformationDisplayPanel()
{
initComponents();
addListeners();
setAppearances();



}

public void addListeners()
{
convertButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent av)
{
try
{
 if(imageFiles.length==0)
{
JOptionPane.showMessageDialog(null,"No file added,Please add File !");
return;
} 
try
{
//Line 292
getConverter(imageFiles);
}catch(Exception e)
{
System.out.println("Exception at 295 "+e);
}


/*
System.out.println("Opening a flie");
//code to open a pdf 

File file=new File("TestFile1.pdf");

if(!Desktop.isDesktopSupported())
{
System.out.println("Bitch there is problem here Line:292");
return ;
}
Desktop desktop=Desktop.getDesktop();

if(file.exists())desktop.open(file);



*/



				
}catch(Exception exception)
{
JOptionPane.showMessageDialog(null,"No file added,Please add File !");

System.out.println(exception+"Exception at 288");
}

}
});
}

public void setAppearances()
{
setLayout(null);
int lm=5,tm=5;
Font font=new Font("Verdana",Font.PLAIN,11);
documentTitleCaption.setFont(font);
documentTitleTextField.setFont(font);
savePathCaption.setFont(font);
savePathTextField.setFont(font);

documentTitleCaption.setBounds(lm+10,tm+240+10+3,180,25);
documentTitleTextField.setBounds(lm+10,tm+240+22+10+3,180,25);
savePathCaption.setBounds(lm+10,tm+240+25+25+5+5,180,25);
savePathTextField.setBounds(lm+10,tm+240+25+25+25+4,180,25);
convertButton.setBounds(lm+30,tm+240+25+25+25+5+5+30,180,45);
imageLabel.setBounds(lm,tm,240,250);

convertButton.setIcon(new ImageIcon("c:\\project\\testcase\\images\\convert.png"));
convertButton.setFont(new Font("Verdana",Font.BOLD,13));
convertButton.setFocusPainted(false);


convertButton.setHorizontalTextPosition(JButton.RIGHT);
convertButton.setVerticalTextPosition(JButton.BOTTOM);

documentTitleCaption.setForeground(Color.black);
documentTitleTextField.setForeground(Color.black);
savePathCaption.setForeground(Color.black);
savePathTextField.setForeground(Color.black);
imageLabel.setBorder(BorderFactory.createLineBorder(Color.red));		


}
public void initComponents()
{
imageLabel=new JLabel();
documentTitleCaption=new JLabel("Document Title(filename):");
documentTitleTextField=new JTextField();
savePathCaption=new JLabel("Save to(path):");
savePathTextField=new JTextField();
convertButton=new JButton("Convert to PDF");


add(documentTitleCaption);
add(documentTitleTextField);
add(savePathCaption);
add(savePathTextField);
add(convertButton);
add(imageLabel);
}
public void setImageonPanel(File file)
{
System.out.println("setImageonPanel called");
imageLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(240,250,Image.SCALE_DEFAULT)));
}

public void clearImagePanel()
{
imageLabel.setIcon(null);
}
}

class ButtonPanel extends JPanel
{
private JButton addButton;
private JButton removeButton;
private JButton cleanButton;
  private JButton exitButton;
private JButton moveUpButton;
  private JButton moveDownButton;


ButtonPanel()
{
initComponents();
addListeners();
setAppearances();
setComponentsSetting();


}
public void initComponents()
{
addButton=new JButton("Add");
removeButton=new JButton("Remove");
cleanButton=new JButton("Clean");
exitButton=new JButton("Exit");
moveDownButton=new JButton("Down");
moveUpButton=new JButton("Up");


add(addButton);
add(removeButton);
add(cleanButton);
add(exitButton);
add(moveUpButton);
add(moveDownButton);

}

public void addListeners()
{
addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
try
{
showFileChooserMessageDialog();
}catch(Exception exception)
{
System.out.println(exception+"Exception at 399");
}
}
});
removeButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
try
{
if(imageFiles.length==0)
{

}
nullifyImagePanel();

removeItem();
updateList();
}catch(Exception exception)
{
if(imageFiles==null){JOptionPane.showMessageDialog(ConverterFrame.this,"No image Present ");
nullifyImagePanel();

}
else
JOptionPane.showMessageDialog(ConverterFrame.this,"Select a Image ");

}


}
});
cleanButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
try
{
ConverterFrame.this.remove();
ConverterFrame.this.clearImagePanel();
imageFiles=null;
}catch(Exception e)
{
System.out.println(e +"Exception atv 426");
}


}

});
exitButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
System.exit(0);
}

});

moveUpButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
System.out.println("Move Called");
System.out.println(getSelectedIndex());

try
{
if(getSelectedIndex()!=0)
{
String selectedItem=getSelectedItem();
String upperItem=getItemAt(getSelectedIndex()-1);		
//Working Here
replaceItem(selectedItem,upperItem,getSelectedIndex(),getSelectedIndex()-1);

System.out.println("Selected Index :"+selectedItem+"upperItem :"+upperItem);
}
}catch(Exception exception)
{
System.out.println(exception+"Exception at 460");
}
 




}
});

moveDownButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
System.out.println("Move Down");
System.out.println(getSelectedIndex());
if(!(getSelectedIndex()==(numberOfItems()-1)))
{
String selectedItem=getSelectedItem(); 
String lowerItem=getItemAt(getSelectedIndex()+1);		
replaceItem(lowerItem,selectedItem,getSelectedIndex(),getSelectedIndex()-1);

System.out.println("Selected Index :"+selectedItem+"lowerItem :"+lowerItem);
}
}
});


}
public void setAppearances()
{
setLayout(null);
int tm=0,lm=0;
addButton.setBounds(lm,tm,95,70);
removeButton.setBounds(lm+95+2,tm,95,70);
cleanButton.setBounds(lm+95+95+2+2,tm,95,70);
moveUpButton.setBounds(lm+95+95+95+2+2+2,tm,95,70);
moveDownButton.setBounds(lm+95+95+95+95+2+2+2+2,tm,95,70);
exitButton.setBounds(lm+95+95+95+95+95+2+2+2+2+2,tm,95,70);
addButton.setIcon(new ImageIcon("C:\\project\\testcase\\images\\add.png"));
removeButton.setIcon(new ImageIcon("C:\\project\\testcase\\images\\remove.png"));
cleanButton.setIcon(new ImageIcon("C:\\project\\testcase\\images\\clear.png"));
exitButton.setIcon(new ImageIcon("C:\\project\\testcase\\images\\exit.png"));
moveUpButton.setIcon(new ImageIcon("C:\\project\\testcase\\images\\moveup.png"));
moveDownButton.setIcon(new ImageIcon("C:\\project\\testcase\\images\\movedown.png"));


addButton.setFocusPainted(false);
removeButton.setFocusPainted(false);
cleanButton.setFocusPainted(false);
exitButton.setFocusPainted(false);
moveUpButton.setFocusPainted(false);
moveDownButton.setFocusPainted(false);


addButton.setHorizontalTextPosition(JButton.CENTER);
addButton.setVerticalTextPosition(JButton.BOTTOM);
cleanButton.setHorizontalTextPosition(JButton.CENTER);
cleanButton.setVerticalTextPosition(JButton.BOTTOM);
removeButton.setHorizontalTextPosition(JButton.CENTER);
removeButton.setVerticalTextPosition(JButton.BOTTOM);
exitButton.setHorizontalTextPosition(JButton.CENTER);
exitButton.setVerticalTextPosition(JButton.BOTTOM);
moveUpButton.setHorizontalTextPosition(JButton.CENTER);
moveUpButton.setVerticalTextPosition(JButton.BOTTOM);
moveDownButton.setHorizontalTextPosition(JButton.CENTER);
moveDownButton.setVerticalTextPosition(JButton.BOTTOM);


addButton.setFont(new Font("Verdana",Font.PLAIN,12));
removeButton.setFont(new Font("Verdana",Font.PLAIN,12));
cleanButton.setFont(new Font("Verdana",Font.PLAIN,12));
exitButton.setFont(new Font("Verdana",Font.PLAIN,12));
moveUpButton.setFont(new Font("Verdana",Font.PLAIN,12));
moveDownButton.setFont(new Font("Verdana",Font.PLAIN,12));

}
public void setComponentsSetting(){}



}





}
//convertButton.setFocusPainted(false);
//lesson:  setHorizontalTextPosition(JButton.CENTER);
//lESSON :imageLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(240,250,Image.SCALE_DEFAULT)));
//Lesson :implement Itemlistener instead of actionListner in case of list
//Lesson1 : There cant be any static method in anoy. class
/*
File file=new File("TestFile1.pdf");

if(!Desktop.isDesktopSupported())
{
System.out.println("Bitch there is problem here Line:292");
return ;
}
Desktop desktop=Desktop.getDesktop();

if(file.exists())desktop.open(file);






*/