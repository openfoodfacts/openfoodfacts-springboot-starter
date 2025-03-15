package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientConfiguration;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.*;

class AuthUtilsTest {

    private  AuthUtils authUtils;
    private  AuthUtils failUtils;

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(
                    OpenFoodFactsWebClientConfiguration.class,
                    AuthUtils.class
            ))
            .withPropertyValues(
                    "openfoodfacts.username=FAKE_USERNAME",
                    "openfoodfacts.password=FAKE_PASSWORD"
            );


    private final ApplicationContextRunner failRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(
                    OpenFoodFactsWebClientConfiguration.class,
                    AuthUtils.class
            ));

    @BeforeEach
    void initUriUtils(){
        contextRunner.run((context -> {
            authUtils = context.getBean(AuthUtils.class);
        }));

        failRunner.run((context -> {
            failUtils = context.getBean(AuthUtils.class);
        }));

    }

    @Test
    void whenPropertiesSettedWillReturnAuthHeaders(){
        assertNotNull(authUtils.getAuthHeaders());
        assertTrue(authUtils.getAuthHeaders().containsKey("Authorization"));
    }

    @Test
    void whenPropertiesNullThrowException(){
       assertThrows(OpenFoodFactsException.class,()->failUtils.getAuthHeaders());
    }

    @Test
    void whenPropertiesValidNotThrowException(){
        assertDoesNotThrow(()->authUtils.getAuthHeaders());
    }

}