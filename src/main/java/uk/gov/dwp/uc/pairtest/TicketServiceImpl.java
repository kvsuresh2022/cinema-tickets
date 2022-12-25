package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;


public class TicketServiceImpl implements TicketService {
	private static final int MAX_TICKETS = 20;
    private static final int INFANT_PRICE = 0;
    private static final int CHILD_PRICE = 10;
    private static final int ADULT_PRICE = 20;

    /**
     * Should only have private methods other than the one below.
     */
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
    	TicketPaymentServiceImpl payment=new TicketPaymentServiceImpl();
    	SeatReservationServiceImpl reserve=new SeatReservationServiceImpl();
    	int numInfant = 0;
        int numChild = 0;
        int numAdult = 0;
        for (TicketTypeRequest request : ticketTypeRequests) {
            switch (request.getTicketType()) {
                case INFANT:
                    numInfant += request.getNoOfTickets();
                    break;
                case CHILD:
                    numChild += request.getNoOfTickets();
                    break;
                case ADULT:
                    numAdult += request.getNoOfTickets();
                    break;
            }
        }
        if (numInfant + numChild + numAdult > MAX_TICKETS) {
            throw new IllegalArgumentException("Cannot purchase more than " + MAX_TICKETS + " tickets at a time");
        }
        if (numChild > 0 && numAdult == 0) {
            throw new IllegalArgumentException(
                    "Cannot purchase child tickets without purchasing at least one adult ticket");
        }
        if (numInfant > 0 && numAdult == 0) {
            throw new IllegalArgumentException(
                    "Cannot purchase infant tickets without purchasing at least one adult ticket");
        }
        if (numInfant + numChild + numAdult == 0) {
            throw new IllegalArgumentException("Must purchase at least one ticket");
        }

        // Calculate the total cost of the tickets
        int totalCost = numInfant * INFANT_PRICE + numChild * CHILD_PRICE + numAdult * ADULT_PRICE;
        System.out.println("Cost:" + totalCost);
        int numSeats = numChild + numAdult;
        payment.makePayment(accountId,totalCost);
        System.out.println("Seat:" + numSeats);
        reserve.reserveSeat(accountId,numSeats);
        
        
    }

/*    public int calculateTicketAmount(TicketTypeRequest... ticketTypeRequests) {
        int totalPayableAmount = 0;
        for (TicketTypeRequest ticketTypeRequest : ticketTypeRequests) {
            switch (ticketTypeRequest.getTicketType()) {
                case ADULT:
                    totalPayableAmount = totalPayableAmount + 20;
                    break;
                case CHILD:
                    totalPayableAmount = totalPayableAmount + 10;
                    break;
                default:
                    break;
            }
        }
        return totalPayableAmount;
    }*/

}
