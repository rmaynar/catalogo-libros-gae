package com.test.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/hello")
@Produces("application/json;charset=utf-8")
@Api(value = "Hello Jersey", description = "Api jersey")
public class HelloRest {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation("Imprime Hola")
	public String sayPlainTextHello(){
		return "Hello jersey";
	}
	
}
