package com.herbst.literium.services;

import com.herbst.literium.dto.FavoriteDTO;
import com.herbst.literium.dto.FavoriteResponseDTO;
import com.herbst.literium.entities.Book;
import com.herbst.literium.entities.Favorite;
import com.herbst.literium.entities.User;
import com.herbst.literium.repositories.BookRepository;
import com.herbst.literium.repositories.FavoriteRepository;
import com.herbst.literium.repositories.UserRepository;
import com.herbst.literium.services.exceptions.EntityAlreadyExistsException;
import com.herbst.literium.services.exceptions.EntityIdNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public Page<FavoriteResponseDTO> findByUserId(Long userId, Pageable pageable){
        verifyUserId(userId);
        Page<Favorite> favorites = favoriteRepository.findByUserId(userId, pageable);
        return favorites.map(favorite -> new FavoriteResponseDTO(favorite));
    }

    public FavoriteResponseDTO insertByUserId(Long userId, Long bookId){
        User user = verifyUserId(userId);
        Book book = verifyBookId(bookId);
        Favorite favoriteVerify = favoriteRepository.findByUserIdAndBookId(userId, bookId);
        if(favoriteVerify != null) throw new EntityAlreadyExistsException();
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setBook(book);
        favoriteRepository.save(favorite);
        return new FavoriteResponseDTO(favorite);
    }
    @Transactional
    public void deleteByBookId(Long userId, Long bookId){
        verifyUserId(userId);
        verifyBookId(bookId);
        favoriteRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    private User verifyUserId(Long userId){
        User user = userRepository.getReferenceById(userId);
        if(!(user != null)) throw new EntityIdNotFoundException(userId);
        return user;
    }

    private Book verifyBookId(Long bookId){
        Book book = bookRepository.getReferenceById(bookId);
        if(!(book != null)) throw new EntityIdNotFoundException(bookId);
        return book;
    }
}
