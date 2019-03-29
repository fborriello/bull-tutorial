package com.hotels.dao.service.ext;

import static java.util.Collections.singletonList;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hotels.common.constants.Genre;
import com.hotels.common.constants.PhoneType;
import com.hotels.common.constants.Sex;
import com.hotels.dao.service.ext.domain.response.BlockbusterMovie;
import com.hotels.dao.service.ext.domain.response.PhoneNumber;
import com.hotels.dao.service.ext.domain.response.Staff;

@Service
public class BlockbusterService {
    public List<BlockbusterMovie> searchMovie(final String title) {
        return singletonList(buildFakeResponse(title));
    }

    private BlockbusterMovie buildFakeResponse(final String title) {
        Staff director = createDirector();
        List<Staff> actors = createActors();
        return new BlockbusterMovie(UUID.randomUUID().toString(), title, "Great film!", director, actors, Genre.CARTOON);
    }

    private Staff createDirector() {
        PhoneNumber phoneNumberDaoResponse = new PhoneNumber("86943", PhoneType.MOBILE);
        return new Staff("Donald Duck", Sex.MALE, singletonList(phoneNumberDaoResponse));
    }

    private List<Staff> createActors() {
        PhoneNumber phoneNumberDaoResponse = new PhoneNumber("123456", PhoneType.HOME);
        Staff actress = new Staff("Daisy Duck", Sex.FEMALE, singletonList(phoneNumberDaoResponse));
        return singletonList(actress);
    }
}
