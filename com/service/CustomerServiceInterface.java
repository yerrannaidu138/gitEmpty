package com.service;

import com.entity.Customer;
import java.util.List;

public interface CustomerServiceInterface {

    public Customer getEntity(int id);
    public Customer saveEntity(Customer entity);
    public void deleteEntity(Customer c);
    public String logInCustomer(int id,String pwd);
    public Customer updateCustomerName(int id,String name);
    public Customer updateCustomerAge(int id,int age);
    public Customer updateCustomerSal(int id,int sal);
    public Customer updateCustomerPassword(int id,String pwd);
    public Customer updateCustomerAddress(int id,String address);

    public Customer retrieveData(String name);
    public List<Customer> retrieveData1(String name,int limit,int offSet);
    public PaginatedData getCustomers(String name, int page, int pageSize);
    public PaginatedData getCustomers1(String name, int page, int pageSize);
    public Customer getCustomerByPassword(String pwd,String name);
}
