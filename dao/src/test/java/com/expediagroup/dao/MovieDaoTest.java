package com.expediagroup.dao;

import static java.util.Collections.singletonList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expediagroup.beans.transformer.BeanTransformer;
import com.expediagroup.common.constants.ContactType;
import com.expediagroup.common.constants.Genre;
import com.expediagroup.common.constants.Sex;
import com.expediagroup.dao.movie.MovieDao;
import com.expediagroup.dao.movie.domain.request.MovieDaoRequest;
import com.expediagroup.dao.movie.domain.response.ContactDao;
import com.expediagroup.dao.movie.domain.response.MovieDaoResponse;
import com.expediagroup.dao.movie.domain.response.PersonDao;
import com.expediagroup.dao.service.ext.BlockbusterService;
import com.expediagroup.dao.service.ext.domain.response.BlockbusterMovie;
import com.expediagroup.dao.service.ext.domain.response.Contact;
import com.expediagroup.dao.service.ext.domain.response.Staff;

@RunWith(MockitoJUnitRunner.class)
public class MovieDaoTest {

    private static final String MOVIE_ID = "test-movie-id-123";
    private static final String MOVIE_TITLE = "Avengers End Game";
    private static final String MOVIE_DESCRIPTION = "Great superhero movie!";
    private static final Genre MOVIE_GENRE = Genre.CARTOON;
    private static final float MOVIE_PRICE = 25.99f;
    private static final String DIRECTOR_NAME = "Joe Russo";
    private static final String ACTOR_NAME = "Robert Downey Jr.";
    private static final String PHONE_NUMBER = "123456789";

    @Mock
    private BlockbusterService blockbusterService;

    @Mock
    private BeanTransformer movieDaoResponseTransformer;

    @InjectMocks
    private MovieDao movieDao;

    private MovieDaoResponse expectedResponse;

    @Before
    public void setUp() {
        ContactDao contactDao = new ContactDao(PHONE_NUMBER, ContactType.MOBILE);
        PersonDao directorDao = new PersonDao(DIRECTOR_NAME, Sex.MALE, singletonList(contactDao));
        PersonDao actorDao = new PersonDao(ACTOR_NAME, Sex.MALE, singletonList(contactDao));

        expectedResponse = new MovieDaoResponse(
                MOVIE_ID,
                MOVIE_TITLE,
                MOVIE_DESCRIPTION,
                directorDao,
                singletonList(actorDao),
                MOVIE_GENRE,
                MOVIE_PRICE
        );
    }

    @Test
    public void testSearchMovieReturnsTransformedResults() {
        // GIVEN
        final BlockbusterMovie movie = createMovie();
        when(blockbusterService.searchMovie(MOVIE_TITLE))
                .thenReturn(singletonList(movie));
        when(movieDaoResponseTransformer.transform(any(BlockbusterMovie.class), eq(MovieDaoResponse.class)))
                .thenReturn(expectedResponse);

        // WHEN
        List<MovieDaoResponse> results = movieDao.searchMovie(createMovieDaoRequest());

        // THEN
        assertNotNull("Results should not be null", results);
        assertFalse("Results should not be empty", results.isEmpty());
        assertEquals("Should return one movie", 1, results.size());
        assertEquals("Movie ID should match", MOVIE_ID, results.get(0).id());
        assertEquals("Movie title should match", MOVIE_TITLE, results.get(0).title());
        assertEquals("Movie description should match", MOVIE_DESCRIPTION, results.get(0).description());
        assertEquals("Movie genre should match", MOVIE_GENRE, results.get(0).genre());
        assertEquals("Movie price should match", MOVIE_PRICE, results.get(0).price(), 0.01f);

        verify(blockbusterService, times(1)).searchMovie(MOVIE_TITLE);
        verify(movieDaoResponseTransformer, times(1)).transform(movie, MovieDaoResponse.class);
    }

    @Test
    public void testSearchMovieReturnsEmptyListWhenNoMoviesFound() {
        // GIVEN
        when(blockbusterService.searchMovie(MOVIE_TITLE))
                .thenReturn(Collections.emptyList());

        // WHEN
        List<MovieDaoResponse> results = movieDao.searchMovie(createMovieDaoRequest());

        // THEN
        assertNotNull("Results should not be null", results);
        assertEquals("Results should be empty", 0, results.size());

        verify(blockbusterService, times(1)).searchMovie(MOVIE_TITLE);
        verify(movieDaoResponseTransformer, times(0)).transform(any(), any());
    }

    @Test
    public void testSearchMovieHandlesMultipleResults() {
        // GIVEN
        final BlockbusterMovie movie = createMovie();
        String secondMovieId = "test-movie-id-456";
        String secondMovieTitle = "Avengers Infinity War";
        BlockbusterMovie secondMovie = new BlockbusterMovie(
                secondMovieId,
                secondMovieTitle,
                "Another great movie!",
                movie.director(),
                movie.actors(),
                MOVIE_GENRE,
                MOVIE_PRICE
        );

        MovieDaoResponse secondResponse = new MovieDaoResponse(
                secondMovieId,
                secondMovieTitle,
                "Another great movie!",
                expectedResponse.director(),
                expectedResponse.actors(),
                MOVIE_GENRE,
                MOVIE_PRICE
        );

        when(blockbusterService.searchMovie(MOVIE_TITLE))
                .thenReturn(List.of(movie, secondMovie));
        when(movieDaoResponseTransformer.transform(movie, MovieDaoResponse.class))
                .thenReturn(expectedResponse);
        when(movieDaoResponseTransformer.transform(secondMovie, MovieDaoResponse.class))
                .thenReturn(secondResponse);

        // WHEN
        List<MovieDaoResponse> results = movieDao.searchMovie(createMovieDaoRequest());

        // THEN
        assertNotNull("Results should not be null", results);
        assertEquals("Should return two movies", 2, results.size());
        assertEquals("First movie ID should match", MOVIE_ID, results.get(0).id());
        assertEquals("Second movie ID should match", secondMovieId, results.get(1).id());

        verify(blockbusterService, times(1)).searchMovie(MOVIE_TITLE);
        verify(movieDaoResponseTransformer, times(2)).transform(any(BlockbusterMovie.class), eq(MovieDaoResponse.class));
    }

    @Test
    public void testSearchMovieUsesCorrectTitleFromRequest() {
        // GIVEN
        String customTitle = "Custom Movie Title";
        MovieDaoRequest customRequest = MovieDaoRequest.builder()
                .title(customTitle)
                .build();

        when(blockbusterService.searchMovie(customTitle))
                .thenReturn(Collections.emptyList());

        // WHEN
        movieDao.searchMovie(customRequest);

        // THEN
        verify(blockbusterService, times(1)).searchMovie(customTitle);
    }

    private BlockbusterMovie createMovie() {
        Contact directorContact = new Contact(PHONE_NUMBER, ContactType.MOBILE);
        Staff director = new Staff(DIRECTOR_NAME, Sex.MALE, singletonList(directorContact));

        Contact actorContact = new Contact(PHONE_NUMBER, ContactType.HOME);
        Staff actor = new Staff(ACTOR_NAME, Sex.MALE, singletonList(actorContact));

        return new BlockbusterMovie(
                MOVIE_ID,
                MOVIE_TITLE,
                MOVIE_DESCRIPTION,
                director,
                singletonList(actor),
                MOVIE_GENRE,
                MOVIE_PRICE
        );
    }

    private MovieDaoRequest createMovieDaoRequest() {
        return MovieDaoRequest.builder()
                .title(MOVIE_TITLE)
                .genre(MOVIE_GENRE.name())
                .build();
    }
}
