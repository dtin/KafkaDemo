package tin.dam.kafkademo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tin.dam.kafkademo.consts.AppConst;
import tin.dam.kafkademo.dtos.AccountDTO;
import tin.dam.kafkademo.dtos.MessageDTO;
import tin.dam.kafkademo.dtos.StatisticDTO;

import java.util.Date;

@RestController
@RequestMapping(AppConst.ACCOUNT_PATH)
public class AccountController {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping(AppConst.CREATE_PATH)
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {
        StatisticDTO stat = new StatisticDTO("Account " + accountDTO.getEmail() + " is created", new Date());

        // Send notification
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setToEmail(accountDTO.getEmail());
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Welcome " + accountDTO.getName() + " to the Kafka world!");
        messageDTO.setContent("This is a content demo for Kafka producer.");

        kafkaTemplate.send("notification", messageDTO);
        kafkaTemplate.send("statistic", stat);

        return accountDTO;
    }
}
