package src.Util;

import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;
//import au.com.bytecode.opencsv.CSVReader;
//import au.com.bytecode.opencsv.CSVWriter;
 
/*A utility class that writes data to a CSV file
 * @author: Eva B. Tate
 * (C) Copyright EBT Tech Enterprises, LLC*/

public class CSVUtility {
	
	private static BufferedWriter fileWriter;
	public static String username = null;
	public static String password = null;
	
	public static void exportData(ResultSet result, String fileName) {
         
		//Get File Name and append "_export"
        String csvFileName = getFileNM(fileName.concat("_export"));
         
        try {
             
            fileWriter = new BufferedWriter(new FileWriter(csvFileName));
             
            int columnCount = writeHdrLine(result);
             
            while (result.next()) {
                String line = "";
                 
                for (int i = 2; i <= columnCount; i++) {
                    Object valueObject = result.getObject(i);
                    String valueString = "";
                     
                    if (valueObject != null) valueString = valueObject.toString();
                                          
                    line = line.concat(valueString);
                      
                    if (i != columnCount) {
                        line = line.concat(",");
                    }
                }
                 
                fileWriter.newLine();
                fileWriter.write(line);            
            }
            fileWriter.close();
             
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
         
    }
 
	//Function to append Date and Time to export file
    private static String getFileNM(String baseName) {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        //String dateTimeInfo = dateFormat.format(new Date());
        return baseName.concat(String.format(".csv"));
    }
     
    private static int writeHdrLine(ResultSet result) throws SQLException, IOException {
        // write header line containing column names
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        String headerLine = "";
         
        // exclude the first column which is the ID field
        for (int i = 2; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            headerLine = headerLine.concat(columnName).concat(",");
        }
         
        fileWriter.write(headerLine.substring(0, headerLine.length() - 1));
         
        return numberOfColumns;
    }
     
    private static String escapeDoubleQuotes(String value) {
        return value.replaceAll("\"", "\"\"");
    }
    
//    public static void readCSVFile(String filename) throws FileNotFoundException, IOException {
//    	//CSVReader Class
//    	CSVReader rdr = new CSVReader(new FileReader(filename));
//    	
//    	//Create a String Array
//    	String[] cell;
//    	
//    	//Interate through each cell
//    	while((cell=rdr.readNext()) != null) {
//    		for(int i=0; i<1; i++) {
//    			value1 = cell[i];
//    		}
//    	}
//    	
//    }
//    
    public static void writeCSV(String filePath, String separator, String value) throws IOException {
        /**
         * We use try with resource so we don't need to call close
         * method or write finally block explicitly it will be called automatically. 
         */
        try (OutputStream fileStream = new BufferedOutputStream(new FileOutputStream(filePath));
                Writer outStreamWriter = new OutputStreamWriter(fileStream, StandardCharsets.UTF_8);
                BufferedWriter buffWriter = new BufferedWriter(outStreamWriter)) {
            buffWriter.append(value);
            buffWriter.append(separator);
            buffWriter.append("Data2");
            buffWriter.newLine();
            buffWriter.flush();
            buffWriter.append("1");
            buffWriter.append(separator);
            buffWriter.append("2");
            buffWriter.newLine();
            buffWriter.flush();
            buffWriter.append("11");
            buffWriter.append(separator);
            buffWriter.append("22");
            buffWriter.flush();
        }
    }
    
    public static void writeCSV2(String filePath, String value) throws IOException {
    	PrintWriter csvWriter;
        try {
            /*1. declare stringBuffer*/
            StringBuffer oneLineStringBuffer = new StringBuffer();

            File file = new File(filePath);
            if (!file.exists()) {
                file = new File(filePath);

            }
            csvWriter = new PrintWriter(new FileWriter(file, true));

            /*2. append to stringBuffer*/   
            oneLineStringBuffer.append(value);
            oneLineStringBuffer.append("\n");

            /*3. print to csvWriter*/
            csvWriter.print(oneLineStringBuffer);

            csvWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	/*
	 * public static void WriteUserToCSVFile(String filename, String username)
	 * throws Exception { String csv = filename; CSVWriter writer = new
	 * CSVWriter(new FileWriter(cs1v));
	 * 
	 * //Create record String record = username; //Write the record to file
	 * writer.writeAll(record);
	 * 
	 * //close the writer writer.close(); }
	 */
    
	/*
	 * public static void ConvertXLSToCSV(File inputFile, File outputFile) { // For
	 * storing data into CSV files StringBuffer data = new StringBuffer(); try {
	 * FileOutputStream fos = new FileOutputStream(outputFile); // Get the workbook
	 * object for XLS file HSSFWorkbook workbook = new HSSFWorkbook(new
	 * FileInputStream(inputFile)); // Get first sheet from the workbook HSSFSheet
	 * sheet = workbook.getSheetAt(0); Cell cell; Row row; // Iterate through each
	 * rows from first sheet Iterator rowIterator = sheet.iterator(); while
	 * (rowIterator.hasNext()) { row = rowIterator.next(); // For each row, iterate
	 * through each columns Iterator cellIterator = row.cellIterator(); while
	 * (cellIterator.hasNext()) { cell = cellIterator.next();
	 * 
	 * switch (cell.getCellType()) { case Cell.CELL_TYPE_BOOLEAN:
	 * data.append(cell.getBooleanCellValue() + ","); break;
	 * 
	 * case Cell.CELL_TYPE_NUMERIC: data.append(cell.getNumericCellValue() + ",");
	 * break;
	 * 
	 * case Cell.CELL_TYPE_STRING: data.append(cell.getStringCellValue() + ",");
	 * break; case Cell.CELL_TYPE_BLANK: data.append("" + ","); break;
	 * 
	 * default: data.append(cell + ","); } } data.append(' '); }
	 * fos.write(data.toString().getBytes()); fos.close(); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */
}
