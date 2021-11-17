import tmp.BadI;
import tmp.GoodI;
import tmp.II;

public class Main {

    MyInterfaceException myInterfaceException = new MyInterfaceException();
    NonInterfaceException nonInterfaceException = new NonInterfaceException();

    /**
     * Analysis misses the problem that this will cause a ClassCastException
     * for a non-null value.
     */
    public void analysisSeesWrongClass() {
        ExceptionInterface.handleException(myInterfaceException);

        // This should be a warning
        ExceptionInterface.handleException((ExceptionInterface) nonInterfaceException);
    }

    public void makeNonInterfaceException() throws NonInterfaceException {
        throw new NonInterfaceException();
    }

    /**
     * Analysis misses the problem that this will cause a ClassCastException
     * for a non-null value.
     */
    public void alsoMissesClassCastProblem() {

        ExceptionInterface.handleException(myInterfaceException);

        try {
            makeNonInterfaceException();
        } catch (NonInterfaceException e) {
            // This should be a warning
            ExceptionInterface.handleException((ExceptionInterface) e);
        }
    }

    /**
     * Analysis correctly reports this will cause a ClassCastException
     * for a non-null value.
     */
    public void thisSeesClassCastProblem() {
        ExceptionInterface.handleException(new MyInterfaceException());

        // This warning is accurate
        ExceptionInterface.handleException((ExceptionInterface) new NonInterfaceException());
    }

    public BadI getBadInterface() {
        return new BadI();
    }

    public static void main(String[] args) {

        Main main = new Main();

        try {
            main.analysisSeesWrongClass();
        } catch (ClassCastException c) {
            // we're causing these for a reason!
            System.out.println("CCE: " + c.getMessage());
        }
        try {
            main.alsoMissesClassCastProblem();
        } catch (ClassCastException c) {
            // we're causing these for a reason!
            System.out.println("CCE: " + c.getMessage());
        }
        try {
            main.thisSeesClassCastProblem();
        } catch (ClassCastException c) {
            // we're causing these for a reason!
            System.out.println("CCE: " + c.getMessage());
        }


        GoodI good = new GoodI();
        II.useII(good);
        BadI bad = new BadI();
        try {
            // This warning is accurate
            II.useII((II) bad);
        } catch (ClassCastException c) {
            System.out.println("CCE: " + c.getMessage());
        }
        try {
            // This could warn about a cast?
            II.useII((II) main.getBadInterface());
        } catch (ClassCastException c) {
            System.out.println("CCE: " + c.getMessage());
        }
    }
}
