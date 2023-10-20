package jgdabc.yingli;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jgdabc.yingli.util.Constants;

import java.util.HashMap;
import java.util.Map;


public class JwtUtils {

    public static String createJwt(String userJson)
    {
        Map<String,Object> header =  new HashMap<>();
        header.put("alg","HS256");
        header.put("typ","JWT");
        return JWT.create().withHeader(header).withClaim("user",userJson)
                .sign(Algorithm.HMAC256(Constants.JWT_SECRERT));
    }
//    验证JWT
    public static Boolean veryJwt(String jwt){
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Constants.JWT_SECRERT)).build();
            jwtVerifier.verify(jwt);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;


    }

    public static String parseJwt(String jwt) {
        JWTVerifier jwtParse = JWT.require(Algorithm.HMAC256(Constants.JWT_SECRERT)).build();
        DecodedJWT decodedJWT = jwtParse.verify(jwt);
        Claim user = decodedJWT.getClaim("user");
        return user.asString();
    }

}
