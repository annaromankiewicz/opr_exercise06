package sportsclub;

import exceptions.NullValueException;
import exceptions.ValueException;

public class Section extends AbstractMember {

    BinarySearchTree<AbstractMember> section;

    public Section(String name) throws NullValueException {
        super(name);
        section = new BinarySearchTree<AbstractMember>();
    }

    @Override
    public double getIncome() {
        AbstractMember[] sectionArray = section.toArray(new AbstractMember[section.size()], true); // I changed the signature of toArray in BST to avoid a typecast
        double sum = 0;
        for (AbstractMember member : sectionArray) {
            sum += member.getIncome();
        }
        return sum;
    }

    @Override
    public double getCosts() {
        AbstractMember[] sectionArray = section.toArray(new AbstractMember[section.size()], true);
        double sum = 0;
        for (AbstractMember abstractMember : sectionArray) {
            sum += abstractMember.getCosts();
        }
        return sum;
    }

    @Override
    public String toString(boolean ascending) {
        return toString(ascending, 0);
    }

    /** the idea is that the sections are always in the right order e.g. Sportunion - Football - U20
     * and the ascending or descending order which is alphabetical a-z for ascending, is
     * applied inside the sections */
    private String toString(boolean ascending, int depth) {
        StringBuilder s = new StringBuilder();
        String indent = "    ".repeat(depth); // section header

        s.append(indent + super.toString(ascending));

        AbstractMember[] members = section.toArray(
                new AbstractMember[section.size()], ascending);

        // First loop: regular members only
        for (AbstractMember member : members) {
            if (!(member instanceof Section)) {
                s.append("    ".repeat(depth + 1) + member.toString(ascending));
            }
        }

        // Second loop: sections only
        for (AbstractMember member : members) {
            if (member instanceof Section) {
                s.append("\n");
                s.append(((Section) member).toString(ascending, depth + 1));
            }
        }
        return s.toString();
    }


    public boolean addMember(AbstractMember m) throws ValueException {
        return section.insert(m);
    }

    public boolean removeMember(AbstractMember m) throws ValueException {
        return section.remove(m);
    }

    public boolean isMember(AbstractMember m) throws ValueException {
        return section.find(m);
    }

}

