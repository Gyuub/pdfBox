import com.lowagie.text.pdf.PdfReader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 2022.05.24
 * 내용 : 유안타 PDF 모듈
 * 작성자 : 황규빈
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //PDFHelper.ShowPDF("", "");
        PDFHelper.merge("test.pdf","test2.pdf");
        //File directory = new File("./");

    }

    public static void test(String... file) {
        Arrays.stream(file)
                .forEach(s -> {
                    System.out.println(s);
                });
        System.out.println();
    }



}
