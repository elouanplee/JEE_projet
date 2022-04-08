package com.example.projet.ejbs.impl;



import com.example.projet.dto.MessageDto;
import com.example.projet.ejbs.Message;
import com.example.projet.listeners.ApplicationListener;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class MessageBean implements Message {
    @Override
    public void send(String username, String message) {
        com.example.projet.entities.Message messageToSend = new com.example.projet.entities.Message();
        messageToSend.setUsername(username);
        messageToSend.setMessage(message);
        messageToSend.setTime(new Date());

        EntityManagerFactory emf = ApplicationListener.getEmf();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(messageToSend);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<MessageDto> getMessages() {
        EntityManagerFactory emf = ApplicationListener.getEmf();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<com.example.projet.entities.Message> messages = query.getResultList();

        List<MessageDto> toReturn = new ArrayList<>();
        messages.forEach(m -> {
            MessageDto messageDto = new MessageDto();
            messageDto.setUsername(m.getUsername());
            messageDto.setMessage(m.getMessage());
            messageDto.setTime(m.getTime().toString());

            toReturn.add(messageDto);
        });

        return toReturn;
    }
}
