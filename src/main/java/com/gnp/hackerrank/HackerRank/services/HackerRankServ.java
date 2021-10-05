package com.gnp.hackerrank.HackerRank.services;

import com.gnp.hackerrank.HackerRank.entities.Challenge;
import com.gnp.hackerrank.HackerRank.entities.ChallengeDetail;
import com.gnp.hackerrank.HackerRank.entities.Player;
import com.gnp.hackerrank.HackerRank.exceptions.HackerRankException;
import com.gnp.hackerrank.HackerRank.handlers.HackerRankExceptionHandler;
import com.gnp.hackerrank.HackerRank.repository.ChallengeRepository;
import com.gnp.hackerrank.HackerRank.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class HackerRankServ {

    private static final long PLAYER_ONE = 1;
    private static final long PLAYER_TWO = 2;
    private static final long NO_WINNER = 3;
    public static final String SPACE = " ";

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    public String registerPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        playerRepository.save(player);
        return "Jugador Registrado!!!";
    }

    public int[] compareTriplets(String scorePlayerOne, String scorePlayerTwo) throws HackerRankException {
        int[] ratingPlayerOne = Arrays.stream(scorePlayerOne.split(SPACE)).mapToInt(Integer::parseInt).toArray();
        int[] ratingPlayerTwo = Arrays.stream(scorePlayerTwo.split(SPACE)).mapToInt(Integer::parseInt).toArray();
        int finalPlayerOne = 0;
        int finalPlayerTwo = 0;
        int[] result;
        validateScore(ratingPlayerOne);
        validateScore(ratingPlayerTwo);
        for(int index = 0; index < ratingPlayerOne.length; index++){
            finalPlayerOne += ratingPlayerOne[index] > ratingPlayerTwo[index] ? 1 : 0;
            finalPlayerTwo += ratingPlayerOne[index] < ratingPlayerTwo[index] ? 1 : 0;
        }
        result = new int[]{finalPlayerOne, finalPlayerTwo};
        return result;
    }

    public String registerChallengeWinner(String scorePlayerOne, String scorePlayerTwo, Long playerOne, Long playerTwo) throws Exception{
        playerOne = playerOne != null ? playerOne : PLAYER_ONE;
        playerTwo = playerTwo != null ? playerTwo : PLAYER_TWO;
        int[] finalResult = compareTriplets(scorePlayerOne, scorePlayerTwo);
        Set<ChallengeDetail> challengeDetailSet = new HashSet<>();
        Challenge challenge = new Challenge();
        ChallengeDetail dataPlayerOne = new ChallengeDetail();
        ChallengeDetail dataPlayerTwo = new ChallengeDetail();
        playerRepository.findById(playerOne).ifPresentOrElse(dataPlayerOne::setPlayer, () -> { new Player("Robot One");});
        playerRepository.findById(playerTwo).ifPresentOrElse(dataPlayerTwo::setPlayer, () -> { new Player("Robot Two");});
        dataPlayerOne.setReview(scorePlayerOne);
        dataPlayerTwo.setReview(scorePlayerTwo);
        Player winner = finalResult[0] > finalResult[1] ? dataPlayerOne.getPlayer() : finalResult[0] == finalResult[1] ?  playerRepository.findById(NO_WINNER).get() : dataPlayerTwo.getPlayer();
        challenge.setDraw(finalResult[0] == finalResult[1]);
        challenge.setScore(Arrays.toString(finalResult));
        challenge.setWinner(winner);
        dataPlayerOne.setChallenge(challenge);
        dataPlayerTwo.setChallenge(challenge);
        challengeDetailSet.add(dataPlayerOne);
        challengeDetailSet.add(dataPlayerTwo);
        challengeRepository.save(challenge);

        return "El ganador es " + winner.getName() + " con el siguiente resultado: " + Arrays.toString(finalResult);
    }

    private boolean validateScore(int[] scores) throws HackerRankException {
        for(int score : scores) {
            if(score == 0 || score > 100) throw new HackerRankException("Limites no validos", "scores", "compareTriplets");
        }
        return true;
    }
}
