package cripto;



public class Encriptar {

	 public static String encriptar(String chave,String senha){
		 Long lng1,lng2;
		 String strRetorno = "";
		 String a = "000000";
		 for(int i=1;i<=senha.length();i++){
			char c = substr(senha,i-1,1).toCharArray()[0];
			
			lng1 = (long)c;
			
			 if((i%chave.length())==0){
			 long x = (long)substr(chave,chave.length()-1,1).toCharArray()[0];
			 long y = (long)substr(chave,(chave.length()-chave.length()),1).toCharArray()[0];
			 lng2=  x+y;
			 }else{
				
				 long x = (long)substr( chave,(i-1)%(chave.length()) ,1).toCharArray()[0];
			     long y = (long)substr(chave,(chave.length()) - (i%(chave.length())),1).toCharArray()[0];
			     lng2=  x+y;
			 }
			
			 strRetorno = strRetorno + a + String.format("%X", lng1 ^ lng2);
		 }
		 return strRetorno;
	 }
	 
	 public static String substr (final String str, int pos, int len) {  
	        return str.substring (pos,Math.min(pos+len, str.length()));  
	    }  
	 

}
