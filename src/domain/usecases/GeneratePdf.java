package domain.usecases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import domain.entities.Event;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

//Download: https://search.maven.org/classic/#search%7Cga%7C1%7Ca%3A%22itextpdf%22

public class GeneratePdf {
    private static String filePath = "c:/Users/Jake/Downloads/Events.pdf";
    private static Font defaultFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL);
    private static ArrayList<HashMap<String, String>> events;


    public GeneratePdf(ArrayList<HashMap<String, String>> events) {
        GeneratePdf.events = events;
    }

    public void createPdf() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Events", defaultFont);
        anchor.setName("Events");
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        createTable(catPart);
        document.add(catPart);
    }

    private static void createTable(Section subCatPart)
            throws BadElementException {

        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("Event"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("ID"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Time"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Speaker"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (HashMap<String, String> m : events) {
            table.addCell(m.get("eventName"));
            table.addCell(m.get("eventID"));
            String time = new SimpleDateFormat("MMMMM d yyyy").format(new Date(Long.valueOf(m.get("eventTime")) * 1000));
            table.addCell(time);
            table.addCell(m.get("speakerName"));
        }
        subCatPart.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}