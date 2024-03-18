package com.example.movie_review.service.impl;

import com.example.movie_review.dao.DirectorDao;
import com.example.movie_review.dto.DirectorDto;
import com.example.movie_review.model.Director;
import com.example.movie_review.service.DirectorService;
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
