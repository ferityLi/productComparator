package com.zhongan.product.cfg.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类
 */
public class DateUtils {

    public static final String DATE_TIME_PATTERN            = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_PATTERN_2          = "yyyyMMddHHmmss";
    public static final String MINUTE_PATTERN               = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN                 = "yyyy-MM-dd HH";
    public static final String DATE_PATTERN                 = "yyyy-MM-dd";
    public static final String MONTH_PATTERN                = "yyyy-MM";
    public static final String YEAR_MONTH_PATTERN           = "yyMM";
    public static final String YEAR_PATTERN                 = "yyyy";
    public static final String MINUTE_ONLY_PATTERN          = "mm";
    public static final String HOUR_ONLY_PATTERN            = "HH";
    public static final String YEAR_MONTH_DAY_PATTERN       = "yyyyMMdd";
    public static final String SHORT_YEAR_MONTH_DAY_PATTERN = "yyMMdd";

    /**
     * 功能: 获取指定格式的当前系统时间字符串,不传参，默认格式yyyy-MM-dd HH:mm:ss
     */
    public static String getSysStrDate(String outFormat) {
        String dateStr;
        SimpleDateFormat sdf;
        if (StringUtils.isBlank(outFormat)) {
            sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        } else {
            sdf = new SimpleDateFormat(outFormat);
        }
        dateStr = sdf.format(new Date());
        return dateStr;
    }

    /**
     * 功能: 获取指定格式的当前系统时间,不传参，默认格式yyyy-MM-dd HH:mm:ss
     */
    public static Date getSysDate(String outFormat) {
        Date date;
        SimpleDateFormat sdf;
        if (StringUtils.isBlank(outFormat)) {
            sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        } else {
            sdf = new SimpleDateFormat(outFormat);
        }
        try {
            date = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * 功能: 将日期转换为指定格式的字符串返回
     *
     * @param inDate 要转换的日期
     * @param outFormat 目标日期字符串格式
     * @return
     */
    public static String dateFormatStr(Date inDate, String outFormat) {
        if (inDate == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(outFormat);
        return sdf.format(inDate);
    }

    /**
     * 功能: 将字符串转换为指定格式的日期返回
     *
     * @param dateStr 要转换的字符串
     * @param outFormat 目标日期格式
     * @return
     */
    public static Date strFormatDate(String dateStr, String outFormat) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(outFormat);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 功能: 将字符串转换为[yyyy-MM-dd HH:mm:ss]的日期返回
     *
     * @param dateStr 要转换的字符串
     */
    public static Date strFormatDefaultDate(String dateStr) {
        return strFormatDate(dateStr, DATE_TIME_PATTERN);
    }

    /**
     * 功能: 将日期字符串转换为指定格式的字符串返回
     *
     * @param dateStr 要转换的字符串
     * @param inFormat 与dateStr字符串相同格式的原日期格式
     * @param outFormat 目标日期格式
     */
    public static String dateStrToDateStr(String dateStr, String inFormat, String outFormat) {
        if (StringUtils.isBlank(dateStr)) {
            return "";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(inFormat);
        Date date = null;
        try {
            date = sdf1.parse(dateStr);
        } catch (ParseException e) {
            return "";
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat(outFormat);
        return sdf2.format(date);
    }

    /**
     * 获取两个日期（不含日时分秒）相差的月数，不包含当月
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     */
    public static int monthDiff(Date startDate, Date endDate) {
        Date dateStart = dateFormatDate(startDate, MONTH_PATTERN);
        Date dateEnd = dateFormatDate(endDate, MONTH_PATTERN);
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24 / 12);
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     */
    public static int dayDiff(Date startDate, Date endDate) {
        Date dateStart = dateFormatDate(startDate, DATE_PATTERN);
        Date dateEnd = dateFormatDate(endDate, DATE_PATTERN);
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 获取两个日期相差的秒数
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     */
    public static int secondsDiff(Date startDate, Date endDate) {
        Date dateStart = dateFormatDate(startDate, DATE_TIME_PATTERN);
        Date dateEnd = dateFormatDate(endDate, DATE_TIME_PATTERN);
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000);
    }

    /**
     * 功能: 将字日期换为指定格式的日期返回
     *
     * @param inDate 要转换的日期
     * @param outFormat 目标日期格式
     */
    public static Date dateFormatDate(Date inDate, String outFormat) {
        if (inDate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(outFormat);
        Date date = null;
        try {
            date = sdf.parse(sdf.format(inDate));
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * 时间加减秒数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param seconds 加减的秒数
     */
    public static Date dateAddSeconds(Date startDate, int seconds) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);
        return c.getTime();
    }

    /**
     * 时间加减分钟
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param minutes 加减的分钟
     */
    public static Date dateAddMinutes(Date startDate, int minutes) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
        return c.getTime();
    }

    /**
     * 时间加减小时
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param hours 加减的小时
     */
    public static Date dateAddHours(Date startDate, int hours) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
        return c.getTime();
    }

    /**
     * 时间加减天数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param days 加减的天数
     */
    public static Date dateAddDays(Date startDate, int days) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
        return c.getTime();
    }

    /**
     * 时间加减月数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param months 加减的月数
     */
    public static Date dateAddMonths(Date startDate, int months) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
        return c.getTime();
    }

    /**
     * 时间加减年数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param years 加减的年数
     */
    public static Date dateAddYears(Date startDate, int years) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + years);
        return c.getTime();
    }

    /**
     * 时间比较（如果myDate>compareDate返回1，<返回-1，相等返回0）
     *
     * @param date1 时间
     * @param date2 要比较的时间
     */
    public static int dateCompare(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        calendar.setTime(date1);
        compareCal.setTime(date2);
        return calendar.compareTo(compareCal);
    }

    /**
     * 获取两个时间中最小的一个时间
     */
    public static Date dateMin(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        return dateCompare(date1, date2) == -1 ? date1 : date2;
    }

    /**
     * 获取两个时间中最大的一个时间
     */
    public static Date dateMax(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        return dateCompare(date1, date2) == 1 ? date1 : date2;
    }

}
