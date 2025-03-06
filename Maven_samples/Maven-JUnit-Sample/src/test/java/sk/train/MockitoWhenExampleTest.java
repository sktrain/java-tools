package sk.train;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MockitoWhenExampleTest {

    @Mock
    Iterator<String> i;

    Comparable<String> c;

    // demonstrates the return of multiple values
    @Test
    void testMoreThanOneReturnValue() {
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result = i.next() ;
        // assert
        //assertEquals("Mockito rocks", result);
        System.out.println(result);
    }

    // this test demonstrates how to return values based on the input
    // and that @Mock can also be used for a method parameter
    @Test
    void testReturnValueDependentOnMethodParameter(@Mock Comparable<String> c)  {
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("Eclipse")).thenReturn(2);
        //assert
        assertEquals(1, c.compareTo("Mockito"));
        assertEquals(2, c.compareTo("Eclipse"));
        System.out.println(c.compareTo("Hallo"));
    }

    // return a value based on the type of the provide parameter

    @Test
    void testReturnValueInDependentOnMethodParameter2(@Mock Comparable<Integer> c )  {
        when(c.compareTo(isA(Integer.class))).thenReturn(0);
        //assert
        assertEquals(0, c.compareTo(Integer.valueOf(4711)));
    }

    @Test
    void testMockitoThrows() {
        Properties properties = Mockito.mock(Properties.class);

        when(properties.get(anyString())).thenThrow(new IllegalArgumentException("Stuff"));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> properties.get("A"));

        assertEquals("Stuff", exception.getMessage());
    }


    @Test
    void ensureSpyForListWorks() {
        var list = new ArrayList<String>();
        var spiedList = spy(list);

        doReturn("42").when(spiedList).get(99);
        //when(spiedList.get(99)).thenReturn("42");
        String value = (String) spiedList.get(99);

        assertEquals("42", value);
    }

    @Mock
    AudioManager audioManager;


    @Test
    void ensureThatMaximizeVolumeUseConfiguredValueFromAudiomanager() {
        // 1. script mock behavior.
        when(audioManager.getRingerMode()).thenReturn(RINGER_MODE.RINGER_MODE_NORMAL);
        when(audioManager.getStreamMaxVolume()).thenReturn(100);

        // 2. Test the code of interest.

        VolumeUtil.maximizeVolume(audioManager);

        // 3. Validate that we saw exactly what we wanted.
        verify(audioManager).setStreamVolume(100);
    }

    @Test
    void ensureSilentModeWillNotSetVolumeIsNotDisturbed() {
        // 1. script mock behavior.

        when(audioManager.getRingerMode()).thenReturn(RINGER_MODE.RINGER_MODE_SILENT);
        // 2. Test the code of interest.
        VolumeUtil.maximizeVolume(audioManager);
        // 3. Validate that we saw exactly what we wanted.
        verify(audioManager).getRingerMode();
        verifyNoMoreInteractions(audioManager);
    }

}