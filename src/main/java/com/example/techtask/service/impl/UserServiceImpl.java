package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUser() {
        String jpql = "SELECT u FROM User u JOIN Order o ON u.id = o.userId WHERE o.orderStatus = 'DELIVERED' AND YEAR(o.createdAt) = 2003 GROUP BY u.id ORDER BY sum(o.price * o.quantity) DESC LIMIT 1";

        try {
            return entityManager.createQuery(jpql, User.class).getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public List<User> findUsers() {
        String jpql = "SELECT u FROM User u JOIN Order o ON u.id = o.userId WHERE o.orderStatus = 'PAID' AND YEAR(o.createdAt) = 2010";

        return entityManager.createQuery(jpql, User.class).getResultList();
    }
}
