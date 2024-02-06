package com.herbst.projectspringtwo.services;

import com.herbst.projectspringtwo.dto.UserDTO;
import com.herbst.projectspringtwo.dto.UserInsertDTO;
import com.herbst.projectspringtwo.entities.Role;
import com.herbst.projectspringtwo.entities.User;
import com.herbst.projectspringtwo.entities.Category;
import com.herbst.projectspringtwo.repositories.UserRepository;
import com.herbst.projectspringtwo.services.exceptions.DataBaseIntegrityViolationException;
import com.herbst.projectspringtwo.services.exceptions.EntityIdNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.boot.model.internal.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Transactional
    public UserDTO store( @RequestBody @Valid UserInsertDTO entity) {
       try{
           User user = new User(entity);
           user.setPassword(passwordEncoder.encode(entity.getPassword()));
           user = userRepository.save(user);
           return new UserDTO(user);
       }catch (DataIntegrityViolationException e){
           throw new DataBaseIntegrityViolationException();
       }
    }
    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPage(Pageable pageable) {
        Page<User> pagesUsers = userRepository.findAll(pageable);
        return pagesUsers.map(user -> new UserDTO(user));
    }
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> entity = userRepository.findById(id);
        User user = entity.orElseThrow(() -> new EntityIdNotFoundException(id));
        return new UserDTO(user);
    }

    @Transactional
    public void delete(Long id) {
       try {
           userRepository.deleteById(id);
       }catch (DataIntegrityViolationException e){
           throw new DataBaseIntegrityViolationException(id);
       }
    }

    @Transactional
    public UserDTO update(Long id, @RequestBody @Valid UserDTO updateEntity) {
        try{
            User entity = userRepository.getReferenceById(id);
            updateData(entity, updateEntity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        }catch (EntityNotFoundException e){
            throw new EntityIdNotFoundException(id);
        }
    }
    private void updateData(User entity, UserDTO updateEntity){
        entity.setName(updateEntity.getName());
        entity.setEmail(updateEntity.getEmail());
        entity.getRoles().clear();
        if(updateEntity.getRoles() != null){
            updateEntity.getRoles().forEach(roleDTO -> entity.getRoles().add(new Role(roleDTO)));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Email Not Found");
        }
        return user;
    }
}
