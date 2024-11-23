/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.DateFormat; // Dùng để định dạng ngày tháng 
import java.text.ParseException; // Lớp mô tả lỗi khi phân tích chuỗi 
import java.text.SimpleDateFormat; // Dùng để định dạng ngày tháng 
import java.util.ArrayList;
import java.util.Calendar; // Lớp mô tả cho lịch nói chung 
import java.util.Date; // Lớp mô tả ngày tháng 
import java.util.GregorianCalendar; // Lớp mô tả cho dương lịch ngày nay 
import java.util.List;
import java.util.Scanner; // Lớp nhập dữ liệu 

/**
 *
 * @author bravee06
 */
public class Validation {
    public static final Scanner sc = new Scanner(System.in);

    /**
     * Target: Parsing the input string to get a boolean data ( true / false )
     *
     * @param input : input string
     * @return true if the first character in input is 'T' or '1' or 'Y'
     */
    public static boolean parseBoolean(String input) {
        input = input.trim().toUpperCase(); // chuẩn hóa chuỗi nhập
        char c = input.charAt(0); // lấy ký tự đầu của chuỗi nhập
        return c == 'T' || c == '1' || c == 'Y'; // trả về true nếu ký tự này là T,1,Y
    }

    // " 7 ... --- 2 //// 2023 " ---> "7-2-2023"

    /**
     * Target: Normalizing a date string: Using '-' to sperate parts only
     *
     * @param dateStr: input date string
     * @return new string
     */
    public static String normalizeDateStr(String dateStr) {
        // Loại bỏ tất cả các khoảng trống trong chuỗi nhập. Dung replaceAll()
        // regex phù hợp " 7 ... --- 2 //// 2023 " --> "7...---2////2023"

        String result = dateStr.replaceAll("[\\s]+", "");

        // Thay thế tất cả các nhóm ký tự . / - bằng '-'. Dùng replaceAll() với regex
        // phù hợp "7...---2////2023" --> "7-2-2023" rồi trả về kết qủa đã xử lý
        result = result.replaceAll("[./-]+", "/");
        return result;
    }

    /**
     * Target: Parsing the input string to date data
     *
     * @param inputStr:   date string input
     * @param dateFormat: chosen date format
     * @return Date object if succeful and null if failed
     */
    public static Date parseDate(String inputStr, String dateFormat) {
        inputStr = normalizeDateStr(inputStr); // chuẩn hóa chuỗi ngày tháng
        DateFormat fommatter = new SimpleDateFormat(dateFormat);
        try { // Dùng formatter parse chuỗi nhập rồi trả kết quả
            return fommatter.parse(inputStr);

        } catch (ParseException e) {
            System.err.println(e);
        }
        return null;// không phân tích thành công thì trả về null
    }

    /**
     * Target: Getting year of date data
     *
     * @param d:            Date data
     * @param calendarPart: data part is declared in the class Calender
     * @return year in date data
     */
    public static int getPart(Date d, int calendarPart) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(calendarPart);
    }

    /* Method for reading data */
    /* #1. Reading a boolean data */
    public static boolean readBoolean(String prompt) {
        System.out.println(prompt + ": ");
        return parseBoolean(sc.nextLine());
    }

    /* Reading a string using predefined pattern */
    // "abc" "abcd" "abc"
    public static String readStr(String prompt, String pattern) {
        String input;
        boolean valid = false;
        do {
            // yêu cầu người dùng nhập sâu
            System.out.println(prompt + ": ");
            input = sc.nextLine();
            // không đúng với pattern => valid = true
            valid = input.matches(pattern);
        } while (!valid);
        return input;
    }

    /* Reading a date using predefined date format */
    public static Date readDate(String prompt, String dateFormat) {
        String input;
        Date result;
        // parseDate => Date object
        // parseDate => null
        do {
            // yêu cầu người dùng nhập vào ngày
            System.out.println(prompt + ": ");
            input = sc.nextLine();
            // chuyển dữ liệu sâu sang dạng Date theo dateFormat
            result = parseDate(input, dateFormat);
            // Nếu date trả về null thì thông báo người dùng nhập không hợp lệ
            if (result == null)
                System.err.println("Date data is not valid !");
        } while (result == null);
        return result;
    }

    /* "01/02/2000" "01/02/1999" => báo lỗi */
    /* Enter a date after given date */
    public static Date readDateAfter(String prompt, String dateFormat, Date givenDate) {
        String input;
        Date result;
        boolean valid = false;
        // parseDate => Date object
        // parseDate => null
        do {
            // yêu cầu người dùng nhập vào ngày
            System.out.println(prompt + ": ");
            input = sc.nextLine();
            // chuyển dữ liệu sâu sang dạng Date theo dateFormat
            result = parseDate(input, dateFormat);
            // so sánh 2 date với nhau và kiểm tra định dạng
            valid = (result != null && result.after(givenDate));
            // Nếu date trả về null thì thông báo người dùng nhập không hợp lệ
            if (result == null)
                System.err.println("Date data is not valid !");
        } while (!valid);
        return result;
    }

    /* Enter a date before given date */

    public static Date readDateBefore(String prompt, String dateFormat, Date givenDate) {
        String input;
        Date result;
        boolean valid = false;
        // parseDate => Date object
        // parseDate => null
        do {
            // yêu cầu người dùng nhập vào ngày
            System.out.println(prompt + ": ");
            input = sc.nextLine();
            // chuyển dữ liệu sâu sang dạng Date theo dateFormat
            result = parseDate(input, dateFormat);
            // so sánh 2 date với nhau và kiểm tra định dạng
            valid = (result != null && result.before(givenDate));
            // Nếu date trả về null thì thông báo người dùng nhập không hợp lệ
            if (result == null)
                System.err.println("Date data is not valid !");
        } while (!valid);
        return result;
    }

    /* #6. Auto generated code */
    // Danh sách học sinh, mỗi học sinh có 1 code khác nhau, ứng với học sinh thứ n
    // n =123 => S0000123
    // n = 3 => S0000003
    // String.format()
    // printf("định dạng(%d %s %c)",biến);
    public static String generateCode(String prefix, int length, int currentSize) {
        String format = "%0" + length + "d"; // %07d
        return prefix + String.format(format, currentSize);
    }

    public static int readInteger(String prompt, int min, int max) {
        int result = 0;
        boolean valid = false;
        do {
            System.out.println(prompt + ": ");
            try {
                result = Integer.parseInt(sc.nextLine());
                valid = result >= min && result <= max;
            } catch (Exception e) {
                valid = false;
            }
            if (!valid) {
                System.err.println("Invalid input !");
            }
        } while (!valid);
        return result;
    }

    public static double readDouble(String prompt, double min, double max) {
        double result = 0;
        boolean valid = false;
        do {
            System.out.println(prompt + ": ");
            try {
                result = Double.parseDouble(sc.nextLine());
                valid = result >= min && result <= max;
            } catch (Exception e) {
                valid = false;
            }
            if (!valid) {
                System.err.println("Invalid input !");
            }
        } while (!valid);
        return result;
    }

    public static List<String> readList(String prompt, String regex) {
        List<String> result = new ArrayList<>();
        boolean valid = false;
        do {
            System.out.println(prompt + ": ");
            String input = sc.nextLine();
            String[] items = input.split(",");
            for (String item : items) {
                if (item.matches(regex)) {
                    result.add(item);
                }
            }
            valid = result.size() > 0;
            if (!valid) {
                System.err.println("Invalid input !");
            }
        } while (!valid);
        return result;
    }

}
