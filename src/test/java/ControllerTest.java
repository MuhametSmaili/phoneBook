import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import controller.Controller;


import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ControllerTest {

    @BeforeClass
    public static void initToolkit()
            throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });

        // That's a pretty reasonable delay... Right?
        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }

    @Test
    public void checkTextFieldInputTrue() throws InterruptedException {
        Controller controller = new Controller();
        initToolkit();
        Method method = null;
        try {
            method = Controller.class.getDeclaredMethod("checkTheInputs", TextField.class,TextField.class,TextField.class, Label.class);
            method.setAccessible(true);

            Boolean output = (Boolean) method.invoke(controller,new TextField(""),new TextField(""),new TextField(""),new Label());
            Assert.assertThat(output, Is.is(false));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
