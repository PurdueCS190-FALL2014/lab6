/** Copyright 2014 -- Levi Starrett
    For educational purposes only

    The FamilyExplorer class is an interface by which a user can explore
    a family tree

    It has a head attribute, corresponding to the entry point to the tree
    It is assumed that every member of the family is connected to the head

    The FamilyExplorer class has several commands:

    help
        - Prints command help

    quit
        - exits the FamilyExplorer

    print [attribute]
        - Prints the attribute specified
        - If no attribute is specified, prints summary for the current Person
        
    print [relationship] [identifier] [attribute]
        - Prints the attribute specified of the person related by relationship
          and identified by identifier
        - If no attribute is specified, prints summary for the specified Person
        - Identifier may be name or Group index
        - No identifier necessary for unambiguous relationships (eg spouse)
        - If no identifier provided for ambiguous relationship (eg siblings),
          the entier Group will be printed
        - No relatiohsip will throw an error
        - Valid relationships:
            + head
            + mom
            + dad
            + spouse
            + siblings
            + children

    goto [relationship] [identifier]
        - Behaves much like the 'print' command however changes the current Person
          to the identified Person
        - Identifier may be name or Group index
        - No identifier necessary for unambiguous relationships (eg spouse)
        - No identifier for ambiguous relationship (eg siblings) will throw
          an error
        - No relatiohsip will throw an error

    **/

import java.util.*;

public class FamilyExplorer {

    // INSTANCE VARIABLES
    private Person head;                    // instance variable reference to the head of household
    private Person current;                 // instance variable reference to the current selected Person
    private boolean running;                // instance variable status of loop

    // CONSTRUCTORS
    public FamilyExplorer(Person head) {
        this.head = head;
        current = this.head;
    }

    // INSTANCE METHODS
    /** print current person */
    private void print() {
        System.out.println(current);
    }
    
    /** Switch to new current person */
    private void goTo(String relationship, String identifier) {
        switch (relationship) {
            case "head":
                current = head;
                break;
            case "mom":
                if (current.getMom() != null) {
                    current = current.getMom();
                    print();
                }
                else
                    System.out.printf("%s has no mom\n", current.getName());
                break;
            case "dad":
                if (current.getDad() != null) {
                    current = current.getDad();
                    print();
                }
                else
                    System.out.printf("%s has no dad\n", current.getName());
                break;
            case "spouse":
                if (current.getSpouse() != null) {
                    current = current.getSpouse();
                    print();
                }
                else
                    System.out.printf("%s has no spouse\n", current.getName());
                break;
            case "siblings":
                try {
                    int index = Integer.parseInt(identifier);
                    if (current.getNumSiblings() > 0 && index >= 0 && index < current.getSiblings().size()) {
                        current = current.getSiblings().get(index);
                        print();
                    }
                    else
                        System.out.println("Invalid sibling index");
                }
                catch (NumberFormatException e) {
                    if (current.getNumSiblings() > 0 && current.getSiblings().get(identifier) != null) {
                        current = current.getSiblings().get(identifier);
                        print();
                    }
                    else
                        System.out.println("Invalid sibling name");
                }
                break;
            case "children":
                break;
            default:
                System.out.println("Invalid relationship");
                break;
        }
    }

    /** Parse input */
    private void parseCommand(String line) {
        String[] args = line.split("\\s+");
        switch(args[0]) {
            case "print":
                if (args.length == 1)
                    print();
                break;
            case "goto":
                if (args.length == 3)
                    goTo(args[1], args[2]);
                else if (args.length == 2)
                    goTo(args[1], "");
                else
                    System.out.println("Invalid 'goto' command");
                break;
            case "help":
                System.out.print("help\n\t- Prints command help\n\nquit\n\t- exits the FamilyExplorer\n\nprint [attribute]\n\t- Prints the attribute specified\n\t- If no attribute is specified, prints summary for the current Person\n\nprint [relationship] [identifier] [attribute]\n\t- Prints the attribute specified of the person related by relationship\n\t  and identified by identifier\n\t- If no attribute is specified, prints summary for the specified Person\n\t- Identifier may be name or Group index\n\t- No identifier necessary for unambiguous relationships (eg spouse)\n\t- If no identifier provided for ambiguous relationship (eg siblings),\n\t  the entier Group will be printed\n\t- No relatiohsip will throw an error\n\t- Valid relationships:\n\t\t+ head\n\t\t+ mom\n\t\t+ dad\n\t\t+ spouse\n\t\t+ siblings\n\t\t+ children\n\ngoto [relationship] [identifier]\n\t- Behaves much like the 'print' command however changes the current Person\n\t  to the identified Person\n\t- Identifier may be name or Group index\n\t- No identifier necessary for unambiguous relationships (eg spouse)\n\t- No identifier for ambiguous relationship (eg siblings) will throw\n\t  an error\n\t- No relatiohsip will throw an error\n");
                break;
            case "quit":
                running = false;
                break;
            default:
                System.out.println("Unrecognized command");
                break;
        }
                
    }

    /** Loop and take commands */
    public void explore() {
        Scanner sc = new Scanner(System.in);
        running = true;
        while (running && sc.hasNextLine()) {
            parseCommand(sc.nextLine());
        }
    }

}
