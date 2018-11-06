package pl.devwannabe.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.devwannabe.domain.Contract;
import pl.devwannabe.domain.ContractRepository;
import pl.devwannabe.services.ContractService;

@Controller
public class MainController {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractService contractService;


    @GetMapping("/all-contracts")
    public String showAll(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractsList", contractService
                .getAllContracts(PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "all contracts";
    }

    @GetMapping("/active-contracts")
    public String showActive(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractsList", contractService
                .getByActive(true, PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "active contracts";
    }

    @GetMapping("/descriptions")
    public String showWithDescriptions(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractsList", contractRepository
                .findAll(PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        return "descriptions";
    }

    @Transactional
    @PostMapping("/saveContract")
    public String create(Contract contract) {
        contractRepository.save(contract);
        return "redirect:/all-contracts";
    }

    @Transactional
    @PostMapping("/saveDescription")
    public String saveDescription(Contract contract) {
        contractRepository.save(contract);
        return "redirect:/descriptions";
    }

    @Transactional
    @GetMapping("/delete")
    public String delete(Long id) {
        contractRepository.deleteById(id);
        return "redirect:/all-contracts";
    }

    @GetMapping("/getOneContract")
    @ResponseBody
    public Contract getOneContract(Long id) {
        return contractRepository.getOne(id);
    }

    @GetMapping("/getOneDescription")
    @ResponseBody
    public Contract getOneDescription(@RequestParam("id") Long id) {
        return contractRepository.getOne(id);
    }

//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    public String download(Model model) {
//        model.addAttribute("contracts", contractRepository.getAllContracts());
//        return "";
//    }

}