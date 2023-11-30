public class Main {
    public static void main(String[] args) {
        System.out.println("Basic Queue/ServicePoint simulator");
        SerrvicePoint serrvicePoint = new SerrvicePoint();

        new CustomerGenerator(serrvicePoint);
        serrvicePoint.serve();

        System.out.println("Average service time " + serrvicePoint.avgServiceTime());

    }
}