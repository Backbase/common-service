package com.backbase.accelerators.common.config;

import com.backbase.buildingblocks.context.ContextScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Pattern;


@Getter
@Setter
@ContextScoped
@Configuration
public class HttpConfig {

    @Value("${backbase.communication.http.default-scheme:http}")
    @Pattern(regexp = "https?")
    protected String scheme;


}
