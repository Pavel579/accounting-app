import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        String year = "";
        String[] urlMonth = new String[12];     //Массив ссылок на месячные отчеты
        String urlYear;     //Ссылка на годовой отчет
        MonthReport[] monthReports = new MonthReport[12];       //Массив объектов месячных отчетов
        YearReport yearReport = null;   //Объект годового отчета
        String command;

        while (true) {
            printMenu();
            command = scanner.next();
            if (command.equals("1")) {
                System.out.print("За какой год необходимо считать месячные отчеты > ");
                year = scanner.next();
                for (int i = 1; i <= 12; i++) {
                    if (i < 10) {
                        urlMonth[i - 1] = "./resources/m." + year + "0" + i + ".csv";
                        monthReports[i - 1] = new MonthReport(i - 1);
                        monthReports[i - 1].readMonthlyReport(urlMonth[i - 1]);
                    } else {
                        urlMonth[i - 1] = "./resources/m." + year + "" + i + ".csv";
                        monthReports[i - 1] = new MonthReport(i - 1);
                        monthReports[i - 1].readMonthlyReport(urlMonth[i - 1]);
                    }
                }

            } else if (command.equals("2")) {
                System.out.print("За какой год необходимо считать годовые отчеты > ");
                year = scanner.next();
                urlYear = "./resources/y." + year + ".csv";
                yearReport = new YearReport();
                yearReport.readYearReport(urlYear);

            } else if (command.equals("3")) {
                boolean incorrectInCheckReport = false;
                boolean isObjectCreated = false;
                for (int i = 0; i < monthReports.length; i++) {
                    if (monthReports[i] != null && yearReport != null) {
                        if (monthReports[i].isRead && yearReport.isRead) {
                            if (monthReports[i].checkReports(yearReport)){
                                incorrectInCheckReport=true;
                            }
                            isObjectCreated = true;
                        }
                    }
                }
                if (!incorrectInCheckReport && isObjectCreated) {
                    System.out.println("Операция завершена успешно");
                }else if (!isObjectCreated){
                    System.out.println("Отчеты не считаны");
                }

            } else if (command.equals("4")) {
                for (int i = 0; i < monthReports.length; i++) {
                    if (monthReports[i] != null) {
                        if (monthReports[i].isRead) {
                            System.out.println("Отчет за " + monthReports[i].getCurrentMonth());
                            monthReports[i].printProfitableItem();
                            monthReports[i].printMaxExpense();
                        }
                    } else {
                        System.out.println("Необходимо сперва считать отчет");
                        break;
                    }
                }

            } else if (command.equals("5")) {
                if (yearReport != null) {
                    if (yearReport.isRead) {
                        System.out.println("Отчет за " + year + " год.");
                        yearReport.printProfitByMonth();
                        yearReport.printAverageExpenseInYear();
                        yearReport.printAverageProfitInYear();
                    }
                } else {
                    System.out.println("Необходимо сперва считать отчет");
                }

            } else if (command.equalsIgnoreCase("exit")) {
                break;

            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    //Метод печати основного меню на экран
    public static void printMenu() {
        System.out.println();
        System.out.println("***** Основное меню! ******");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("Для выхода введите exit");
        System.out.print("Введите номер команды > ");
    }
}

