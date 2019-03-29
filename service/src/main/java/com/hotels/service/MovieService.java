package com.hotels.service;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.beans.BeanUtils;
import com.hotels.beans.transformer.Transformer;
import com.hotels.dao.MovieDao;
import com.hotels.dao.domain.request.MovieDaoRequest;
import com.hotels.dao.service.ext.domain.response.BlockbusterMovie;
import com.hotels.service.domain.request.MovieSvcRequest;
import com.hotels.service.domain.response.MovieSvc;

@Service
public class MovieService {
    @Autowired
    private BeanUtils beanUtils;

    @Autowired
    private Transformer movieDaoResponseTransformer;

    @Autowired
    private MovieDao movieDao;

    public List<MovieSvc> searchMovie(final MovieSvcRequest movieSvcRequest) {
        notNull(movieSvcRequest, "movieSvcRequest cannot be null!");
        MovieDaoRequest movieDaoRequest = beanUtils.getTransformer().transform(movieSvcRequest, MovieDaoRequest.class);
        List<BlockbusterMovie> foundMovies = movieDao.searchMovie(movieDaoRequest);
        return transformResponse(movieDaoResponseTransformer, foundMovies);
    }

    private List<MovieSvc> transformResponse(final Transformer beanTransformer, final List<BlockbusterMovie> foundMovies) {
        return foundMovies.stream()
                .map(movieDao -> movieDaoResponseTransformer.transform(movieDao, MovieSvc.class))
                .collect(Collectors.toList());
    }
}
