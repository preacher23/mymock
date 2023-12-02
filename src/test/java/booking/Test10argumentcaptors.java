package booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Test10argumentcaptors {
@InjectMocks
private BookingService bookingService;
@Mock
private PaymentService paymentService;
@Mock
private BookingDAO bookingDAO;
@Mock
private RoomService roomService;
@Mock
private MailSender mailSender;

private ArgumentCaptor<Double>captor ;

@BeforeEach
void setup(){
    this.captor=ArgumentCaptor.forClass(Double.class);
}

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
   void shouldusedcaptorwhenprepaid(){
        //given
        BookingRequest bookingRequest=new BookingRequest("1", LocalDate.of(2020,01,01),LocalDate.of(2020,01,05),
                2,true);
        //when
        bookingService.makeBooking(bookingRequest);
        //then
        verify(paymentService,times(1)).pay(eq(bookingRequest),captor.capture());
        Double value = captor.getValue();
        System.out.println(value);
    }



}
