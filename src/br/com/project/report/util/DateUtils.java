package br.com.project.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String getDateAtualReportName() {
        DateFormat df = new SimpleDateFormat("ddMMyyyy");
        return df.format(Calendar.getInstance().getTime());
    }

    public static String formatDateSql(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + df.format(date) + "'";
    }

    public static String formatDateSqlSimple(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
}
