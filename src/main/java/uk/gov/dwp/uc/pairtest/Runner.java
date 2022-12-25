/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import java.util.Scanner;


/**
 *
 * @author Wings
 */
public class Runner {
    public static void main(String args[]) {
    	TicketService ticketService = new TicketServiceImpl();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the account ID: ");
            long accountId = scanner.nextInt();

            // Create a list of ticket type requests
            TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[3];

            // Read the number of infant tickets
            System.out.println("Enter the number of infant tickets: ");
            int numInfant = scanner.nextInt();
            ticketTypeRequests[0] = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, numInfant);

            // Read the number of child tickets
            System.out.println("Enter the number of child tickets: ");
            int numChild = scanner.nextInt();
            ticketTypeRequests[1] = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, numChild);

            // Read the number of adult tickets
            System.out.println("Enter the number of adult tickets: ");
            int numAdult = scanner.nextInt();
            ticketTypeRequests[2] = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, numAdult);
            try {
                // Purchase the tickets
                ticketService.purchaseTickets(accountId, ticketTypeRequests);
                //ticketService.calculateTicketAmount(ticketTypeRequests);
                System.out.println("Tickets purchased successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
