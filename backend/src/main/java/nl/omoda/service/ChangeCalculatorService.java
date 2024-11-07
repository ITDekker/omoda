package nl.omoda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.omoda.model.ChangeRequest;
import nl.omoda.model.ChangeResponse;
import nl.omoda.model.Valuta;
import nl.omoda.repository.ValutaRepository;
import nl.omoda.utils.Valutas;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChangeCalculatorService {
	
	@Autowired
    private ValutaRepository valutaRepository;

    public ChangeResponse calculateChange(ChangeRequest request) {   	
    	
        double changeAmount = request.getAmountPaid() - request.getTotalAmount();
        
        if (changeAmount < 0) {
            throw new IllegalArgumentException("Amount paid is less than total amount");
        }

        Map<String, Integer> change = new LinkedHashMap<>();
        double remainingChange = changeAmount;

        List<Valuta> valuta = valutaRepository.findByValuta(request.getValuta())
        		.stream()
        		.sorted((o1, o2)->o2.getValue().
                        compareTo(o1.getValue())).
                        collect(Collectors.toList());
        
        for (int i = 0; i < valuta.size(); i++) {
            int count = (int) (remainingChange / valuta.get(i).getValue());
            if (count > 0) {
                change.put(valuta.get(i).getName(), count);
                remainingChange -= count * valuta.get(i).getValue();
            }

            remainingChange = Math.round(remainingChange * 100.0) / 100.0;
        }

        return new ChangeResponse(changeAmount, change, 200, "");
    }
}