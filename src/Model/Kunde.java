package Model;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Kunde implements Cloneable {
	
	private static int i = 1;
	private  int id;
	private String name;
	private String adress;
	
	public Kunde() {
		this.id = i++;
	}
	public Kunde(String _name , String _adress) {
		this.id = i++;
		this.name = _name;
		this.adress = _adress;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@Override
    public Kunde clone() {
        try {
           Kunde cloned = (Kunde) super.clone();
           cloned.name = new String(this.getName());
           cloned.adress = new String(this.getAdress());
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
	
	@Override
	public String toString() {
	    String tableFormat = "%-15s %-20s %-20s\n";
	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format(tableFormat, id, name, adress));
	    
	    return sb.toString();
	}


	
	
}
