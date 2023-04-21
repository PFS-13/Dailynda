package Tubes.DailyndaMongoDB.controller;

import Tubes.DailyndaMongoDB.model.Agenda;
import Tubes.DailyndaMongoDB.repository.AgendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Controller
@Slf4j
public class AgendaController {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@GetMapping("/")
	public String getAllAgendas(final Model model) {
		IReactiveDataDriverContextVariable reactiveDataDriverContextVariable
			= new ReactiveDataDriverContextVariable(agendaRepository.findAll());
		//log.info("AGENDAS READ FROM MONGODB {}"), reactiveDataDriverContextVariable);
		model.addAttribute("agendas", reactiveDataDriverContextVariable);
		return "allAgendas";
	}
	
	@GetMapping(path = {"/edit","/edit/{id}"})
	public String createUpdateAgendaById(Model model,@PathVariable("id") Optional<Long> id) {
		if(id.isPresent()) {
			Mono<Agenda> agenda = agendaRepository.findById(id.get());
			//log.info(" $$$$$ CREATE/UPDATED by ID Agenda :: {}", agenda);
			model.addAttribute("agenda",agenda);
		}else{
			model.addAttribute("agenda",new Agenda());
		}
		return "addAgenda";
	}

	@GetMapping("/delete/{id}")
	public String deleteAgenda(final Model model, @PathVariable Long id) {
		
		agendaRepository.deleteById(id).subscribe();
		
		return "redirect:/";
	}
	
	@PostMapping("/create")
	public String createAgenda(Agenda agenda) {
		agendaRepository.save(agenda).subscribe();
		return "redirect:/";
	}
}
