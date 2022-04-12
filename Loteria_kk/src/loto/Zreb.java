package loto;
import java.text.SimpleDateFormat;
import java.util.*;

public class Zreb {

	private Date datum;
	private int [] poleCisel;
	
	public Zreb(int [] poleCisel){
		this.poleCisel = poleCisel;
		this.datum=new Date();
	}

	public int [] getPoleCisel(){
		return poleCisel;
	}

	public Date getDatum(){
		return datum;
	} 

	public String getInfo(){
		SimpleDateFormat helpDate = new SimpleDateFormat("dd.MM.yyyy");
		String strDate = helpDate.format(datum);
		StringBuilder finalString = new StringBuilder();
		finalString.append("Datum: " + strDate + " | Cisla: ");
		for(int cislo:poleCisel){
			finalString.append(cislo + " ");
		}
		return finalString.toString();
	}

	public static void main(String[]args){
		int [] poleCisel = {1, 14, 30, 65, 85, 22, 45};
		Zreb zreb1 = new Zreb(poleCisel);
		System.out.println(zreb1.getInfo());
	}

}
