import com.dao.CustomerDao;
import com.entity.Customer;
import com.resource.CustomerResource;
import com.service.CustomerAuthorizer;
import com.service.CustomerService;
import com.service.CustomerServiceInterface;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class CustomerApplication extends Application<CustomerConfiguration> {
    public static void main(String[] args) throws Exception
    {
        new CustomerApplication().run(args);
    }
    final static HibernateBundle<CustomerConfiguration> hibernateBundle=new HibernateBundle<CustomerConfiguration>(Customer.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(CustomerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<CustomerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(CustomerConfiguration configuration, Environment environment) throws Exception {
        final CustomerDao customerDao = new CustomerDao(hibernateBundle.getSessionFactory());
        final CustomerServiceInterface customerServiceInterface = new CustomerService(customerDao);
        environment.jersey().register(new CustomerResource(customerServiceInterface));
        environment.jersey().register(new AuthDynamicFeature
                (new BasicCredentialAuthFilter.Builder<Customer>()
                        .setAuthenticator(new CustomerAuthentication(customerServiceInterface))
                        .setAuthorizer(new CustomerAuthorizer())
                        .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Customer.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }
}

