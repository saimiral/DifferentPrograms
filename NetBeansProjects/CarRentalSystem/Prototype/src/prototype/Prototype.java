package prototype;

public class Prototype {

    public static void main(String[] args) {
        ReservationChecker checker = new ReservationChecker();
        checker.checkAndUpdateReservations();
        LoginSignUpFrame lsp = new LoginSignUpFrame();
	lsp.init();
    }
    
}
