import java.util.ArrayList;

public class YearReport {
    CSVReader csvReader = new CSVReader();
    private String report;      //Переменная хранит значение считанного отчета из файла
    public boolean isRead = false;  //Переменная хранит значение считан отчет или нет
    private String[] lines;
    private String[] linesContent;
    String[] monthsName = {"январь", "февраль", "март", "апрель", "май", "июнь",
            "июль", "август", "сентябрь", "ноябрь", "декабрь"};
    private ArrayList<Integer> month = new ArrayList<>();   //Список столбца month из таблицы файла
    private ArrayList<Integer> amount = new ArrayList<>();  //Список столбца amount из таблицы файла
    private ArrayList<Boolean> isExpense = new ArrayList<>();   //Список столбца isExpense из таблицы файла
    private int[][] profitExpense = new int[12][2]; //Массив [месяц][0 - доходы, 1 - расходы]
    int monthQuantityInReport;      //Заполненное кол-во месяцев в отчете
    CheckReportData checkReportData = new CheckReportData();

    /* Метод читает данные из файла CSV годового отчета,
     копирует данные в массивы ArrayList и заполняет массив profitExpense
    данными о доходах и расходах по месяцам */
    public void readYearReport(String url) {
        report = csvReader.readCSV(url);
        boolean isCorrectFile = true;
        if (report != null) {
            lines = report.split("\\n");
            for (int i = 1; i < lines.length; i++) {
                lines[i] = lines[i].replace("\r", "");
                linesContent = lines[i].split(",");

                if (checkReportData.isPositiveNumber(linesContent[0]) && checkReportData.isPositiveNumber(linesContent[1]) && checkReportData.isBooleanData(linesContent[2])){
                    month.add(Integer.parseInt(linesContent[0]));
                    amount.add(Integer.parseInt(linesContent[1]));
                    isExpense.add(Boolean.parseBoolean(linesContent[2]));
                }else {
                    System.out.println("В файле " + url.replace("./resources/", "") + " имеются ошибки в строке " + i + ". Исправьте файл и считайте его заново.");
                    isCorrectFile = false;
                    break;
                }
            }

            if (isCorrectFile){
                for (int i = 0; i < month.size(); i++) {
                    if (isExpense.get(i)) {
                        profitExpense[month.get(i) - 1][1] += amount.get(i);
                    } else {
                        profitExpense[month.get(i) - 1][0] += amount.get(i);
                    }
                }
            }

            isRead = isCorrectFile;

        }
    }

    //Метод печатает прибыль по каждому месяцу
    public void printProfitByMonth() {

        monthQuantityInReport = month.size() / 2;
        if (monthQuantityInReport < 12) {
            System.out.println("Годовой отчет не полный. Доступна информация по некоторым месяцам");
        } else {
            System.out.println("Полный годовой отчет за 12 месяцев");
        }
        for (int i = 0; i < monthQuantityInReport; i++) {
            int profitPerMonth = profitExpense[i][0] - profitExpense[i][1];
            System.out.println("Прибыль за " + monthsName[i] + " " + profitPerMonth);
        }
    }

    //Метод печатет средний расход за те месяцы, которые заполнены в CSV файле
    public void printAverageExpenseInYear() {
        int totalExpenseInYear = 0;
        for (int i = 0; i < monthQuantityInReport; i++) {
            totalExpenseInYear += profitExpense[i][1];
        }
        double averageExpenseInYear = totalExpenseInYear / (double) monthQuantityInReport;

        System.out.print("Средний расход за " + monthQuantityInReport + " " + setCorrectMonth(monthQuantityInReport));
        System.out.println(" составил: " + averageExpenseInYear);

    }

    //Метод печатет средний доход за те месяцы, которые заполнены в CSV файле
    public void printAverageProfitInYear() {
        int totalProfitInYear = 0;
        for (int i = 0; i < monthQuantityInReport; i++) {
            totalProfitInYear += profitExpense[i][0];
        }
        double averageProfitInYear = totalProfitInYear / (double) monthQuantityInReport;

        System.out.print("Средний доход за " + monthQuantityInReport + " " + setCorrectMonth(monthQuantityInReport));
        System.out.println(" составил: " + averageProfitInYear);

    }

    /*Метод возвращает слово "месяц" в нужном склонении в зависимости
     от кол-ва месяцев в году, которое передается на вход*/
    private String setCorrectMonth(int monthQuantity) {
        String month = "";
        if (monthQuantity == 1) {
            month = "месяц";
        } else if (monthQuantity > 1 && monthQuantity < 5) {
            month = "месяца";
        } else if (monthQuantity > 4) {
            month = "месяцев";
        }
        return month;
    }

    //Метод возвращает массив доходов и расходов profitExpense
    public int[][] getProfitExpense() {
        if (isRead) {
            return profitExpense;
        } else {
            return null;
        }

    }
}
