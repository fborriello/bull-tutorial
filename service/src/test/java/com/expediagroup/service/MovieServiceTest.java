package com.expediagroup.service;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.expediagroup.common.constants.Genre;
import com.expediagroup.dao.movie.MovieDao;
import com.expediagroup.dao.movie.domain.request.MovieDaoRequest;
import com.expediagroup.dao.movie.domain.response.MovieDaoResponse;
import com.expediagroup.service.movie.MovieService;
import com.expediagroup.service.movie.domain.request.MovieSvcRequest;
import com.expediagroup.service.movie.domain.response.MovieSvcResponse;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    private static final String MOVIE_TITLE = "Avengers End Game";
    private static final String MOVIE_GENRE = "ACTION";

    @Mock
    private MovieDao movieDao;

    @Mock
    private BeanTransformer movieSvcResponseTransformer;

    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        // Use a real BeanUtils so beanUtils.getTransformer().transform() works;
        // only movieDao and movieSvcResponseTransformer are mocked.
        movieService = new MovieService(new BeanUtils(), movieDao, movieSvcResponseTransformer);
    }

    @Test
    public void testSearchMovieTransformsAndDelegatesCorrectly() {
        // GIVEN
        MovieSvcRequest request = createMovieSvcRequest();
        MovieDaoResponse movieDaoResponse = new MovieDaoResponse(
                "some-id", MOVIE_TITLE, "description", null, emptyList(), Genre.ACTION, 9.99f);
        MovieSvcResponse movieSvcResponse = new MovieSvcResponse(
                "some-id", MOVIE_TITLE, "description", null, emptyList(), Genre.ACTION, null);

        when(movieDao.searchMovie(any(MovieDaoRequest.class))).thenReturn(singletonList(movieDaoResponse));
        when(movieSvcResponseTransformer.transform(movieDaoResponse, MovieSvcResponse.class)).thenReturn(movieSvcResponse);

        // WHEN
        List<MovieSvcResponse> results = movieService.searchMovie(request);

        // THEN
        assertEquals(1, results.size(), "Should return one movie");
        assertEquals(MOVIE_TITLE, results.get(0).title(), "Movie title should match");

        verify(movieDao, times(1)).searchMovie(any(MovieDaoRequest.class));
        verify(movieSvcResponseTransformer, times(1)).transform(movieDaoResponse, MovieSvcResponse.class);
    }

    @Test
    public void testSearchMovieThrowsOnNullRequest() {
        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> movieService.searchMovie(null),
                "Should throw IllegalArgumentException when request is null");
    }

    @Test
    public void testSearchMovieReturnsEmptyListWhenDaoReturnsEmpty() {
        // GIVEN
        MovieSvcRequest request = createMovieSvcRequest();

        when(movieDao.searchMovie(any(MovieDaoRequest.class))).thenReturn(emptyList());

        // WHEN
        List<MovieSvcResponse> results = movieService.searchMovie(request);

        // THEN
        assertTrue(results.isEmpty(), "Result list should be empty when DAO returns empty");

        verify(movieDao, times(1)).searchMovie(any(MovieDaoRequest.class));
        verify(movieSvcResponseTransformer, never()).transform(any(), eq(MovieSvcResponse.class));
    }

    private MovieSvcRequest createMovieSvcRequest() {
        MovieSvcRequest request = new MovieSvcRequest();
        request.setTitle(MOVIE_TITLE);
        request.setGenre(MOVIE_GENRE);
        return request;
    }
}
