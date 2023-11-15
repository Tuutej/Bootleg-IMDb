package hh.sof03.bootlegimdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.sof03.bootlegimdb.domain.User;
import hh.sof03.bootlegimdb.domain.UserRepository;

@Service
public class UserDetailService implements UserDetailsService  {
	private final UserRepository userRepo;

	@Autowired
	public UserDetailService(UserRepository userRepository) {
		this.userRepo = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	User currentUser = userRepo.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    }   
} 
