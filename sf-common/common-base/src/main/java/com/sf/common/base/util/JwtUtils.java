package com.sf.common.base.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.sf.common.base.exception.BusinessException;
import com.sf.common.base.exception.CommonErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ky
 * @description
 * @date 2024-05-15 09:45
 */
@Slf4j
@Component
public class JwtUtils {

    private static String ACCESS_SECRET;

    private static String REFRESH_SECRET;

    @Value("${jwt.access-token-secret}")
    public void setAccessSecret(String accessSecret) {
        JwtUtils.ACCESS_SECRET = accessSecret;
    }

    @Value("${jwt.refresh-token-secret}")
    public void setRefreshSecret(String refreshSecret) {
        JwtUtils.REFRESH_SECRET = refreshSecret;
    }

    private static final String USER_ID_CLAIM = "userId";

    /**
     * 生成token（过期时间通过redis维护）
     * @param userId 用户id
     * @param isAccess 是否是AccessToken
     * @return token
     */
    public static String generateToken(String userId, Boolean isAccess) {
        Algorithm algorithm = Algorithm.HMAC256(isAccess ? ACCESS_SECRET : REFRESH_SECRET);
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "jwt");
        header.put("alg", "HS256");
        return JWT.create()
                .withHeader(header)
                .withClaim(USER_ID_CLAIM, userId)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * 解析token
     * @param token
     * @return userId
     */
    public static String verify(String token, Boolean isAccess) {
        try {
            if (StringUtils.isBlank(token)) {
                return null;
            }
            String secret = isAccess ? ACCESS_SECRET : REFRESH_SECRET;
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(USER_ID_CLAIM).asString();
        } catch (Exception e) {
            throw new BusinessException(CommonErrorEnum.UN_KNOWN_SESSION_ERROR);
        }
    }
}
