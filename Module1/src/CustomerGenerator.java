public class CustomerGenerator {
    public CustomerGenerator(SerrvicePoint serrvicePoint) {
        for (int i = 0; i < 10; i++) {
            serrvicePoint.addCustomer(new Customer());
        }
    }

}
