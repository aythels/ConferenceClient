package domain.usecases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import domain.entities.Event;

import java.io.FileOutputStream;
import java.util.ArrayList;

//Download: https://search.maven.org/classic/#search%7Cga%7C1%7Ca%3A%22itextpdf%22

public class GeneratePdf {
    private static String filePath;
    private static Font defaultFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL);
    private EventManager eventManager;
    private static ArrayList<Event> events;

    public GeneratePdf(EventManager eventManager, String filePath) {
        this.eventManager = eventManager;
        events = eventManager.getAllEvents();
        GeneratePdf.filePath = filePath;
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
        table.setHeaderRows(1);

        for (Event e : events) {
            int x = e.getEventID();
            table.addCell(e.getEventName());
            table.addCell(""+e.getEventID());
            table.addCell(""+e.getEventTime());
        }
        subCatPart.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}