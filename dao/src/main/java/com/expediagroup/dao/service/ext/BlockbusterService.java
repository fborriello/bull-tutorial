package com.expediagroup.dao.service.ext;

import static java.util.Collections.singletonList;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.expediagroup.common.constants.ContactType;
import com.expediagroup.common.constants.Genre;
import com.expediagroup.common.constants.Sex;
import com.expediagroup.dao.service.ext.domain.response.BlockbusterMovie;
import com.expediagroup.dao.service.ext.domain.response.Contact;
import com.expediagroup.dao.service.ext.domain.response.Staff;

@Service
public class BlockbusterService {
    public List<BlockbusterMovie> searchMovie(final String title) {
        return singletonList(buildFakeResponse(title));
    }

    private BlockbusterMovie buildFakeResponse(final String title) {
        Staff director = createDirector();
        List<Staff> actors = createActors();
        return new BlockbusterMovie(UUID.randomUUID().toString(), title, "Great film!", director, actors, Genre.CARTOON, 20.0f);
    }

    private Staff createDirector() {
        Contact phoneNumberDaoResponse = new Contact("86943", ContactType.MOBILE);
        return new Staff("Donald Duck", Sex.MALE, singletonList(phoneNumberDaoResponse));
    }

    private List<Staff> createActors() {
        Contact phoneNumberDaoResponse = new Contact("123456", ContactType.HOME);
        Staff actress = new Staff("Daisy Duck", Sex.FEMALE, singletonList(phoneNumberDaoResponse));
        return singletonList(actress);
    }
}
