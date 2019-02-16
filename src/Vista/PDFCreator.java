package Vista;

import Entitat.Client;
import Entitat.Pet;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.BadElementException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class PDFCreator {

    public PDFCreator() {
    }

    public void createPDF1(String dest) throws FileNotFoundException, java.io.IOException {
        //Initialitzar PDF writer
        System.out.println(dest);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        // Initializar el document, ara si definim el tamany de pàgina per si la taula es molt gran
        // que s’adapte al tamany que volem de resultat, a més li definim els marges.
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        //Definir la font
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        // Crear la taula definint les columnes amb les proporcions de tamany de columna:
        Table table = new Table(new float[]{1, 2, 4, 2, 3});
        table.setWidthPercent(100);

        // DATOS:
        ArrayList<Client> clients = (ArrayList<Client>) new ClientTableModel(false).clientData;

        // Creació del Chart:
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < clients.size(); i++) {
            Client c = clients.get(i);

            ArrayList<Pet> pets = new PetTableModel(c.getCodi_id()).petData;
            dataset.setValue(c.getCodi_id(), pets.size());
        }

        PieChart3D pieChart;
        try {
            pieChart = new PieChart3D(dataset, "Informe de clients");
            table.addCell(new Cell(1, 5).add(new Paragraph().add(pieChart.getITextImage())).setBorder(Border.NO_BORDER));
        } catch (BadElementException ex) {
            Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Espaciat:
        for (int k = 0; k < 6; k++) {
            table.addCell(new Cell(1, 5).add("").setBorder(Border.NO_BORDER));
        }

        // Bucle que recorre els clients de la base de dates:
        for (int i = 0; i < clients.size(); i++) {
            Client c = clients.get(i);

            // Primer mostrem el text FACTURA amb un ColSpan de 2
            Cell cela = new Cell(1, 5).add(c.getNom()).setFont(font);
            table.addCell(cela);

            // Afegim les capçaleres definint la font en negreta:
            table.addCell(new Cell().add(new Paragraph("Id").setFont(bold)));
            table.addCell(new Cell().add(new Paragraph("Nom").setFont(bold)));
            table.addCell(new Cell().add(new Paragraph("Data naixement").setFont(bold)));
            table.addCell(new Cell().add(new Paragraph("Especie").setFont(bold)));
            table.addCell(new Cell().add(new Paragraph("Xip").setFont(bold)));

            ArrayList<Pet> pets = new PetTableModel(c.getCodi_id()).petData;
            // Bucle intern que recorre els animals vinculats a un client:
            for (int j = 0; j < pets.size(); j++) {
                Pet p = pets.get(j);

                // Afegim el cos de la taula cel·la a cel·la, ella ja sap que cada 3 ha de canviar de fila:
                table.addCell(new Cell().add(new Paragraph(p.getNum_id()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getNom()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getData().toString()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getEspecie()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getXip()).setFont(font)));
            }

            // Espaciat:
            for (int k = 0; k < 6; k++) {
                table.addCell(new Cell(1, 5).add("").setBorder(Border.NO_BORDER));
            }
        }

        // Afegir la taula al document:
        document.add(table);

        // Tancar document:
        document.close();
    }

    public void createPDF2(String dest) throws FileNotFoundException, java.io.IOException {
        //Initialitzar PDF writer
        System.out.println(dest);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        // Initializar el document, ara si definim el tamany de pàgina per si la taula es molt gran
        // que s’adapte al tamany que volem de resultat, a més li definim els marges.
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        //Definir la font
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        // Crear la taula definint les columnes amb les proporcions de tamany de columna:
        Table table = new Table(new float[]{1, 2, 4, 2, 3});
        table.setWidthPercent(100);

        process(table, "Id", bold, true);
        process(table, "Nom", bold, true);
        process(table, "Data naixement", bold, true);
        process(table, "Especie", bold, true);
        process(table, "Xip", bold, true);

        // Bucle que recorre els clients de la base de dates:
        for (int j = 0; j < 20; j++) {
            ArrayList<Pet> pets = new PetTableModel("").petData;
            for (int i = 0; i < pets.size(); i++) {
                Pet p = pets.get(i);

                // Primer mostrem el text FACTURA amb un ColSpan de 2
                /*Cell cela = new Cell(1, 5).add(c.getNom()).setFont(font);
            table.addCell(cela);*/
                // Afegim el cos de la taula cel·la a cel·la, ella ja sap que cada 3 ha de canviar de fila:
                table.addCell(new Cell().add(new Paragraph(p.getNum_id()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getNom()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getData().toString()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getEspecie()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getXip()).setFont(font)));

                // Afegim les capçaleres definint la font en negreta:
                /*table.addCell(new Cell().add(new Paragraph("Id").setFont(bold)));
            table.addCell(new Cell().add(new Paragraph("Nom").setFont(bold)));
            table.addCell(new Cell().add(new Paragraph("Data naixement").setFont(bold)));
            table.addCell(new Cell().add(new Paragraph("Especie").setFont(bold)));
            table.addCell(new Cell().add(new Paragraph("Xip").setFont(bold)));*/
                // Espaciat:
                /*for (int k = 0; k < 6; k++) {
                table.addCell(new Cell(1, 5).add("").setBorder(Border.NO_BORDER));
            }*/
            }
        }

        // Afegir la taula al document:
        document.add(table);

        // Tancar document:
        document.close();
    }

    public void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)).setBorder(Border.NO_BORDER));
            } else {
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }
}
