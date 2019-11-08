package ch.heigvd.amt.models;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Game {
    private long id;
    private LocalDateTime timestamp;
    private Team away;
    private Team home;
    private Official referee;
    private Official umpire;
    private Official chainJudge;
    private Official lineJudge;
    private Official backJudge;
    private Official sideJudge;
    private Official fieldJudge;

    public Game(long id, LocalDateTime timestamp, Team away, Team home, Official referee, Official umpire, Official chainJudge,
                Official lineJudge, Official backJudge, Official sideJudge, Official fieldJudge){
        this(timestamp, away, home, referee, umpire, chainJudge, lineJudge, backJudge, sideJudge, fieldJudge);
        this.id = id;
    }

    public Game(LocalDateTime timestamp, Team away, Team home, Official referee, Official umpire, Official chainJudge,
                Official lineJudge, Official backJudge, Official sideJudge, Official fieldJudge){
        this.timestamp = timestamp;
        this.away = away;
        this.home = home;
        this.referee = referee;
        this.umpire = umpire;
        this.chainJudge = chainJudge;
        this.lineJudge = lineJudge;
        this.backJudge = backJudge;
        this.sideJudge = sideJudge;
        this.fieldJudge = fieldJudge;
    }
}
