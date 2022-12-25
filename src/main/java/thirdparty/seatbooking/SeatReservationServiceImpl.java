package thirdparty.seatbooking;

public class SeatReservationServiceImpl implements SeatReservationService{

	@Override
	public void reserveSeat(long accountId, int totalSeatsToAllocate) {
		// TODO Auto-generated method stub
		System.out.println("Seat is reserved for account id " + accountId + " Total Seat Allocated are: " + totalSeatsToAllocate);
		
	}
	
	
}