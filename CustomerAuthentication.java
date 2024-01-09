import com.entity.Customer;
import com.service.CustomerServiceInterface;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import jakarta.ws.rs.ForbiddenException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.Optional;

public class CustomerAuthentication implements Authenticator<BasicCredentials,Customer> {

    private final CustomerServiceInterface customerServiceInterface;

    public CustomerAuthentication(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }
    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Override
    public Optional<Customer> authenticate(BasicCredentials credentials) throws AuthenticationException {

        Session session=CustomerApplication.hibernateBundle.getSessionFactory().openSession();
        NativeQuery<Customer> query= session.createNativeQuery("select * from customer where customername = :name and customerLogInPwd = :pwd",Customer.class)
                .setParameter("name",credentials.getUsername())
                .setParameter("pwd",credentials.getPassword());

        Optional<Customer> customer= query.uniqueResultOptional();

//       Customer customer=customerServiceInterface.getCustomerByPassword(credentials.getPassword(),credentials.getUsername());
        if(customer.isPresent())
        {
            String name=customer.get().getCustomerName();
            String password=customer.get().getCustomerLogInPwd();
            if(name.equals(credentials.getUsername()) && password.equals(credentials.getPassword()))
            {

                return customer;
            }
            return Optional.empty();
        }
        else {
            throw new ForbiddenException("username or password is invalid");
        }
    }
}
