
# Resource Allocation

## Requirement

Auto-population of daily rotation sheet based on skill level of operator.

## Background

An employee can have 1 of 2 roles:
    1. Team Leader (TL)
    2. Operator (OP)

In this instance only Operators are considered currently, not Team Leaders.

An OP is given a skill level for each workstation in their zone. A skill level can be in the range 0 – 5 – zero implying the operator cannot work at that station.
 
 A workstation has 3 sessions per day i.e. 3 slots that operators may be assigned to.
 
 A workstation is given an ergonomic ranking of A, B, C.
 
## Allocation

- An operator can be assigned a skill level in the range 0 -5 for each workstation.

- An operator can operate a workstation if he has skill level 2,3,4,5.  The exception to this rule is that a skill level < 3 cannot operate a workstation with Ergonomic Ranking of A.

- An operator can only be allocated to 1 'A' workstation per shift.

- An allocation to an 'A' workstation cannot be followed by a 'B' workstation.

- An operator cannot be allocated to more than 2 'B' workstations per shift.

- An operator cannot operate more than one workstation at the same time i.e. in a single slot.

## Optimization 

- Try an optimise solution so that all workstations are to be operated by 3 different O/Ps per shift.

## Data

Currently a single handcrafted problem is described in the data files operators.txt, skilllevels.txt, workstations.txt.  These can be changed as appropriate and are laid out as follows:

he operators.txt file has a separate line for each operator.  Where each line is in the format; user id, worker type and user name.

e.g.

1,OPERATOR,oper1

declares an OPERATOR (the only type supported currently) with an id of 1 and a name of oper1.

The workstations.txt file has a separate line for each workstation.  Where each line is in the format; workstation id, workstation name, Ergonomic Ranking. e.g.

1,wkst1,A

declares a workstation with id of 1, name of wkst1 and Ergonomic Ranking of A.

 The skilllevels.txt file has a separate line for each possible assignment of user to workstation at a given skill level.  Where each line is in the format operator name, workstation name, skill level.

e.g.

oper5,wkst4,TWO

 declares an operator oper5 can operate a workstation named wkst4 with skill level of 2.
 
## Executing code

The code is a simple Java application that should be run within the IDE currently.
