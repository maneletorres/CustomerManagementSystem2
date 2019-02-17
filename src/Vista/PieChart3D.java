package Vista;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.text.BadElementException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class PieChart3D extends JPanel {

    private final Image iTextImage;

    public PieChart3D(PieDataset dataset, String title) throws IOException, BadElementException {
        super.setSize(400, 400);
        super.setVisible(true);

        JFreeChart chart = ChartFactory.createPieChart3D(
                title, dataset, true, true, false);

        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "Client {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        BufferedImage bi = chart.createBufferedImage(400, 400);
        ImageIcon imatge = new ImageIcon(bi);
        java.awt.Image image = imatge.getImage();
        iTextImage = new Image(ImageDataFactory.create(image, Color.yellow));
    }

    public Image getITextImage() {
        return iTextImage;
    }
}
