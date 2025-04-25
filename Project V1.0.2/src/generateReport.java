import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import java.awt.*;
import com.itextpdf.text.pdf.*;

public class generateReport {
    public generateReport(JTable table,String a) {
        generatePDF(table,a); // Call the method to generate the PDF with the table
    }

    private void generatePDF(JTable table,String a) {
        // Create a new PDF document
        Document document = new Document();
        try {
            String filePath = a+" Report.pdf"; // Define the file name
            // Create a PdfWriter instance to write the content to the specified file
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Open the document for adding content
            document.open();

            // Add Title to the document
            document.add(new Paragraph(a+"\n\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));

            // Create a PdfPTable that corresponds to the JTable's column count
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            pdfTable.setWidthPercentage(100);  // Set the table width to 100% of the page width
            pdfTable.setSpacingBefore(10f);  // Space before the table
            pdfTable.setSpacingAfter(10f);   // Space after the table

            // Add Table Headers from JTable Column Names
            for (int i = 0; i < table.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY); // Set header background color
                cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Align text to center
                pdfTable.addCell(cell);  // Add the header cell
            }

            // Add Table Rows from JTable Data
            TableModel model = table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    pdfTable.addCell(model.getValueAt(i, j).toString()); // Add each cell value
                }
            }

            // Add the table to the document
            document.add(pdfTable);

            // Close the document

    File fp = new File(filePath);

            Desktop.getDesktop().open(fp); // Open the PDF file
            document.close();
            // Notify the user that the PDF was created successfully
            JOptionPane.showMessageDialog(null, "PDF Report Generated: " + filePath);
        } catch (Exception e) {
            // Handle any exceptions that occur during PDF generation
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating the report.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public generateReport(JTable eventTable, JTable studentTable, boolean b,String a) {
        generatePDF(eventTable, studentTable, b,a);
    }

    private void generatePDF(JTable eventTable, JTable studentTable, boolean b,String a) {
        Document document = new Document();
        try {
            String c;
            if(a=="Student"){
                c="Event";
            }
            else{
               c="Student";
            }
            String filePath = "Event_Student_Report.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            // Add Title
            document.add(new Paragraph("Event and Student Report\n\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));

            // Add Event Table
            document.add(new Paragraph(c+" Details\n"));
            PdfPTable eventPdfTable = createPdfTableFromJTable(eventTable);
            document.add(eventPdfTable);
            document.add(new Paragraph("\n\n"));

            // Add Student Table (If students exist)
            if (b) {
                document.add(new Paragraph(a+" Details\n"));
                PdfPTable studentPdfTable = createPdfTableFromJTable(studentTable);
                document.add(studentPdfTable);
            }

            // Always close the document after adding content
            document.close();

            // Open the PDF
            File fp = new File(filePath);
            Desktop.getDesktop().open(fp);

            // Show message based on student data presence
            String message = b ? "PDF Report Generated.. " + filePath : "PDF Generated..";
            JOptionPane.showMessageDialog(null, message);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating the report.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private PdfPTable createPdfTableFromJTable(JTable table) {
        PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
        pdfTable.setWidthPercentage(100);
        pdfTable.setSpacingBefore(10f);
        pdfTable.setSpacingAfter(10f);

        // Add Table Headers
        for (int i = 0; i < table.getColumnCount(); i++) {
            PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i)));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell);
        }

        // Add Table Rows
        TableModel model = table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                pdfTable.addCell(model.getValueAt(i, j).toString());
            }
        }
        return pdfTable;
    }
}
