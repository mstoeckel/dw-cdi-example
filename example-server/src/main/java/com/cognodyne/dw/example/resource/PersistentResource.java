package com.cognodyne.dw.example.resource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognodyne.dw.example.api.model.Persistent;
import com.cognodyne.dw.example.service.PersistentService;

public class PersistentResource implements com.cognodyne.dw.example.api.service.PersistentService {
    private static final Logger logger = LoggerFactory.getLogger(PersistentResource.class);
    @Inject
    private PersistentService   service;

    @Override
    public <T extends Persistent> List<T> get(String type) {
        try {
            return service.findAll(toClass(type));
        } catch (ClassNotFoundException e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public <T extends Persistent> Optional<T> get(String type, Long id) {
        try {
            return service.find(toClass(type), id);
        } catch (ClassNotFoundException e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public <T extends Persistent> T create(T obj) {
        logger.debug("Creating {}", obj);
        return service.create(obj);
    }

    @Override
    public <T extends Persistent> List<T> create(List<T> objs) {
        if (objs != null) {
            objs.stream().map(obj -> create(obj)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    private <T extends Persistent> Class<T> toClass(String type) throws ClassNotFoundException {
        return (Class<T>) Class.forName(type);
    }
}
