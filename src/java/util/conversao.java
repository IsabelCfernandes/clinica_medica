package util;

/*
Uma vez que essas duas classes fazem uso de atributos para representar o dia, o mês e o ano 
de uma determi- nada data, teremos de desenvolver ainda uma classe com métodos que efetuem as 
devidas conversões. Isso é necessário para que seja possível gravar datas no banco de dados.
Vamos criar essa classe dentro de um novo pacote denominado util, que também poderá servir 
para abrigar ou- tras classes que contenham métodos de uso genérico. O objetivo disso é ter 
uma biblioteca de funções, como ocorre em outros tipos de linguagem.
 */
//IMPORTAÇÕES DE BIBLIOTECAS
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class conversao {

    //METODO QUE RETORNA DATA | TRAZENDO ELA COMO STRING E CONVERTENDO PARA DATA CONFIGURADA
    public Date StringToDate(String strData) {
        //criação de uma variavel tipo SimpleDateFormat que armazena um novo objeto
        //SimpleDateFormat, com a configuração da entrada da data.
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        try {
            //cria uma variavel do tipo data, recebendo um novo objeto data
            //convertendo a string data recebida no parametro do metodo para tipo data
            Date dtData = new Date(formatoData.parse(strData).getTime());
            //retorna essa data já convertida e configurada
            return dtData;
            //clausula de erro retorna data como nula.
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }

    //METODO QUE RETORNA DATA INVERTIDA, TRAZENDO ELA COMO STRING PARA SER ACEITO NO BANCO
    public String DataInvertida(Date dtData) {
        //se dtData não for nula
        if (dtData != null) {
            //cria variaveis tipo int
            int intDia, intMes, intAno;
            //cria variaveis tipo String
            String strData;
            //cria varivael tipo Calendar, que recebe uma instância de Calendar
            Calendar calendario = Calendar.getInstance();
            //pega essa variavel e seta a data como o dtData já estabelecido no metodo acima.
            calendario.setTime(dtData);

            //pega dia, mês e ano da variavel calendario
            intDia = calendario.get(Calendar.DAY_OF_MONTH);
            intMes = calendario.get(Calendar.MONTH) + 1;
            intAno = calendario.get(Calendar.YEAR);

            strData = intAno + "/" + intMes + "/" + intDia;
            //retorna a variavel com a string acima
            return strData;
        } else {
            return "null";
        }
    }

    //METODO CONVERTE FORMATO DATA PARA STRING NA SAÍDA
    public String DateToString(Date dtData) {
        //cria String strData
        String strData;
        //cria variavel tipo SimpleDateFormat
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            //pega data e converte para string
            strData = formatoData.format(dtData);
            return strData;

        } catch (Exception erro) {
            erro.printStackTrace();
            return "null";
        }
    }

    public int DiaData(Date dtData) {

        if (dtData != null) {
            int intDia;
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dtData);
            intDia = calendario.get(Calendar.DAY_OF_MONTH);
            return intDia;
        } else {
            return 0;
        }
    }
    

    public int MesData(Date dtData) {
        if (dtData != null) {
            int intMes;
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dtData);
            intMes = calendario.get(Calendar.MONTH) +1;            
            return intMes;           
        } else {
            return 0;
        }
    }
    
    
     public int AnoData(Date dtData) {
        if (dtData != null) {
            int intAno;
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dtData);
            intAno = calendario.get(Calendar.YEAR);            
            return intAno;           
        } else {
            return 0;
        }
    }
}