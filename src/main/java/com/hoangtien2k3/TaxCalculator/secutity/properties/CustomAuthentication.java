package com.hoangtien2k3.TaxCalculator.secutity.properties;

import com.hoangtien2k3.TaxCalculator.utils.JwtPayload;
import jakarta.servlet.http.HttpServletRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.io.Serial;

@Getter
@EqualsAndHashCode(callSuper = true)
public class CustomAuthentication extends AbstractAuthenticationToken {

    @Serial
    private static final long serialVersionUID = 7470565413815315182L;

    private final JwtPayload principal;

    public CustomAuthentication(JwtPayload principal, HttpServletRequest httpServletRequest) {
        super(principal.toAuthority());
        this.principal = principal;
        setDetails(new WebAuthenticationDetails(httpServletRequest));
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
