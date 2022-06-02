
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class PDFHelper {

    final static String FILE_PATH_PREFIX = "./temp/";


    public static void ShowPDF(String url, String data) throws IOException {
        Map<String, String> dataMap = new HashMap<>();

        try {
            PdfReader reader = new PdfReader("./temp/test.pdf");
            PdfStamper stamper = null;
            stamper = new PdfStamper(reader, new FileOutputStream("./temp/test2.pdf"));
            BuildText(stamper, 1, "testtesttest", 171, 74);


            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void create(String file, String data) throws Exception {
        PdfReader reader = new PdfReader("file");
        PdfStamper stamper = null;
        stamper = new PdfStamper(reader, new FileOutputStream("./lib/test1.pdf"));
        BuildText(stamper, 1, "testtesttest", 171, 74);


        stamper.close();
        reader.close();
    }

    public static void merge(String... file) throws IOException {
        final String MERGED_PDF_NAME = FILE_PATH_PREFIX + getCurrentTimeStamp() + ".pdf";
        try {
            // PDF file list
            List<String> fileList = Arrays.asList(file);

            Document doc = new Document();
            doc.open();

            // Output stream to target PDF document
            PdfCopy copy = new PdfCopy(doc, new FileOutputStream(MERGED_PDF_NAME));

            PdfImportedPage page;
            for (String filePath : fileList) {
                System.out.println(FILE_PATH_PREFIX + filePath);
                PdfReader pdfreader = new PdfReader(FILE_PATH_PREFIX + filePath);
                int n = pdfreader.getNumberOfPages();
                for (int i = 1; i <= n; i++) {
                    // grab page from input document
                    page = copy.getImportedPage(pdfreader, i);
                    // add content to target PDF
                    copy.addPage(page);
                }
                copy.freeReader(pdfreader);
            }

            doc.close();
            copy.close();
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void BuildText(PdfStamper stamper, int page, String value, int x, int y) {
        y = 825 - y - 5;
        x = x + 20;
        PdfContentByte cb = stamper.getOverContent(page);
        cb.beginText();
        try {
            cb.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, false), 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cb.setTextMatrix(x, y);
        cb.showTextAligned(Element.ALIGN_RIGHT, value, x, y, 0);
        cb.endText();
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

}
