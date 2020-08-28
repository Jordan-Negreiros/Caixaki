package br.com.project.report.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked", "FieldCanBeLocal",
        "ConstantConditions", "DuplicateBranchesInSwitch", "EnhancedSwitchMigration"})
@Component
public class ReportUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PONTO = ".";
    private static final String UNDERLINE = "_";
    private static final String FOLDER_RELATORIOS = "/relatorios";
    private static final String SUB_REPORT_DIR = "SUBREPORT_DIR";
    private static final String EXTENSION_ODS = "ods";
    private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_HTML = "html";
    private static final String EXTENSION_PDF = "pdf";

    private static final int RELATORIO_PDF = 1;
    private static final int RELATORIO_EXCEL = 2;
    private static final int RELATORIO_HTML = 3;
    private static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;

    private String SEPARATOR = File.separator;
    private String caminhoArquivoRelatorio = null;
    private String extensaoArquivoExportado = "";
    private String caminhoSubReportDir = "";

    private JRExporter tipoArquivoExportado = null;
    private File arquivoGerado = null;
    private StreamedContent arquivoRetorno = null;


    public StreamedContent geraRelatorio(List<?> listDataBeanCollectionReport, HashMap parametrosRelatorios,
            String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRelatorio) throws Exception {

        /* Cria a lista de collectionDataSource de beans que carregam os dados para os relatórios */
        JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanCollectionReport);

        /* Fornece o caminho fisico até a pasta que contém os relatórios compilados .jasper */
        FacesContext context = FacesContext.getCurrentInstance();
        context.responseComplete();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();

        String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);

        /* EX: -> c:/aplicação/relatorios/rel_bairro.jasper */
        File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "jasper");

        if (caminhoRelatorio == null
                || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {

            caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
            SEPARATOR = "";
        }

        /* caminho para imagens */
        parametrosRelatorios.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);

        /* caminho completo até o relatório compilado indicado */
        String caminhoArquivoJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "jasper";

        /* Faz o carregamento do relatório indicado */
        JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);

        /* Seta parametros SUB_REPORT_DIR como caminho fisico para sub-reports*/
        caminhoSubReportDir = caminhoRelatorio + SEPARATOR;
        parametrosRelatorios.put(SUB_REPORT_DIR, caminhoSubReportDir);

        /* Carrega o arquivo compilado para a memória */
        JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorios, jrbcds);

        switch (tipoRelatorio) {
            case RELATORIO_PDF:
                tipoArquivoExportado = new JRPdfExporter();
                extensaoArquivoExportado = EXTENSION_PDF;
                break;

            case RELATORIO_EXCEL:
                tipoArquivoExportado = new JRXlsExporter();
                extensaoArquivoExportado = EXTENSION_XLS;
                break;

            case RELATORIO_PLANILHA_OPEN_OFFICE:
                tipoArquivoExportado = new JROdsExporter();
                extensaoArquivoExportado = EXTENSION_ODS;
                break;

            case RELATORIO_HTML:
                tipoArquivoExportado = new JRHtmlExporter();
                extensaoArquivoExportado = EXTENSION_HTML;
                break;

            default:
                tipoArquivoExportado = new JRPdfExporter();
                extensaoArquivoExportado = EXTENSION_PDF;
                break;
        }

        /* EX -> reportBairro28082020 */
        nomeRelatorioSaida += UNDERLINE + DateUtils.getDateAtualReportName();

        /* Caminho relatório exportado */
        caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + PONTO + extensaoArquivoExportado;

        /* Cria novo file exportado */
        arquivoGerado = new File(caminhoArquivoRelatorio);

        /* Prepara a impressão */
        tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);

        /* Nome do arquivo fisico a ser impresso/exportado */
        tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

        /* Executa a exportação */
        tipoArquivoExportado.exportReport();

        /* Remove o arquivo do servidor após ser feito o download pelo usuário */
        arquivoGerado.deleteOnExit();

        /* Cria o input stream para ser usado pelo PrimeFaces */
        InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);

        /* Faz o retorno para aplicação */
        arquivoRetorno = new DefaultStreamedContent(
                conteudoRelatorio,
                "application/" + extensaoArquivoExportado,
                nomeRelatorioSaida + PONTO + extensaoArquivoExportado);

        return arquivoRetorno;
    }

}
