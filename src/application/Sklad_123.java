package application;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sklad_123 {
	
	@Column(name = "id")
	public int id;
	@Column(name = "������")
	public String tovar;
	@Column(name = "����������")
	public int kol;
	
	public Sklad_123() {
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setTovar(String tovar)
	{
		this.tovar = tovar;
	}
	
	public void setKol(int kol)
	{
		this.kol = kol;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getTovar()
	{
		return tovar;
	}
	
	public int getKol()
	{
		return kol;
	}
	
	public String toString() {
        return "Sklad_123 {id = " + String.valueOf(id) + ", ����� = '" + tovar + ", ���������� = '" + String.valueOf(kol) + "'}";
    }
}

