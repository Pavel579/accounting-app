public class CheckReportData {
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

    public boolean isEmptyData(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBooleanData(String s) {
        if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
            return true;
        } else {
            return false;
        }
    }
}
