package pl.devwannabe.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.devwannabe.entity.Contract;
import pl.devwannabe.repository.ContractRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping("/main")
    //@ResponseBody

    public String showContracts(Model model, @RequestParam(defaultValue="0") int page) {

        StringBuilder response = new StringBuilder();

        Contract contract = new Contract()
                .withNumber("1/2018")
                .withName("Potato")
                .withStartDate(LocalDate.of(2018, 05, 01))
                .withEndDate(LocalDate.of(2019, 05, 01))
                .withImpact(new BigDecimal("500000.00"))
                .withScale("Per year")
                .withActive(true);
        //contractRepository.save(contract);

/*        for (Contract i : contractRepository.findAll()) {
            response.append(i).append("<br>");
        }
        return response.toString();
*/


        model.addAttribute("contractList", contractRepository
                .findAll(PageRequest.of(page,5, Sort.Direction.ASC, "id")));
        model.addAttribute("currentPage", page);

        return "index";

    }

    @PostMapping("/save")
    public String createContract(Contract contract){
        Contract contract1 = new Contract();
        contractRepository.save(contract);
        return "redirect:/main";
    }


    @GetMapping("/delete/")
    public String deleteContract(Long id){
        contractRepository.deleteById(id);
        return "redirect:/main";
    }

    @GetMapping("/getOne")
    @ResponseBody
    public Contract getOne(Long id){
        return contractRepository.getOne(id);
    }

    private Sort orderBy() {
        return new Sort(Sort.Direction.ASC, "id");
    }

//-------------------------------------------------------
    @GetMapping("/")
    @ResponseBody
    public ModelAndView rose(){
        ModelAndView modelAndView = new ModelAndView("rose");
        return modelAndView;
    }



}