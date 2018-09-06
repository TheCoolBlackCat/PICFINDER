# PICFINDER
Simple Python 3 command line application to find all pictures in a directory and its subdirectories

This version has a partial GUI interface from which you can select directories [(Switch Branch)](https://github.com/TheCoolBlackCat/PICFINDER/tree/master)

## Install
1. Install [dependencies](#Dependencies)
2. Download the repository
  Use Git
    ```
    cd ~/Downloads
    git clone https://github.com/TheCoolBlackCat/PICFINDER.git
    cd PICFINDER
    git checkout gui
    ```

  OR

  Just click "Download ZIP" under "Clone or download"

3. (Optional) Check whether your python3 interpreter matches that which is specified in the file
 * Simply open up picfinder.py and change the top line as appropriate
 * Default: /usr/bin/python3
4. Make sure picfinder.py is executable
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

You can install Python 3 and Tkinter with: ```sudo apt install python3 python3-tk```

TL;DR Just Python 3 installed!
