import javax.swing.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
class psp
{
public static void main(String aa[])
{


try 
{ 
InputStreamReader isr=new InputStreamReader(System.in);
BufferedReader br=new BufferedReader(isr);
System.out.println("Enter the Path of file");
String nameoffile=br.readLine();
 Document document = new Document();   
OutputStream outputStream = new FileOutputStream(new File("TestFile1.pdf")); 
PdfWriter.getInstance(document, outputStream);   
Image image=Image.getInstance("c:\\itext\\aa.jpg");
image.setAbsolutePosition(100,250);
image.scalePercent(50);
document.open();    
document.add(image);   
 document.close();
 outputStream.close(); 
JFileChooser jfc=new JFileChooser();
jfc.setCurrentDirectory(new File("."));
javax.swing.filechooser.FileFilter ff;
//ff=new FileNameExtensionFilter("PDF Files","pdf");
  System.out.println("Pdf created successfully."); 
} catch (Exception e) 
{
 e.printStackTrace(); 
}
 }


}

