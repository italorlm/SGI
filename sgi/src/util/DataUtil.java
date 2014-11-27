package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

	public static String dataHoraAtual(){
		Date data = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//
		//Cria uma string que recebe a data formatada
		String dataFormatada  = formataData.format(data);
		return (dataFormatada);
	}
}
