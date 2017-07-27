package observer;

public class StudentObserver implements Observer {

    // 保存一个Subject的引用,以后如果可以想取消订阅,有了这个引用会比较方便
    private TeacherSubject teacherSubject;
    // 学生的姓名,用来标识不同的学生对象
    private String name;

    // 构造器用来注册观察者
    public StudentObserver(String name, TeacherSubject teacherSubject) {
        this.name = name;
        this.teacherSubject = teacherSubject;
        // 每新建一个学生对象,默认添加到观察者的行列
        teacherSubject.addObserver(this);
    }

    @Override
    public void update(String info) {
        System.out.println(name + "得到作业:" + info);

    }

    public static void main(String[] args) {

        TeacherSubject teacher = new TeacherSubject();
        StudentObserver zhangSan = new StudentObserver("张三", teacher);
        StudentObserver LiSi = new StudentObserver("李四", teacher);
        StudentObserver WangWu = new StudentObserver("王五", teacher);

        teacher.setHomework("第二页第六题");
        teacher.setHomework("第三页第七题");
        teacher.setHomework("第五页第八题");
    }
}
