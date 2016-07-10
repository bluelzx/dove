/**
 * @(#)NumberUtil.java
 */
package com.gustz.dove.api.service.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Depict: Number util
 * 
 * @author zhfgzhang
 * @date [2011-3-29]
 */
public abstract class NumberUtil extends NumberUtils {

    // --------------------- 精确数字 -------------------//
    /**
     * 精确到小数点后2位
     * 
     * @param numeric
     * @return 0.01
     */
    public static String formatDecimal2(Object numeric) {
        try {
            return new DecimalFormat("0.0#").format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后2位
     * 
     * @param numeric
     * @return 0.01
     */
    public static double formatDecimal2_(Object numeric) {
        String str = formatDecimal2(numeric);
        if (isDouble(str)) {
            return Double.parseDouble(str);
        }
        return 0.0;
    }

    /**
     * 精确到小数点后2位
     * 
     * @param maxDigit
     *            最大位数
     * @param numeric
     * @return 0.01
     * @throws Exception
     */
    public static double formatDecimal2_(final int maxDigit, Object numeric) throws Exception {
        String str = formatDecimal2(numeric);
        if (isDouble(str)) {
            Double tmp = Double.parseDouble(str);
            int tmpInt = tmp.intValue();
            if (String.valueOf(tmpInt).length() > maxDigit) {
                throw new Exception("numeric digit gt max digit!");
            }
            return tmp;
        }
        return 0.0;
    }

    /**
     * 精确到小数点后3位
     * 
     * @param numeric
     * @return 0.01
     */
    public static double formatDecimal3_(Object numeric) {
        String str = formatDecimal3(numeric);
        if (isDouble(str)) {
            return Double.parseDouble(str);
        }
        return 0.0;
    }

    /**
     * 精确到小数点后2位
     * 
     * @param numeric
     * @param unit
     * @return 0.01kg
     */
    public static String formatDecimal2(Object numeric, String unit) {
        try {
            return new DecimalFormat("0.0#".concat(unit)).format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后3位
     * 
     * @param numeric
     * @return 0.001
     */
    public static String formatDecimal3(Object numeric) {
        try {
            return new DecimalFormat("0.0##").format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后3位
     * 
     * @param numeric
     * @param unit
     * @return 0.001kg
     */
    public static String formatDecimal3(Object numeric, String unit) {
        try {
            return new DecimalFormat("0.0##".concat(unit)).format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后5位
     * 
     * @param numeric
     * @return 0.00001
     */
    public static String formatDecimal5(Object numeric) {
        try {
            return new DecimalFormat("0.0####").format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后7位
     * 
     * @param numeric
     * @return 0.0000001
     */
    public static String formatDecimal7(Object numeric) {
        try {
            return new DecimalFormat("0.0######").format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后9位
     * 
     * @param numeric
     * @return 0.000000001
     */
    public static String formatDecimal9(Object numeric) {
        try {
            return new DecimalFormat("0.0########").format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后2位,并以逗号分隔每三位数.
     * 
     * @param numeric
     * @return 123,123.01
     */
    public static String formatCaDecimal2(Object numeric) {
        try {
            return new DecimalFormat("#,##0.0#").format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后3位,并以逗号分隔每三位数.
     * 
     * @param numeric
     * @return 123,123.001
     */
    public static String formatCaDecimal3(Object numeric) {
        try {
            return new DecimalFormat("#,##0.0##").format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 精确到小数点后4位,并以逗号分隔每三位数.
     * 
     * @param numeric
     * @return 123,123.0001
     */
    public static String formatCaDecimal4(Object numeric) {
        try {
            return new DecimalFormat("#,##0.0###").format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 格式化为数字,并精确到小数点后1位.
     * 
     * @param numeric
     * @return 0.1
     */
    public static String formatNumeric(Object numeric) {
        return formatNumeric("0.#", numeric);
    }

    /**
     * 格式化为数字,并精确到小数点后2位.
     * 
     * @param numeric
     * @return 0.01
     */
    public static String formatNumeric2(Object numeric) {
        return formatDecimal2(numeric);
    }

    /**
     * 格式化为数字,并精确到小数点后3位.
     * 
     * @param numeric
     * @return 0.001
     */
    public static String formatNumeric3(Object numeric) {
        return formatDecimal3(numeric);
    }

    /**
     * 按照模式精确小数点后位数
     * 
     * @param pattern
     * @param numeric
     * @return
     */
    public static String formatNumeric(String pattern, Object numeric) {
        try {
            return new DecimalFormat(pattern).format(Double.parseDouble(formatExcepNum(numeric)));
        } catch (Exception ex) {
            return null;
        }
    }

    // ------------------------------------------------
    // ----------------------- 数字运算 ----------------
    /**
     * 提供（相对）精确的除法运算,当发生除不尽的情况时,由scale参数指定精度,以后的数字四舍五入.
     * 
     * @param scale
     *            精确位数
     * @param d1
     *            被除数
     * @param d2
     *            除数
     */
    public static double divide(int scale, double d1, double d2) {
        double _data = 0.0d;
        String _d1 = String.valueOf(d1);
        String _d2 = String.valueOf(d2);
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero!");
        } else if (isNumeric(_d1) && isNumeric(_d2)) {
            try {
                _data = new BigDecimal(_d1).divide(new BigDecimal(_d2), scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
            } catch (Exception ex) {
                _data = 0.0d;
            }
        }
        return _data;
    }

    /**
     * 提供精确的乘法运算
     * 
     * @param d1
     *            被乘数
     * @param d2
     *            乘数
     */
    public static double multiply(double d1, double d2) {
        String _d1 = String.valueOf(d1);
        String _d2 = String.valueOf(d2);
        if (isNumeric(_d1) && isNumeric(_d2)) {
            return new BigDecimal(_d1).multiply(new BigDecimal(_d2)).doubleValue();
        }
        return 0.0d;
    }

    /**
     * 提供精确的减法运算
     * 
     * @param d1
     *            被减数
     * @param d2
     *            减数
     */
    public static double subtract(double d1, double d2) {
        String _d1 = String.valueOf(d1);
        String _d2 = String.valueOf(d2);
        if (isNumeric(_d1) && isNumeric(_d2)) {
            return new BigDecimal(_d1).subtract(new BigDecimal(_d2)).doubleValue();
        }
        return 0.0d;
    }

    /**
     * 提供精确的减法运算
     * 
     * @param s1
     *            被减数
     * @param s2
     *            减数
     */
    public static double subtract(String s1, String s2) {
        s1 = s1.replaceAll(",", "");
        s2 = s2.replaceAll(",", "");
        if (isNumeric(s1) && isNumeric(s2)) {
            return new BigDecimal(s1).subtract(new BigDecimal(s2)).doubleValue();
        }
        return 0.0d;
    }

    /**
     * 提供精确的加法运算
     * 
     * @param d1
     *            被加数
     * @param d2
     *            加数
     */
    public static double add(double d1, double d2) {
        String _d1 = String.valueOf(d1);
        String _d2 = String.valueOf(d2);
        if (isNumeric(_d1) && isNumeric(_d2)) {
            return new BigDecimal(_d1).add(new BigDecimal(_d2)).doubleValue();
        }
        return 0.0d;
    }

    /**
     * 提供精确的加法运算
     * 
     * @param s1
     *            被加数
     * @param s2
     *            加数
     */
    public static double add(String s1, String s2) {
        s1 = s1.replaceAll(",", "");
        s2 = s2.replaceAll(",", "");
        if (isNumeric(s1) && isNumeric(s2)) {
            return new BigDecimal(s1).add(new BigDecimal(s2)).doubleValue();
        }
        return 0.0d;
    }

    /**
     * 提供精确的小数位四舍五入处理
     * 
     * @param scale
     * @param str
     */
    public static double round(int scale, String str) throws Exception {
        str = str.replaceAll(",", "");
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero!");
        } else if (isNumeric(str)) {
            return new BigDecimal(str).divide(new BigDecimal("1"), scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        }
        return 0.0d;
    }

    /**
     * 提供精确的小数位四舍五入处理
     * 
     * @param scale
     * @param d
     */
    public static double round(int scale, double d) throws Exception {
        String _d = String.valueOf(d);
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero!");
        } else if (isNumeric(_d)) {
            return new BigDecimal(_d).divide(new BigDecimal("1"), scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        }
        return 0.0d;
    }

    /**
     * 提供精确的小数位四舍五入处理
     * 
     * @param scale
     * @param f
     */
    public static double round(int scale, float f) throws Exception {
        String _f = String.valueOf(f);
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero!");
        } else if (isNumeric(_f)) {
            return new BigDecimal(_f).divide(new BigDecimal("1"), scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        }
        return 0.0d;
    }

    /**
     * 提供精确的类型转换(Float)
     * 
     * @param str
     */
    public static float convertsToFloat(String str) {
        return new BigDecimal(formatDecimal9(str)).floatValue();
    }

    /**
     * 提供精确的类型转换(Float)
     * 
     * @param d
     */
    public static float convertsToFloat(double d) {
        return new BigDecimal(d).floatValue();
    }

    /**
     * 提供精确的类型转换(Int)不进行四舍五入
     * 
     * @param str
     */
    public static int convertsToInt(String str) {
        return new BigDecimal(formatDecimal9(str)).intValue();
    }

    /**
     * 提供精确的类型转换(Int)不进行四舍五入
     * 
     * @param d
     */
    public static int convertsToInt(double d) {
        return new BigDecimal(d).intValue();
    }

    /**
     * 提供精确的类型转换(Long)
     * 
     * @param str
     */
    public static long convertsToLong(String str) {
        return new BigDecimal(formatDecimal9(str)).longValue();
    }

    /**
     * 提供精确的类型转换(Long)
     * 
     * @param d
     */
    public static long convertsToLong(double d) {
        return new BigDecimal(d).longValue();
    }

    /**
     * 返回两个数中大的一个值
     * 
     * @param d1
     *            需要被对比的第一个数
     * @param d2
     *            需要被对比的第二个数
     */
    public static double max(double d1, double d2) {
        return new BigDecimal(d1).max(new BigDecimal(d2)).doubleValue();
    }

    /**
     * 返回两个数中小的一个值
     * 
     * @param d1
     *            需要被对比的第一个数
     * @param d2
     *            需要被对比的第二个数
     */
    public static double min(double d1, double d2) {
        return new BigDecimal(d1).min(new BigDecimal(d2)).doubleValue();
    }

    /**
     * 精确对比两个数字
     * 
     * @param d1
     *            需要被对比的第一个数
     * @param d2
     *            需要被对比的第二个数
     * @return 如果两个数一样则返回0,如果第一个数比第二个数大则返回1,反之返回-1.
     */
    public static int compareTo(double d1, double d2) {
        return new BigDecimal(d1).compareTo(new BigDecimal(d2));
    }

    /**
     * 判断是否是数字
     * 
     * @param str
     */
    public static boolean isNumeric(String str) {
        return NumberUtils.isNumber(str);
    }

    /**
     * 交换字符位置
     * 
     * @param data
     * @param src
     * @param dest
     */
    public static void swap(int[] data, int src, int dest) {
        int temp = data[src];
        data[src] = data[dest];
        data[dest] = temp;
    }

    /**
     * 快速排序入口
     * 
     * @param intArray
     */
    public static int[] quickSortMain(int[] intArray) {
        if (intArray == null)
            return null;
        int[] srcDatas = (int[]) intArray.clone();
        return quickSort(srcDatas, 0, srcDatas.length - 1);
    }

    /**
     * 快速排序
     * 
     * @param srcDatas
     * @param first
     * @param last
     */
    private static int[] quickSort(int[] srcDatas, int first, int last) {
        if (first < last) {
            int pos = partition(srcDatas, first, last);
            quickSort(srcDatas, first, pos - 1);
            quickSort(srcDatas, pos + 1, last);
        }
        return srcDatas;
    }

    /**
     * 寻找数组的分支点
     * 
     * @param srcDatas
     *            待排序的数组
     * @param first
     *            起始下标
     * @param last
     *            终止下标
     * @return 传入数组的第一个数的最终下标
     */
    private static int partition(int[] srcDatas, int first, int last) {
        int temp = srcDatas[first];
        int pos = first;
        for (int i = first + 1; i <= last; i++) {
            if (srcDatas[i] < temp) {
                pos++;
                swap(srcDatas, pos, i);
            }
        }
        // 交换位置
        swap(srcDatas, first, pos);
        return pos;
    }

    // ------------------------------------------
    // ----------------- 数字大小比较 -------------
    /**
     * 比较前数d1是否小于后数d2
     * 
     * @param d1
     * @param d2
     */
    public static boolean minCompare(double d1, double d2) {
        return Double.compare(d1, d2) == -1 ? true : false;
    }

    /**
     * 比较前数d1是否大于后数d2
     * 
     * @param d1
     * @param d2
     */
    public static boolean maxCompare(double d1, double d2) {
        return Double.compare(d1, d2) == 1 ? true : false;
    }

    /**
     * 比较前数d1是否等于后数d2
     * 
     * @param d1
     * @param d2
     */
    public static boolean equalCompare(double d1, double d2) {
        return Double.compare(d1, d2) == 0 ? true : false;
    }

    // ----------------------------------------------------------
    // --------------------- 货币/百分数数字转换 -------------------
    /**
     * 格式化货币
     * 
     * @param money
     * @param locale
     */
    public static String formatMoney(double money, Locale locale) {
        return NumberFormat.getCurrencyInstance(locale).format(money);
    }

    /**
     * 格式化为人民币
     * 
     * @param money
     */
    public static String formatChinaMoney(double money) {
        return NumberFormat.getCurrencyInstance(Locale.CHINA).format(money);
    }

    /**
     * 格式化为美元
     * 
     * @param money
     */
    public static String formatUSMoney(double money) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(money);
    }

    /**
     * 格式化为百分数
     * 
     * @param numeric
     */
    public static String formatPercent(double numeric) {
        return new DecimalFormat("#.###%").format(numeric);
    }

    /**
     * 获取随机2位序列号
     */
    public static String getRandomSN2() {
        return getRandomSN(2);
    }

    /**
     * 获取随机4位序列号
     */
    public static String getRandomSN4() {
        return getRandomSN(4);
    }

    /**
     * 获取随机5位序列号
     * 
     * @return
     */
    public static String getRandomSN5() {
        return getRandomSN(5);
    }

    /**
     * 获取随机6位序列号
     * 
     * @return
     */
    public static String getRandomSN6() {
        return getRandomSN(6);
    }

    /**
     * 获取随机8位序列号
     * 
     * @return
     */
    public static String getRandomSN8() {
        return getRandomSN(8);
    }

    /**
     * 获取随机N位序列号
     * 
     * @param length
     */
    public static String getRandomSN(int length) {
        Long nanoTime = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        sbf.append(String.valueOf(Math.abs(nanoTime.longValue())));
        sbf = sbf.reverse();
        // max value
        length = length > sbf.length() ? sbf.length() : length;
        return sbf.substring(0, length < 0 ? 0 : length);
    }

    /**
     * Get UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取默认值
     * 
     * @param pattern
     * @param obj
     */
    public static String getDefaultValue(String pattern, Object obj) {
        String _str = String.valueOf(obj);
        if (obj == null || "".equals(_str)) {
            return pattern;
        }
        return String.valueOf(obj);
    }

    /**
     * 获取默认数字值
     * 
     * @param pattern
     * @param obj
     */
    public static String getDefNumericValue(String pattern, Object obj) {
        String str = getDefaultValue(pattern, obj);
        if (!isNumeric(str)) {
            return pattern;
        }
        return str;
    }

    /**
     * 格式化异常数字
     * 
     * @param numeric
     *            (NaN、123E、123,21...)
     */
    public static String formatExcepNum(Object numeric) {
        String _numeric = String.valueOf(numeric);
        // System.out.println("numeric=:" + numeric);
        try {
            // 去掉特殊字符
            _numeric = _numeric.replaceAll(",", "");
            if ("NaN".equals(_numeric) || "+NaN".equals(_numeric) || "-NaN".equals(_numeric)) {
                _numeric = "0.0";
            } else if ("?".equals(_numeric) || "-0.0".equals(_numeric) || "+0.0".equals(_numeric)) {
                _numeric = "0.0";
            } else if ("null".equalsIgnoreCase(_numeric) || "".equals(_numeric) || "undefined".equalsIgnoreCase(_numeric)) {
                _numeric = "0.0";
            } else if ("Infinity".equals(_numeric) || "+Infinity".equals(_numeric) || "-Infinity".equals(_numeric)) {
                _numeric = "0.0";
            }
            // 格式化数字
            _numeric = new DecimalFormat("0.0###########").format(Double.parseDouble(_numeric));
        } catch (Exception ex) {
            _numeric = null;
        }
        return _numeric;
    }

    /**
     * 验证是否为整数
     * 
     * @param str
     */
    public static boolean isInt(String str) {
        boolean flag = true;
        try {
            Integer.parseInt(str);
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证是否为长整数
     * 
     * @param str
     */
    public static boolean isLong(String str) {
        boolean flag = true;
        try {
            Long.parseLong(str);
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证是否为浮点型
     * 
     * @param str
     */
    public static boolean isFloat(String str) {
        boolean flag = true;
        try {
            Float.parseFloat(str);
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证是否为双精度型
     * 
     * @param str
     */
    public static boolean isDouble(String str) {
        boolean flag = true;
        try {
            Double.parseDouble(str);
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    /**
     * Get os datetime
     * 
     * @return 20090818121212
     */
    public static String getOsDateTimeSN() {
        //        
        return FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * Get os datetime
     * 
     * @return 20090818121212123
     */
    public static String getOsTimeMillisSN() {
        //        
        return FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(new Date());
    }

    /**
     * Get os datetime
     * 
     * @return 20090818121212
     */
    public static String getTimeSN() {
        //        
        return FastDateFormat.getInstance("yyMMddHHmmss").format(new Date());
    }

    /**
     * Get Year SN
     * 
     * @return 2009
     */
    public static String getYearSN() {
        //        
        return FastDateFormat.getInstance("yyyy").format(new Date());
    }

    /**
     * Get YM SN
     * 
     * @return 200901
     */
    public static String getYMSN() {
        //        
        return FastDateFormat.getInstance("yyyyMM").format(new Date());
    }

    /**
     * Get YMD SN
     * 
     * @return 20090101
     */
    public static String getYMDSN() {
        //      
        return FastDateFormat.getInstance("yyyyMMdd").format(new Date());
    }

    /**
     * Get YMDHM SN
     * 
     * @return 200901010101
     */
    public static String getYmdHmSN() {
        //      
        return FastDateFormat.getInstance("yyyyMMddHHmm").format(new Date());
    }

    /**
     * Object[](BigDecimal) to Long[]
     * 
     * @param objs
     * @return
     */
    public static long[] toBds2Longs(Object[] objs) {
        long[] _ls = null;
        if (objs != null && objs.length > 0) {
            BigDecimal _bd = null;
            _ls = new long[objs.length];
            for (int i = 0, len = objs.length; i < len; i++) {
                _bd = (BigDecimal) objs[i];
                if (_bd != null) {
                    _ls[i] = _bd.longValue();
                }
            }
        }
        return _ls;
    }

    /**
     * Get WorkDay number
     * 
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static int getWorkDayNum(String beginDateStr, String endDateStr) {
        try {
            FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd");
            return getWorkDayNum(df.parse(beginDateStr), df.parse(endDateStr));
        } catch (ParseException e) {
            //null
        }
        return 0;
    }

    /**
     * Get WorkDay number
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getWorkDayNum(Date beginDate, Date endDate) {
        // 日期差值,即包含周六日、以天为单位的工时，86400000=1000*60*60*24.
        long workDayVal = (getMillisOfDate(endDate) - getMillisOfDate(beginDate)) / 86400000 + 1;
        long remainder = (workDayVal % 7); // 工时的余数
        double weekendDay = 2 * Math.floor(workDayVal / 7); // 工时向下取整的除数
        // 起始日期的星期，星期取值有（1,2,3,4,5,6,0）
        int nextDay = getDayOfWeek(beginDate);
        for (long tempDay = remainder; tempDay >= 1; tempDay--) {
            if (tempDay == remainder) { // 第一天不用加1
                nextDay = nextDay + 0;
            } else if (tempDay != remainder) {
                nextDay = nextDay + 1;
            }
            if (nextDay == 7) { // 周日
                nextDay = 0;
            }
            if (nextDay == 0 || nextDay == 6) { // 周六日
                weekendDay = weekendDay + 1;
            }
        }
        int _num = convertsToInt(workDayVal - weekendDay);
        return (_num < 0 ? 0 : _num);
    }

    /**
     * Get day of week
     * 
     * @param date
     * @return
     */
    private static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get millis for Date
     * 
     * @param date
     * @return
     */
    private static long getMillisOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis();
    }

}
