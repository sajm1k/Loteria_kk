package loto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util{
    public static int [] generujZreb(int pocetCisel,int cislaOd, int cislaDo){
        int [] poleCisel = new int [pocetCisel];
        List<Integer> vyradene = new ArrayList<>();
        Random rng = new Random();
        for(int i = 0;i < poleCisel.length;i++){
            int cislo = -1;
			while(cislo == -1){
				cislo = rng.nextInt(cislaOd,cislaDo);
				for(int vyradeneCislo:vyradene){
					if(cislo == vyradeneCislo){
						cislo = -1;
						break;
					}
				}
			}
			vyradene.add(cislo);
			poleCisel[i] = cislo;
        }
        return poleCisel;
    }

	public static void out(String text){
		System.out.println(text);
	}
}
