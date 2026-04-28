package com.expediagroup.dao.service.ext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.expediagroup.common.constants.ContactType;
import com.expediagroup.common.constants.Genre;
import com.expediagroup.common.constants.Sex;
import com.expediagroup.dao.service.ext.domain.response.BlockbusterMovie;

public class BlockbusterServiceTest {

    private BlockbusterService blockbusterService;

    @Before
    public void setUp() {
        blockbusterService = new BlockbusterService();
    }

    @Test
    public void testSearchMovieReturnsSingleMovie() {
        // WHEN
        List<BlockbusterMovie> results = blockbusterService.searchMovie("Avengers");

        // THEN
        assertNotNull("Results should not be null", results);
        assertFalse("Results should not be empty", results.isEmpty());
        assertEquals("Should return exactly one movie", 1, results.size());
    }

    @Test
    public void testSearchMovieReturnsMovieWithCorrectTitle() {
        // GIVEN
        String title = "Avengers End Game";

        // WHEN
        List<BlockbusterMovie> results = blockbusterService.searchMovie(title);

        // THEN
        assertEquals("Movie title should match the search title", title, results.get(0).title());
    }

    @Test
    public void testSearchMovieReturnsMovieWithNonNullId() {
        // WHEN
        List<BlockbusterMovie> results = blockbusterService.searchMovie("Some Movie");

        // THEN
        assertNotNull("Movie ID should not be null", results.get(0).id());
        assertFalse("Movie ID should not be empty", results.get(0).id().isEmpty());
    }

    @Test
    public void testSearchMovieReturnsMovieWithDirector() {
        // WHEN
        List<BlockbusterMovie> results = blockbusterService.searchMovie("Some Movie");

        // THEN
        BlockbusterMovie movie = results.get(0);
        assertNotNull("Director should not be null", movie.director());
        assertEquals("Director full name should be Donald Duck", "Donald Duck", movie.director().fullName());
        assertEquals("Director sex should be MALE", Sex.MALE, movie.director().sex());
        assertFalse("Director contacts should not be empty", movie.director().contacts().isEmpty());
        assertEquals("Director contact type should be MOBILE", ContactType.MOBILE, movie.director().contacts().get(0).contactType());
    }

    @Test
    public void testSearchMovieReturnsMovieWithActors() {
        // WHEN
        List<BlockbusterMovie> results = blockbusterService.searchMovie("Some Movie");

        // THEN
        BlockbusterMovie movie = results.get(0);
        assertNotNull("Actors list should not be null", movie.actors());
        assertFalse("Actors list should not be empty", movie.actors().isEmpty());
        assertEquals("Actress full name should be Daisy Duck", "Daisy Duck", movie.actors().get(0).fullName());
        assertEquals("Actress sex should be FEMALE", Sex.FEMALE, movie.actors().get(0).sex());
        assertEquals("Actress contact type should be HOME", ContactType.HOME, movie.actors().get(0).contacts().get(0).contactType());
    }

    @Test
    public void testSearchMovieReturnsMovieWithGenreAndDescription() {
        // WHEN
        List<BlockbusterMovie> results = blockbusterService.searchMovie("Some Movie");

        // THEN
        BlockbusterMovie movie = results.get(0);
        assertEquals("Genre should be CARTOON", Genre.CARTOON, movie.genre());
        assertEquals("Description should be 'Great film!'", "Great film!", movie.description());
        assertEquals("Price should be 20.0f", 20.0f, movie.price(), 0.01f);
    }
}
