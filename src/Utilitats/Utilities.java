package Utilitats;

import BLL.ClientBLL;
import BLL.PetBLL;
import Entitat.Client;
import Entitat.Pet;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class Utilities {

    public static void createPDF1(String dest) throws FileNotFoundException, java.io.IOException {
        try {
            // S'inicialitza PDFWriter:
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);

            // S'inicialitza el document, és defineix el tamany de la pàgina i els marges.
            Document document = new Document(pdf, PageSize.A4.rotate());
            document.setMargins(20, 20, 20, 20);

            // És defineixen les fonts:
            PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
            PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

            // Creació de la taula principal definint les columnes amb les proporcions de tamany de columna:
            Table table = new Table(new float[]{1, 2, 4, 2, 3});
            table.setWidthPercent(100);

            // Obtenció dels clients:
            ArrayList<Client> clients = new ClientBLL().obtenirClients();

            // Creació del conjunt de dades que alimentaràn al gràfic circular en 3D:
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (int i = 0; i < clients.size(); i++) {
                Client c = clients.get(i);

                dataset.setValue(c.getCodi_id(), new PetBLL().obtenirMascotes(c.getCodi_id()).size());
            }

            // Creació del gràfic circular en 3D:
            // Alternativa 1 - Ús d'un objecte de tipus PieChart3D:
            /*try {
                PieChart3D pieChart = new PieChart3D(dataset, "Informe de clients");
                table.addCell(new Cell(1, 5).add(new Paragraph().setTextAlignment(TextAlignment.CENTER).add(pieChart.getITextImage())).setBorder(Border.NO_BORDER));
            } catch (BadElementException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            // Alternative 2 - Ús d'un mètode estàtic:
            table.addCell(new Cell(1, 5).add(new Paragraph().setTextAlignment(TextAlignment.CENTER).add(createPieChart3D(dataset, "Informe de clients"))).setBorder(Border.NO_BORDER));

            // Creació d'un espaiat entre el gràfic circular en 3D i el text:
            for (int k = 0; k < 6; k++) {
                table.addCell(new Cell(1, 5).add("").setBorder(Border.NO_BORDER));
            }

            // Bucle que recorre els clients emmagatzemats a la base de dates:
            for (int i = 0; i < clients.size(); i++) {
                Client c = clients.get(i);

                // Primer afegim a la taula el nom del client amb un ColSpan de 5:
                Cell cela = new Cell(1, 5).add(c.getNom()).setFont(font);
                table.addCell(cela);

                // Després afegim a la taula les capçaleres definint la font en negreta:
                table.addCell(new Cell().add(new Paragraph("Id").setFont(bold)));
                table.addCell(new Cell().add(new Paragraph("Nom").setFont(bold)));
                table.addCell(new Cell().add(new Paragraph("Data naixement").setFont(bold)));
                table.addCell(new Cell().add(new Paragraph("Especie").setFont(bold)));
                table.addCell(new Cell().add(new Paragraph("Xip").setFont(bold)));

                // Obtenció de les mascotes:
                ArrayList<Pet> pets = new PetBLL().obtenirMascotes(c.getCodi_id());
                // Bucle intern que recorre les mascotes vinculades a un client:
                for (int j = 0; j < pets.size(); j++) {
                    Pet p = pets.get(j);

                    // Afegim el cos de la taula cel·la a cel·la:
                    table.addCell(new Cell().add(new Paragraph(p.getNum_id()).setFont(font)));
                    table.addCell(new Cell().add(new Paragraph(p.getNom()).setFont(font)));
                    table.addCell(new Cell().add(new Paragraph(p.getData().toString()).setFont(font)));
                    table.addCell(new Cell().add(new Paragraph(p.getEspecie()).setFont(font)));
                    table.addCell(new Cell().add(new Paragraph(p.getXip()).setFont(font)));
                }

                // Sempre i quant no es tracti de l'últim client...
                if (i != clients.size() - 1) {
                    // es crearà un espaiat entre client i client:
                    for (int k = 0; k < 6; k++) {
                        table.addCell(new Cell(1, 5).add("").setBorder(Border.NO_BORDER));
                    }
                }
            }

            // Afegir la taula al document:
            document.add(table);

            // Tancar document:
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createPDF2(String dest) throws FileNotFoundException, java.io.IOException {
        try {
            // S'inicialitza PDFWriter:
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);

            // S'inicialitza el document, és defineix el tamany de la pàgina i els marges.
            Document document = new Document(pdf, PageSize.A4.rotate());
            document.setMargins(20, 20, 20, 20);

            // És defineixen les fonts:
            PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
            PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

            // Creació de la taula principal definint les columnes amb les proporcions de tamany de columna:
            Table table = new Table(new float[]{1, 2, 4, 2, 3});
            table.setWidthPercent(100);

            // Creació de les capçaleres recurrents:
            process(table, "Id", bold, true);
            process(table, "Nom", bold, true);
            process(table, "Data naixement", bold, true);
            process(table, "Especie", bold, true);
            process(table, "Xip", bold, true);

            // Obtenció de les mascotes:
            ArrayList<Pet> pets = new PetBLL().obtenirMascotes("");

            // Bucle que recorre les mascotes emmagatzemades a la base de dades:
            for (int i = 0; i < pets.size(); i++) {
                Pet p = pets.get(i);

                // Primer mostrem el text FACTURA amb un ColSpan de 2
                /*Cell cela = new Cell(1, 5).add(c.getNom()).setFont(font);
            table.addCell(cela);*/
                // Afegim el cos de la taula cel·la a cel·la:
                table.addCell(new Cell().add(new Paragraph(p.getNum_id()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getNom()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getData().toString()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getEspecie()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(p.getXip()).setFont(font)));
            }

            // Afegir la taula al document:
            document.add(table);

            // Tancar document:
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)).setBorder(Border.NO_BORDER));
            } else {
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }

    public static Image createPieChart3D(PieDataset dataset, String title) {
        Image iTextImage = null;
        try {
            JFreeChart chart = ChartFactory.createPieChart3D(
                    title, dataset, true, true, false);

            PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                    "Client {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

            BufferedImage bi = chart.createBufferedImage(400, 400);
            ImageIcon imatge = new ImageIcon(bi);
            java.awt.Image image = imatge.getImage();
            iTextImage = new Image(ImageDataFactory.create(image, Color.yellow));
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }

        return iTextImage;
    }
}
