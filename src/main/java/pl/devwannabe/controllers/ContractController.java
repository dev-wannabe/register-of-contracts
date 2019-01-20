package pl.devwannabe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.devwannabe.domain.Model.Contract;
import pl.devwannabe.services.ContractService;

import javax.validation.Valid;

@Controller
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/all-contracts")
    public String showAll(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractsList", contractService
                .getAllContracts(PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "contracts";
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
        model.addAttribute("contractsList", contractService
                .getAllContracts(PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "descriptions";
    }

    @PostMapping("/saveContract")
    public String saveContract(@Valid Contract contract, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ContractService.printBlue("************ There were errors ***********");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() +
                        " " + error.getDefaultMessage());
            });
            return "errors";
        } else {
            contractService.save(contract);
            return "redirect:/all-contracts";
        }
    }

    @PostMapping("/saveDescription")
    public String saveDescription(Contract contract) {
        contractService.save(contract);
        return "redirect:/descriptions";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        contractService.deleteById(id);
        return "redirect:/all-contracts";
    }

    @GetMapping("/getOneContract")
    @ResponseBody
    public Contract getOneContract(@RequestParam("id") Long id) {
        return contractService.getOne(id);
    }
}