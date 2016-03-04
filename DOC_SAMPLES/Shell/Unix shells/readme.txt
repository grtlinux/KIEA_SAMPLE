CD-ROM for UNIX Shells by Example, 3/e


Script files and datafiles are contained in separate directories for
each chapter, chap01 through chap13.

The script names and datafile names are the same as in the book.
NOTE: Some of the early scripts send mail to a specified list
of persons, so you may choose not to run these scripts (or
you may want to change the list to just include your own 
e-mail address to ensure it works okay).

For example, to run Example 1.6, just change directory to chap01 and run "ex_1.6" from the command line. (You may need to run "chmod +x ex_1.6" first to ensure that it has execute permission.  If so,  you'll have to do this for every script file.)  The script "ex_1.6" requires a datafile named "guests" which is provided in the same chap01 directory.

CHAPTERS 7 THROUGH 10:
Chapters 7 through 10 have many examples, so the example files 
are separated into subdirectories by example number. 
For example, Chapter 7 contains subdirectories named:
   Ex_7.05-7.40
   Ex_7.49-7.60
   Ex_7.74-7.80
   Ex_7.81-7.105

For example, to run Example 7.5 requires the datafile named "employees" which is contained in directory chap07/Ex_7.05-7.40.
You can change to that directory and run the command line shown
in the book for Example 7.5.

MINOR SCRIPT CHANGES:
Lastly, a few of the scripts in the book ask for a datafile to be
in a certain directory.  The CD version of each of these scripts has
been changed to look for the datafile in the same directory as the 
script.  This minor change was made to enable each 
script to run immediately without further modification.

For instance, Example 1.6 in the book has a line:
  set guestfile = ~/shell/guests

The CD version of the script has been changed to:
    set guestfile = ./guests

HAPPY SHELLING!!
