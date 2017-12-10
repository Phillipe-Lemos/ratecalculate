# Ratecaculate
Is a command line application to calculate total repayment, final rate and monthly repayment from one requested loan.

# How to build the application 
This application is a Java Maven project and to build just follows the command.

* Copy the source code to one directory.

* In chosen directory type the follow command  
	mvn clean package

* You could found in target directory the jar called RateCalulate.jar

# How to execute the application 
To execute the application type the command as follow

java -jar RateCalulate.jar <csv file with lenders> <total amount request>

# CVS lenders file format
The cvs must contain three columns in specific order:
* Lender name
* Rate
* Available

# Expected header from cvs file
Lender,Rate,Available

# Errors messages from incorrect parameters values. 

* You must inform a loan amount.
* The total requested exceeded maximum value to loan.(1,500.00)  
* The total requested is under minimum value to loan.(1,000.00)
* The loan requested should be multiple from 100.00 Pounds
* The lenders file is empty.
* Does not have sufficient offers from lenders to satisfy the loan.
* Problems reading file <filename>. 
 
# Output format

Requested amount  : <double value>
Rate              : <double value with one decimal position>
Monthly repayment : <double value with two decimal position>
Total repayment   : <double value with two decimal position> 

# Assumptions

* The rate in csv file it is a annual rate.

* The operator ** means power to. eg: 4 ** 2 = 16.

* Formula to convert annual rate into monthly rate : (1 + anualRate) ** (1/12) -1.

* Formula to convert monthly rate into annual rate : (1 + monthlyRate) ** 12 - 1. 

* Formula to calculate final rate is : (totalRepaiments / totalRequested) ** (1/36) - 1.

