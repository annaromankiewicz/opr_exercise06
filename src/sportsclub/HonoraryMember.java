package sportsclub;

import exceptions.ValueException;

public class HonoraryMember extends AbstractMember {

    public HonoraryMember(String name) throws ValueException {
        super(name);
    }

    @Override
    public double getIncome() {
        return 0;
    }

    @Override
    public double getCosts() {
        return 20;
    }

}

