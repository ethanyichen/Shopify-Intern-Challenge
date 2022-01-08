/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.webservice.Controller;

import com.example.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.UUID;

import static javax.xml.transform.OutputKeys.MEDIA_TYPE;

@RestController
@Produces({MEDIA_TYPE})
@ComponentScan(basePackages = {"com.example.inventory.service"})
public class ItemController {
    private static final String HEADER_ITEM_ID = "itemID";
    private static final String HEADER_GROUP_ID = "groupID";
    private static final String HEADER_QUANTITY = "quantity";
    private static final String HEADER_NAME = "name";

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/")
    public String hello() {
        return "index";
    }

    // add an item with given name
    @RequestMapping(value = "/item")
    @POST
    public Response addItem(@RequestHeader(value = HEADER_NAME) String name) {
        if(name.length() == 0) {
            return Response.status(400).entity("Name should not be empty").build();
        }
        if(name.length() > 256) {
            return Response.status(401).entity("Name too long").build();
        }
        UUID itemID = itemService.addItem(name);
        return Response.ok().entity("Item " + itemID + " "+ name + " Created").build();
    }

    // assign item with itemID to group with groupID
    @RequestMapping(value = "/group/assign")
    @POST
    public Response assignItemToGroup(@RequestHeader(value = HEADER_ITEM_ID) String itemID, @RequestHeader(value = HEADER_GROUP_ID) String groupID) {
        if(itemID.length() == 0 || groupID.length() == 0) {
            return Response.status(400).entity("ID should not be empty").build();
        }
        UUID itemUUID;
        UUID groupUUID;
        try {
            itemUUID = UUID.fromString(itemID);
            groupUUID = UUID.fromString(groupID);
        } catch (Exception e) {
            return Response.status(400).entity("Please Enter a valid ID").build();
        }
        String res = itemService.assignItemToGroup(itemUUID, groupUUID);
        if(res.equalsIgnoreCase("Group Not Found")) {
            return Response.status(404).entity("Group Not Found").build();
        } else if (res.equalsIgnoreCase("Item Not Found")) {
            return Response.status(404).entity("Item Not Found").build();
        }
        return Response.ok().entity("Item " + itemUUID + " assigned to Group " + groupUUID).build();
    }

    // remove item with itemID from its group
    @RequestMapping(value = "/group/remove")
    @POST
    public Response removeItemFromGroup(@RequestHeader(value = HEADER_ITEM_ID) String itemID) {
        if(itemID.length() == 0) {
            return Response.status(400).entity("ID should not be empty").build();
        }
        UUID itemUUID;
        try {
            itemUUID = UUID.fromString(itemID);
        } catch (Exception e) {
            return Response.status(400).entity("Please Enter a valid ID").build();
        }
        String res = itemService.removeItemFromGroup(itemUUID);
        if (res.equalsIgnoreCase("Item Not Found")) {
            return Response.status(404).entity("Item Not Found").build();
        }
        return Response.ok().entity("Item " + itemUUID + " removed from its group").build();
    }

    // remove Item with itemID
    @DELETE
    @RequestMapping(value = "/item/remove")
    public Response deleteItemById (@RequestHeader(value = HEADER_ITEM_ID) String itemID) {
        if(itemID.length() == 0) {
            return Response.status(400).entity("ID should not be empty").build();
        }
        UUID itemUUID;
        try {
            itemUUID = UUID.fromString(itemID);
        } catch (Exception e) {
            return Response.status(400).entity("Please Enter a valid ID").build();
        }
        itemService.deleteItemById(itemUUID);
        return Response.ok().entity("Item " + itemID +  "Deleted").build();
    }

    // get Item with itemID
    @GET
    @RequestMapping(value = "/item/{itemID}")
    public Response getItemById (@PathVariable String itemID) {
        if(itemID.length() == 0) {
            return Response.status(400).entity("ID should not be empty").build();
        }
        UUID itemUUID;
        try {
            itemUUID = UUID.fromString(itemID);
        } catch (Exception e) {
            return Response.status(400).entity("Please Enter a valid ID").build();
        }
        return Response.ok().entity(itemService.getItemById(itemUUID)).build();
    }

    // change the quantity of the item with ItemID
    @PUT
    @RequestMapping(value = "/item/quantity/{itemID}")
    public Response UpdateItemQuantity (@PathVariable String itemID, @RequestHeader(value = HEADER_QUANTITY) String quantity) {
        if(itemID.length() == 0) {
            return Response.status(400).entity("ID should not be empty").build();
        }
        UUID itemUUID;
        int quantityNum;
        try {
            itemUUID = UUID.fromString(itemID);
            quantityNum = Integer.parseInt(quantity);
            if(quantityNum < 0) {
                return Response.status(400).entity("Please enter a positive quantity").build();
            }
        } catch (Exception e) {
            return Response.status(400).entity("Invalid Input").build();
        }
        String res = itemService.updateItemQuantity(itemUUID, quantityNum);
        if(res.equalsIgnoreCase("Item Not Found")) {
            return Response.status(404).entity("Item Not Found").build();
        }
        return Response.ok().entity(res).build();
    }

    // get a list of all the items
    @GET
    @RequestMapping(value = "/items")
    public Response getItems () {
        return Response.ok().entity(itemService.getItems()).build();
    }
}
