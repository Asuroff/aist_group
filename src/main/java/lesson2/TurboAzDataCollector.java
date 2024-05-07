package lesson2;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TurboAzDataCollector {
      private static final String BASE_URL = "https://turbo.az/autos";
    private static final String STATE_FILE_PATH = "state.txt";
    private static final int MAX_PAGES = 5;

    public static void main(String[] args) {
        try {
            int currentPage = getCurrentPage();
            List<String> vehicleInfoList = new ArrayList<>();

            for (int page = currentPage; page <= MAX_PAGES; page++) {
                String pageUrl = BASE_URL + "?page=" + page;
                Document doc = Jsoup.connect(pageUrl).get();
                Elements carLinks = doc.select(".products-list .products-i .products-info .products-name a");

                for (Element link : carLinks) {
                    String carUrl = link.absUrl("href");
                    Document carDoc = Jsoup.connect(carUrl).get();
                    String vehicleInfo = carDoc.select(".product-properties").text();
                    String price = carDoc.select(".product-price").text();
                    vehicleInfoList.add(vehicleInfo + " - " + price);
                }
            }

            writeDataToExcel(vehicleInfoList);
            updateCurrentPage(MAX_PAGES); // Update current page to the last processed page
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDataToExcel(List<String[]> carDataList) {
        try {
            File file = new File("TurboAzData.xlsx");
            Workbook workbook;

            if (!file.exists()) {
                workbook = new XSSFWorkbook();
            } else {
                FileInputStream inputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(inputStream);
            }

            Sheet sheet;
            if (workbook.getNumberOfSheets() == 0) {
                sheet = workbook.createSheet("Car Data");
            } else {
                sheet = workbook.getSheetAt(0);
            }

            if (!carDataList.isEmpty()) {
                for (String[] carData : carDataList) {
                    Row row = sheet.createRow(sheet.getLastRowNum() + 1);
                    int cellNum = 0;
                    for (String data : carData) {
                        Cell cell = row.createCell(cellNum++);
                        cell.setCellValue(data);
                    }
                }

                FileOutputStream outputStream = new FileOutputStream("TurboAzData.xlsx");
                workbook.write(outputStream);
                outputStream.close();
                System.out.println("Success!");
            } else {
                System.out.println("No data to write!");
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getCurrentPage() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STATE_FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            // Ignore, assume file doesn't exist or is corrupted
        }
        return 1; // Start from the first page if state file doesn't exist or is corrupted
    }

    private static void updateCurrentPage(int page) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STATE_FILE_PATH))) {
            writer.write(String.valueOf(page));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

