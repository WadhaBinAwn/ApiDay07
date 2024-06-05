package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.JobDAO;
import org.example.dto.JobsDto;
import org.example.dto.JobsFilterDto;
import org.example.exceptions.DataNotFoundException;
import org.example.models.Jobs;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/jobs")
public class JobController {

   JobDAO dao = new JobDAO();

    @Context
    private UriInfo uriInfo;

    @Context
    private HttpHeaders headers;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllJobs(@BeanParam JobsFilterDto filterDto) {
        try {
            GenericEntity<ArrayList<Jobs>> jobs = new GenericEntity<ArrayList<Jobs>>(dao.selectAllJobs(filterDto)) {};
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(jobs)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }

            return Response
                    .ok(jobs, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{jobId}")
    public Response getJob(@PathParam("jobId") int jobId) throws SQLException {

        try {
            Jobs job = dao.selectJob(jobId);

            if (job == null) {
                throw new DataNotFoundException("Job with ID " + jobId + " not found");
            }
            JobsDto dto = new JobsDto();
            dto.setJobId(job.getJob_id());
            dto.setMaxSalary(job.getMax_salary());
            dto.setMinSalary(job.getMin_salary());
            dto.setJob_title(job.getJob_title());
            addLink(dto);

            return Response.ok(dto).build();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private  void addLink(JobsDto dto){

        URI selfUri = uriInfo.getAbsolutePath();
        dto.addLink(selfUri.toString(), "self");
    }

    @DELETE
    @Path("{jobId}")
    public void deleteJob(@PathParam("jobId") int jobId) {

        try {
            dao.deleteJob(jobId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response insertJob(Jobs job) {
        try {
            dao.insertJob(job);
            NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(job.getJob_id()+"").build();
            return Response
                    .created(uri)
                    .cookie(cookie)
                    .header("Created by", "Wadha")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{jobId}")
    public void updateJob(@PathParam("jobId") int jobId, Jobs job) {

        try {
            job.setJob_id(jobId);
            dao.updateJob(job);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
