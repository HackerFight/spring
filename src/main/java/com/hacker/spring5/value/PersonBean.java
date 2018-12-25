package com.hacker.spring5.value;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author Hacker
 * @date：2018/12/24
 * @project spring
 * @describe
 *
 * @Value()
 *   1.可以写基本数值：      @Value("23")
 *   2.可以写SpEL表达式:     @Value(#{45 + 23})
 *   3.可以读取属性配置文件：@Value("${jdbc.user}")
 */
public class PersonBean {

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    //直接写基本数值
    @Value("24")
    private Integer age;

    @Value("${user.address}")
    private String address;

    /**
     * @Value("${phone}") 这样子也可以，只是这样会把属性文件中的三个手机号当成一个保存到集合中
     * 所以这里可以使用@Value 的SpEL 表达式, 还是非常的强大
     * @Value("#{'${phone}'.split(',')}")
     */
    @Value("#{'${phone}'.trim().split(',')}")
    private List<String> phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}
