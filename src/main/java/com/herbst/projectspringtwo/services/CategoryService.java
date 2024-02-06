package com.herbst.projectspringtwo.services;

import com.herbst.projectspringtwo.dto.CategoryDTO;
import com.herbst.projectspringtwo.entities.Category;
import com.herbst.projectspringtwo.repositories.CategoryRepository;
import com.herbst.projectspringtwo.services.exceptions.DataBaseIntegrityViolationException;
import com.herbst.projectspringtwo.services.exceptions.EntityIdNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public CategoryDTO store(CategoryDTO entite){
        Category category = categoryRepository.save(new Category(entite));
        return new CategoryDTO(category);
    }
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPage(Pageable pageable){
        Page<Category> categoryList = categoryRepository.findAll(pageable);
        return categoryList.map(catDTO -> new CategoryDTO(catDTO));

    }
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> optionalCategory =  categoryRepository.findById(id);
        Category category =  optionalCategory.orElseThrow(() -> new EntityIdNotFoundException(id));
        return new CategoryDTO(category);
    }


    public void delete(Long id){
        try {
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseIntegrityViolationException(id);
        }
    }
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO updateEntite){
         try{
             Category category = categoryRepository.getReferenceById(id);
             updateData(category, updateEntite);
             category = categoryRepository.save(category);
             return new CategoryDTO(category);
         }catch (LazyInitializationException e){
            throw new EntityIdNotFoundException(id);
         }catch (EntityNotFoundException e){
             throw new EntityIdNotFoundException(id);
         }
    }

    public void updateData(Category entite, CategoryDTO updateEntite){
        entite.setName(updateEntite.getName());
    }
}
