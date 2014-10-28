public class TheJohnsons {

    public static void main(String[] args) {
        Person p1 = new Person("Levi", 20, Person.MALE);
        Person p2 = new Person("Lucas", 20, Person.MALE);
        Person p3 = new Person("Kate", 24, Person.FEMALE);
        Person p4 = new Person("Lucas", 24, Person.MALE);

        Group g = new Group();
        g.add(p1);
        g.add(p2);
        g.add(p3);
        g.add(p4);

        System.out.println(g);
        for (Person p : g)
            System.out.println(p);
    }

}
