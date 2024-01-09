package com.dao;

import com.entity.Customer;
import com.exception.DuplicateException;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.ForbiddenException;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class CustomerDao extends AbstractDAO<Customer> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Customer getEntity(int id)
    {
        return get(id);
    }
    public Customer saveEntity(Customer entity)
    {
        boolean isRepeat=false;
        try{
            Customer customer=retrieveCustomer(entity.getCustomerName());
            if(customer.isNotEmpty())
            {
                throw new DuplicateException("the user name is already there");
            }
        }
        catch(NullPointerException e)
        {
            isRepeat=true;
        }
        if(isRepeat)
        {
            return persist(entity);
        }
        else {
            throw new DuplicateException("the user name is already there");
        }
    }
    public void deleteEntity(Customer l)
    {
        currentSession().remove(l);
    }
    public Customer updateCustomerName(int id,String name){
        Customer c=getEntity(id);
        c.setCustomerName(name);
        return c;
    }
    public Customer updateCustomerAge(int id,int age){
        Customer c=getEntity(id);
        c.setCustomerAge(age);
        return c;
    }
    public Customer updateCustomerSal(int id,int sal){
        Customer c=getEntity(id);
        c.setCustomerSal(sal);
        return c;
    }
    public Customer updateCustomerAddress(int id,String address){
        Customer c=getEntity(id);
        c.setCustomerAddress(address);
        return c;
    }
    public Customer updateCustomerPassword(int id,String pwd){
        Customer c=getEntity(id);
        c.setCustomerLogInPwd(pwd);
        return c;
    }
    public Customer retrieveCustomer(String name)
    {
        NativeQuery<Customer> query =currentSession()
                .createNativeQuery("select * from customer where customername='"+name+"'",Customer.class);
        return query.uniqueResult();
    }
    public List<Customer> retrieveCustomer1(String name,int limit,int offSet)
    {
        NativeQuery<Customer> query =currentSession().createNativeQuery("select * from customer where customername like :name order By customerid asc limit :limit offset :offSet",Customer.class);
        query.setParameter("name",name);
        query.setParameter("limit",limit);
        query.setParameter("offSet",offSet);
        return query.list();
    }

    @UnitOfWork
    public Customer getCustomerByPassWord(String pwd,String name)
    {
        NativeQuery<Customer> query =currentSession()
                .createNativeQuery("select * from customer where customerloginpwd = :pwd and customername = :name",Customer.class)
                .setParameter("pwd",pwd).setParameter("name",name);
        return query.uniqueResult();
    }
}
