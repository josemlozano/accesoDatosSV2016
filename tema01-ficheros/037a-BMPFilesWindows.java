import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author chao
 */
public class BMPFilesWindows extends javax.swing.JFrame {

    private String nombreFichero;
    int ancho;
    int alto;
    int tamanyo;
                
    /**
     * Creates new form BMPFilesWindows
     */
    public BMPFilesWindows() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setName("tbNombre"); // NOI18N

        jTextField2.setName("tbAncho"); // NOI18N

        jTextField3.setName("tbAlto"); // NOI18N

        jTextField4.setName("tbTamanyo"); // NOI18N

        jLabel1.setText("Nombre:");

        jLabel2.setText("Ancho:");

        jLabel3.setText("Alto:");

        jLabel4.setText("Tamaño (bytes):");

        jButton2.setText("Exportar");
        jButton2.setName("btExportar"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Elegir");
        jButton1.setName("btElegir"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Boton Elegir
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(
            "BMP Files", "bmp"));
        chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showOpenDialog(this);
        
        File fichero = chooser.getSelectedFile();
        try (FileInputStream lector = new FileInputStream(fichero)){
            final int TAMANYO_BUFFER = 54;
            byte[] buffer = new byte[TAMANYO_BUFFER];
            int cantidadLeida = lector.read(buffer, 0, TAMANYO_BUFFER);
            
            if (cantidadLeida != TAMANYO_BUFFER) {
                JOptionPane.showMessageDialog(null, "Error en la lectura del " +
                    "archivo");
                return;
            }
            
            if (buffer[0] == 'B' && buffer[1] == 'M') {
                ancho = 0;
                alto = 0;
                tamanyo = (int)fichero.length();
                nombreFichero = fichero.getName();
                
                ancho = buffer[19] * 256 + buffer[18];
                if (buffer[18] < 0) {
                     ancho += 256;
                }
                
                alto = buffer[23] * 256 + buffer[22];
                if (buffer[22] < 0) {
                    alto += 256;
                }
                // Nombre
                jTextField1.setText(nombreFichero);
                // Ancho
                jTextField2.setText("" + ancho);
                // Alto
                jTextField3.setText("" + alto);
                // Tamaño
                jTextField4.setText("" + tamanyo);
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No es un archivo bmp válido");
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado.");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + 
                e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void guardarCSV() {
        File fichero = new File("ficheros.csv");
        try (BufferedWriter imprimir = new BufferedWriter(
                new FileWriter(fichero, true))){
            imprimir.write("\"" + nombreFichero + "\"," + ancho + "," + alto + 
                "," + tamanyo);
            imprimir.newLine();
            imprimir.close();
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado.");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + 
                e.getMessage());
        }
    }
    
    private void guardarXML() {
        try {
            File fichero = new File("ficheros.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;
            Element rootElement;
            NodeList nList;
            
            /* Comprobar si existe el fichero, dependiendo del caso, si no existe,
             * se crea un nuevo documento y si no, leer el contenido del archivo
            */
            if (fichero.exists()) {
                doc = dBuilder.parse(fichero);
                doc.getDocumentElement().normalize();
                nList = doc.getElementsByTagName("ficheros");
                rootElement = (Element)nList.item(0);
            }
            else {
                doc = dBuilder.newDocument();
                rootElement = doc.createElement("ficheros");
                doc.appendChild(rootElement);
            }
            
            // Añadir elementos
            Element nodoFichero = doc.createElement("fichero");
            rootElement.appendChild(nodoFichero);
            
            Element nombreFich = doc.createElement("nombre");
            nombreFich.appendChild(doc.createTextNode(nombreFichero));
            nodoFichero.appendChild(nombreFich);
            
            Element anchoFich = doc.createElement("ancho");
            anchoFich.appendChild(doc.createTextNode("" + ancho));
            nodoFichero.appendChild(anchoFich);
            
            Element altoFich = doc.createElement("alto");
            altoFich.appendChild(doc.createTextNode("" + alto));
            nodoFichero.appendChild(altoFich);
            
            Element tamanyoFich = doc.createElement("tamanyo");
            tamanyoFich.appendChild(doc.createTextNode("" + tamanyo));
            nodoFichero.appendChild(tamanyoFich);
            
            // Volcar el contenido en el fichero xml
            TransformerFactory transformerFactory = 
                TransformerFactory.newInstance();
            
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fichero);
            transformer.transform(source, result);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
        }
        catch (ParserConfigurationException pce) {
            JOptionPane.showMessageDialog(null, pce.getMessage());
        }
        catch (TransformerException tfe) {
            JOptionPane.showMessageDialog(null, tfe.getMessage());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + 
                e.getMessage());
        }
    }
    
    // Boton Exportar
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        guardarCSV();
        guardarXML();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BMPFilesWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BMPFilesWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BMPFilesWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BMPFilesWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BMPFilesWindows().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
