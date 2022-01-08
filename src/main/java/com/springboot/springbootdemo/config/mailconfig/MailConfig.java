package com.springboot.springbootdemo.config.mailconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangxia
 * @date 2022年01月08日 13:12
 */
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private String host;
    private Integer port;
    public String username;
    private String password;
    private String protocol = "smtp";
    private Charset defaultEncoding;
    private Map<String, String> properties;
    private String jndiName;
    private boolean testConnection;

    public MailConfig() {
        this.defaultEncoding = DEFAULT_CHARSET;
        this.properties = new HashMap();
    }

}
