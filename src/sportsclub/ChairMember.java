package sportsclub;

import exceptions.ValueException;

public class ChairMember extends AbstractMember {

    /**
     * Vorstandsmitglieder haben einen
     * ganzzahligen Kompetenzwert im Bereich von 0 und 10. Ein Vorstandsmitglied erzeugt durch
     * das Lukrieren von Sponsorengeldern und Förderungen Jahreseinnahmen von Kompetenz *
     * 100 € und verursacht Ausgaben, indem es 20% Provision für lukrierte Einnahmen erhält.
     */

    protected int competenceValue;

    public ChairMember(String name, int competenceValue) throws ValueException {
        super(name);
        if (competenceValue < 0 || competenceValue > 10) {
            throw new ValueException("Competence level must be between 0 and 10", competenceValue);
        } else {
            this.competenceValue = competenceValue;
        }
    }

    @Override
    double getIncome() {
        return competenceValue * 100;
    }

    @Override
    double getCosts() {
        return getIncome() * 0.2;
    }

}

