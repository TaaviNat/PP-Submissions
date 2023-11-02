public class CustomerGenerator {
    public CustomerGenerator(ServicePoint servicePoint) {
        for (int i = 0; i < 10; i++) {
            servicePoint.addCustomer(new Customer());
        }
    }

}
