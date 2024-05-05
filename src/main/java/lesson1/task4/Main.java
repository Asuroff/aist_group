package lesson1.task4;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        User user1 = new User("Asuroff","1234","Sanan Ashurov",22,800);
        User user2 =  new User("John","1234","John Alex",30,1000);
        System.out.println(user1);
        System.out.println(user2);

        User customerUser = new User();
        customerUser.setUserName("Elon");
        customerUser.setPassword("194");
        customerUser.setFullName("Elon Musk");
        customerUser.setAge(35);
        customerUser.setBalance(20000);

        System.out.println(customerUser.getUserName());
        System.out.println(customerUser.getFullName());

    }
}
