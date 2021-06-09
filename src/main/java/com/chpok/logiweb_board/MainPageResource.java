package com.chpok.logiweb_board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/board")
public class MainPageResource {

    @GET
    @Produces("text/html")
    public Response hello(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/mainPage.xhtml");

        return Response.status(Response.Status.ACCEPTED).build();
    }

}