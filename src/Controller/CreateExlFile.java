package Controller;

import Model.Courrier;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class CreateExlFile {

    static int oui = 0, non = 0, en = 0, cmp = 0;

    public static String Create(ArrayList<Courrier> C) throws IOException {
        int t = 0;
        String S;
        String n =new SimpleDateFormat("HH_mm_ss").format(new Date());
        try {

            HSSFRow row;
            String filename = System.getProperty("user.home") +"\\desktop"+ "\\Rapp"+n+".xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFCellStyle style = workbook.createCellStyle();
            HSSFCellStyle stylenor = workbook.createCellStyle();
            HSSFCellStyle styleoui = workbook.createCellStyle();
            HSSFCellStyle stylenon = workbook.createCellStyle();

            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            styleoui.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            styleoui.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            stylenor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            stylenor.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            stylenon.setFillForegroundColor(IndexedColors.RED.getIndex());
            stylenon.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            HSSFFont fontoui = workbook.createFont();
            fontoui.setColor(HSSFColor.WHITE.index); 
            
            HSSFFont fontnor = workbook.createFont();
            fontnor.setFontHeight((short) 200);
            fontnor.setBold(true);

            
            styleoui.setFont(fontoui);
            stylenon.setFont(fontoui);

            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.WHITE.index);
            font.setBold(true);
            font.setFontHeight((short) 300);

            style.setFont(font);
            stylenor.setFont(fontnor);


            Row rowhead = sheet.createRow((short) 0);

            //rowhead.setRowStyle(style);
            Cell[] cellH = {rowhead.createCell(4), rowhead.createCell(5), rowhead.createCell(6), rowhead.createCell(7), rowhead.createCell(8), rowhead.createCell(9), rowhead.createCell(10), rowhead.createCell(11), rowhead.createCell(12), rowhead.createCell(13)};

//          sheet.autoSizeColumn(7);  sheet.autoSizeColumn(8);    sheet.autoSizeColumn(9);
            cellH[0].setCellValue("No d'Order");
            cellH[1].setCellValue("Type");
            cellH[2].setCellValue("Date de rèception");
            cellH[3].setCellValue("Expediteur");
            cellH[4].setCellValue("Destinataire");
            cellH[5].setCellValue("Date d'envoi");
            cellH[6].setCellValue("Tél");
            cellH[7].setCellValue("Object");
            cellH[8].setCellValue("Observation");
            cellH[9].setCellValue("Traité");
                   

            for (int a = 0; a < C.size(); a++) {
                cmp++;
                row = sheet.createRow((short) (a + 1));
                Cell[] cell = {row.createCell(4), row.createCell(5), row.createCell(6), row.createCell(7), row.createCell(8), row.createCell(9), row.createCell(10), row.createCell(11), row.createCell(12), row.createCell(13)};
                System.out.println("A   " + a);

                cell[0].setCellValue(C.get(a).getNumOrd());
                cell[0].setCellStyle(stylenor);
                cell[1].setCellValue(C.get(a).getTypeCour());
                cell[2].setCellValue(C.get(a).getDateResption());
                cell[3].setCellValue(C.get(a).getExpediteur().getNomExp());
                cell[4].setCellValue(C.get(a).getDestination().getNomDest());
                cell[5].setCellValue(C.get(a).getDateRenvoye());
                cell[6].setCellValue(C.get(a).getDestination().getTeleDest());
                cell[7].setCellValue(C.get(a).getObjet());
                cell[8].setCellValue(C.get(a).getObservation());
                cell[9].setCellValue(C.get(a).getTraite());

                if (C.get(a).getTraite().equals("oui")) {
                    cell[9].setCellStyle(styleoui);
                    oui++;
                } else if (C.get(a).getTraite().equals("non")) {
                    cell[9].setCellStyle(stylenon);

                    non++;
                    //cell[6].setCellStyle(styleoui);
                } else {

                    en++;
                }
            }
            System.out.println("cmp  " + cmp);
            cmp++;
            row = sheet.createRow((short) (cmp + 1));
            Cell coui = row.createCell(7);
            Cell couii = row.createCell(6);
            couii.setCellStyle(styleoui);
            coui.setCellStyle(stylenor);

            row = sheet.createRow((short) (cmp + 2));
            Cell cnon = row.createCell(7);
            Cell cnonn = row.createCell(6);
            cnonn.setCellStyle(stylenon);
            cnon.setCellStyle(stylenor);
            
            row = sheet.createRow((short) (cmp + 3));
            Cell cen = row.createCell(7);
            Cell cenn = row.createCell(6);
            cen.setCellStyle(stylenor);

            coui.setCellValue(oui);
            couii.setCellValue(" Traié");

            cnon.setCellValue(non);
            cnonn.setCellValue(" Non Traité");
            sheet.autoSizeColumn(0);
            cen.setCellValue(en);
            cenn.setCellValue(" en cours de Traité");

            //Cell cen = row.createCell(3);
            for (int j = 0; j < cellH.length; j++) {
                cellH[j].setCellStyle(style);
                sheet.autoSizeColumn(j + 4);

            }
            
            
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");

            S = filename;
            non = oui = en = cmp = 0;
            
        } catch (Exception ex) {
            t = 1;
            System.out.println(ex);
            S = "" + ex.toString();
        }
        return S;
    }
}
