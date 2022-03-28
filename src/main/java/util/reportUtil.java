package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({"rawtypes","unchecked"})
public class reportUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
public byte [] gerarRelatorioPDF (List listaDados, String nomeRelatorio, HashMap<String, Object> params, ServletContext context) throws Exception {
		
		/*CRIANDO A LISTA DE DADOS VINDA DA CONSULTA SQL*/
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = context.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, params, dataSource);
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
		
		
	}
	
	public byte [] gerarRelatorioPDF (List listaDados, String nomeRelatorio, ServletContext context) throws Exception {
		
		/*CRIANDO A LISTA DE DADOS VINDA DA CONSULTA SQL*/
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = context.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashedMap(), dataSource);
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
		
		
	}

}
