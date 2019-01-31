package com.ql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    /** 锁对象 */
    private static final Object lockObj = new Object();
    /** 存放不同的日期模板格式的simpleDateFormat的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> simpleDateFormatMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    /**
     * 返回一个ThreadLocal的simpleDateFormat,每个线程只会new一次simpleDateFormat
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSimpleDateFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> threadLocal = simpleDateFormatMap.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (threadLocal == null) {
            synchronized (lockObj) {
                threadLocal = simpleDateFormatMap.get(pattern);
                if (threadLocal == null) {
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    threadLocal = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    simpleDateFormatMap.put(pattern, threadLocal);
                }
            }
        }
        return threadLocal.get();
    }
    /**
     * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getSimpleDateFormat(pattern).format(date);
    }
    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSimpleDateFormat(pattern).parse(dateStr);
    }
}