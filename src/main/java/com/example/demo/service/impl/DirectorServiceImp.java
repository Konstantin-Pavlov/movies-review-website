package com.example.demo.service.impl;

import com.example.demo.dao.DirectorDao;
import com.example.demo.dto.DirectorDto;
import com.example.demo.model.Director;
import com.example.demo.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorServiceImp implements DirectorService {
    private final DirectorDao directorDao;

    //    @SneakyThrows
    @Override
    public DirectorDto getDirectorById(long id) {
        try {
            Director director = directorDao.getDirectorById(id)
                    .orElseThrow(() -> new Exception("Can't find director with id " + id));
            return DirectorDto.builder()
                    .id(director.getId())
                    .fullName(director.getFullName())
                    .build();
        } catch (Exception e) {
            log.error("Can't find director with id " + id);
        }
        return null;
    }
}
