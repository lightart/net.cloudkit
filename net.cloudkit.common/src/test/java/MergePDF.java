import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MergePDF {

    public static void main(String[] args) {
        try {

            File[] files = new File("D:\\Desktop\\pdf").listFiles();
            List<InputStream> pdfs = new LinkedList<>();
            for (File file : files) {
                pdfs.add(new FileInputStream(file));
            }

            String name = "D:\\merge.pdf";
            File file = new File(name);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            OutputStream output = new FileOutputStream(name);
            MergePDF.merge(pdfs, output, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void merge(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) {

        Document document = new Document();
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            // Create Readers for the pdfs.
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
            // data

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            // Loop through the PDF files and add to the output.
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();

                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader,
                        pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);

                    // Code for pagination.
                    if (paginate) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
                            "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
