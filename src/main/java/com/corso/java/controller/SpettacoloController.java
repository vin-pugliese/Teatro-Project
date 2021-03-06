package com.corso.java.controller;

import com.corso.java.domain.Cliente;
import com.corso.java.domain.Spettacolo;
import com.corso.java.service.SpettacoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/spettacolo/v1")
public class SpettacoloController {

    @Autowired
    SpettacoloService spettacoloService;

    @GetMapping
    ResponseEntity<List<Spettacolo>> findAll(){
        List<Spettacolo> spettacoli = spettacoloService.findAll();
        return new ResponseEntity<List<Spettacolo>>(spettacoli, HttpStatus.OK);
    }


    @PostMapping(path="/create")
    ResponseEntity<Spettacolo> create(@RequestBody Spettacolo spettacolo){
        Spettacolo x = spettacoloService.create(spettacolo);
        return new ResponseEntity<Spettacolo>(x, HttpStatus.OK);
    }

    @PutMapping("/prenota")
    ResponseEntity<String> prenota(@RequestParam String id, @RequestParam String name, @RequestParam String tel){
       spettacoloService.prenota(id, name, tel);
       return new ResponseEntity<String>("Aggiunto", HttpStatus.OK);
    }

    @DeleteMapping(path="/disdici")
    ResponseEntity<String> disdici(@RequestParam String id, @RequestParam String name, @RequestParam String tel){
        spettacoloService.disdici(id, name, tel);
        return new ResponseEntity<String>("Disdetto", HttpStatus.OK);

    }

    @GetMapping(path="/incompleto/{id}")
    ResponseEntity<Boolean> incompleto(@PathVariable String id){
        return new ResponseEntity<Boolean>(spettacoloService.incompleto(id), HttpStatus.OK);
    }

    @GetMapping(path="/getattesa/{id}")
    ResponseEntity<List<Cliente>> getAttesa(@PathVariable String id){
        return new ResponseEntity<List<Cliente>>(spettacoloService.getClientiAttesa(id), HttpStatus.OK);
    }

    @GetMapping(path ="/getprenotazioni/{id}")
    ResponseEntity<Cliente[]> getPrenotazioni (@PathVariable String id){
        return new ResponseEntity<Cliente[]>(spettacoloService.getClientiPrenotati(id), HttpStatus.OK);
    }

}
