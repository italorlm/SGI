package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("globalSession")
public class PainelController {
	// BarChart
	private Map<String, Integer[]> data = new LinkedHashMap<String, Integer[]>();
	private ArrayList<String> colors = new ArrayList<String>();
	private ArrayList<String> names = new ArrayList<String>();
	private Integer[] data2012 = new Integer[] { 2510, 521, 951 };
	private Integer[] data2013 = new Integer[] { 1936, 1238, 836 };
	private Integer[] data2014 = new Integer[] { 828, 921, 323 };

	// LineChart
	private ArrayList<String> nameslc = new ArrayList<String>();
	private ArrayList<String> colorslc = new ArrayList<String>();
	private Map<String, Object> datalc = new LinkedHashMap<String, Object>();
	private Integer[] chart1 = new Integer[] { 2100, 4000, 5200 };
	private Integer[] chart2 = new Integer[] { 2500, 6000, 2800 };
	private Integer[] chart3 = new Integer[] { 3500, 6500, 4520 };
	private Integer[] chart4 = new Integer[] { 3720, 8200, 3200 };
	private Integer[] chart5 = new Integer[] { 3000, 12000, 3800 };

	// ColumnChart
	private ArrayList<Integer> datacc = new ArrayList<Integer>();
	private Map<String, Object> mesMap = new LinkedHashMap<String, Object>();
	private ArrayList<String> namescc = new ArrayList<String>();
	private ArrayList<String> colorscc = new ArrayList<String>();
	Random rnd = new Random(new Date().getTime());

	// StackedColumChart
	private Map<String, Integer[]> datascc = new LinkedHashMap<String, Integer[]>();
	private ArrayList<String> namesscc = new ArrayList<String>();
	private ArrayList<String> colorsscc = new ArrayList<String>();
	private Integer[] chart1scc = new Integer[] { 17, 57, 20 };
	private Integer[] chart2scc = new Integer[] { 26, 54, 14 };
	private Integer[] chart3scc = new Integer[] { 38, 48, 8 };

	// FlexAPIChart
	private String currentCountry;
	private Integer[] currentMedals;
	private String currentMedalType;
	private Integer currentMedalsCount;
	// private ArrayList<Medal> currentCountryMedals = new ArrayList<Medal>();
	// private ArrayList<Medal> currentMedalsByType = new ArrayList<Medal>();
	private Map<String, Integer> commonData = new LinkedHashMap<String, Integer>();
	private Map<String, Integer[]> selectedData = new LinkedHashMap<String, Integer[]>();
	private Map<String, Integer[]> selectedDataLinha = new LinkedHashMap<String, Integer[]>();
	private Map<String, Integer[]> datafa = new LinkedHashMap<String, Integer[]>();
	private ArrayList<String> colorsfa = new ArrayList<String>();
	private ArrayList<String> totalColors = new ArrayList<String>();
	private ArrayList<String> namesfa = new ArrayList<String>();
	private ArrayList<String> countries = new ArrayList<String>();
	private Integer[] data1 = new Integer[] { 51, 21, 28 };
	private Integer[] data2 = new Integer[] { 36, 38, 36 };
	private Integer[] data3 = new Integer[] { 23, 21, 28 };
	private Integer[] dataTotal = new Integer[] { 0, 0, 0 };

	// Chart labels
	private String tituloChart = "Quantidade de Jovens Encaminhados";
	private String subTituloChart = "Periodo - 01/01/2012 à 31/12/2014";

