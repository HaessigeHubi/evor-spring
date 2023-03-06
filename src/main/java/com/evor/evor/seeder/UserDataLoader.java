package com.evor.evor.seeder;

import com.evor.evor.entity.User;
import com.evor.evor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
    //Test Cases when Spring Boot is started
    private void loadUserData(){
        if (userRepository.count() == 0){
            User user1 = new User("Oliver","Huber","18.10.1996","asdf@asdf.com");
            User user2 = new User("User2","Gregory","18.10.1993","location@asd.com");
            User user3 = new User("User3","Milan","18.10.1993","ojhw181818@gmail.com");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
    }
}
