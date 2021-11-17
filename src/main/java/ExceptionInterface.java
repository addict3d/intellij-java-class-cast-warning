public interface ExceptionInterface {

    String getFooFromInterface();

    public static void handleException(ExceptionInterface e) {
        System.out.println("Handling " + e.getFooFromInterface());
    }
}
