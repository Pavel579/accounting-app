import java.util.ArrayList;

public class MonthReport {
    CSVReader csvReader = new CSVReader();
    private String report;      //Переменная хранит значение считанного отчета из файла
    public boolean isRead = false;      //Переменная хранит значение считан отчет или нет
    private String[] lines;
    private String[] linesContent;
    private ArrayList<String> itemName = new ArrayList<>();     //Список столбца itemName из таблицы файла
    private ArrayList<Boolean> isExpense = new ArrayList<>();   //Список столбца isExpense из таблицы файла
    private ArrayList<Integer> quantity = new ArrayList<>();    //Список столбца quantity из таблицы файла
    private ArrayList<Integer> sumOfOne = new ArrayList<>();    //Список столбца sumOfOne из таблицы файла
    private int currentMonth;       //Номер текущего месяца
    String[] monthsName = {"январь", "февраль", "март", "апрель", "май", "июнь",
            "июль", "август", "сентябрь", "ноябрь", "декабрь"};
    CheckReportData checkReportData = new CheckReportData();

    //Инициализация номера месяца
    MonthReport(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    //Метод считывает CSV файл месячного отчета и копирует данные в массивы ArrayList
    public void readMonthlyReport(String url) {
        boolean isCorrectFile = true;
        report = csvReader.readCSV(url);
        if (report != null) {
            lines = report.split("\\n");
            for (int i = 1; i < lines.length; i++) {
                lines[i] = lines[i].replace("\r", "");
                linesContent = lines[i].split(",");

                if (!checkReportData.isEmptyData(linesContent[0]) && checkReportData.isBooleanData(linesContent[1]) &&
                        checkReportData.isPositiveNumber(linesContent[2]) && checkReportData.isPositiveNumber(linesContent[3])) {
                    itemName.add(linesContent[0]);
                    isExpense.add(Boolean.parseBoolean(linesContent[1]));
                    quantity.add(Integer.parseInt(linesContent[2]));
                    sumOfOne.add(Integer.parseInt(linesContent[3]));
                } else {
                    System.out.println("В файле " + url.replace("./resources/", "") + " имеются ошибки в строке " + i + ". Исправьте файл и считайте его заново.");
                    isCorrectFile = false;
                    break;
                }

            }
            isRead = isCorrectFile;

        }
    }

    //Метод печатает самый прибыльный товар и его сумму
    public void printProfitableItem() {
        int max = 0;
        int index = 0;
        String profitableItem;
        if (isRead) {
            for (int i = 0; i < quantity.size(); i++) {
                int multiplyQuantityOnSum = quantity.get(i) * sumOfOne.get(i);
                if (!isExpense.get(i) && multiplyQuantityOnSum > max) {
                    max = multiplyQuantityOnSum;
                    index = i;
                }
            }
            profitableItem = itemName.get(index);
            System.out.println("Самый прибыльный товар - \"" + profitableItem + "\" суммой - " + sumOfOne.get(index));
        }
    }

    //Метод печатает самую большую трату
    public void printMaxExpense() {
        int maxExpense = 0;
        int index = 0;
        if (isRead) {
            for (int i = 0; i < itemName.size(); i++) {
                int multiplyQuantityOnSum = quantity.get(i) * sumOfOne.get(i);
                if (isExpense.get(i) && multiplyQuantityOnSum > maxExpense) {
                    maxExpense = multiplyQuantityOnSum;
                    index = i;
                }
            }
            System.out.println("Самая большая трата -\"" + itemName.get(index) + "\" на сумму - " + maxExpense);
        }
    }

    /* Метод сверяет месячный отчет с годовым.
    В случае несоответствий выводит номер месяца и возвращает true,
    иначе - false */
    public boolean checkReports(YearReport yearReport) {
        int monthExpense = 0;
        int monthProfit = 0;

        if (isRead && yearReport.isRead) {
            for (int i = 0; i < isExpense.size(); i++) {
                if (isExpense.get(i)) {
                    monthExpense += quantity.get(i) * sumOfOne.get(i);
                } else {
                    monthProfit += quantity.get(i) * sumOfOne.get(i);
                }
            }
            if (yearReport.getProfitExpense()[currentMonth][0] != monthProfit ||
                    yearReport.getProfitExpense()[currentMonth][1] != monthExpense) {
                System.out.println("Обнаружено несоответствие в месяце - " + monthsName[currentMonth]);
                return true;
            }
        }
        return false;
    }

    //Метод возвращает название текущего месяца на русском
    public String getCurrentMonth() {
        return monthsName[currentMonth];
    }



}
