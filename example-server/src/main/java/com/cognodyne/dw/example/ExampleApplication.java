package com.cognodyne.dw.example;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognodyne.dw.cdi.CdiBundle;
import com.cognodyne.dw.jpa.JpaBundle;
import com.cognodyne.dw.jpa.JpaServiceProvider;
import com.cognodyne.dw.jta.JtaBundle;
import com.cognodyne.dw.jta.ResourceInjectionServiceProvider;
import com.cognodyne.dw.jta.TransactionServiceProvider;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;
import com.palantir.config.crypto.EncryptedConfigValueBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ExampleApplication extends Application<ExampleConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(ExampleApplication.class);
    @Inject
    private CdiBundle           cdiBundle;
    @Inject
    private JtaBundle           jtaBundle;
    @Inject
    private JpaBundle           jpaBundle;

    @Override
    public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
        bootstrap.addBundle(cdiBundle);
        bootstrap.addBundle(jtaBundle);
        bootstrap.addBundle(jpaBundle);
        bootstrap.addBundle(new EncryptedConfigValueBundle());
        bootstrap.getObjectMapper().registerModule(new Hibernate5Module()//
                //.disable(Feature.FORCE_LAZY_LOADING)//
                .enable(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS)//
                );
    }

    @Override
    public void run(ExampleConfiguration configuration, Environment environment) throws Exception {
    }

    public static void main(String... args) {
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(ch.qos.logback.classic.Level.WARN);
        try {
            CdiBundle.application(ExampleApplication.class, args)//
                    .with(ResourceInjectionServiceProvider.getInstance())//
                    .with(TransactionServiceProvider.getInstance())//
                    .with(JpaServiceProvider.getInstance())//
                    .start();
        } catch (Exception e) {
            logger.error("Unable to start the application", e);
        }
    }
}
