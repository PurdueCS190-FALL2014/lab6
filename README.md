# CS 190 Lab 6 - Basics of Git Part 1: Local Git

The purpose of this lab is to gain a workable knowledge of git. Not only is this a powerful
tool for assuring you get the best grade possible on your projects, protecting you from losing
code, and collaborating, but it is also a necessary skill in the work place. You will never
have a computer science job that doesn't require the use of a version control system.

## The Lab

This lab will walk you through an example project and teach you in a real use case situation
how to use git and it's features.

## The magic command

Run this command to set up your working directory.

```bash
cd; curl -sL https://raw.githubusercontent.com/PurdueCS190/lab6/master/materials/setup.sh | /bin/bash
```

## Required git setup

Before you start working you have to do some setup of git. You need to set up your global gitconfig with 
your name and email. These will be tied to each commit so that it is known who committed the code.

```bash
git config --global user.name $USER
git config --global user.email $USER@purdue.edu
```
## Step 0: The project

Your given a project in CS 180 in which you have to create a Java program for traversing a family tree. Your
program should be able to take a family in a given format and interact with the tree. You should be able to
print out specific individuals in the family, groups of individuals, or switch context to a new individual.
You can see a picture of the family tree below
> ![] (https://raw.githubusercontent.com/PurdueCS190/lab6/master/materials/tree.png)

We've provided you with the code already. Let's run it and see what it does. 

1. Navigate to the __~/cs190lab6/FamilyExplorer/__ directory
2. Run the command `$ bash compile.sh`.
3. Now run `$ bash run.sh`.
4. Enter `help`. This command will print a list of available commands. Familiarize yourself with
the project. (*Bonus*: Find bugs if you can!)

## Step 1: Setup your git repository

Now that we've got our project, let's get it in a git repository so we don't lose it before we have to turn it
in.

Let's start by creating a new repository to contain our project. 
* Run the command `$ git init`. You should
get an output that looks something like this:

```
Initialized empty Git repository in /u/data/u95/<your_username>/cs190lab6/FamilyExplorer/.git/
```

* Now enter `$ git status`.



```
    On branch master

    Initial commit

    Untracked files:
      (use "git add <file>..." to include in what will be committed)

            build/
            compile.sh
            run.sh
            src/

    nothing added to commit but untracked files present (use "git add" to track)
```

We have a new repository and we can see that git recognizes that there are new files. Before we make an initial commit, let's
add a `.gitignore` file. Recall that we don't want to commit `.class` files to a git repository. 
* Run `$ echo "*.class" >> .gitignore`. This adds a line in the `.gitignore` file to ignore files
ending in `.class`.

Now run `$ git status` again.

    On branch master

    Initial commit

    Untracked files:
      (use "git add <file>..." to include in what will be committed)

            .gitignore
            compile.sh
            run.sh
            src/

    nothing added to commit but untracked files present (use "git add" to track)

We can see that the `.gitignore` file is there, but the __build/__ directory is gone! No more `.class` files!

Remember from lecture the staging area? Right now these files are *untracked* by git. This means that if we run `$ git commit`
now, no new changes will be commited. First we have to add these changes.
* Run the command `$ git add .`. This command adds all of the current working directory to be tracked by git. 
* Run the status command again. This time you should see a list of all the new files. We're ready to
commit.
* Run `$ git commit -m "<your commit message>"`. Insert some message in the quotes. Your commit messages should
be short, but descriptive and to the point. Say something like "Added initial source files." (for some hilarious 
git commit messages: http://www.commitlogsfromlastnight.com/. *Bonus*: Explain to your lab TA how this website works).

Congratulations, you've successfully committed your files. Run `$ git log` to view your commit message.

*Bonus*: Why might it be good to do this step in two commits instead of one?

## Step 2: Make a change

Now that we've gotten our files commited, we'd like to make a change. Say we want the print output to be a little shorter
and more concise.

Change the `toString()` method in `Group.java` to look like this:

    71      /** Override of toString() from superclass */
    72      @Override
    73      public String toString() {
    74          StringBuilder output = new StringBuilder();
    75          Formatter f = new Formatter(output);
    76
    77          for (Person p : group) {
    78              f.format("%d: %s, ", group.indexOf(p), p.getName());
    79          }
    80
    81          return output.toString();
    82      }

Change the `toString()` method in `Person.java` to look like this:

    129     /** Override of toString() from superclass */
    130     @Override
    131     public String toString() {
    132         StringBuilder output = new StringBuilder();
    133         Formatter f = new Formatter(output);
    134         f.format("Name: %s, ", getName());
    135         f.format("Age: %d, ", getAge());
    136         String gender = getGender() == Person.MALE ? "MALE" : "FEMALE";
    137         f.format("Gender: %s ", gender);
    138         
    149         return output.toString();
    140     }

Recompile and run the project. Observe the differences in the output. Be sure to let your lab TA know right away if
you are having compile or other errors. We don't want you to spend time debugging (at least not this week).

Let's check the status of our git repo. 
* Run the status command. You should see that both `Person.java` and `Group.java` show up as *modified* 
under __Changes not staged for commit:__ Now wee need to add them to the staging area and commit
* Run `$ git add src/Group.java src/Person.java`. Now commit the files with a nice descriptive commit message.
* Run `$ git log` and you should now see both of your commits. You're well on your way to becoming a git pro!

## Step 3: Make another change

Now your CS 180 lab TA tells you that you need to add in support for printing and navigating to cousins. This is
a serious new feature that could break things! With git you can feel confident that you won't lose the progress
you've already made.

Add the following method to `FamilyExplorer.java`:

    63      /** Get cousins */
    64      private Group getCousins() {
    65          Group cousins = new Group();
    66          Person dad = current.getDad();
    67          if (dad != null) {
    68              Group sibs = dad.getSiblings();
    69              for (Person sib : sibs) {
    70                  if (!sib.equals(dad)) {
    71                      Group children = sib.getChildren();
    72                      for (Person child : children) {
    73                          cousins.add(child);
    74                      }
    75                  }
    76              }
    77          }
    78          Person mom = current.getMom();
    79          if (mom != null) {
    80              Group sibs = mom.getSiblings();
    81              for (Person sib : sibs) {
    82                  if (!sib.equals(mom)) {
    83                      Group children = sib.getChildren();
    84                      for (Person child : children) {
    85                          cousins.add(child);
    86                      }
    87                  }
    88              }
    89          }
    90          return cousins;
    91      }

Also add this case to the switch statement in `FamilyExplorer.java` right above *default*:

    149     case "cousins":
    150         group = getCousins();
    151         break;

Recompile and try it out! Now you should be able to enter `print cousins`. Again, don't get stuck on this part.
If you are getting errors, ask a TA right away.

* Add the changes to `FamilyExplorer.java`
* Commit your changes. 

You're getting fluent at these git commands
already!

__Pro tip: You can never overuse the *log* and *status* commands. You should be looking at these between
almost every command__

## Step 4: A step backwards

Now that your this far you decide that you want the output to be printed the way you had it before. First
let's look at our commit log. Run `$ git log`. Your output should look something like this:

    commit a6bd3cc3ece612ee4ff1a343acfb36b90fcd5d32
    Author: Levi Starrett <levi@roxsoftware.com>
    Date:   Sun Nov 2 23:51:05 2014 -0500

        Added support for cousins

    commit bfe63e9095f46ce522ac0033951090203a07fc31
    Author: Levi Starrett <levi@roxsoftware.com>
    Date:   Sun Nov 2 23:46:22 2014 -0500

        Changed output to shorter format

    commit 179762b4273282d798b0df0dfc745620a79fffde
    Author: Levi Starrett <levi@roxsoftware.com>
    Date:   Sun Nov 2 18:57:48 2014 -0500

        Initial commit

We want to revert the commit that changed the output.
* Run `$ git revert <commit_hash>`. This will open up an editor with a commit that says something like 
"Revert '*some message*' This commit reverts *some commit hash*".
* Run the compile script and run the program again. It should be back to the old style of output.

__Give your neighbor a high five, you just did some serious source control.__

## Grading

Show your TA your git log.

## Step 5: Extras!

Here are some practical things you could do with git if you have extra time:
* Work on your .gitconfig file. Some useful information here: http://git-scm.com/docs/git-config
* Put your CS 180 project into a git repository! You can pretty much follow this lab to do so.
* Ask your TA a question about something you don't understand.
