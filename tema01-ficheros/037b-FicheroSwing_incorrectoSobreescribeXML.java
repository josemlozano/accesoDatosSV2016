/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author bruno
 */
public class FicheroSwing extends javax.swing.JFrame {

    String fileName, fileWidth, fileHeight, fileSize;
    
    /**
     * Creates new form FicheroSwing
     */
    public FicheroSwing() {
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

        FileChooser = new java.awt.Button();
        name = new javax.swing.JLabel();
        size = new javax.swing.JLabel();
        width = new javax.swing.JLabel();
        height = new javax.swing.JLabel();
        export = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FileChooser.setActionCommand("FileChooser");
        FileChooser.setLabel("Seleccionar fichero");
        FileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileChooserActionPerformed(evt);
            }
        });

        name.setText("Nombre");

        size.setText("Tamaño bytes");

        width.setText("Ancho");

        height.setText("Alto");

        export.setText("Exportar");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(FileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(237, 237, 237))
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(width, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(height, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(size, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(export)
                .addGap(264, 264, 264))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(FileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addComponent(name)
                .addGap(4, 4, 4)
                .addComponent(width)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(height)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(size)
                .addGap(37, 37, 37)
                .addComponent(export)
                .addGap(33, 33, 33))
        );

        FileChooser.getAccessibleContext().setAccessibleName("Seleccionar fichero");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileChooserActionPerformed
        // TODO add your handling code here:
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            fileName = selectedFile.getName();
            name.setText("Nombre:" + fileName);
            ReadBMP(selectedFile);
        }
        
        
        
    }//GEN-LAST:event_FileChooserActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        
        exportToXML();
        
        exportToCVS();
        
        
    }//GEN-LAST:event_exportActionPerformed

    private void exportToXML() {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder =
                    dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("ficheros");
            doc.appendChild(rootElement);

            //  state element
            //Element state = doc.createElement("surname");
            //rootElement.appendChild(state);

            Element currentFile = doc.createElement("fichero");

            
            Element attrName = doc.createElement("nombre");
            attrName.setTextContent((String)fileName);
            currentFile.appendChild(attrName);

            Element attrWidth = doc.createElement("ancho");
            attrWidth.setTextContent(((String)fileWidth).trim());
            currentFile.appendChild(attrWidth);

            Element attrHeight = doc.createElement("alto");
            attrHeight.setTextContent(((String)fileHeight).trim());
            currentFile.appendChild(attrHeight);

            Element attrSize = doc.createElement("tamano");
            attrSize.setTextContent(((String)fileSize).trim());
            currentFile.appendChild(attrSize);
            /*
            Attr attrName = doc.createAttribute("nombre");
            attrName.setValue((String)fileName);
            currentFile.setAttributeNode(attrName);

            Attr attrWidth = doc.createAttribute("ancho");
            attrWidth.setValue(((String)fileWidth).trim());
            currentFile.setAttributeNode(attrWidth);

            Attr attrHeight = doc.createAttribute("alto");
            attrHeight.setValue(((String)fileHeight).trim());
            currentFile.setAttributeNode(attrHeight);

            Attr attrSize = doc.createAttribute("tamano");
            attrSize.setValue(((String)fileSize).trim());
            currentFile.setAttributeNode(attrSize);
*/

            rootElement.appendChild(currentFile);


            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer =
                    transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result =
                    new StreamResult(new File("ficheros.xml"));
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult =
                    new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void exportToCVS() {
        
        try
        {
            PrintWriter printWriter = new PrintWriter (new FileWriter("ficheros.csv",true));
            printWriter.print (fileName+",");
            printWriter.print (fileWidth+",");
            printWriter.print (fileHeight+",");
            printWriter.println (fileSize);
            printWriter.close (); 
            JOptionPane.showMessageDialog(null, "Exportado correctamente");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void ReadBMP(File selectedFile) {
        
    try {
            FileInputStream myFile = new FileInputStream(selectedFile);
            
            int BUFFER_SIZE = (int) selectedFile.length();
            fileSize = BUFFER_SIZE+"";
            size.setText("Tamaño bytes: " + BUFFER_SIZE);
            
            byte[] imageData = new byte [BUFFER_SIZE];
            
            myFile.read(imageData,0,BUFFER_SIZE);
            myFile.close();

            fileWidth = ((int) imageData[18]) +
                    (((int) imageData[19]) * 256)+"";
            width.setText("Ancho: " + fileWidth);
            
            fileHeight = ((int) imageData[22]) + 
                    (((int) imageData[23]) * 256)+"";
            
            height.setText("Alto: " + fileHeight);
            
            if ( (imageData[0] != 'B') || (imageData[1] != 'M') ) {
                System.out.println("Does not seem a valid BMP!");
                return;
            }

            int startPos = ((int) imageData[10]) + (((int) imageData[11]) * 256);
            
            System.out.println("Data starts at position: "+
                startPos);
            System.out.println();
            
            System.out.println("Image lines:");
            System.out.println();

            int count = 0;
            String dataLine = "";
            
            for (int i = BUFFER_SIZE-1 ; i > startPos ; i--) {
                count++;
                
                if (imageData[i]<0) {
                    dataLine+=" ";
                }
                else {
                    dataLine+="*";
                }

            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage() );
        }
    
        
    }
    
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
            java.util.logging.Logger.getLogger(FicheroSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FicheroSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FicheroSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FicheroSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FicheroSwing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button FileChooser;
    private javax.swing.JButton export;
    private javax.swing.JLabel height;
    private javax.swing.JLabel name;
    private javax.swing.JLabel size;
    private javax.swing.JLabel width;
    // End of variables declaration//GEN-END:variables
}
