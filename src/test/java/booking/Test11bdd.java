package booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Test11bdd {
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
void shouldcountavailableplaceswhenoneroomavailable(){//bdd style
    //given
    given(roomService.getAvailableRooms()).willReturn(Collections.singletonList(new Room("1",2)));
    int expected =2;
    //when
    int actual=bookingService.getAvailablePlaceCount();
    //then
    //assertEquals(2,actual);
    assertEquals(expected,actual);
}

}
