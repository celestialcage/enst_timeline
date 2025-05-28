package com.shabet.ensthistory.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("com.shabet.ensthistory")
public class JwtProps {

    private String secretKey;
}
