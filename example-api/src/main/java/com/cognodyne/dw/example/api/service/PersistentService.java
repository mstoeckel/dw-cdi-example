package com.cognodyne.dw.example.api.service;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.cognodyne.dw.example.api.model.Persistent;

@Path("/persistent")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PersistentService {
    @GET
    @Path("/{type}")
    @Timed
    public <T extends Persistent> List<T> get(@PathParam("type") String type);

    @GET
    @Path("/{type}/{id}")
    @Timed
    public <T extends Persistent> Optional<T> get(@PathParam("type") String type, @PathParam("id") Long id);

    @PUT
    @Timed
    public <T extends Persistent> T create(T obj);

    @PUT
    @Path("/all")
    @Timed
    public <T extends Persistent> List<T> create(List<T> objs);
}
