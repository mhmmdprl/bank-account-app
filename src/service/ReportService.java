package service;


import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import entity.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

public class ReportService {
    public void writeAsPdf(List<Account> accountList) {
        List<Map<String, ?>> list = new ArrayList<>();

        accountList.forEach(account -> {
            Map<String, Object> row = new HashMap<>();
            row.put("balance", account.getBalance());
            row.put("accountName", account.getAccountName());
            row.put("accountNo", account.getAccountNo());

            list.add(row);
        });
        StyleBuilder style = stl.style(stl.pen1Point())
            .setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);

        MultiPageListBuilder multiPageList = cmp.multiPageList();

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle);
        StyleBuilder titleStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);

        try {
            JasperReportBuilder report = DynamicReports.report();
            DynamicReports.report()
                .setColumnTitleStyle(style).columns(
                    col.columnRowNumberColumn("No").setHorizontalAlignment(HorizontalAlignment.CENTER),
                    col.column("Bakiye", "balance", Double.class).setHorizontalAlignment(HorizontalAlignment.CENTER),
                    col.column("Hesap Adi", "accountName", String.class).setHorizontalAlignment(HorizontalAlignment.CENTER),
                    col.column("Hesap No", "accountNo", Integer.class).setHorizontalAlignment(HorizontalAlignment.CENTER))
                .title(cmp.text("Hesaplar\n").setStyle(titleStyle))
                .pageFooter(cmp.pageXofY())
                .setDataSource(list)
                .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


