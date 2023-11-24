package com.hoangtien2k3.TaxCalculator.model.record;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.With;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

@JsonInclude(Include.NON_NULL)
@Builder
@With
public record RequestAccessToken(
        String username, String audiences, Collection<String> roles, String accessToken) {
    public RequestAccessToken(
            String username, String audiences, Collection<String> roles, String accessToken) {
        this.username = username;
        this.audiences = audiences;
        this.roles = CollectionUtils.emptyIfNull(roles);
        this.accessToken = accessToken;
    }
}
