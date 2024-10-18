package group.aist.itext.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

public class PDFBackground extends PdfPageEventHelper {
    private final static String pathToBackground = "C:\\all_projects\\java_projects\\i-text\\src\\main\\resources\\static\\backgroundTriangle.jpeg";
    private final static String pathToHeader = "C:\\all_projects\\java_projects\\i-text\\src\\main\\resources\\static\\header.png";
    private final static String pathToFooter = "C:\\all_projects\\java_projects\\i-text\\src\\main\\resources\\static\\footer.png";

    private Image background = null;
    private Image header = null;
    private Image footer = null;

    public PDFBackground() {
        try {
            background = Image.getInstance(pathToBackground);
            header = Image.getInstance(pathToHeader);
            footer = Image.getInstance(pathToFooter);

        } catch (IOException | BadElementException e) {
            throw new RuntimeException("Error loading images", e);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        float pageWidth = document.getPageSize().getWidth();
        float pageHeight = document.getPageSize().getHeight();

        try {
            background.scaleToFit(pageWidth, pageHeight);
            background.setAbsolutePosition(0, 0);
            writer.getDirectContentUnder().addImage(background);

            header.scaleToFit(pageWidth, pageHeight / 10);
            header.setAbsolutePosition(0, pageHeight - 60);
            writer.getDirectContent().addImage(header);

            footer.scaleToFit(pageWidth, pageHeight / 10);
            footer.setAbsolutePosition(0, 30);
            writer.getDirectContent().addImage(footer);

        } catch (DocumentException e) {
            throw new RuntimeException("Error adding images to the PDF", e);
        }
    }
}
