package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.validation.ValidationMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import java.security.Principal;

@Entity
@Table(name = "customer")
public class Customer implements Principal {
    @Id
    @GeneratedValue
    private int customerId;

    @NotNull
    @NotEmpty(message = "customer name should not be empty")
    @NotBlank
    private String customerName;
    @JsonProperty("Age")
    private int customerAge;
    private int customerSal;

//    @Min(2)
    private String customerAddress;
    private String customerLogInPwd;

//    @ValidationMethod(message = "the customer name is already there")
//    public boolean isRepeat()
//    {
//        boolean isRepeat=false;
//
//        if(getCustomerName().equals(customerName))
//        {
//
//        }
//        return
//    }

    public String getCustomerLogInPwd() {
        return customerLogInPwd;
    }

    public void setCustomerLogInPwd(String customerLogInPwd) {
        this.customerLogInPwd = customerLogInPwd;
    }
    @ValidationMethod(message = "should not be abc")
    @JsonIgnore
    public boolean isNotEmpty()
    {
        return StringUtils.isNotEmpty(customerAddress) && !"abc".equals(customerLogInPwd);
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

//    @ValidationMethod(message = "the value should not be null ")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public int getCustomerSal() {
        return customerSal;
    }

    public void setCustomerSal(int customerSal) {
        this.customerSal = customerSal;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Customer()
    {

    }

    @JsonIgnore
    @Override
    public String getName() {
        return null;
    }
}
