package ru.bellintegrator.practice.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.country.dto.CountryDto;
import ru.bellintegrator.practice.country.model.Country;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<CountryDto> countries() {
        List<Country> countries = countryDao.countries();
//        return countries.stream().map(country -> {
//            final CountryView view = new CountryView();
//            view.setCode(country.getCode());
//            view.setName(country.getName());
//            return view;
//        }).collect(Collectors.toList());
        List<CountryDto> countryDtoList = new ArrayList<>(countries.size());
        for (Country country : countries) {
            CountryDto countryDto = new CountryDto();
            countryDto.setName(country.getName());
            countryDto.setCitizenshipCode(country.getCitizenshipCode());
            countryDtoList.add(countryDto);
        }
        return countryDtoList;
    }
}
