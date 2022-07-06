package com.expediagroup.web.movie;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.expediagroup.service.movie.MovieService;
import com.expediagroup.service.movie.domain.request.MovieSvcRequest;
import com.expediagroup.service.movie.domain.response.MovieSvcResponse;
import com.expediagroup.web.movie.domain.request.MovieRequest;
import com.expediagroup.web.movie.domain.response.Movie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Movie service")
@Schema(name = "Movie services", description = "endpoint for service related to movies")
@AllArgsConstructor
public class MovieController {
    private static final String V1_MOVIE_SEARCH = "v1/movie/search";

    private final BeanUtils beanUtils;

    private final BeanTransformer movieResponseTransformer;

    private final MovieService movieService;

    /**
     * Movie search.
     * @param title the movie title
     * @param genre the movie genre
     * @return the found movies with 200 Http Response and wrapped in a {@link Movie} object, <br>
     * or a 400 Http Response if could not bind the httpRequest parameters,<br>
     * or a 500 Http response if there was any error in the search flow.
     */
    @Operation(description = "Returns the found movies.")
    @Parameters({
            @Parameter(name = "title", description = "The movie title.",
                    in = ParameterIn.QUERY),
            @Parameter(name = "genre", description = "The movie genre", in = ParameterIn.QUERY,
                    example = "action, comedy, horror, science_fiction")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The found movies"),
            @ApiResponse(responseCode = "400", description = "Bad http request"),
            @ApiResponse(responseCode = "500", description = "Any error in the service")
    })
    @GetMapping(path = V1_MOVIE_SEARCH, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> searchMovie(@RequestParam @NotNull final String title, @RequestParam(required = false) final String genre) {
        var beanTransformer = beanUtils.getTransformer().setValidationEnabled(true);
        // request transformation
        var movieSvcRequest = beanTransformer.transform(new MovieRequest(title, genre), MovieSvcRequest.class);
        var foundMovies = movieService.searchMovie(movieSvcRequest);
        // response transformation
        return new ResponseEntity<>(transformResponse(foundMovies), OK);
    }

    private List<Movie> transformResponse(final List<MovieSvcResponse> foundMovies) {
        return foundMovies.stream()
                .map(movieSvcResponse -> movieResponseTransformer.transform(movieSvcResponse, Movie.class))
                .collect(Collectors.toList());
    }
}
