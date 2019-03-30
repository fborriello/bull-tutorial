package com.hotels.service.movie;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.service.movie.domain.response.MovieSvc;
import com.hotels.dao.movie.MovieDao;
import com.hotels.dao.movie.domain.request.MovieDaoRequest;
import com.hotels.service.movie.domain.request.MovieSvcRequest;
import com.hotels.beans.BeanUtils;
import com.hotels.beans.transformer.Transformer;

@Service
public class MovieService {
    @Autowired
    private BeanUtils beanUtils;

    @Autowired
    private MovieDao movieDao;

    public List<MovieSvc> searchMovie(final MovieSvcRequest movieSvcRequest) {
        notNull(movieSvcRequest, "movieSvcRequest cannot be null!");
        Transformer beanTransformer = beanUtils.getTransformer();
        MovieDaoRequest movieDaoRequest = beanTransformer.transform(movieSvcRequest, MovieDaoRequest.class);
        List<MovieDao> foundMovies = movieDao.searchMovie(movieDaoRequest);
        return transformResponse(beanTransformer, foundMovies);
    }

    private List<MovieSvc> transformResponse(final Transformer beanTransformer, final List<MovieDao> foundMovies) {
        return foundMovies.stream()
                .map(movie -> beanTransformer.transform(movie, MovieSvc.class))
                .collect(Collectors.toList());
    }
}
