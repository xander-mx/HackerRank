package com.gnp.hackerrank.HackerRank.controllers;

import com.gnp.hackerrank.HackerRank.exceptions.HackerRankException;
import com.gnp.hackerrank.HackerRank.services.HackerRankServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/HackerRank")
public class HackerRankCtrl {

    @Autowired
    private HackerRankServ hackerRankServ;

    @PostMapping(path="/player/register")
    public @ResponseBody ResponseEntity<String> addNewUser (@RequestParam String name) {
        try{
        return new ResponseEntity<>(hackerRankServ.registerPlayer(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/evaluate")
    public @ResponseBody ResponseEntity<Object> evaluate(@RequestParam(value = "scoreOne") String scoreOne, @RequestParam(value = "scoreTwo") String scoreTwo) {
        try{
        return new ResponseEntity<>(hackerRankServ.compareTriplets(scoreOne, scoreTwo), HttpStatus.OK);
        }catch (HackerRankException e) {
            return new ResponseEntity<>("Error: "+e.message+" Method: "+e.method, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/challenge/save")
    public @ResponseBody ResponseEntity<String> saveChallenge(@RequestParam(value = "scoreOne") String scoreOne, @RequestParam(value = "scoreTwo") String scoreTwo,
                                                @RequestParam(value = "playerOne", required = false) Long playerOne, @RequestParam(value = "playerTwo", required = false) Long playerTwo) {
        try {
            return new ResponseEntity<>(hackerRankServ.registerChallengeWinner(scoreOne, scoreTwo, playerOne, playerTwo), HttpStatus.OK);
        }catch (HackerRankException e) {
            return new ResponseEntity<>("Error: "+e.message+" Method: "+e.method, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
