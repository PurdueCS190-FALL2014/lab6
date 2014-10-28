#!/bin/bash

# clean
rm -rf ~/cs190lab6/

# change directories
mkdir ~/cs190lab6/
cd ~/cs190lab6/

# get tarball
wget https://raw.githubusercontent.com/PurdueCS190/lab6/master/materials/lab6.tgz

# untar
tar -zxvf lab6.tgz

# remove tarball
rm -f lab6.tgz
