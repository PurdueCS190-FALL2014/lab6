public class TheJohnsons {

    public static void main(String[] args) {
        Person levi = new Person("Levi", 20, Person.MALE);
        Person lucasv = new Person("Lucas", 20, Person.MALE);
        Person kate = new Person("Kate", 24, Person.FEMALE);
        Person lucass = new Person("Lucas", 24, Person.MALE);

        Group levi_sibs = new Group();
        levi_sibs.add(lucass);
        levi.setSiblings(levi_sibs);

        Group lucasv_sibs = new Group();
        lucasv_sibs.add(kate);
        lucasv.setSiblings(lucasv_sibs);

        Group kate_sibs = new Group();
        kate_sibs.add(lucasv);
        kate.setSiblings(kate_sibs);
        kate.setSpouse(lucass);

        Group lucass_sibs = new Group();
        lucass_sibs.add(levi);
        lucass.setSiblings(lucass_sibs);
        lucass.setSpouse(kate);

        FamilyExplorer f = new FamilyExplorer(levi);
        f.explore();
    }

}
