package com.gnp.hackerrank.HackerRank.controllers;

import com.gnp.hackerrank.HackerRank.services.HackerRankServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/HackerRank")
public class HackerRankCtrl {

    @Autowired
    private HackerRankServ hackerRankServ;

    @PostMapping(path="/player/register")
    public @ResponseBody String addNewUser (@RequestParam String name) {
        return hackerRankServ.registerPlayer(name);
    }

    @GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

}
