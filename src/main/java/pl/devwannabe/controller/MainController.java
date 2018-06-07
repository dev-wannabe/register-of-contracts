package pl.devwannabe.controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.devwannabe.entity.Contract;
import pl.devwannabe.entity.Description;
import pl.devwannabe.repository.ContractRepository;
import pl.devwannabe.repository.DescriptionRepository;

import javax.management.modelmbean.DescriptorSupport;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private DescriptionRepository descriptionRepository;



    @GetMapping("/main")
    public String showAll(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractList", contractRepository
                .findAll(PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "all contracts";
    }

    @GetMapping("/active")
    public String showActive(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractList", contractRepository
                .findByActive(true, PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "active contracts";
    }

    @GetMapping("/descriptions")
    public String showWithDescriptions(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractList", contractRepository
                .findAll(PageRequest.of(page, 5)));
        model.addAttribute("descriptionList", descriptionRepository
                .findAll(PageRequest.of(page, 5)));
        model.addAttribute("currentPage", page);
        return "descriptions";
    }

    @Transactional
    @PostMapping("/save")
    public String create(Contract contract, Description description) {

        contract.setDescription(description);
        contractRepository.save(contract);
        descriptionRepository.save(description);

        return "redirect:/main";
    }

   // @Transactional
    @PutMapping("/saveDescription")
    public String saveDescription(Description description) {


       // description.setDescription();
       // descriptionRepository.save(description);



        return "redirect:/descriptions";
    }

    @Transactional
    @GetMapping("/delete")
    public String delete(Long id) {
        contractRepository.deleteById(id);
       // descriptionRepository.deleteById(id);
        return "redirect:/main";
    }

    @GetMapping("/getOneContract")
    @ResponseBody
    public Contract getOneContract(Long id) {
        return contractRepository.getOne(id);
    }

    @GetMapping("/getOneDescription")
    @ResponseBody
    public Description getOneDescription(Long id) {
        return descriptionRepository.getOne(id);
    }






/*
------------------Some experimental method-----------------------------
    @GetMapping("/")
    @ResponseBody
    public ModelAndView rose() {
        ModelAndView modelAndView = new ModelAndView("rose");
        return modelAndView;
    }

    @GetMapping("/main")
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
*/

}