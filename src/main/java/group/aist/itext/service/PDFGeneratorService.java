package group.aist.itext.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import group.aist.itext.util.PDFBackground;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;


@Service
public class PDFGeneratorService {

    private static final String TITLE = "MƏXFİLİK VƏ QƏRƏZSİZLİK BƏYANNAMƏSİ";

    public void generatePdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=PDF.pdf");

        Document document = new Document();
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        try (OutputStream out = response.getOutputStream()) {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
            pdfWriter.setPageEvent(new PDFBackground());
            document.open();

            String fontPath = "C:\\all_projects\\java_projects\\i-text\\src\\main\\resources\\static\\Agency.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 16);

            PdfContentByte canvas = pdfWriter.getDirectContent();

            canvas.beginText();
            canvas.setFontAndSize(baseFont, 24);
            canvas.showTextAligned(Element.ALIGN_CENTER, TITLE, 297.5f, 730f, 0);
            canvas.endText();

            document.add(new Paragraph("\n\n\n\n", font));

            PdfPCell nameCell = new PdfPCell();
            nameCell.setBorder(PdfPCell.NO_BORDER);
            nameCell.addElement(new Paragraph("""
                            Mən,    ________________________________________________
                    """, font));
            Font smallFont = new Font(baseFont, 12);
            nameCell.addElement(new Paragraph("                                    (Soyad, ad, ata adı)", smallFont));
            nameCell.setPaddingTop(20f);

            nameCell.setColspan(2);

            table.addCell(nameCell);


            PdfPCell declarationCell = new PdfPCell(new Paragraph(
                    """
                                    Ilkin akkreditasiya prosesində qurumun nüfuzuna və maraqlarına xələl gətirə biləcək
                                    hərəkətlərə yol verməyəcəm, işləri yüksək peşəkarlıqla yerinə yetirmək üçün səy
                                    göstərəcəm, proses zamanı mənə məlum olmuş məlumatların məxfiliyini qoruyacam, şəxsi
                                    və sair məqsədlər üçün istifadə etməyəcəm, kommersiya sirri təşkil edən məlumatların
                                    ictimailəşdirilməsi və ya üçüncü tərəfə verilməsinin qarşısını alacam, proses zamanı heç
                                    bir şəxsin, qrupun və ya tərəfin təsirinə məruz qalmadan, vəzifəmi obyektivlik və
                                    qərəzsizlik prinsiplərinə riayət etməklə yerinə yetirəcəyəm.
                                    Yuxarıda adıçəkilən qurumla çalışdığım müəssisə və ya şəxsim arasında keçmişdə və
                                    ya davam edən məsləhət, təlim, daxili audit və s. fəaliyyətlərlə bağlı əlaqələrim və ya
                                    qurumda çalışan səlahiyyətli şəxslərlə qohumluq, dostluq, düşmənçilik və bu kimi
                                    münasibətlərim mövcud deyildir.
                                    Mənə məlum olan məlumatların məxfiliyinin pozulması, kommersiya sirri təşkil edən
                                    məlumatların yayılması və ya üçüncü tərəfə verilməsi halında qanunvericiliklə nəzərdə
                                    tutulmuş inzibati və cinayət məsuliyyəti barədə xəbərdar edilmişəm.
                                    Bu bəyannaməni imzalamaqla qiymətləndirmə zamanı yuxarıda sadalanan və AzAK-ın
                                    siyasət və prosedurlarında nəzərdə tutulmuş bütün tələblərə əməl edəcəyimi bəyan edirəm.
                            """, font));
            declarationCell.setColspan(10);
            declarationCell.setBorder(PdfPCell.NO_BORDER);
            declarationCell.setPaddingTop(30f);
            declarationCell.setPaddingLeft(1f);
            table.addCell(declarationCell);

            PdfPCell signatureCell = new PdfPCell();
            signatureCell.setBorder(PdfPCell.NO_BORDER);
            signatureCell.addElement(new Paragraph("\n    İmza:    __________________________\n\n", font));
            signatureCell.addElement(new Paragraph("        Tarix: 11.10.2024\n", smallFont));
            signatureCell.addElement(new Paragraph("        Qeyd: ", smallFont));

            table.addCell(signatureCell);

            PdfPCell emptyCell = new PdfPCell();
            emptyCell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(emptyCell);

            document.add(table);

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
