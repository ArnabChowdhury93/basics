package com.learning.basics.service;

import com.learning.basics.database.MyUserRepository;
import com.learning.basics.models.MyUser;
import com.learning.basics.models.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepository myUserRepository;

    @Override
    public MyUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<MyUser> myUser = myUserRepository.findByUserName(s);
        myUser.orElseThrow(() -> new UsernameNotFoundException("User not found : " + s));
        return myUser.map(MyUserDetails::new).get();
    }
}
