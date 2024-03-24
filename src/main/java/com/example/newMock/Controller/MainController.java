package com.example.newMock.Controller;


import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO){
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;
            String currency;

            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000);
                currency = "US";
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000);
                currency = "EU";
            } else {
                maxLimit = new BigDecimal(10000);
                currency = "RUB";
            }

            ResponceDTO responceDTO = new ResponceDTO();

            BigDecimal balance = new BigDecimal(new Random().nextDouble()).multiply(maxLimit);
            BigDecimal balanceRounded = balance.setScale(0, RoundingMode.HALF_UP);

            responceDTO.setRqUID(requestDTO.getRqUID());
            responceDTO.setClientId(clientId);
            responceDTO.setAccount(requestDTO.getAccount());
            responceDTO.setCurrency(currency);
            responceDTO.setBalance(balanceRounded);
            responceDTO.setMaxLimit(maxLimit);

            log.error("********** RequestDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("********** ResponceDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responceDTO));

            return responceDTO;

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
