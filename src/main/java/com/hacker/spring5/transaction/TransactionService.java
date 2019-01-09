package com.hacker.spring5.transaction;

import com.hacker.spring5.mapper.ClassMapper;
import com.hacker.spring5.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author hacker
 * @date 2019/1/8
 * @describe
 */
@Service
public class TransactionService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;


    /**
     * <pre>
     *  有没有 @Transactional 的区别：
     *   1.没有@Transactional 的时候：
     *      1）studentMapper.insertStudent(student); 执行成功，断点先停住，然后看数据库，发现数据已经插入
     *      2）classMapper.insertClass(classEntry); 执行失败（让classNo超过数据库限制），插入失败，最后是学生插入成功，班级插入失败
     *
     *   2.有@Transactional 的时候：
     *      1）studentMapper.insertStudent(student); 执行成功，断点先停住，然后看数据库，发现数据没有插入，同时主键可以正确封装
     *         这里的原因来自于 DataSourceTransactionManager 里面的 doBegin() 方法，他在开始之前关闭了 自动提交
     *      2）classMapper.insertClass(classEntry); 执行失败，然后看数据库，发现数据没有插入，但是 学生表的数据还是插入了？？？
     *         这就要回到 事物的回滚机制：
     *
     *   3.回滚机制：
     *      Spring事务管理器回滚一个事务的推荐方法是"在当前事务的上下文内抛出异常"。spring事务管理器会捕捉任何未处理的异常，然后依据规则决定是否回滚抛出异常的事务。
     *      默认配置下，spring只有在抛出的异常为运行时unchecked异常时才回滚该事务，也就是抛出的异常为RuntimeException的子类(Errors也会导致事务回滚)，
     *      而抛出checked异常则不会导致事务回滚。可以明确的配置在抛出那些异常时回滚事务，包括checked异常。也可以明确定义那些异常抛出时不回滚事务。
     *
     *   4.回到 2.2
     *     如果想实现 学生表 的数据能够正常回滚，可以有两种方式：
     *     1）Spring 推荐的方式，就是在 3 中 提到的  抛出异常，不要进行try ... catch(),我本身是进行了try ...catch() 的
     *        请执行方法：addObjectThrowsException() ，测试后发现，确实ok了
     *
     *     2）手动回滚：请参照方法 addObjectSetOnlyBack()
     *        测试发现，确实ok，他是当执行到 catch 中的  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 代码时
     *        就会回滚一切
     *
     *   5.注意：如果回滚一直失败的话，请怀疑mysql的引擎类型，因为 MyISAM 是不支持事物的！！！
     *
     * </pre>
     */
    @Transactional()
    public void addObject() {
        try {
            Student student = new Student("hacker", 23, "20190101001", "0556");
            studentMapper.insertStudent(student);
            System.out.println("返回的主键: " + student.getId()); //这里看sql的写法

            ClassEntry classEntry = new ClassEntry();
            classEntry.setName("高一一班");
            classEntry.setClassNo("055611111111111111111"); //055611111111111111111
            classMapper.insertClass(classEntry);
            System.out.println("返回的主键: " + classEntry.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 手动抛出异常
     */
    @Transactional
    public void addObjectThrowsException() throws Exception {
        Student student = new Student("hacker", 23, "20190101001", "0556");
        studentMapper.insertStudent(student);
        System.out.println("返回的主键: " + student.getId()); //这里看sql的写法

        ClassEntry classEntry = new ClassEntry();
        classEntry.setClassNo("055611111111111111111"); //055611111111111111111
        classEntry.setName("高一一班");
        classMapper.insertClass(classEntry);
        System.out.println("返回的主键: " + classEntry.getId());
    }

    /**
     * 手动回滚事务：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     */
    @Transactional
    public void addObjectSetOnlyBack() {
        try {
            Student student = new Student("hacker", 23, "20190101001", "0556");
            System.out.println("................");
            studentMapper.insertStudent(student);
            System.out.println("返回的主键: " + student.getId()); //这里看sql的写法

            ClassEntry classEntry = new ClassEntry();
            classEntry.setClassNo("055611111111111111111"); //055611111111111111111
            classEntry.setName("高一一班");
            classMapper.insertClass(classEntry);
            System.out.println("返回的主键: " + classEntry.getId());
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
