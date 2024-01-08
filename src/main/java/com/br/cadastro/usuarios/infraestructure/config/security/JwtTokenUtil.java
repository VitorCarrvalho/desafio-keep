package com.br.cadastro.usuarios.infraestructure.config.security;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class    JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_EXPIRES = 120000;

    private static String secret =  "95f5746f772e6c883a12e81f4f382926115d17a226e81bcd016dc59fab818532cc5c73623f5ec2a1df7da68e0488bc4247d775ee92cb93973e29156a8211ae99";

    //retorna o username do token do jwt
    public String getUsernameFronToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //Retorna vários objetos possíveis de vários tipos possíveis. - captura as informações de dentro do token.
    public <T>T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    //retorna expiration date do token jwt
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //para retornar qualquer informação do token, e pra isso nós vamos precisar da secret.
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //validar se o token é expirado.
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Gerar Token
    public String generateToken(String clientId){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, clientId);
    }

    //Cria o token e também vai definir o tempo de expiração.
    private String doGenerateToken(Map<String, Object> claims, String clientId) {
        return Jwts.builder().setClaims(claims).setSubject(clientId).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRES))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //valida o token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFronToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
