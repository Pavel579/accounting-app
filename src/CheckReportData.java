public class CheckReportData {
    //Проверка строки на положительное целочисленное значение
    public boolean isPositiveNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //Проверка на пустую строку
    public boolean isEmptyData(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //Проверка строки на Boolean
    public boolean isBooleanData(String s) {
        if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
            return true;
        } else {
            return false;
        }
    }
}
