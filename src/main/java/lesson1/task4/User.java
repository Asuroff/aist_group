package lesson1.task4;

public class User {
    private String userName;
    private String password;
    private String fullName;
    private int age;
    private double balance;
    private static int count=0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User(String userName, String password, String fullName, int age, double balance) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.balance = balance;
    }
    public User() {
        count++;
        System.out.println("Yeni istifadəçi yaradıldı. İstifadəçilərin sayı: "+count);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                '}';
    }

}
