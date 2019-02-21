package Vista;

import static Utilitats.Utilities.createPDF1;
import static Utilitats.Utilities.createPDF2;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class FinestraPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FinestraPrincipal
     */
    public FinestraPrincipal() {
        initComponents();

        super.setTitle("Pràctica final");
        jMenu1.setVisible(false);
        isWindowThrown = false;
        isInternalFrameOpen = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        optionJMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        exitJMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        printButton = new javax.swing.JMenuItem();
        listButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 979, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        optionJMenu.setText("Opcions");

        jMenuItem1.setText("Alta d'animals");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        optionJMenu.add(jMenuItem1);

        jMenuItem2.setText("Clients");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        optionJMenu.add(jMenuItem2);

        exitJMenuItem.setText("Eixir");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        optionJMenu.add(exitJMenuItem);

        jMenuBar1.add(optionJMenu);

        jMenu1.setText("Informes");

        printButton.setText("Imprimir en PDF");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        jMenu1.add(printButton);

        listButton.setText("Llistar animals");
        listButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listButtonActionPerformed(evt);
            }
        });
        jMenu1.add(listButton);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (!isWindowThrown) {
            JInternalFrame1 jInternalFrame = new JInternalFrame1();

            // Aquestes dues sentències permeten que la finestra llançada aparegui centrada:
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            jInternalFrame.setLocation(dim.width / 2 - jInternalFrame.getSize().width / 2, dim.height / 2 - jInternalFrame.getSize().height / 2);

            jInternalFrame.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    isWindowThrown = false;
                }
            });
            jDesktopPane1.add(jInternalFrame);

            try {
                jInternalFrame.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FinestraPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            isWindowThrown = true;
        } else {
            showInfoDialog();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JInternalFrame3 jInternalFrame3 = new JInternalFrame3();

        // Aquestes dues sentències permeten que la finestra llançada aparegui centrada:
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jInternalFrame3.setLocation(dim.width / 2 - jInternalFrame3.getSize().width / 2, dim.height / 2 - jInternalFrame3.getSize().height / 2);

        if (!isWindowThrown) {
            if (!isInternalFrameOpen) {
                jMenu1.setVisible(true);
            }

            jInternalFrame3.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    isWindowThrown = false;
                    isInternalFrameOpen = false;
                    jMenu1.setVisible(false);
                }

                @Override
                public void internalFrameDeactivated(InternalFrameEvent e
                ) {
                    jMenu1.setVisible(false);
                }

                @Override
                public void internalFrameActivated(InternalFrameEvent e
                ) {
                    jMenu1.setVisible(true);
                }
            });
            jDesktopPane1.add(jInternalFrame3);

            try {
                jInternalFrame3.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FinestraPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            isInternalFrameOpen = true;
            isWindowThrown = true;
        } else {
            showInfoDialog();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        try {
            fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("clients.pdf"));
            fileChooser.setFileFilter(new PDFFilter());
            if (fileChooser.showSaveDialog(jDesktopPane1) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                createPDF1(file.getAbsolutePath());
            }
        } catch (IOException ex) {
            Logger.getLogger(FinestraPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void listButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listButtonActionPerformed
        try {
            fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("animals.pdf"));
            fileChooser.setFileFilter(new PDFFilter());
            if (fileChooser.showSaveDialog(jDesktopPane1) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                createPDF2(file.getAbsolutePath());
            }
        } catch (IOException ex) {
            Logger.getLogger(FinestraPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listButtonActionPerformed

    public void showInfoDialog() {
        JOptionPane.showMessageDialog(this,
                "No pots obrir una nova finestra si ja roman oberta altra.", "Informació",
                JOptionPane.INFORMATION_MESSAGE);
    }

    class PDFFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            String nombre = f.getName();
            return nombre.substring(Math.max(nombre.length() - 4, 0)).equals(".pdf");
        }

        @Override
        public String getDescription() {
            return "Ficheros de tipo PDF";
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem listButton;
    private javax.swing.JMenu optionJMenu;
    private javax.swing.JMenuItem printButton;
    // End of variables declaration//GEN-END:variables
    private boolean isWindowThrown;
    private boolean isInternalFrameOpen;
    private JFileChooser fileChooser;
}
