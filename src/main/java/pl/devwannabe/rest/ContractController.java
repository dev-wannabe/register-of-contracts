package pl.devwannabe.rest;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.devwannabe.domain.contract.Contract;
import pl.devwannabe.domain.contract.ContractService;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/register/contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController(@NonNull ContractService contractService) {
        Validate.notNull(contractService);
        this.contractService = contractService;
    }

    @GetMapping("/id")
    @ResponseBody
    public Contract getOneContract(@RequestParam("id") Long id) {
        return contractService.getOneContract(id);
    }

    @GetMapping("/all")
    public String showAll(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractsList", contractService
                .getAllContracts(PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "contracts";
    }

    @GetMapping("/active")
    public String showActive(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractsList", contractService
                .getActiveContracts(true, PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "active contracts";
    }

    @PostMapping("/save")
    public String saveContract(@Valid Contract contract, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() +
                        " " + error.getDefaultMessage());
            });
            return "errors";
        } else {
            contractService.saveContract(contract);
            return "redirect:/register/contract/all";
        }
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        contractService.deleteContractById(id);
        return "redirect:/register/contract/all";
    }

    @GetMapping("/description")
    public String showWithDescriptions(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("contractsList", contractService
                .getAllContracts(PageRequest.of(page, 5, Sort.Direction.ASC, "startDate")));
        model.addAttribute("currentPage", page);
        return "descriptions";
    }

    @PostMapping("/description/save")
    public String saveDescription(Contract contract) {
        contractService.saveContract(contract);
        return "redirect:/register/contract/description";
    }




}