package main.java.com.battlejawn.Battle.Jawn.Player;

@Entity
@Tables(name = "healers")
public class Healer extends Player {

    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "health")
    private int health;
    @Column(name = "max_health")
    private int maxHealth;
    @Column(name = "strength")
    private int strength;
    @Column(name = "potions")
    private int potions;
    @Column(name = "maxPotions")
    private int maxPotions;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_ailments")
    private StatusAilments statusAilments;

    public Healer(Long id, int health, int maxHealth, int strength, int potions,
            int maxPotions, StatusAilments statusAilments) {
        super(100, 100, 17, 0, 0, statusAilments);
        this.id = id;
    }
}
