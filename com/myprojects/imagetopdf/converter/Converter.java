package com.myprojects.imagetopdf.converter;


import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;



import javax.swing.*;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;

import com.myprojects.imagetopdf.converter.exception.*;

public class Converter  
{
private File file[];
private Document document;

public void convertToPDF(File file[]) throws ConverterException 
{
	

try 
{
System.out.println("Reached here");
int x=0;
document = new Document();   
OutputStream outputStream = new FileOutputStream(new File("TestFile1.pdf")); 
PdfWriter.getInstance(document, outputStream);   
document.open();   

while(x<file.length)
{
document.newPage();
Image image=Image.getInstance(file[x].getPath());
int indentation = 0;
float scaler = ((document.getPageSize().getWidth() - document.leftMargin()- document.rightMargin() - indentation) / image.getWidth()) * 100;

//image.setAbsolutePosition(0,0);
//image.scalePercent(25);
//image.setAlignment(Image.MIDDLE);
//image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
image.scalePercent(scaler);
 
document.add(image);   
//nameoffile=br.readLine();
// image=Image.getInstance(nameoffile);
//image.setAbsolutePosition(0,0);
//image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
//image.scalePercent(scaler);
//image.scalePercent();

//document.add(image);   
x++;
}
 document.close();
 outputStream.close(); 
  System.out.println("Pdf created successfully."); 
} catch (Exception e) 
{
System.out.println(e);
throw new ConverterException(e.getMessage());
}
//code to open file 
File file1=new File("TestFile1.pdf");

if(!Desktop.isDesktopSupported())
{
System.out.println("Bitch there is problem here Line:292");
return ;
}
Desktop desktop=Desktop.getDesktop();
try
{
if(file1.exists())desktop.open(file1);
}catch(Exception e)
{}




 }

}