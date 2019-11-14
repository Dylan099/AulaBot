package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.UserRepository;
import com.example.ElAulaBot.domain.Botuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBl {
    UserRepository userRepository;
    public UserBl(){
        userRepository = null;
    }

    @Autowired
    public UserBl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Botuser findPersonById(Integer pk){
        Optional<Botuser> optional = this.userRepository.findById(pk);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new RuntimeException("Record cannot be found for user with ID: "+pk );
        }
    }
}
