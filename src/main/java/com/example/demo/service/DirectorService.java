package com.example.demo.service;

import com.example.demo.dao.DirectorDao;
import com.example.demo.dto.DirectorDto;

public interface DirectorService {
    DirectorDto getDirectorById(long id);
}
