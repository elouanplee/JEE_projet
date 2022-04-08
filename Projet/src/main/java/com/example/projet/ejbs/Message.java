package com.example.projet.ejbs;

import com.example.projet.dto.MessageDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Message {
    void send(String username, String message);
    List<MessageDto> getMessages();
}
