package com.example.api_gateway_1.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties("rsa")

public record RsaConfig(RSAPublicKey publicKey) {
}
