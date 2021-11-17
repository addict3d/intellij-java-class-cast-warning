package tmp;

public interface II {
    Integer getIt();

    public static void useII(II number) {
        System.out.println("Number: " + number.getIt());
    }
}
