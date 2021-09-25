package bases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserTest {


    User user = mock(User.class);

    UserBasket basketAction = new UserBasket(user);


    @Test
    public void createUser() {
        Assertions.assertNotNull(user);
    }


    @Test
    public void getName() {
        basketAction.checkBasket();
        verify(user, times(1)).getName();
    }

}