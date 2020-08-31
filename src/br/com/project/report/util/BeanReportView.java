package br.com.project.report.util;

import br.com.project.util.all.BeanViewAbstract;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public abstract class BeanReportView extends BeanViewAbstract {
    private static final long serialVersionUID = 1L;

    protected StreamedContent arquivoReport;
    protected int tipoRelatiorio;
    protected List<?> listDataBeanCollectionReport;
    protected HashMap<Object, Object> parametrosRelatorios;
    protected String nomeRelatorioJasper = "default";
    protected String nomeRelatorioSaida = "default";

    @Autowired
    private ReportUtil reportUtil;

    public BeanReportView() {
        this.parametrosRelatorios = new HashMap<Object, Object>();
        this.listDataBeanCollectionReport = new ArrayList<>();
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public StreamedContent getArquivoReport() throws Exception {
        return getReportUtil().geraRelatorio(
                getListDataBeanCollectionReport(),
                getParametrosRelatorios(),
                getNomeRelatorioJasper(),
                getNomeRelatorioSaida(),
                getTipoRelatiorio()
        );
    }

    public void setArquivoReport(StreamedContent arquivoReport) {
        this.arquivoReport = arquivoReport;
    }

    public int getTipoRelatiorio() {
        return tipoRelatiorio;
    }

    public void setTipoRelatiorio(int tipoRelatiorio) {
        this.tipoRelatiorio = tipoRelatiorio;
    }

    public List<?> getListDataBeanCollectionReport() {
        return listDataBeanCollectionReport;
    }

    public void setListDataBeanCollectionReport(List<?> listDataBeanCollectionReport) {
        this.listDataBeanCollectionReport = listDataBeanCollectionReport;
    }

    public HashMap<Object, Object> getParametrosRelatorios() {
        return parametrosRelatorios;
    }

    public void setParametrosRelatorios(HashMap<Object, Object> parametrosRelatorios) {
        this.parametrosRelatorios = parametrosRelatorios;
    }

    public String getNomeRelatorioJasper() {
        return nomeRelatorioJasper;
    }

    public void setNomeRelatorioJasper(String nomeRelatorioJasper) {

        if (nomeRelatorioJasper == null || nomeRelatorioJasper.isEmpty()) {
            nomeRelatorioJasper = "default";
        }

        this.nomeRelatorioJasper = nomeRelatorioJasper;
    }

    public String getNomeRelatorioSaida() {
        return nomeRelatorioSaida;
    }

    public void setNomeRelatorioSaida(String nomeRelatorioSaida) {

        if (nomeRelatorioSaida == null || nomeRelatorioSaida.isEmpty()) {
            nomeRelatorioSaida = "default";
        }

        this.nomeRelatorioSaida = nomeRelatorioSaida;
    }
}
