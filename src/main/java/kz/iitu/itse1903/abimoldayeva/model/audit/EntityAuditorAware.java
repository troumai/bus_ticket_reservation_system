package kz.iitu.itse1903.abimoldayeva.model.audit;

import kz.iitu.itse1903.abimoldayeva.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class EntityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return Optional.ofNullable(((User) authentication.getPrincipal()).getUsername());
    }
}
