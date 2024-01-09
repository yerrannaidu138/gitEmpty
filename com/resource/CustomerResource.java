package com.resource;
import com.entity.Customer;
import com.exception.NotFoundException;
import com.service.CustomerServiceInterface;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private  final CustomerServiceInterface customerServiceInterface;

    public CustomerResource(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    @GET
    @Path("/get")
    @UnitOfWork
    public Customer getCustomer(@QueryParam("id") @Min(1) int id)
    {
        Customer customer=customerServiceInterface.getEntity(id);
        if(customer==null)
        {
            throw new NotFoundException("not found with this id "+id);
        }
        return customer;
    }

    @POST
    @Path("/save")
    @UnitOfWork
    public Customer save(@Valid Customer l)
    {
        return customerServiceInterface.saveEntity(l);
    }
    @PUT
    @Path("/update")
    @UnitOfWork
    public Customer updateCustomer(Customer l)
    {
        return customerServiceInterface.saveEntity(l);
    }
    @DELETE
    @Path("/delete")
    @UnitOfWork
    public void deleteCustomer(@QueryParam("id") int id)
    {
        Customer l1=getCustomer(id);
        if(l1!=null)
        {
            customerServiceInterface.deleteEntity(l1);
        }
    }

    @GET
    @Path("/logIn")
    @UnitOfWork
    public String logIn(@QueryParam("id") int id,@QueryParam("pwd") String pwd) {
        return customerServiceInterface.logInCustomer(id,pwd);
    }

    @PUT
    @Path("/updateCustomerName")
    @UnitOfWork
    public Customer updateCustomerName(@QueryParam("id")int id,@QueryParam("name")String name)
    {
        return customerServiceInterface.updateCustomerName(id,name);
    }
    @PUT
    @Path("/updateCustomerAge")
    @UnitOfWork
    public Customer updateCustomerAge(@QueryParam("id")int id,@QueryParam("age")int age)
    {
        return customerServiceInterface.updateCustomerAge(id,age);
    }
    @PUT
    @Path("/updateCustomerSal")
    @UnitOfWork
    public Customer updateCustomerSal(@QueryParam("id")int id,@QueryParam("sal")int sal)
    {
        return customerServiceInterface.updateCustomerSal(id,sal);
    }
    @PUT
    @Path("/updateCustomerAddress")
    @UnitOfWork
    public Customer updateCustomerAddress(@QueryParam("id")int id,@QueryParam("address")String address)
    {
        return customerServiceInterface.updateCustomerAddress(id,address);
    }
    @PUT
    @Path("/updateCustomerPassword")
    @UnitOfWork
    public Customer updateCustomerPassword(@QueryParam("id")int id,@QueryParam("pwd")String pwd)
    {
        return customerServiceInterface.updateCustomerPassword(id,pwd);
    }

    @GET
    @Path("/retrieve")
    @UnitOfWork
    public List<Customer> retrieveCustomerList(@QueryParam("name") @NotEmpty String name, @QueryParam("limit")int limit, @QueryParam("offSet")int offSet){
//        System.out.println(customerServiceInterface.getCustomerByPassword("gaya3124"));
       return customerServiceInterface.retrieveData1(name,limit,offSet);
    }

    @GET
    @Path("/getResponse")
    @UnitOfWork
    @RolesAllowed("gayatri")
    public Response getResponse(@Auth Customer customer)
    {
        return Response.ok(customer).build();
    }
}
