package jna.windows;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

/**
 * event call interface
 */
public interface CallbackEvent extends StdCallLibrary.StdCallCallback {
    void callback(WinDef.HWND hWnd, Pointer pUser);
}
