package support.verifycation;

public class Verifier {

    public static void verifyEquals(String actual, String expect){

if(!actual.equals(expect))
    throw new AssertionError("actual is different with expect");
    }
}
