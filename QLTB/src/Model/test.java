package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


public class test {
	public static void main(String[] args) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Employees sheet");

		List<ThietBi> list = new ArrayList<ThietBi>();
		ThietBi tb1 = new ThietBi(); tb1.setMaTB("tb1"); tb1.setTenTB("Thiet Bi 1"); tb1.setMaPhong("P01");
		tb1.setMaTinhTrang("TT01"); tb1.setModel("xyz");
		ThietBi tb2 = new ThietBi(); tb2.setMaTB("tb2"); tb2.setTenTB("Thiet Bi 2"); tb2.setMaPhong("P02");
		tb2.setMaTinhTrang("TT02"); tb2.setModel("xyz2");
		ThietBi tb3 = new ThietBi(); tb3.setMaTB("tb3"); tb3.setTenTB("Thiet Bi 3"); tb3.setMaPhong("P03");
		tb3.setMaTinhTrang("TT03"); tb3.setModel("xyz3");
		list.add(tb1);list.add(tb2);list.add(tb3);

		int rownum = 0;
		Cell cell;
		Row row;
		//
		XSSFCellStyle style = createStyleForTitle(workbook);

		row = sheet.createRow(rownum);

		// EmpNo
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("MaTB");
		cell.setCellStyle(style);
		// EmpName
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("TenTB");
		cell.setCellStyle(style);
		// Salary
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("MaPhong");
		cell.setCellStyle(style);
		// Grade
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("MaTT");
		cell.setCellStyle(style);
		// Bonus
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Model");
		cell.setCellStyle(style);

		// Data
		for (ThietBi tb : list) {
			
			System.out.println(tb.getMaTB());
			rownum++;
			row = sheet.createRow(rownum);

			// EmpNo (A)
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(tb.getMaTB());
			// EmpName (B)
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(tb.getTenTB());
			// Salary (C)
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(tb.getMaPhong());
			// Grade (D)
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(tb.getMaTinhTrang());
			// Bonus (E)
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(tb.getModel());
		}
		File file = new File("D:/duyanh/readexcel.xls");
		file.getParentFile().mkdirs();

		FileOutputStream outFile = new FileOutputStream(file);
		workbook.write(outFile);
		System.out.println("Created file: " + file.getAbsolutePath());

	}
	
    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }


}
