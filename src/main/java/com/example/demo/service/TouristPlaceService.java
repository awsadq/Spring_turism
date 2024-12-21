package com.example.demo.service;

import com.example.demo.model.TouristPlace;
import com.example.demo.repository.TouristPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
public class TouristPlaceService {
    private TouristPlaceRepository touristPlaceRepository;

    @Autowired
    public TouristPlaceService(TouristPlaceRepository touristPlaceRepository) {
        this.touristPlaceRepository = touristPlaceRepository;
    }

    public List<TouristPlace> getAllTouristPlaces() {
        return touristPlaceRepository.findAll().isEmpty() ? new LinkedList<>() : touristPlaceRepository.findAll();
    }

    public List<TouristPlace> getAllTouristPlacesByCountry(String country) {
        return touristPlaceRepository.findAllByCountry(country);
    }

    public Optional<TouristPlace> getTouristPlaceById(Long id) {
        return touristPlaceRepository.findById(id);
    }

    public TouristPlace createTouristPlace(TouristPlace touristPlace) {
        return touristPlaceRepository.save(touristPlace);
    }

    public TouristPlace updateTouristPlace(Long id, TouristPlace touristPlaceDetails) {
        TouristPlace touristPlace = touristPlaceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TouristPlace not found for this id :: " + id));
        touristPlace.setPlaceName(touristPlaceDetails.getPlaceName());
        touristPlace.setDescription(touristPlaceDetails.getDescription());
        return touristPlaceRepository.save(touristPlace);
    }

    public void deleteTouristPlace(Long id) {
        TouristPlace touristPlace = touristPlaceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TouristPlace not found for this id :: " + id));
        touristPlaceRepository.delete(touristPlace);
    }

    private static final Map<Character, String> transliterationMap = new HashMap<>();
    static {
        transliterationMap.put('а', "a");
        transliterationMap.put('б', "b");
        transliterationMap.put('в', "v");
        transliterationMap.put('г', "g");
        transliterationMap.put('д', "d");
        transliterationMap.put('е', "e");
        transliterationMap.put('ё', "yo");
        transliterationMap.put('ж', "zh");
        transliterationMap.put('з', "z");
        transliterationMap.put('и', "i");
        transliterationMap.put('й', "y");
        transliterationMap.put('к', "k");
        transliterationMap.put('л', "l");
        transliterationMap.put('м', "m");
        transliterationMap.put('н', "n");
        transliterationMap.put('о', "o");
        transliterationMap.put('п', "p");
        transliterationMap.put('р', "r");
        transliterationMap.put('с', "s");
        transliterationMap.put('т', "t");
        transliterationMap.put('у', "u");
        transliterationMap.put('ф', "f");
        transliterationMap.put('х', "kh");
        transliterationMap.put('ц', "ts");
        transliterationMap.put('ч', "ch");
        transliterationMap.put('ш', "sh");
        transliterationMap.put('щ', "shch");
        transliterationMap.put('ы', "y");
        transliterationMap.put('э', "e");
        transliterationMap.put('ю', "yu");
        transliterationMap.put('я', "ya");
    }

    public Map<String, String> transliteratedCountries = new HashMap<>();

    public String transliterate(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toLowerCase().toCharArray()) {
            result.append(transliterationMap.getOrDefault(c, String.valueOf("")));
        }

        return result.toString();
    }


}