/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.webservice.Controller;

import com.example.inventory.service.GroupService;
import com.example.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.xml.transform.OutputKeys.MEDIA_TYPE;

@RestController
@Produces({MEDIA_TYPE})
@ComponentScan(basePackages = {"com.example.inventory.service"})
public class GroupController {
    private static final String HEADER_NAME = "name";

    @Autowired
    private GroupService groupService;

    // Add a group
    @RequestMapping(value = "/group")
    @POST
    public Response addGroup(@RequestHeader(value = HEADER_NAME) String name) {
        if(name.length() == 0) {
            return Response.status(400).entity("Name should not be empty").build();
        }
        if(name.length() > 256) {
            return Response.status(401).entity("Name too long").build();
        }
        String groupID = groupService.addGroup(name);
        return Response.ok().entity("Group " + groupID + " "+ name + " Created").build();
    }


    // Get a group by groupID
    @GET
    @RequestMapping(value = "/group/{groupID}")
    public Response getGroupById (@PathVariable String groupID) {
        if(groupID.length() == 0) {
            return Response.status(400).entity("ID should not be empty").build();
        }
        UUID groupUUID;
        try {
            groupUUID = UUID.fromString(groupID);
        } catch (Exception e) {
            return Response.status(400).entity("Please Enter a valid ID").build();
        }
        return Response.ok().entity(groupService.getGroupByID(groupUUID)).build();
    }

    // Get a group members by groupID
    @GET
    @RequestMapping(value = "/group/members/{groupID}")
    public Response showMembers (@PathVariable String groupID) {
        if(groupID.length() == 0) {
            return Response.status(400).entity("ID should not be empty").build();
        }
        UUID groupUUID;
        try {
            groupUUID = UUID.fromString(groupID);
        } catch (Exception e) {
            return Response.status(400).entity("Please Enter a valid ID").build();
        }

        return Response.ok().entity(groupService.showMembers(groupUUID)).build();
    }
}
