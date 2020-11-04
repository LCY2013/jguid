package org.fufeng.project.lamda.module03;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * @ClassName: DateApi
 * @author: LuoChunYun
 * @Date: 2019/4/28 15:49
 * @Description: TODO
 */
public class DateApi {

    public static void main(String[] args) {
        //001 计算算法时间
        //statisticsMethodRunTime();

        //002 本地时间
        localDateTime();
    }

    private static void localDateTime() {
        //今天的日期
        final LocalDate now = LocalDate.now();
        //计算某一个特定的时间点
        final LocalDate of = LocalDate.of(1995, 2, 20);
        final LocalDate birthDayDate = LocalDate.of(1995, Month.FEBRUARY, 20);
        //输出下24一个生日
        out.println(birthDayDate.plus(Period.ofYears(24)));
        //计算程序员日
        final LocalDate programmer = LocalDate.of(2019, 1, 1).plusDays(255);
        out.println(programmer.format(DateTimeFormatter.ISO_DATE));

        //计算下一个的最后一天
        final LocalDate lastDayOfMonth = LocalDate.of(2019, 1, 31);
        out.println(lastDayOfMonth.plusMonths(1).format(DateTimeFormatter.ISO_DATE));

        //日期校准
        final LocalDate mondayLocalDate = LocalDate.of(2019, 5, 1)
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        out.println(mondayLocalDate.format(DateTimeFormatter.ISO_DATE));

        out.println("总共的时区："+ZoneId.getAvailableZoneIds().size());
    }

    private static void statisticsMethodRunTime() {
        final Instant start = Instant.now();
        methodOneRunTime();
        final Instant end = Instant.now();
        final Duration duration = Duration.between(start, end);
        out.println("方法一用时:"+duration.toMillis());

        final Instant start1 = Instant.now();
        methodTwoRunTime();
        final Instant end1 = Instant.now();
        final Duration duration1 = Duration.between(start1, end1);
        out.println("方法二用时:"+duration1.toMillis());

        //第一种比较
        final boolean negative = duration.multipliedBy(9).minus(duration1).isNegative();
        out.println("duration1 用时是否是 duration 的10倍: "+negative);
        //第二种比较
        out.println("duration1 用时是否是 duration 的10倍: "+ (duration.toNanos() * 9 < duration1.toNanos()));
    }

    private static void methodOneRunTime(){
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void methodTwoRunTime(){
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
