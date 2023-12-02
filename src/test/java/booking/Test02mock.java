package booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Test02mock {
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
void shouldcountavailableplaceswhenoneroomavailable(){
    //given
    when(roomService.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("1",2)));
    int expected =2;
    //when
    int actual=bookingService.getAvailablePlaceCount();
    //then
   //assertEquals(2,actual);
   assertEquals(expected,actual);
}

@Test
void shouldcountavilableplaceswhenmultipleroomsavilable(){
    List<Room>rooms= Arrays.asList(new Room("1",2),new Room("2",4));
    //given
    when(roomService.getAvailableRooms()).thenReturn(rooms);
    int expected =6;
    //when
    int actual=bookingService.getAvailablePlaceCount();
    //then
    //assertEquals(2,actual);
    assertEquals(expected,actual);

}

}
