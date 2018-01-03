package snw.engine.database;

import com.sun.org.apache.regexp.internal.RE;
import snw.engine.core.Engine;
import snw.tests.TestAll;

public enum DataType {
    /*
        Formats:
        long    : an integer with L/l
        int     : an integer with I/i(optional)
        short   : an integer with S/s
        char    : a character surrounded with a pair of quote mark "'"
                  or a simple character (only matches when cannot match any other type other than String)
        byte    : an integer with B/b
        double  : a digital number with D/d(optional)
                  or an integer with D/d
        float   : a digital number with F/f
                  or an integer with F/f
        boolean : true/false/True/False
        String  : a string surrounded with a pair of double quote mark """
                  or a simple string (only matches when cannot match any other type)
        Object  : [class : reload data] (whitespace will be trimmed) (reload data can be empty)
     */
    LONG, INT, SHORT, CHAR, BYTE, DOUBLE, FLOAT, BOOLEAN, STRING, OBJECT;


    public static DataType getType(String valueStr) {
        if (valueStr.matches("(true)|(false)|(True)|(False)")) {
            return BOOLEAN;
        }
        if (valueStr.matches("'.'")) {
            return CHAR;
        }
        if (valueStr.matches("\".*\"")) {
            return STRING;
        }
        if (valueStr.matches("[0-9]+[Ii]?")) {
            return INT;
        }
        if (valueStr.matches("[0-9]+[Ll]")) {
            return LONG;
        }
        if (valueStr.matches("[0-9]+[Ss]")) {
            return SHORT;
        }
        if (valueStr.matches("[0-9]+[Bb]")) {
            return BYTE;
        }
        if (valueStr.matches("([0-9]*\\.[0-9]+[Dd]?)|[0-9]+[Dd]")) {
            return DOUBLE;
        }
        if (valueStr.matches("([0-9]*\\.)?[0-9]+[Ff]")) {
            return FLOAT;
        }
        if (valueStr.matches("\\[.+:.*]")) {
            return OBJECT;
        }
        if (valueStr.length() == 1) {
            return CHAR;
        }
        return STRING;
    }

    public static int parseInt(String value) {
        if (value.endsWith("I") || value.endsWith("i")) {
            value = value.substring(0, value.length() - 1);
        }
        return Integer.parseInt(value);
    }

    public static String saveInt(int value) {
        return value + "";
    }

    public static long parseLong(String value) {
        value = value.substring(0, value.length() - 1);
        return Long.parseLong(value);
    }

    public static String saveLong(long value) {
        return value + "L";
    }

    public static short parseShort(String value) {
        value = value.substring(0, value.length() - 1);
        return Short.parseShort(value);
    }

    public static String saveShort(short value) {
        return value + "S";
    }

    public static byte parseByte(String value) {
        value = value.substring(0, value.length() - 1);
        return Byte.parseByte(value);
    }

    public static String saveByte(byte value) {
        return value + "B";
    }

    public static char parseChar(String value) {
        if (value.length() != 1) {
            return value.charAt(1);
        } else {
            return value.charAt(0);
        }
    }

    public static String saveChar(char value) {
        return "'" + value + "'";
    }

    public static double parseDouble(String value) {
        if (value.endsWith("D") || value.endsWith("d")) {
            value = value.substring(0, value.length() - 1);
        }
        if (value.startsWith(".")) {
            value = "0" + value;
        }
        return Double.parseDouble(value);
    }

    public static String saveDouble(double value) {
        return value + "";
    }

    public static float parseFloat(String value) {
        value = value.substring(0, value.length() - 1);
        if (value.startsWith(".")) {
            value = "0" + value;
        }
        return Float.parseFloat(value);
    }

    public static String saveFloat(float value) {
        return value + "F";
    }

    public static boolean parseBoolean(String value) {
        return value.equals("True") || value.equals("true");
    }

    public static String saveBoolean(boolean value) {
        return value + "";
    }

    public static String parseString(String value) {
        if (value.matches("\".*\"")) {
            value = value.substring(1, value.length() - 1);
        }
        return value;
    }

    public static String saveString(String value) {
        return "\"" + value + "\"";
    }

    public static Reloadable parseObject(String value) {
        return Engine.loadUserDataStr(value.substring(1, value.length() - 1));
    }

    public static String saveObject(Reloadable value) {
        return "[" + value.getClass().getName() + ": " + value.save() + "]";
    }

    public static void main(String[] args) {
        TestAll test = new TestAll();
        test.setName("1!!");
        System.out.println(test);
        String testInfo = saveObject(test);

        test = null;

        System.out.println(test);

        test = (TestAll) parseObject(testInfo);

        System.out.println(test);
    }
}