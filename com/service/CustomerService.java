package com.service;

import com.dao.CustomerDao;
import com.entity.Customer;
import com.exception.AuthorizationException;
import com.exception.NotFoundException;

import java.util.List;

public class CustomerService implements CustomerServiceInterface{

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer getEntity(int id)
    {
        return customerDao.getEntity(id);
    }
    @Override
    public Customer saveEntity(Customer entity)
    {
        return customerDao.saveEntity(entity);
    }
    @Override
    public void deleteEntity(Customer c)
    {
        customerDao.deleteEntity(c);
    }
    @Override
    public String logInCustomer(int id,String pwd) {
        Customer customer=getEntity(id);
        if(customer!=null)
        {
            if(id==customer.getCustomerId() && pwd.equalsIgnoreCase(customer.getCustomerLogInPwd()))
            {
                return "successfully";
            }
            else
            {
                throw new AuthorizationException("Authorization error","the password and id are not matched or credentials are may be wrong");
            }
        }
        else
        {
            throw new NotFoundException("not found with id "+id);
        }
    }
    @Override
    public Customer updateCustomerName(int id,String name)
    {
        return customerDao.updateCustomerName(id,name);
    }
    @Override
    public Customer updateCustomerAge(int id,int age)
    {
        return customerDao.updateCustomerAge(id,age);
    }
    @Override
    public Customer updateCustomerSal(int id,int sal)
    {
        return customerDao.updateCustomerSal(id,sal);
    }
    @Override
    public Customer updateCustomerPassword(int id,String pwd)
    {
        return customerDao.updateCustomerPassword(id,pwd);
    }
    @Override
    public Customer updateCustomerAddress(int id,String address)
    {
        return customerDao.updateCustomerAddress(id,address);
    }
    @Override
    public PaginatedData getCustomers(String name, int page, int pageSize) {
        List<Customer> filteredCustomers = retrieveData1(name,pageSize,page);

        int totalItems = filteredCustomers.size();
        int totalPages = (int) Math.ceil((double) totalItems /pageSize);

        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize,filteredCustomers.size());

        List<Customer> paginatedItems = filteredCustomers.subList(startIndex, endIndex);;

        PaginationMetadata metadata = new PaginationMetadata(page, totalPages);

        // Construct links for next and previous pages
        if (page < totalPages - 1) {
            metadata.setNextLink("/customer/retrieve?name="+name+"&page=" + (page + 1) + "&pageSize=" + pageSize);
        }
        if (page > 0) {
            metadata.setPrevLink("/customer/retrieve?name="+name+"&page=" + (page - 1) + "&pageSize=" + pageSize);
        }
        return new PaginatedData(paginatedItems, metadata);
    }
    @Override
    public Customer retrieveData(String name) {
        return customerDao.retrieveCustomer(name);
    }
    @Override
    public PaginatedData getCustomers1(String name, int limit, int offSet) {
        List<Customer> filteredCustomers = retrieveData1(name,limit,offSet);

        int totalItems = filteredCustomers.size();
        int totalPages = (int) Math.ceil((double) totalItems / limit);

        PaginationMetadata metadata = new PaginationMetadata(offSet, totalPages);

        // Construct links for next and previous pages
        if (offSet < totalPages - 1) {
            metadata.setNextLink("/customer/retrieve?name=" + name + "&page=" + (offSet + 1) + "&pageSize=" + limit);
        }
        if (offSet > 0) {
            metadata.setPrevLink("/customer/retrieve?name=" + name + "&page=" + (offSet - 1) + "&pageSize=" + limit);
        }
        return new PaginatedData(filteredCustomers, metadata);
    }

    @Override
    public Customer getCustomerByPassword(String pwd,String name) {
        return customerDao.getCustomerByPassWord(pwd,name);
    }

    @Override
    public List<Customer> retrieveData1(String name,int limit,int offSet) {
        return customerDao.retrieveCustomer1(name,limit,offSet);
    }
}
