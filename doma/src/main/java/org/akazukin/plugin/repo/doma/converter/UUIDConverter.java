package org.akazukin.plugin.repo.doma.converter;

import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.DomainConverter;

import java.util.UUID;

@ExternalDomain
public final class UUIDConverter implements DomainConverter<UUID, String> {

    @Override
    public String fromDomainToValue(final UUID domain) {
        return domain.toString();
    }

    @Override
    public UUID fromValueToDomain(final String value) {
        if (value == null) {
            return null;
        }
        return UUID.fromString(value);
    }
}
