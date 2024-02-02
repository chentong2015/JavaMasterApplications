package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ApachePoiDemo {

    // User API (HSSF and XSSF)
    private void testOutputExcel() throws IOException {
        Workbook wb1 = new HSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
            wb1.write(fileOut);
        }
        Workbook wb2 = new XSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb2.write(fileOut);
        }
    }

    // ʹ��Workbook  ==>  Workbook���Բ���Ҫ����lifecycle
    // 1. ʹ��File������Խ����ڴ�����
    // 2. ʹ��InputStream��Ҫ������ڴ棬��Ϊ�����뻺�������ļ�
    private void testInputExcel() throws Exception {
        // create(POIFSFileSystem fs)
        // create(NPOIFSFileSystem fs)
        // create(OPCPackage pkg)
        // Workbook wb1 = WorkbookFactory.create(new File("MyExcel.xls"));
        // Workbook wb2 = WorkbookFactory.create(new FileInputStream("MyExcel.xlsx"));
    }

    // ֱ��ʹ��HSSFWorkbook����XSSFWorkbook  ====> ���뿼�ǿ�����������
    // 1. ʹ��FileInputStream����ȡ��
    // 2. ��Ҫʹ��NPOIFSFileSystem��OPCPackage����ȫ����Workbook����������
    private void testReadExcel() throws Exception {
        //  HSSFWorkbook workbook1 = new HSSFWorkbook(new FileInputStream("text.xlsx")); // ���Ƽ�
        //  NPOIFSFileSystem fs = new NPOIFSFileSystem(new File("file.xls"));
        //  HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
        //  fs.close();
        //  NPOIFSFileSystem fs1 = new NPOIFSFileSystem(new FileInputStream("MyExcel.xlsx"));
        //  HSSFWorkbook wb3 = new HSSFWorkbook(fs.getRoot(), true);
        //  fs1.close();

        //  XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("text.xlsx")); // ���Ƽ�
        //  OPCPackage pkg = OPCPackage.open(new File("file.xlsx"));
        //  XSSFWorkbook wb4 = new XSSFWorkbook(pkg);
        //  pkg.close();
        //  OPCPackage pkg1 = OPCPackage.open(new FileInputStream("MyExcel.xlsx"));
        //  XSSFWorkbook wb5 = new XSSFWorkbook(pkg);
        //  pkg1.close();
    }
}
