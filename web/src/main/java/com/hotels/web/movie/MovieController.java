package com.hotels.web.movie;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.beans.BeanUtils;
import com.hotels.beans.transformer.Transformer;
import com.hotels.service.movie.MovieService;
import com.hotels.service.movie.domain.request.MovieSvcRequest;
import com.hotels.service.movie.domain.response.MovieSvcResponse;
import com.hotels.web.movie.domain.request.MovieRequest;
import com.hotels.web.movie.domain.response.Movie;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = {"Movie service"})
@ApiModel(value = "Movie services", description = "endpoint for service related to movies")
public class MovieController {
    private static final String V1_MOVIE_SEARCH = "v1/movie/search";

    @Autowired
    private BeanUtils beanUtils;

    @Autowired
    private Transformer movieResponseTransformer;

    @Autowired
    private MovieService movieService;

    /**
     * Movie search.
     * @param movieRequest {@link MovieRequest}
     * @return the found movies with 200 Http Response and wrapped in a {@link Movie} object, <br>
     *     or a 400 Http Response if could not bind the httpRequest parameters,<br>
     *     or a 500 Http response if there was any error in the search flow.
     */
    @ApiOperation(value = "Returns the found movies.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "The movie title.",
                    paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "genre", value = "The movie genre", paramType = "query",
                    dataType = "string", allowableValues = "action, comedy, horror, science_fiction")
    })
    @ApiResponses({
            @ApiResponse(code = HTTP_OK, message = "The found movies"),
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad http request"),
            @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Any error in the service")
    })
    @GetMapping(path = V1_MOVIE_SEARCH, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> searchMovie(@Valid final MovieRequest movieRequest) {
        notNull(movieRequest, "movieRequest cannot be null!");
        final Transformer beanTransformer = beanUtils.getTransformer();
        MovieSvcRequest movieSvcRequest = beanTransformer.transform(movieRequest, MovieSvcRequest.class);
        List<MovieSvcResponse> foundMovies = movieService.searchMovie(movieSvcRequest);
        return new ResponseEntity<>(transformResponse(foundMovies), OK);
    }

    private List<Movie> transformResponse(final List<MovieSvcResponse> foundMovies) {
        return foundMovies.stream()
                .map(movieSvcResponse -> movieResponseTransformer.transform(movieSvcResponse, Movie.class))
                .collect(Collectors.toList());
    }
}
