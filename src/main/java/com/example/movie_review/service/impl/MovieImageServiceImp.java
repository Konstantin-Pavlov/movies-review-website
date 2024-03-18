package com.example.movie_review.service.impl;

import com.example.movie_review.dao.MovieImageDao;
import com.example.movie_review.dto.MovieImageDto;
import com.example.movie_review.model.MovieImage;
import com.example.movie_review.service.MovieImageService;
import com.example.movie_review.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieImageServiceImp implements MovieImageService {
    private final MovieImageDao movieImageDao;
    private final FileUtil fileUtil;

    @Override
    public void upload(MovieImageDto movieImageDto) {
        String fileName = fileUtil.saveUploadedFile(movieImageDto.getFile(), "images");
        MovieImage movieImage = new MovieImage();
        movieImage.setMovieId(movieImageDto.getMovieId());
        movieImage.setFileName(fileName);

        movieImageDao.save(movieImage);
        log.info(String.format("added image with id %d and name %s", movieImage.getMovieId(), fileName));
    }
}