	public PainelController() {
		super();
		generateDataBarChart();
		generateDataLineChart();
		generateDataColumnChart();
		generateDataStackedColumnChart();
		generateDataFlexAPIChart();
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

	private void generateDataColumnChart() {
		mesMap.put("Janeiro", getDatacc());
		mesMap.put("Fevereiro", getDatacc());
		mesMap.put("Março", getDatacc());

		namescc.add("Aprendiz");
		namescc.add("Estagiário");
		namescc.add("Bolsista");

		colorscc.add("#5db2c2");
		colorscc.add("#b0343c");
		colorscc.add("#dd9f2c");
	}

	private void generateDataStackedColumnChart() {
		datascc.put("2006", chart1scc);
		datascc.put("2007", chart2scc);
		datascc.put("2008", chart3scc);

		colorsscc.add("#b0343c");
		colorsscc.add("#dd9f2c");
		colorsscc.add("#5db2c2");

		namesscc.add("Aprendiz");
		namesscc.add("Estagiário");
		namesscc.add("Bolsista");
	}

	private void generateDataFlexAPIChart() {
		datafa.put("2014", data3);
		datafa.put("2013", data2);
		datafa.put("2012", data1);

		for (int i = 0; i < data3.length; i++) {
			dataTotal[0] += data3[i];
			dataTotal[1] += data2[i];
			dataTotal[2] += data1[i];
		}

		commonData.put("2014", dataTotal[0]);
		commonData.put("2013", dataTotal[1]);
		commonData.put("2012", dataTotal[2]);

		countries.add("2014");
		countries.add("2013");
		countries.add("2012");

		namesfa.add("Bolsista");
		namesfa.add("Estagiario");
		namesfa.add("Aprendiz");

		colorsfa.add("#DAA520");
		colorsfa.add("#C0C0C0");
		colorsfa.add("#B87333");

		totalColors.add("51476B");

	}

	public void filtrar() {

	}

	// INICIO BAR CHART

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

	// INICIO LINE CHART

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

	// INICIO TITULOS DOS GRAFICOS

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

	// INICIO COLUMN CHART

	public Map<String, Object> getMesMap() {
		return mesMap;
	}

	public void setMesMap(Map<String, Object> mesMap) {
		this.mesMap = mesMap;
	}

	public ArrayList<String> getNamescc() {
		return namescc;
	}

	public void setNamescc(ArrayList<String> namescc) {
		this.namescc = namescc;
	}

	public ArrayList<String> getColorscc() {
		return colorscc;
	}

	public void setColorscc(ArrayList<String> colorscc) {
		this.colorscc = colorscc;
	}

	public ArrayList<Integer> getDatacc() {
		datacc = new ArrayList<Integer>();
		int dataSize = 3;
		for (int i = 0; i < dataSize; i++) {
			datacc.add(rnd.nextInt(50));
		}
		return datacc;
	}

	public void setDatacc(ArrayList<Integer> datacc) {
		this.datacc = datacc;
	}

	// INICIO STACKED COLUMN CHART

	public Map<String, Integer[]> getDatascc() {
		return datascc;
	}

	public void setDatascc(Map<String, Integer[]> datascc) {
		this.datascc = datascc;
	}

	public ArrayList<String> getNamesscc() {
		return namesscc;
	}

	public void setNamesscc(ArrayList<String> namesscc) {
		this.namesscc = namesscc;
	}

	public ArrayList<String> getColorsscc() {
		return colorsscc;
	}

	public void setColorsscc(ArrayList<String> colorsscc) {
		this.colorsscc = colorsscc;
	}
	
	//INICIO FLEX API CHART
	
	public Integer[] getMedalCountsByCountry(String currentCountry) {
		if ("2012".equals(currentCountry.toLowerCase())) {
			return data1;
		} else if ("2013".equals(currentCountry.toLowerCase())) {
			return data2;
		} else if ("2014".equals(currentCountry.toLowerCase())) {
			return data3;
		}

		return null;
	}

	public void showDetails() {
		currentMedals = getMedalCountsByCountry(currentCountry);
		selectedData.clear();
		selectedData.put(currentCountry, currentMedals);
	}
	
//	public void showDetailsLinhaSelected() {
//		Integer[] currentStatus = getMedalCountsByCountry(currentCountry);
//		selectedDataLinha.clear();
//		selectedData.put(currentMedals, currentStatus);
//	}

	public String getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}

	public String getCurrentMedalType() {
		return currentMedalType;
	}

	public void setCurrentMedalType(String currentMedalType) {
		this.currentMedalType = currentMedalType;
	}

	public Integer getCurrentMedalsCount() {
		return currentMedalsCount;
	}

	public void setCurrentMedalsCount(Integer currentMedalsCount) {
		this.currentMedalsCount = currentMedalsCount;
	}

	public Map<String, Integer> getCommonData() {
		return commonData;
	}

	public void setCommonData(Map<String, Integer> commonData) {
		this.commonData = commonData;
	}

	public Map<String, Integer[]> getSelectedData() {
		return selectedData;
	}

	public void setSelectedData(Map<String, Integer[]> selectedData) {
		this.selectedData = selectedData;
	}

	public Map<String, Integer[]> getDatafa() {
		return datafa;
	}

	public void setDatafa(Map<String, Integer[]> datafa) {
		this.datafa = datafa;
	}

	public ArrayList<String> getColorsfa() {
		return colorsfa;
	}

	public void setColorsfa(ArrayList<String> colorsfa) {
		this.colorsfa = colorsfa;
	}

	public ArrayList<String> getTotalColors() {
		return totalColors;
	}

	public void setTotalColors(ArrayList<String> totalColors) {
		this.totalColors = totalColors;
	}

	public ArrayList<String> getNamesfa() {
		return namesfa;
	}

	public void setNamesfa(ArrayList<String> namesfa) {
		this.namesfa = namesfa;
	}

	public ArrayList<String> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<String> countries) {
		this.countries = countries;
	}

	public Integer[] getDataTotal() {
		return dataTotal;
	}

	public void setDataTotal(Integer[] dataTotal) {
		this.dataTotal = dataTotal;
	}

	public Integer[] getCurrentMedals() {
		return currentMedals;
	}

	public void setCurrentMedals(Integer[] currentMedals) {
		this.currentMedals = currentMedals;
	}

	public Map<String, Integer[]> getSelectedDataLinha() {
		return selectedDataLinha;
	}

	public void setSelectedDataLinha(Map<String, Integer[]> selectedDataLinha) {
		this.selectedDataLinha = selectedDataLinha;
	}
	
}
