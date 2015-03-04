package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Component;

@Component
public class RelatorioGenerico {

	public  static void imprimir(ServletContext ctx,Map parameters,String caminhoRelatorio,Object dataSource,String nomePDF){
		try {
			File f = new File(ctx.getRealPath("/imagens/topo.jpg"));
			BufferedImage logo = ImageIO.read(f);			
			parameters.put("logo", logo);
//			
//			File fsgp = new File(ctx.getRealPath("/imagens/logosgp.jpg"));
//			BufferedImage logosgp = ImageIO.read(fsgp);
//			parameters.put("logosgp", logosgp);	
//
//			File festado = new File(ctx.getRealPath("/imagens/logo_estado.jpg"));
//			BufferedImage logoEstado = ImageIO.read(festado);
//			parameters.put("logoEstado", logoEstado);
//			
//			File fportal = new File(ctx.getRealPath("/imagens/logoPortal.jpg"));
//			BufferedImage logoPortal = ImageIO.read(fportal);
//			parameters.put("logoPortal", logoPortal);
			
//			parameters.put("SUBREPORT_DIR",ctx.getRealPath("/relatorios/")+File.separator);
			
			String path = ctx.getRealPath(caminhoRelatorio);
			HttpServletResponse response = (HttpServletResponse) FacesContext
			.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("content-disposition",
					"attachment;filename="+nomePDF);
			
			byte[]bytes = null;
			if(dataSource ==null){
				bytes = JasperRunManager.runReportToPdf(path, parameters,
						(Connection)dataSource);
			}
			else{
				bytes = JasperRunManager.runReportToPdf(path, parameters,
						(JRBeanCollectionDataSource)dataSource);
			}
			response.setContentLength(bytes.length);
			ServletOutputStream servletStream = response.getOutputStream();
			servletStream.write(bytes, 0, bytes.length);
			servletStream.flush();
			servletStream.close();
			FacesContext.getCurrentInstance().responseComplete();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	


}
