package com.hc.gateway.authentication;

import lombok.Data;

/**
 * JSON Web Token
 */
@Data
public class JWTToken  {

    private static final long serialVersionUID = 1282057025599826155L;

    private String token;

    private String exipreAt;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String exipreAt) {
        this.token = token;
        this.exipreAt = exipreAt;
    }

}
