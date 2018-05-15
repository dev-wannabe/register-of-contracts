package pl.devwannabe.registerofcontracts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.devwannabe.registerofcontracts.entity.Contract;
import pl.devwannabe.registerofcontracts.repository.ContractRepository;

import java.math.BigDecimal;
import java.time.LocalDate;


@Controller
public class MainController {


    @Autowired
    private ContractRepository contractRepository;


    @RequestMapping("/main")
    @ResponseBody

    public String testMethod() {

        StringBuilder response = new StringBuilder();

        Contract contract = new Contract()
                .withNumber("1/2018")
                .withName("Potato")
                .withStartDate(LocalDate.of(2018, 05, 01))
                .withEndDate(LocalDate.of(2019, 05, 01))
                .withImpact(new BigDecimal("500000.00"))
                .withScale("Per year")
                .withActive(true);
        contractRepository.save(contract);

        for (Contract i : contractRepository.findAll()) {
            response.append(i).append("<br>");
        }
        return response.toString();
    }

    @RequestMapping("/")
    public String rose(){
        return "----------------<<<<<<-------@";
    }


}