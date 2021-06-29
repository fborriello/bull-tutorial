package com.hotels.web.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Map;

/**
 * {@link MovieController} test.
 */
public class MovieControllerTest extends AbstractControllerTest {
    private static final String V1_SEARCH_ENDPOINT = "/v1/movie/search";
    private static final String TITLE_PARAM_NAME = "title";
    private static final String FILM_NAME = "Avengers End Game";

    @Test
    @SuppressWarnings("unchecked")
    public void testMovieSearchReturnsTheSearchedTitle() throws Exception {
        // GIVEN
        var searchRequest = get(V1_SEARCH_ENDPOINT)
                .param(TITLE_PARAM_NAME, FILM_NAME)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON_VALUE);

        // WHEN
        MockHttpServletResponse response = mvc.perform(searchRequest).andReturn().getResponse();
        List<Map> movies = jsonMapper.readValue(response.getContentAsString(), List.class);

        // THEN
        assertEquals(OK.value(), response.getStatus());
        assertFalse(movies.isEmpty());
        assertEquals(FILM_NAME, movies.get(0).get(TITLE_PARAM_NAME));
    }

    @Test
    public void testMovieSearchReturnsBadRequestIfTheRequestIsNotValid() throws Exception {
        // GIVEN
        MockHttpServletRequestBuilder searchRequest = get(V1_SEARCH_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON_VALUE);

        // WHEN
        MockHttpServletResponse response = mvc.perform(searchRequest).andReturn().getResponse();

        // THEN
        assertEquals(BAD_REQUEST.value(), response.getStatus());
    }
}
