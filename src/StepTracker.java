import java.sql.SQLOutput;
import java.util.Locale;

public class StepTracker {
    int[][] stepsByMonth = new int[12][30];     //массив [номер месяца][кол-во шагов по дням]
    int targetSteps = 10000;    //Целевое кол-во шагов
    String[] months = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    Converter converter = new Converter();

    //Метод добавления кол-ва шагов в календарь
    public void saveStepsPerDay(String month, int day, int steps){
        if (steps > 0 && day>0 && day<31 && checkMonth(month)){
            for (int i=0; i<months.length; i++){
                if (month.equals(months[i])){
                    stepsByMonth[i][day-1] = steps;
                }
            }
        }else if (!checkMonth(month)) {
            System.out.println("Такого месяца не существует");
            System.out.println();
        }else{
            System.out.println("Кол-во шагов должно быть больше 0 и день в диапазоне 1 - 30");
            System.out.println();
        }
    }

    //Метод печати статистики
    public void printStatistic(String month){
        int totalStepsPerMonth = 0;
        int maxStepsPerMonth = 0;
        int averageStepsPerMonth = 0;
        double distanceInKm = 0;
        double quantityKkal = 0;
        int bestSerie = 0;
        int tempBestSerie = 0;

        if (checkMonth(month)){
            System.out.print("Количество пройденных шагов по дням за ");
            for (int i=0; i<months.length; i++){
                if (month.equals(months[i])){
                    System.out.println(month);
                    for (int j = 0; j<stepsByMonth[0].length; j++){
                        System.out.print((j+1) + " день: " + stepsByMonth[i][j]);
                        if (j<stepsByMonth[0].length-1){
                            System.out.print(", ");
                        }
                        totalStepsPerMonth += stepsByMonth[i][j];   //вычисление общего кол-ва шагов за месяц

                        if (maxStepsPerMonth < stepsByMonth[i][j]){ //Вычисление максимального кол-ва шагов за месяц
                            maxStepsPerMonth = stepsByMonth[i][j];
                        }

                        if (stepsByMonth[i][j] >= targetSteps){      //Вычисление лучшей серии
                            tempBestSerie++;
                        }else if (bestSerie < tempBestSerie){
                            bestSerie = tempBestSerie;
                            tempBestSerie = 0;
                        }else {
                            tempBestSerie = 0;
                        }

                    }
                    System.out.println();

                    System.out.println("Общее количество шагов за месяц = " + totalStepsPerMonth);
                    System.out.println("Максимальное пройденное количество шагов в месяце = " + maxStepsPerMonth);


                    averageStepsPerMonth = totalStepsPerMonth/stepsByMonth[i].length; //вычисление среднего кол-ва шагов за месяц
                    System.out.println("Среднее количество шагов = " + averageStepsPerMonth);

                    distanceInKm = converter.convertStepsToDistance(totalStepsPerMonth);    //Вычисление дистанции в км
                    System.out.println("Пройденная дистанция (в км) = " + distanceInKm);

                    quantityKkal = converter.convertStepsToKkal(totalStepsPerMonth);    //вычисление Ккал
                    System.out.println("Количество сожжённых килокалорий = " + quantityKkal);

                    System.out.println("Лучшая серия: максимальное количество подряд идущих дней = " + bestSerie);
                    bestSerie = 0;
                    System.out.println();
                }
            }
        }else {
            System.out.println("Такого месяца не существует");
        }
    }

    public void setTargetSteps(int targetSteps){
        if (targetSteps > 0){
            this.targetSteps = targetSteps;
        }else {
            System.out.println("Число должно быть больше 0");
        }

    }

    //Метод проверят существует ли месяц, введенный пользователем
    public boolean checkMonth(String month){
        boolean existingMonth = false;  //Переменная хранит значение, что введенный пользователем месяц существует
        month = month.toLowerCase();
        for (int i=0; i<months.length; i++){
            if (month.equals(months[i])){
                existingMonth=true;
            }
        }
        return existingMonth;
    }
}
