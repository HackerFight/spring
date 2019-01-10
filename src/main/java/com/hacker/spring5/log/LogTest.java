package com.hacker.spring5.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * @author hacker
 * @date 2019/1/10
 * @describe
 *  简单回顾门面模式
 *  slf4j是门面模式的典型应用，因此在讲slf4j前，我们先简单回顾一下门面模式，
 *  门面模式，其核心为外部与一个子系统的通信必须通过一个统一的外观对象进行，使得子系统更易于使用。用一张图来表示门面模式的结构为：
 *  img/门面模式.png
 *
 *  我们为什么要使用slf4j ? 举个例子：
 *    我们自己的系统中使用了logback这个日志系统
 *    我们的系统使用了A.jar，A.jar中使用的日志系统为log4j
 *    我们的系统又使用了B.jar，B.jar中使用的日志系统为slf4j-simple
 *
 *    这样，我们的系统就不得不同时支持并维护logback、log4j、slf4j-simple三种日志框架，非常不便。
 *    解决这个问题的方式就是引入一个适配层，由适配层决定使用哪一种日志系统，而调用端只需要做的事情就是打印日志而不需要关心如何打印日志，
 *    slf4j或者commons-logging就是这种适配层，slf4j是本文研究的对象。
 *
 *    从上面的描述，我们必须清楚地知道一点：slf4j只是一个日志标准，并不是日志系统的具体实现。理解这句话非常重要，slf4j只做两件事情：
 *      提供日志接口
 *      提供获取具体日志对象的方法
 *
 *   slf4j-simple、logback都是slf4j的具体实现，log4j并不直接实现slf4j，但是有专门的一层桥接slf4j-log4j12来实现slf4j。
 *   为了更理解slf4j，我们先看例子，再读源码，相信读者朋友会对slf4j有更深刻的认识。
 *
 * </pre>
 */
public class LogTest {

    /**
     * <pre>
     * 这里，先把 pom.xml 文件中除了 slf4j-api 以外，其他的暂时都先拿掉:
     *   SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
     *   SLF4J: Defaulting to no-operation (NOP) logger implementation
     *   SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
     *
     *   没有任何日志的输出，这也验证了我们的观点：slf4j 不提供日志的实现，只有 slf4j是无法打印日志的
     * </pre>
     */
    @Test
    public void testSlf4j1() {
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.info("test only slf4j.....");
    }

    /**
     * <pre>
     * 接着打开logback-classic的注释，运行Test方法，我们看一下控制台的输出为：
     * 10:06:12.170 [main] INFO com.hacker.spring5.log.LogTest - hello slf4j by logback-classic.....
     * 看到我们只要引入了一个slf4j的具体实现类，即可使用该日志框架输出日志。
     * </pre>
     */
    @Test
    public void testSlf4j2() {
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.info("hello slf4j by logback-classic.....");
    }


    /**
     * <pre>
     *  最后做一个测验，我们把所有日志打开，引入logback-classic、slf4j-simple、log4j，运行Test方法，控制台输出为：
     *  和上面的差别是，可以输出日志，但是会输出一些告警日志，提示我们同时引入了多个slf4j的实现，然后选择其中的一个作为我们使用的日志系统。
     *  从例子我们可以得出一个重要的结论，即slf4j的作用：只要所有代码都使用门面对象slf4j，我们就不需要关心其具体实现，
     *  最终所有地方使用一种具体实现即可，更换、维护都非常方便。
     * </pre>
     */
    @Test
    public void testSlf4j3() {
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.info("hello slf4j all");
    }


    /**
     * <pre>
     *     源码探究：
     *      1. 进入：Logger logger = LoggerFactory.getLogger(LogTest.class); getLogger 的实现类中
     *      2. 进入：getLogger(clazz.getName());
     *      3. 进入：getILoggerFactory();
     *      4. 进入： performInitialization();
     *      5. 进入： bind(); 这个方法是我们探究的重点
     *          1）findPossibleStaticLoggerBinderPathSet();
     *          2）这个地方重点其实就是paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);，
     *             getLogger的时候会去classpath下找 STATIC_LOGGER_BINDER_PATH，STATIC_LOGGER_BINDER_PATH 值为”org/slf4j/impl/StaticLoggerBinder.class”，
     *             即所有slf4j的实现，在提供的jar包路径下，一定是有”org/slf4j/impl/StaticLoggerBinder.class”存在的，我们可以看一下：
     *             在：logback-classic jar 包下是有 StaticLoggerBinder 这个类的，在 slf4j-log4j12 中也是有的，在 slf4j-simple 下也是有的
     *
     *   我们不能避免在系统中同时引入多个slf4j的实现，所以接收的地方是一个Set。大家应该注意到，
     *   上部分在演示同时引入logback、slf4j-simple、log4j的时候会有警告：
     *   这就是因为有三个”org/slf4j/impl/StaticLoggerBinder.class”存在的原因。
     *
     *   那网友朋友可能会问，同时存在三个”org/slf4j/impl/StaticLoggerBinder.class”怎么办？首先确定的是这不会导致启动报错，
     *   其次在这种情况下编译期间，编译器会选择其中一个StaticLoggerBinder.class进行绑定，
     *   这个地方sfl4j也在reportActualBinding方法中报告了绑定的是哪个日志框架：
     *
     *   对照上一个方法的执行结果，看最后一行，确实是”Actual binding is of type…”这句。
     *   绑定的是：[ch.qos.logback.classic.util.ContextSelectorStaticBinder]  logback-classic 这个日志
     *
     *   注意：log4j 这样的日志是需要日志文件的，所以测试他的时候可能会出现不打印的情况,后来我加上 log4j.properties 属性文件测试通过!
     *
     * </pre>
     */
    @Test
    public void testSource() {

    }
}
