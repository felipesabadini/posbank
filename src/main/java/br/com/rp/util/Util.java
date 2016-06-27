package br.com.rp.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
	
	public static Date getDataAtual(){
		return new Date(System.currentTimeMillis());
	}
	
	public static Timestamp getDataHoraAtual(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Date addData(int quantidade, Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DATE, quantidade);
		
		return calendar.getTime();
	}
	
	public static Date setTime(Integer hour, Integer minute){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
	
	public static String formataData(Date date, String formato){
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(date);
	}
	
	public static Boolean isDateBetweenDate1andDate2(Date dataVerificar, Date date1, Date date2) {
		if (dataVerificar.after(date1) && dataVerificar.before(date2)){
			return true;
		}else{
			return false;
		}
	}
}