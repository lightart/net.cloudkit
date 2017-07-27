package observer;

import java.util.Observer;

//主题接口
interface Subject {
    //添加观察者
    void addObserver(observer.Observer obj);

    //移除观察者
    void deleteObserver(observer.Observer obj);
    //当主题方法改变时,这个方法被调用,通知所有的观察者
    void notifyObserver();
}
