package com.myprojects.imagetopdf.global;

import java.io.*;
public class Global
{
private final static File file=new File("");
private static String filename=" cdbh";
static
{
filename="c:\\ImageToPDF";
new File(filename).mkdirs();
System.out.println("Yes it works");
}
public void setFileName(String filename)
{
this.filename=filename;
}
public static String getFileName()
{
return filename;
}
}