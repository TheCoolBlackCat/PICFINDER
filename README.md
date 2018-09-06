# PICFINDER
Simple Python 3 command line application to find all pictures in a directory and its subdirectories

This version has a partial GUI interface from which you can select directories [(Switch Branch)](https://github.com/TheCoolBlackCat/PICFINDER/tree/master)

## Install
1. Download the repository
  Use Git
    ```
    cd ~/Downloads
    git clone https://github.com/TheCoolBlackCat/PICFINDER.git
    cd PICFINDER
    git checkout gui
    ```

  OR

  Just click "Download ZIP" under "Clone or download"

2. (Optional) Check whether your python3 interpreter matches that which is specified in the file
 * Simply open up picfinder.py and change the top line as appropriate
 * Default: /usr/bin/python3
3. Make sure picfinder.py is executable
    ```sudo chmod +x picfinder.py```

## Run
Double-click on picfinder.py

OR

From a terminal, run ```./picfinder.py```

## Dependencies
* os
* shutil
* tkinter

These should be within your standard Python 3 libraries

TL;DR Just Python 3 installed!
