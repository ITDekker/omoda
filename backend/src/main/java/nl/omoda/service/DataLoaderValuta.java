package nl.omoda.service;

import jakarta.annotation.PostConstruct;
import nl.omoda.model.Valuta;
import nl.omoda.repository.ValutaRepository;

import org.springframework.stereotype.Component;

@Component
public class DataLoaderValuta {

    private final ValutaRepository valutaRepository;

    public DataLoaderValuta(ValutaRepository valutaRepository) {      
		this.valutaRepository = valutaRepository;
    }

    @PostConstruct
    public void init() {
        valutaRepository.deleteAll();        
        valutaRepository.save(new Valuta("euro", "€100", 100d));
        valutaRepository.save(new Valuta("euro", "€50", 50d));
        valutaRepository.save(new Valuta("euro", "€20", 20d));
        valutaRepository.save(new Valuta("euro", "€10", 10d));
        valutaRepository.save(new Valuta("euro", "€5", 5d));
        valutaRepository.save(new Valuta("euro", "€2", 2d));
        valutaRepository.save(new Valuta("euro", "€1", 1d));
        valutaRepository.save(new Valuta("euro", "€0.50", 0.50));
        valutaRepository.save(new Valuta("euro", "€0.20", 0.20));
        valutaRepository.save(new Valuta("euro", "€0.10", 0.10));
        valutaRepository.save(new Valuta("euro", "€0.05", 0.05));
        
        valutaRepository.save(new Valuta("dollar", "$100", 100d));
        valutaRepository.save(new Valuta("dollar", "$50", 50d));
        valutaRepository.save(new Valuta("dollar", "$20", 20d));
        valutaRepository.save(new Valuta("dollar", "$10", 10d));
        valutaRepository.save(new Valuta("dollar", "$5", 5d));
        valutaRepository.save(new Valuta("dollar", "$2", 2d));
        valutaRepository.save(new Valuta("dollar", "$1", 1d));
        valutaRepository.save(new Valuta("dollar", "$0.50", 0.50));
        valutaRepository.save(new Valuta("dollar", "$0.25", 0.25));
        valutaRepository.save(new Valuta("dollar", "$0.10", 0.10));
        valutaRepository.save(new Valuta("dollar", "$0.05", 0.05));
    }
}