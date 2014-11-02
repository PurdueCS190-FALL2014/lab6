# CS 190 Lab 6 - Basics of Git Part 1: Local Git

The purpose of this lab is to gain a workable knowledge of git. Not only is this a powerful
tool for assuring you get the best grade possible on your projects, protecting you from losing
code, and collaborating, but it is also a necessary skill in the work place. You will never
have a computer science job that doesn't require the use of a version control system.

## The Lab

This lab will walk you through an example project and teach you in a real use case situation
how to use git and it's features.

## The magic command

```cd; curl -sL https://raw.githubusercontent.com/PurdueCS190/lab6/master/materials/setup.sh | /bin/bash```

## Step 0: The project

Your given a project in CS 180 in which you have to create a Java program for traversing a family tree. Your
program should be able to take a family in a given format and interact with the tree. You should be able to
print out specific individuals in the family, groups of individuals, or switch context to a new individual.

We've provided you with the code already. Let's run it and see what it does. Navigate to the __~/cs190lab6/FamilyExplorer/__
directory and run the command `$ bash compile.sh`. Now run `$ bash run.sh`. Congratulations you're running the
Family Explorer! Enter `help`. This command will print a list of available commands. Familiarize yourself with
the project. (Find bugs if you can!)

## Step 1: Setup your git repository

Now that we've got our project, let's get it in a git repository so we don't lose it before we have to turn it
in.

Let's start by creating a new repository to contain our project. Run the command `$ git init`. You should
get an output that looks something like this:

```Initialized empty Git repository in /u/data/u95/<your_username>/cs190lab6/FamilyExplorer/.git/```

Now enter `$ git status`.

    On branch master

    Initial commit

    Untracked files:
      (use "git add <file>..." to include in what will be committed)

            build/
            compile.sh
            run.sh
            src/

    nothing added to commit but untracked files present (use "git add" to track)

Score! We have a new repository and we can see that git recognizes that there are new files. Let's make an initial commit
Remember from lecture the staging area? Right now these files are **untracked** by git. This means that if we run `$ git commit`
now, no new changes will be commited. First we have to add these changes. Before we do, recall that we don't want to
commit `.class` files to a git repository, but the __build/__ folder is full of them! Before we add the files let's
add a `.gitignore` file. Run `$ echo *.class >> .gitignore`. This adds a line in the `.gitignore` file to ignore files
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

Yes! we can see that the `.gitignore` file is there, but the __build/__ directory is gone! No more `*.class` files!
Now we're ready to add. Run the command `$ git add .`. This command adds all of the current working directory to be
tracked by git. Run the status command again. This time you should see a list of all the new files. We're ready to
commit. Run `$ git commit -m "<your commit message>"`. Insert some message in the quotes. Your commit messages should
be short, but descriptive and to the point. Say something like "Added initial source files." (for some hilarious 
git commit messages: http://www.commitlogsfromlastnight.com/).

Congratulations, you've successfully committed your files. Run `$ git log` to view your commit message.

## Step 2: Make a change
