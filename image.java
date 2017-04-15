import com.itextpdf.text.Document.*;
import com.itextpdf.text.DocumentException.*;
import com.itextpdf.text.pdf.PdfWriter.*;

class psp
{
private Document document;
psp()
{
try{
document=new Document();
PdfWriter.getInstance(document,new File("exp.pdf"));
document.open();
document.add(new Paragraph("Hello"));
document.close();
}catch(Exception de)
{

}
}
public static void main(String aa[])
{
psp p=new psp();
}
}
