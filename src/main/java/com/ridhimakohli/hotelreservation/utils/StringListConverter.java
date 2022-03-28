package com.ridhimakohli.hotelreservation.utils;


import com.ridhimakohli.hotelreservation.types.Amenities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class StringListConverter implements AttributeConverter<Set<Amenities>, String> {
    @Override
    public String convertToDatabaseColumn(Set<Amenities> amenities) {
        return String.join(",", amenities.toString());
    }

    @Override
    public Set<Amenities> convertToEntityAttribute(String s) {
        return Arrays.stream(s.split(","))
                .map(str -> str.trim().replaceAll("[\\[\\](){}]",""))
                .map(a -> Amenities.valueOf(a))
                .collect(Collectors.toSet());
    }
}
