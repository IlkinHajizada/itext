package group.aist.itext.controller;

import com.itextpdf.text.DocumentException;
import group.aist.itext.service.PDFGeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/api")
public class PDFGeneratorController {
    private PDFGeneratorService pdfService;
    public PDFGeneratorController(PDFGeneratorService pdfGeneratorService) {
        this.pdfService = pdfGeneratorService;
    }

    @GetMapping("/generate-pdf")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        pdfService.generatePdf(response);
    }
}
