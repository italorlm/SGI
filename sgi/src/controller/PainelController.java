package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("globalSession")
public class PainelController {
	
	
	
	
	
	private Map<String, Integer[]> data = new LinkedHashMap<String, Integer[]>();
	private ArrayList<String> colors = new ArrayList<String>();
	private ArrayList<String> names = new ArrayList<String>();
	private Integer[] data2012 = new Integer[] { 2510, 521, 951 };
	private Integer[] data2013 = new Integer[] { 1936, 1238, 836 };
	private Integer[] data2014 = new Integer[] { 828, 921, 323 };
	
	//Atributos do componente BarChart
	private String tituloBarChart = "Quantidade de Jovens Encaminhados";
	private String subTituloBarChart = "Periodo - 01/01/2012 à 31/12/2014";
	
	public PainelController(){
		generateData();		
	}
	
	public String listar() {
		return "painelSecretarioCoordenador";
	}
	
	private void generateData() {
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

	public Integer[] getData2012() {
		return data2012;
	}

	public void setData2012(Integer[] data2012) {
		this.data2012 = data2012;
	}

	public Integer[] getData2013() {
		return data2013;
	}

	public void setData2013(Integer[] data2013) {
		this.data2013 = data2013;
	}

	public Integer[] getData2014() {
		return data2014;
	}

	public void setData2014(Integer[] data2014) {
		this.data2014 = data2014;
	}

	public String getTituloBarChart() {
		return tituloBarChart;
	}

	public void setTituloBarChart(String tituloBarChart) {
		this.tituloBarChart = tituloBarChart;
	}

	public String getSubTituloBarChart() {
		return subTituloBarChart;
	}

	public void setSubTituloBarChart(String subTituloBarChart) {
		this.subTituloBarChart = subTituloBarChart;
	}	
	
	
}

