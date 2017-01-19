package jna;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import jna.windows.CallbackEvent;
import jna.windows.User32;

public class Test {

    public static final User32 lib = User32.INSTANCE;

    public static void main(String[] args) throws Exception {

        WinDef.HWND hwnd = lib.FindWindow(null, null);
        // 设置编码，防止乱码
        System.setProperty("jna.encoding", "GBK");


        // WinDef.DWORDByReference dwordByReference = null;
        // lib.SendMessageTimeout(hwnd, 0x0112, 0xF170, 2, 0, 10, dwordByReference);
        // lib.SendMessageTimeout(hwnd, 0x0112, 0xF170, -1, 0, 10, dwordByReference);

        /*
        // 调用消息对话框
        lib.MessageBox(hwnd, "hello!", 0, 0);

        // 阻塞鼠标键盘的输入
        lib.BlockInput(true);
        // 关闭显示器
        lib.SendMessage(hwnd, 0x0112, 0xF170, 2);

        // 打开显示器
        lib.SendMessage(hwnd, 0x0112, 0xF170, -1);
        // 间隔2秒
        Thread.sleep(2000);
        // 释放鼠标键盘
        lib.BlockInput(false);
        */

        // W32APIOptions
        WinDef.HWND hwnd1 = lib.CreateWindowEx(new WinDef.DWORD(0x00000200L | 0x00000400L),"Message",
            "Test", new WinDef.DWORD(0x00000000L | 0x10000000L | 0x00080000L | 0x00040000L | 0x00020000L | 0x00010000L), 0, 0, 800,
            500, null, null, null, null);
        // lib.ShowWindow(hwnd1, 5);
        // Thread.sleep(20000);

        lib.EnumWindows((hwnd2, uMsg, wParam, lParam) -> {
            System.out.println(hwnd2.toNative());
            return new WinDef.LRESULT();
        }, new WinDef.LPARAM(5));

    }
}
