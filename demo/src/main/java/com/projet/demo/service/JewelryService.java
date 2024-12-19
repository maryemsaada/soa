package com.projet.demo.service;

import com.projet.demo.Entity.Jewelry;
import com.projet.demo.repository.JewelryRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Path("/api")
public class JewelryService {

    @Autowired
    private JewelryRepository jewelryRepository;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addJewelry(Jewelry jewelry) {
        jewelryRepository.save(jewelry);
        return Response.ok("Jewelry added successfully!").build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteJewelryById(@PathParam("id") Long id) {
        if (jewelryRepository.existsById(id)) {
            jewelryRepository.deleteById(id);
            return Response.ok("Jewelry deleted successfully.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error: Jewelry with ID " + id + " not found.")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJewelry(@PathParam("id") Long id, Jewelry updatedJewelry) {
        Jewelry existingJewelry = jewelryRepository.findById(id).orElse(null);

        if (existingJewelry == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jewelry with ID " + id + " not found.")
                    .build();
        }


        existingJewelry.setName(updatedJewelry.getName());
        existingJewelry.setBrand(updatedJewelry.getBrand());
        existingJewelry.setPrice(updatedJewelry.getPrice());
        existingJewelry.setImage(updatedJewelry.getImage());


        jewelryRepository.save(existingJewelry);

        return Response.ok(existingJewelry).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJewelry() {
        List<Jewelry> allJewelry = jewelryRepository.findAll();
        return Response.ok(allJewelry).build();
    }

    @GET
    @Path("/search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJewelryByName(@PathParam("name") String name) {
        List<Jewelry> jewelry = jewelryRepository.findByName(name);

        if (jewelry.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No jewelry found with the name " + name)
                    .build();
        }

        return Response.ok(jewelry).build();
    }
}
