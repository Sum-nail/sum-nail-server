package backend.sumnail.domain.auth.service.helper;

import backend.sumnail.domain.auth.controller.dto.AuthGoogleLoginDto;
import backend.sumnail.global.exception.CustomException;
import backend.sumnail.global.exception.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleLoginHelper {
    public AuthGoogleLoginDto getUserInfo(String idToken){
        try{
            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.getForEntity(
                    "https://oauth2.googleapis.com/tokeninfo?id_token={idToken}",
                    AuthGoogleLoginDto.class,
                    idToken
            ).getBody();

        }catch (HttpClientErrorException e){
            throw new CustomException(ErrorCode.UNAUTHORIZED_TOKEN);
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}