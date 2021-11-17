public class MyInterfaceException extends Exception implements ExceptionInterface{
    @Override
    public String getFooFromInterface() {
        return "foo";
    }
}
