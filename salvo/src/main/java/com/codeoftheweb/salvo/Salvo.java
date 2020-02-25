package com.codeoftheweb.salvo;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import org.hibernate.annotations.GenericGenerator;
        import javax.persistence.*;
        import java.util.ArrayList;
        import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private long  gamePlayer_id;
    private int turn_number;

    @ElementCollection
    @Column(name="salvoLocation")
    private List<String> salvoLocations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_po")
    private  GamePlayer gamePlayer;

    public Salvo(){
    }
    public Salvo(int turn_number,GamePlayer gamePlayer ,List<String> salvoLocations){
        this.turn_number = turn_number;
        this.gamePlayer = gamePlayer;
        this.salvoLocations = salvoLocations;
    }
    public List<String> getSalvoLocations() {
        return salvoLocations;
    }

    public void setSalvoLocations(List<String> salvoLocations) {
        this.salvoLocations = salvoLocations;
    }

    public long getGamePlayer_id() {
        return gamePlayer_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGamePlayer_id(long gamePlayer_id) {
        this.gamePlayer_id = gamePlayer_id;
    }
    public int getTurn_number(){
        return turn_number;
    }
    @JsonIgnore
    public GamePlayer getGamePlayer (){
        return gamePlayer;
    }
}