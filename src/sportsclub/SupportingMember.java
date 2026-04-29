package sportsclub;

import exceptions.ValueException;

public class SupportingMember extends AbstractMember {

    public SupportingMember(String name) throws ValueException {
        super(name);
    }

//    Unterstützende Mitglieder (Klasse SupportingMember): Diese Mitglieder bezahlen einen
//    Jahresbeitrag von €100,- und verursachen bei Vereinsfesten Ausgaben von durchschnittlich
//    €15,- pro Jahr.

    @Override
    public double getIncome() {
        return 100;
    }

    @Override
    public double getCosts() {
        return 15;
    }



}

