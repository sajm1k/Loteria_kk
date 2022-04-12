package loto;
import java.util.*;

public class Loteria {

	private String nazov;
	private float peniaze;
	private int cislaOd;
	private int cislaDo;
	private int pocet;
	List<Zreb> zreby = new ArrayList<>();
	private List<UserZreb> userZreb = new ArrayList<>();

	public Loteria(String nazov, float peniaze, int cislaOd, int cislaDo, int pocet){
		this.nazov = nazov;
		this.peniaze = peniaze;
		this.cislaOd = cislaOd;
		this.cislaDo = cislaDo;
		this.pocet = pocet;
	}

	public Loteria(String nazov, float peniaze, int cislaDo, int pocet){
		this(nazov, peniaze, 1, cislaDo, pocet);
	}
	
	public String getNazov() {
		return nazov;
	}
	
	public float getPeniaze() {
		return peniaze;
	}
	public int getCislaOd() {
		return cislaOd;
	}
	public int getCislaDo() {
		return cislaDo;
	}
	public int getPocet() {
		return pocet;
	}
	

	public int [] vyzrebujCisla(){
		int [] cisla = new int [pocet];
		List<Integer> vyradene = new ArrayList<>();
		Random rnd = new Random();
		for(int i = 0; i < pocet; i++){
			int cislo = -1;
			while(cislo == -1){
				cislo = rnd.nextInt(1,cislaDo);
				for(int vyradeneCislo:vyradene){
					if(cislo == vyradeneCislo){
						cislo = -1;
						break;
					}
				}
			}
			vyradene.add(cislo);
			cisla[i] = cislo;
		}
		return cisla;
	}

	public String vypis(int [] pole){
		StringBuilder finalString = new StringBuilder();
		finalString.append("|| Cisla: ");
		for(int cislo:pole){
			finalString.append(cislo + " ");
		}
		finalString.append(" ||");
		return finalString.toString();
	}

	public Zreb pridajZrebovanie(){
		Date datum = new Date();
		for(Zreb pzreb: zreby){
			if(pzreb.getDatum().getYear() == datum.getYear()){
				if(pzreb.getDatum().getMonth() == datum.getMonth()){
					if(pzreb.getDatum().getDay() == datum.getDay()){
						return null;
					}
				}
			}
		}
		Zreb zreb = new Zreb(vyzrebujCisla());
		zreby.add(zreb);
		return zreb;
	}

	private void addZreb(Zreb zreb){
		zreby.add(zreb);
	}

	private void addUserZreb(UserZreb zreb){
		userZreb.add(zreb);
	}

	public UserZreb makeUserZreb(double vklad,int [] poleCisel){
		boolean dupe = false;
		String id = generujID();
		UserZreb zreb = new UserZreb(vklad, poleCisel, id);
		for(int i = 0;i < poleCisel.length;i++){
			for(int j = i;j < poleCisel.length;j++){
				if(i != j){
					if(poleCisel[i] == poleCisel[j]){
						dupe = true;
					}
				}
			}
		}
		if(dupe == false){
			userZreb.add(zreb);
			return zreb;
		}
		else{
			return null;
		}
	}

	private String generujID(){
		return UUID.randomUUID().toString();
	}

	public Zreb getZrebByDatum(Date datum){
		for(Zreb zreb : zreby){
			if(datum.getYear() == zreb.getDatum().getYear()){
				if(datum.getMonth() == zreb.getDatum().getMonth()){
					if(datum.getDay() == zreb.getDatum().getDay()){
						return zreb;
					}
				}
			}
		}
		return null;
	}

	public int [] vyhodnotZreb(UserZreb userZreb){
		Zreb zreb = getZrebByDatum(userZreb.getDatum());
		if(zreb == null){
			return null;
		}
		int [] pZreb = zreb.getPoleCisel();
		int [] pUserZreb = userZreb.getPoleCisel();
		int [] pole = {0,pZreb.length,pUserZreb.length};
		for(int cislo:pZreb){
			for(int cisloUser: pUserZreb){
				if(cislo == cisloUser){
					pole[0] ++;
				}
			}
		}
		return pole;
	}

	public String showAllZreb(){
		StringBuilder finalString = new StringBuilder();
		for(int i = 0;i < zreby.size();i++){
			finalString.append("|| Zreb cislo " + (i+1) + ": ");
			finalString.append(zreby.get(i).getInfo());
		}
		finalString.append(" ||");
		return finalString.toString();
	}

	public String showInfo(){
		Util.out("| Nazov: " + nazov);
		Util.out("| Peniaze: " + peniaze);
		Util.out("| Cisla od: " + cislaOd);
		Util.out("| Cisla do: " + cislaDo);
		Util.out("| Pocet cisel: " + pocet);
		return (nazov + peniaze +cislaOd + cislaDo + pocet);
	}

	public static void main(String[] args) {
		Loteria loteria = new Loteria("Loto", 1000, 30, 10);

		loteria.showInfo();

		Zreb zreb = loteria.pridajZrebovanie();
		Util.out(zreb.getInfo());
		int [] poleCisel2 = {10,5,9,5,3,7,8,2,1};
		UserZreb zreb4 = loteria.makeUserZreb(1000,poleCisel2);
		if(zreb4 == null){
			Util.out("Tiket je neplatny");
		}
		else{
			Util.out(zreb4.getInfo());

			int [] pole = loteria.vyhodnotZreb(zreb4);
			System.out.println(pole[0]);
		}
		Zreb zreb2 = loteria.pridajZrebovanie();
		if(zreb2 == null){
			System.out.println("Zrebovanie uz v tento den prebehlo");
		}
	}
}