package pl.devwannabe.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.devwannabe.entity.Contract;
import pl.devwannabe.entity.ContractDescription;
import pl.devwannabe.repository.ContractRepository;
import pl.devwannabe.repository.ContractDescriptionRepository;

@Controller
public class MainController {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ContractDescriptionRepository contractDescriptionRepository;



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

    @GetMapping("/description")
    public String showWithDescriptions(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractList", contractRepository
                .findAll(PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        return "description";
    }

    @Transactional
    @PostMapping("/create")
    public String create(Contract contract, ContractDescription contractDescription) {
        contractDescription.setDescription("No description available, yet");
        contract.setDescription(contractDescription);
        contractRepository.save(contract);
        return "redirect:/main";
    }

    @Transactional
    @PostMapping("/saveDescription")
    public String saveDescription(Contract contract, ContractDescription contractDescription) {
        //@ModelAttribute("myForm")
        contract.setDescription(contractDescription);
        contractRepository.save(contract);
       // contractDescriptionRepository.save(contractDescription);
        return "redirect:/description";
    }

    @Transactional
    @GetMapping("/delete")
    public String delete(Long id) {
        contractRepository.deleteById(id);
        return "redirect:/main";
    }

    @GetMapping("/getOneContract")
    @ResponseBody
    public Contract getOneContract(Long id) {
        return contractRepository.getOne(id);
    }

    @GetMapping("/getOneDescription")
    @ResponseBody
    public Contract getOneDescription(Long id) {
        return contractRepository.getOneByDescription(id);
    }

//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    public String download(Model model) {
//        model.addAttribute("contracts", contractRepository.findAll());
//        return "";
//    }



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