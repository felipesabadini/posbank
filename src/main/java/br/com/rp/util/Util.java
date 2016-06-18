package br.com.rp.util;

import java.sql.Timestamp;
import java.util.Date;

public class Util {
	
	public static Date getDataAtual(){
		return new Date(System.currentTimeMillis());
	}
	
	public static Timestamp getDataHoraAtual(){
		return new Timestamp(System.currentTimeMillis());
	}
}