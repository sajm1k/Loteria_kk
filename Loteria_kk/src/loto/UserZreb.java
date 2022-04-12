package loto;

public class UserZreb extends Zreb{
    public double vklad;
    public String id;

    public UserZreb(double vklad,int [] poleCisel, String id){
        super(poleCisel);
        this.vklad = vklad;
        this.id = id;
    }

    public double getVklad(){
        return vklad;
    }

    public String getID(){
        return id;
    }
}