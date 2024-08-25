package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findOrder() {
        String jpql = "SELECT o FROM Order o WHERE o.quantity > 1 ORDER BY o.createdAt DESC LIMIT 1";

        try {
            return entityManager.createQuery(jpql, Order.class).getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("No order found");
        }
    }

    @Override
    public List<Order> findOrders() {
        String jpql = "SELECT o FROM Order o JOIN User u ON o.userId = u.id WHERE u.userStatus = 'ACTIVE' ORDER BY o.createdAt DESC";

        return entityManager.createQuery(jpql, Order.class).getResultList();
    }
}
