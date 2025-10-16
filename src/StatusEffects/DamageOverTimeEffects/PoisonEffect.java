package StatusEffects.DamageOverTimeEffects;

import Entities.Characters.DamageType;

//Use this class if it's impossible to immediately instantiate in the Game class (Ang int duration variable ma shared for all entities with the status effect. Meaning dili siya mo reset back to the original duration.)
public class PoisonEffect extends DamageOverTimeEffect{
    public PoisonEffect(){
        super("Poison", "Deals poison damage every end of the turn", 3, DamageType.MAGICAL, 20);
    }
}
