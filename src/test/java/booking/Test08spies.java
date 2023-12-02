package booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Test08spies {
@InjectMocks
private BookingService bookingService;
@Mock
private PaymentService paymentService;
@Spy
private BookingDAO bookingDAO;
@Mock
private RoomService roomService;
@Mock
private MailSender mailSender;

//@BeforeEach
//    void setup(){
//    this.paymentService=mock(PaymentService.class);
//    this.mailSender=mock(MailSender.class);
//    this.bookingDAO=mock(BookingDAO.class);
//    this.roomService=mock(RoomService.class);
//    this.bookingService=new BookingService(paymentService,roomService,bookingDAO,mailSender);
    //roomservice.getavilablerooms()->emptylist,roomservice.findavilablerromid()->null,roomservice.getromcount()->0
//}
    @Test
   void shouldcalulatecorrectvalue(){
    //given
        BookingRequest bookingRequest=new BookingRequest("1", LocalDate.of(2020,01,01),LocalDate.of(2020,01,05),
                2,false);
        double epected=4*2*50.0;//here final return value we calculating
    //when
       double nights=bookingService.calculatePrice(bookingRequest);

       //then
       // Assertions.assertEquals(bookingRequest.getGuestCount(),2);
        //Assertions.assertNotNull(nights);
        assertEquals(epected,nights);
   }
    @Test
   void shouldinvokepaymentwhenprepaidwithspy(){
        //given
        BookingRequest bookingRequest=new BookingRequest("1", LocalDate.of(2020,01,01),LocalDate.of(2020,01,05),
                2,true);
        //when
       String id= bookingService.makeBooking(bookingRequest);
        //then
        verify(bookingDAO).save(bookingRequest);
        System.out.println("bookingid="+ id);
   }

    @Test
    void shouldcancelbookingwheniputokwithspy(){
        //given
        BookingRequest bookingRequest=new BookingRequest("1", LocalDate.of(2020,01,01),LocalDate.of(2020,01,05),
                2,true);
        bookingRequest.setRoomId("1.3");
        String bookingId="1";
        Mockito.doReturn(bookingRequest).when(bookingDAO).get(bookingId);

        //when
         bookingService.cancelBooking(bookingId);
        //then

    }



}
