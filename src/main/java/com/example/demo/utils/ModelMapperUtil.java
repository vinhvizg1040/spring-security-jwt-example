/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCvinhvizg
 */
@Service
public class ModelMapperUtil extends ModelMapper {

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> map(element, targetClass))
            .collect(Collectors.toList());
    }
}
