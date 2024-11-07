package nl.omoda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.omoda.model.Valuta;

@Repository
public interface ValutaRepository extends JpaRepository<Valuta, Long> {
	
	List<Valuta> findByValuta(String name);
}