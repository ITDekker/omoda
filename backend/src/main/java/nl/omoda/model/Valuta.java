package nl.omoda.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "valuta")
@Data
public class Valuta {
	
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "valuta", nullable = false)
    private String valuta;
    
	@Column(name = "name", nullable = false)
    private String name;

    @Column(name = "valuta_value")
    private Double value;

    public Valuta(long id, String valuta, String name, Double value) {
        this.id = id;
        this.valuta = valuta;
        this.name = name;
        this.value = value;
    }

    public Valuta(String valuta, String name, Double value) {
        this.valuta = valuta;
        this.name = name;
        this.value = value;
    }

    public Valuta() {
    }
    
    public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}    
    
}