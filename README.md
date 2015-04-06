## Problem Statement
You are a drug trafficker. Every day you meet with a different nice older lady (the mule) and find out how much weight she can carry in her handbag. You then meet with your supplier who has packed various drugs into a myriad of collectible porcelain dolls. Once packed with drugs, each of the precious dolls has a unique combination of weight and street value. Sometimes your supplier has more dolls than the nice lady can carry, though space in her handbag is never an issue. Your job is to choose which dolls the kind soul will carry, maximizing street value, while not going over her weight restriction.

Write a Clojure program, which given:

* a set of dolls with a name and unique weight and value combination
* an overall weight restriction

Produces the optimal set of drug-packed porcelain dolls which:

* are within the total weight restriction
* maximize the total street value of drugs delivered

## Requirements
* leiningen
* org.clojure/clojure
* clj-yaml
* org.clojure/tools/cli  

These requirements are defined in `project.clj`

## Usage
`doll-smuggler` can be run from the command-line, using `lein`. 

####Basic usage:
Run the tool using the default data and produce a tabular output to stdout:  
  
`lein run`  

####Advanced usage: 
There are several options available for `doll-smuggler`. The general syntax for supplying arguments is:  
  
`lein run -m doll-smuggler.core <OPT1> <OPT2> ...`
  
**Options**
```
Options:
  -w, --weight WEIGHT  Default: 400 lbs         Maximum total weight (in lbs) for the dolls
  -f, --file FILEPATH  Default: doll-input.yml  Input file path (YAML). E.g. drug-stuff.yaml
      --chart                                   Create a graphical chart of the calculated data
  -h, --help
```

If no options are given, the defaults are:
* max weight: 400 lbs 
* default input file: resources/doll-input.yml
* no GUI chart (results written to stdout)

**Examples**  
*  Run the tool with a max weight of 300lbs, using file `resources/example.yml`:  
    
    `lein run -m doll-smuggler.core -w 300 -f resources/example.yml`

*  Same as above, but show a GUI chart with the results:  
    
    `lein run -m doll-smuggler.core -w 300 -f resources/example.yml --chart`  

**YAML Tips**  
The defualt YAML file (`@/resources/doll-input.yml`) can be modified as needed, in case the doll weights/values must be updated.    

You can also create a new YAML file (e.g. `example.yml`) and use the `-f <FILE>` switch to use this YAML file as the input. For example, if a different doll-set is used for a particular day, you could create several YAML files for each day (e.g. `doll-input-monday.yaml`, `doll-input-tuesday.yaml`, etc).   

Below is the required format for the YAML input files:
```yaml
# List of dolls including name, weight, and value
DOLLS:
- name:   <string>
  weight: <int>
  value:  <int>
- name:   <string>
  weight: <int>
  value:  <int>
....
```

## Tests
_Oh yeah, tests..._  
You can simply run the tests with:  
  
`lein test`  

**Output:**
```
$ lein test

lein test doll-smuggler.core-test
lein test doll-smuggler.doll_reader-test

Ran 10 tests containing 24 assertions.
0 failures, 0 errors.
```
_Protip: Read [this](http://en.wikipedia.org/wiki/Knapsack_problem) for more information about the analogous "Knapsack Problem"_

