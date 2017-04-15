package com.myprojects.imagetopdf.converter;



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
public class Converter  
{
private File file[];
private Document document;

public void convertToPDF(File file[])
{


try 
{
/*
JFileChooser filechooser=new JFileChooser();
filechooser.addActionListener(this);

filechooser.setCurrentDirectory(new File("."));

filechooser.setMultiSelectionEnabled(true);

int user=filechooser.showOpenDialog(null);
File file[]=filechooser.getSelectedFiles();
System.out.println(file.length);
Image image1=Image.getInstance("c:\\images\\1.jpg");
progress=new JProgressBar();
/* 
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
 e.printStackTrace(); 
}
 }

}