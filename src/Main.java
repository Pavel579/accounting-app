import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        int command;
        StepTracker stepTracker = new StepTracker();


        while(true){
            printMenu();
            command = Integer.parseInt(scanner.next());

            if (command == 1){
                //Ввод данных с клавиатуры
                System.out.print("Введите название месяца > ");
                String month = scanner.next();
                System.out.print("Введите номер дня > ");
                int day = scanner.nextInt();
                System.out.print("Введите кол-во шагов > ");
                int step = scanner.nextInt();

                stepTracker.saveStepsPerDay(month, day, step);

            }else if (command == 2){
                System.out.print("Введите месяц за который необходимо показать статистику > ");
                String month = scanner.next();
                stepTracker.printStatistic(month);
            }else if (command == 3){
                System.out.print("Введите число целевых шагов > ");
                int targetSteps = scanner.nextInt();
                stepTracker.setTargetSteps(targetSteps);
            }else if (command == 4){
                System.out.println("До свидания!");
                break;
            }else {
                System.out.println("Такой команды не существует, повторите ввод");
            }
        }
    }

    //Метод печати основного меню на экран
    public static void printMenu(){
        System.out.println("Основное меню!");
        System.out.println("1. Введите количество шагов за определённый день");
        System.out.println("2. Напечатать статистику за определённый месяц");
        System.out.println("3. Изменить цель по количеству шагов в день");
        System.out.println("4. Выйти из приложения");
        System.out.print("Введите номер команды > ");
    }
}

