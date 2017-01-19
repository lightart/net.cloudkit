package jna.windows;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface User32  extends StdCallLibrary {

    /**
     * The instance.
     */
    User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

    WinDef.HWND FindWindow(String lpClassName, String lpWindowName);

    /*
    LRESULT WINAPI SendMessage(
      _In_ HWND   hWnd,
      _In_ UINT   Msg,
      _In_ WPARAM wParam,
      _In_ LPARAM lParam
    );
    */
    int SendMessage(WinDef.HWND hwnd, int msg, long wParam, long lParam);

    /*
    LRESULT WINAPI SendMessageTimeout(
      _In_      HWND       hWnd,
      _In_      UINT       Msg,
      _In_      WPARAM     wParam,
      _In_      LPARAM     lParam,
      _In_      UINT       fuFlags,
      _In_      UINT       uTimeout,
      _Out_opt_ PDWORD_PTR lpdwResult
    );
    */
    long SendMessageTimeout(WinDef.HWND hWnd, int msg, long wParam, long lParam, int fuFlags, int uTimeout, WinDef.DWORDByReference lpdwResult);

    /*
    BOOL WINAPI EnumChildWindows(
        _In_opt_ HWND        hWndParent,
        _In_     WNDENUMPROC lpEnumFunc,
        _In_     LPARAM      lParam
    );
    */
    boolean EnumChildWindows(WinDef.HWND hWndParent, WinUser.WindowProc lpEnumFunc, int lParam);

    /*
    BOOL WINAPI BlockInput(
      _In_ BOOL fBlockIt
    );
    */
    boolean BlockInput(boolean fBlockIt);

    /*
    int WINAPI MessageBox(
      _In_opt_ HWND    hWnd,
      _In_opt_ LPCTSTR lpText,
      _In_opt_ LPCTSTR lpCaption,
      _In_     UINT    uType
    );
    */
    int MessageBox(WinDef.HWND  hWnd, String lpText, int lpCaption, int uType);

    /*
    HWND WINAPI CreateWindow(
        _In_opt_ LPCTSTR   lpClassName,
        _In_opt_ LPCTSTR   lpWindowName,
        _In_     DWORD     dwStyle,
        _In_     int       x,
        _In_     int       y,
        _In_     int       nWidth,
        _In_     int       nHeight,
        _In_opt_ HWND      hWndParent,
        _In_opt_ HMENU     hMenu,
        _In_opt_ HINSTANCE hInstance,
        _In_opt_ LPVOID    lpParam
    );
    */

    /*
    HWND WINAPI CreateWindowEx(
      _In_     DWORD     dwExStyle,
      _In_opt_ LPCTSTR   lpClassName,
      _In_opt_ LPCTSTR   lpWindowName,
      _In_     DWORD     dwStyle,
      _In_     int       x,
      _In_     int       y,
      _In_     int       nWidth,
      _In_     int       nHeight,
      _In_opt_ HWND      hWndParent,
      _In_opt_ HMENU     hMenu,
      _In_opt_ HINSTANCE hInstance,
      _In_opt_ LPVOID    lpParam
    );
    */
    WinDef.HWND CreateWindowEx(
        WinDef.DWORD dwExStyle,
        String lpClassName,
        String lpWindowName,
        WinDef.DWORD dwStyle,
        int x,
        int y,
        int nWidth,
        int nHeight,
        WinDef.HWND hWndParent,
        WinDef.HMENU hMenu,
        WinDef.HINSTANCE hInstance,
        WinDef.LPVOID lpParam
    );

    /*
    BOOL WINAPI ShowWindow(
      _In_ HWND hWnd,
      _In_ int  nCmdShow
    );
    */
    boolean ShowWindow(WinDef.HWND hWnd, int nCmdShow);

    /*
    BOOL WINAPI CloseWindow(
        _In_ HWND hWnd
    );
    */
    boolean CloseWindow(WinDef.HWND hWnd);

    /*
    BOOL WINAPI ShowWindowAsync(
      _In_ HWND hWnd,
      _In_ int  nCmdShow
    );
    */
    boolean ShowWindowAsync(WinDef.HWND hWnd, int nCmdShow);

    /*
    BOOL WINAPI DestroyWindow(
      _In_ HWND hWnd
    );
    */
    boolean DestroyWindow(WinDef.HWND hWnd);

    /*
    BOOL WINAPI EnumWindows(
      _In_ WNDENUMPROC lpEnumFunc,
      _In_ LPARAM      lParam
    );
    */
    boolean EnumWindows(WinUser.WindowProc lpEnumFunc, WinDef.LPARAM lParam);

    /*
    BOOL CALLBACK EnumWindowsProc(
      _In_ HWND   hwnd,
      _In_ LPARAM lParam
    );
    */
    // WinUser.WindowProc EnumWindowsProc(WinDef.HWND hWnd, WinDef.LPARAM lParam);
}
