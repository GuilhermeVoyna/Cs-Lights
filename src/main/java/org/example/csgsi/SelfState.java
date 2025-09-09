package org.example.csgsi;

import org.example.utils.json.JsonField;

public class SelfState {
    @JsonField("health")
    private int health;
    @JsonField("armor")
    private int armor;
    @JsonField("helmet")
    private boolean helmet;
    @JsonField("flashed")
    private int flashed;
    @JsonField("smoked")
    private int smoked;
    @JsonField("burning")
    private int burning;
    @JsonField("money")
    private int money;
    @JsonField("round_kills")
    private int roundKills;
    @JsonField("round_killhs")
    private int roundHs;
    @JsonField("equip_value")
    private int equipValue;

    public SelfState(int health, int armor, boolean helmet, int flashed, int smoked, int burning, int money, int roundKills, int roundHs, int equipValue) {
        this.health = health;
        this.armor = armor;
        this.helmet = helmet;
        this.flashed = flashed;
        this.smoked = smoked;
        this.burning = burning;
        this.money = money;
        this.roundKills = roundKills;
        this.roundHs = roundHs;
        this.equipValue = equipValue;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public int getFlashed() {
        return flashed;
    }

    public void setFlashed(int flashed) {
        this.flashed = flashed;
    }

    public int getSmoked() {
        return smoked;
    }

    public void setSmoked(int smoked) {
        this.smoked = smoked;
    }

    public int getBurning() {
        return burning;
    }

    public void setBurning(int burning) {
        this.burning = burning;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getRoundKills() {
        return roundKills;
    }

    public void setRoundKills(int roundKills) {
        this.roundKills = roundKills;
    }

    public int getRoundHs() {
        return roundHs;
    }

    public void setRoundHs(int roundHs) {
        this.roundHs = roundHs;
    }

    public int getEquipValue() {
        return equipValue;
    }

    public void setEquipValue(int equipValue) {
        this.equipValue = equipValue;
    }
}
