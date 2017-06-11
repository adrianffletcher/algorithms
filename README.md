`find-pair` allows you to search through a sorted collection of items to find two items that are minimally under (or equal to) a supplied gift card balance.

`find-triple` allows you to search through a sorted collection of items to find three items that are minimally under (or equal to) a supplied gift card balance.

`find-combinations` takes as an input a string composed of 1s, 0s and Xs and prints out every combination where the Xs are replaced with both 0 and 1.

## Prerequisites

* Java 1.8 (code has been tested against Java version 1.8.0_131). Download from [Oracle](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
* Git (https://git-scm.com/downloads)

## Installation

* Clone this git repository
```
    :::term
    $ git clone https://github.com/adrianffletcher/algorithms
    Cloning into 'algorithms'...
    remote: Counting objects: 118, done.
    remote: Compressing objects: 100% (73/73), done.
    remote: Total 118 (delta 40), reused 106 (delta 28), pack-reused 0
    Receiving objects: 100% (118/118), 379.87 KiB | 0 bytes/s, done.
    Resolving deltas: 100% (40/40), done.
```
* Set your PATH
```
    :::term
    cd algorithms
    $ PATH=./:$PATH
```
* Run `find-pair`
```
    :::term
    $ find-pair prices.txt 2300
    Paperback Book 700, Headphones 1400
```

* Run `find-triple`
```
    :::term
    $ find-triple prices.txt 2300
    Candy Bar 500, Paperback Book 700, Detergent 1000
```

* Run `find-combinations`
```
    :::term
    $ find-combinations 10X10X0
    1001000
    1001010
    1011000
    1011010
```
