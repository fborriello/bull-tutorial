package com.expediagroup.service.movie.domain.response;

import com.expediagroup.common.constants.ContactType;

import lombok.Getter;

@Getter
public final class ContactSvc {
    private final String value;
    private final ContactType contactType;

    private ContactSvc(final ContactSvcBuilder builder) {
        this.value = builder.getValue();
        this.contactType = builder.getContactType();
    }

    @Getter
    public static final class ContactSvcBuilder {
        private String value;
        private ContactType contactType;

        private ContactSvcBuilder() {
        }

        public static ContactSvcBuilder aContactSvc() {
            return new ContactSvcBuilder();
        }

        public ContactSvcBuilder withValue(String value) {
            this.value = value;
            return this;
        }

        public ContactSvcBuilder withContactType(ContactType contactType) {
            this.contactType = contactType;
            return this;
        }

        public ContactSvc build() {
            return new ContactSvc(this);
        }
    }
}
