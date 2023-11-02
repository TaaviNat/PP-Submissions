public class Main {
    public static void main(String[] args) {
        System.out.println("Basic Queue/ServicePoint simulator");
        ServicePoint servicePoint = new ServicePoint();

        new CustomerGenerator(servicePoint);
        servicePoint.serve();

        System.out.println("Average service time " + servicePoint.avgServiceTime());

    }
}