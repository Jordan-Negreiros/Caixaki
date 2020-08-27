package test.junit;

import br.com.project.report.util.DateUtils;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TesteData {

    @Test
    public void testData() {
        try {
            assertEquals("27082020", DateUtils.getDateAtualReportName());
            assertEquals("'2020-08-27'", DateUtils.formatDateSql(Calendar.getInstance().getTime()));
            assertEquals("2020-08-27", DateUtils.formatDateSqlSimple(Calendar.getInstance().getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
