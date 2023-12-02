package booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Test023mockmultiplereturn {
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
void shouldreturnmultiplecallwithdifferentvalues(){
    //given
    List<Room>rooms=Arrays.asList(new Room("1",0));
    when(roomService.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("1",2))).thenReturn(
          rooms//collections.emptylist
    );
    int firstexpected =2;
    int secondexpected=0;
    //when
    int actual=bookingService.getAvailablePlaceCount();
    int second=bookingService.getAvailablePlaceCount();
    //then
   //assertEquals(2,actual);
   assertAll(()->assertEquals(firstexpected,actual),
           ()->assertEquals(secondexpected,second));
}



}
