package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("globalSession")
public class PainelController {	
	//BarChart
	private Map<String, Integer[]> data = new LinkedHashMap<String, Integer[]>();
	private ArrayList<String> colors = new ArrayList<String>();
	private ArrayList<String> names = new ArrayList<String>();
	private Integer[] data2012 = new Integer[] { 2510, 521, 951 };
	private Integer[] data2013 = new Integer[] { 1936, 1238, 836 };
	private Integer[] data2014 = new Integer[] { 828, 921, 323 };
	
	//LineChart
	private ArrayList<String> nameslc = new ArrayList<String>();
	private ArrayList<String> colorslc = new ArrayList<String>();
	private Map<String, Object> datalc = new LinkedHashMap<String, Object>();
	private Integer[] chart1 = new Integer[]{2100, 4000, 5200};
	private Integer[] chart2 = new Integer[]{2500, 6000, 2800};
	private Integer[] chart3 = new Integer[]{3500, 6500, 4520};
	private Integer[] chart4 = new Integer[]{3720, 8200, 3200};
	private Integer[] chart5 = new Integer[]{3000, 12000, 3800};
	
	//Chart labels
	private String tituloChart = "Quantidade de Jovens Encaminhados";
	private String subTituloChart = "Periodo - 01/01/2012 à 31/12/2014";
	
	public PainelController(){
		generateDataBarChart();	
		generateDataLineChart();
	}
	
	public String listar() {
		return "painelSecretarioCoordenador";
	}
	
	private void generateDataBarChart() {
		data.put("2012", data2012);
		data.put("2013", data2013);
		data.put("2014", data2014);

		names.add("Bolsista");
		names.add("Estagiario");
		names.add("Aprendiz");

		colors.add("#DAA520");
		colors.add("#C0C0C0");
		colors.add("#B87333");
	}
	
	private void generateDataLineChart() {
        datalc.put("Ano 2004", chart1);
        datalc.put("Ano 2005", chart2);
        datalc.put("Ano 2006", chart3);
        datalc.put("Ano 2007", chart4);
        datalc.put("Ano 2008", chart5);
        
        nameslc.add("Aprendiz");
        nameslc.add("Estagiário");
        nameslc.add("Bolsista");

        colorslc.add("#5db2c2");
        colorslc.add("#b0343c");
        colorslc.add("#DAA520");
	}
		
	public void filtrar(){
		
	}
		
	public Map<String, Integer[]> getData() {
		return data;
	}

	public void setData(Map<String, Integer[]> data) {
		this.data = data;
	}

	public ArrayList<String> getColors() {
		return colors;
	}

	public void setColors(ArrayList<String> colors) {
		this.colors = colors;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

	public ArrayList<String> getNameslc() {
		return nameslc;
	}

	public void setNameslc(ArrayList<String> nameslc) {
		this.nameslc = nameslc;
	}

	public ArrayList<String> getColorslc() {
		return colorslc;
	}

	public void setColorslc(ArrayList<String> colorslc) {
		this.colorslc = colorslc;
	}

	public Map<String, Object> getDatalc() {
		return datalc;
	}

	public void setDatalc(Map<String, Object> datalc) {
		this.datalc = datalc;
	}

	public String getTituloChart() {
		return tituloChart;
	}

	public void setTituloChart(String tituloChart) {
		this.tituloChart = tituloChart;
	}

	public String getSubTituloChart() {
		return subTituloChart;
	}

	public void setSubTituloChart(String subTituloChart) {
		this.subTituloChart = subTituloChart;
	}	

		
}

