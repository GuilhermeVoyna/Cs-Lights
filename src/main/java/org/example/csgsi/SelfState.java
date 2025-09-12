package org.example.csgsi;

import org.example.utils.json.JsonField;

public class SelfState {
    @JsonField("health")
    private Integer health;
    @JsonField("armor")
    private Integer armor;
    @JsonField("helmet")
    private Boolean helmet;
    @JsonField("flashed")
    private Integer flashed;
    @JsonField("smoked")
    private Integer smoked;
    @JsonField("burning")
    private Integer burning;
    @JsonField("money")
    private Integer money;
    @JsonField("round_kills")
    private Integer roundKills;
    @JsonField("round_killhs")
    private Integer roundHs;
    @JsonField("equip_value")
    private Integer equipValue;

    public SelfState(Integer health, Integer armor, Boolean helmet, Integer flashed, Integer smoked, Integer burning, Integer money, Integer roundKills, Integer roundHs, Integer equipValue) {
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

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public Integer getFlashed() {
        return flashed;
    }

    public void setFlashed(Integer flashed) {
        this.flashed = flashed;
    }

    public Integer getSmoked() {
        return smoked;
    }

    public void setSmoked(Integer smoked) {
        this.smoked = smoked;
    }

    public Integer getBurning() {
        return burning;
    }

    public void setBurning(Integer burning) {
        this.burning = burning;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getRoundKills() {
        return roundKills;
    }

    public void setRoundKills(Integer roundKills) {
        this.roundKills = roundKills;
    }

    public Integer getRoundHs() {
        return roundHs;
    }

    public void setRoundHs(Integer roundHs) {
        this.roundHs = roundHs;
    }

    public Integer getEquipValue() {
        return equipValue;
    }

    public void setEquipValue(Integer equipValue) {
        this.equipValue = equipValue;
    }
}
